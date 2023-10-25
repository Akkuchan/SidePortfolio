package com.newproject.projectn.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newproject.projectn.config.security.auth.jwt.JwtService;
import com.newproject.projectn.config.security.auth.AuthenticationRequest;
import com.newproject.projectn.config.security.auth.AuthenticationResponse;
import com.newproject.projectn.config.security.auth.jwt.Token;
import com.newproject.projectn.config.security.auth.jwt.TokenRepository;
import com.newproject.projectn.config.security.auth.jwt.TokenType;
import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import com.newproject.projectn.entitiy.Role;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.address.Address;
import com.newproject.projectn.entitiy.address.City;
import com.newproject.projectn.repository.AddressRepository;
import com.newproject.projectn.repository.CityRepository;
import com.newproject.projectn.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService{

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final TokenRepository tokenRepository;
    @Transactional
    public User register(User user, List<Boolean> duplicationCheck, Long cityId, String details, String zipcode) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Role.MANAGER);
        for(Boolean check: duplicationCheck){
            if (check) {System.out.println("중복검사PASS");}
            else {throw new BusinessLogicException(ExceptionCode.DUPLICATION_CHECK_IS_WRONG);}
        }

        City city = cityRepository.findById(cityId).orElseThrow();
        Address newAddress = addressRepository.save(new Address(city, details, zipcode));
        user.setAddress(newAddress);

        return userRepository.save(user);

    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        Map<String, Object> claims = new HashMap<>();
        claims.put("Role",user.getAuthorities());

        String jwtToken = jwtService.generateToken(claims, user);
        String refreshToken = jwtService.generateRefreshToken( user);
        System.out.println("컨텍스트: " +  SecurityContextHolder.getContext());
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

            if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
                return;
            }

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractEmail(refreshToken);

        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("Role",user.getAuthorities());//리프레시 토큰에 새로 claims(role)추가
                var accessToken = jwtService.generateToken(claims,user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


    public User findUser(long userId) {
        return getUser(userId);
    }

    public User editUser(long userId, User editUserInfo) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        User foundUser = getUser(userId);

        Class<? extends User> testUser = editUserInfo.getClass();
        Field[] fields = testUser.getDeclaredFields();

        for(Field eachField: fields){
            eachField.setAccessible(true);
            String capitalizedFieldName = methodNameStartWithCapital(eachField);

            if(eachField.getType().equals(String.class)){
                HashMap<String, Object> fieldValue = getFieldValue(capitalizedFieldName, foundUser );
                Object value = eachField.get(editUserInfo);// 수정본에서 value가져오기
                checkIsValueChanged(foundUser, eachField, fieldValue, value);
                System.out.println("Field name: " + eachField.getName() + ", Value: " + value);
            }

            if(eachField.getType().equals(Boolean.class)){
                HashMap<String, Object> fieldValue = getFieldValue(capitalizedFieldName, foundUser );
                Object value = eachField.get(editUserInfo);// 수정본에서 value가져오기
                checkIsValueChanged(foundUser, eachField, fieldValue, value);
                System.out.println("Field name: " + eachField.getName() + ", Value: " + value);
            }
        }

        return userRepository.save(foundUser);
    }
    public List<User> findUserList(int pageIdx) {
        return userRepository.findAll(PageRequest.of(pageIdx, 30, Sort.by("username").descending()))
                .stream().toList();
    }
    private User getUser(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }






    public String methodNameStartWithCapital(Field field){
        return field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);//username -> Username
    }

    public HashMap<String,Object> getFieldValue(String upperFiledName, User dbUser) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashMap<String,Object> result = new HashMap<String,Object>();

        Method getFiled = dbUser.getClass().getDeclaredMethod("get" + upperFiledName);//get 메서드 생성
        Object fieldValue = getFiled.invoke(dbUser);//비교할 원본 객체에서 각 필드의 value 가져오기
        result.put(upperFiledName,fieldValue );
        return result;
    }

    private void checkIsValueChanged(User foundUser, Field eachField, HashMap<String, Object> fieldValue, Object value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(!fieldValue.equals(value)){
            String setMethodName = "set" + methodNameStartWithCapital(eachField);// set메서드 설정위해 이름 가져오기
            Method setFiled = foundUser.getClass().getDeclaredMethod(setMethodName, eachField.getType());
            setFiled.invoke(foundUser, value);
        }
    }

    public void removeUser(Long userId) {
        userRepository.deleteById(userId);
    }
    public boolean checkEmailDuplication(String email){
        return userRepository.existsByEmail(email);
    }
    public boolean checkUsernameDuplication(String email){
        return userRepository.existsByUsername(email);
    }
    public boolean checkNicknameDuplication(String email){
        return userRepository.existsByNickName(email);
    }

    public Set<User> findUserListByIdList(List<Long> invitedUsersIdList) {
        List<String> userIdList = invitedUsersIdList.stream().map(String::valueOf).toList();
        return userRepository.findByIdIn(userIdList).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
    }

    public User findByUserProfile(String jwt) {
        String email = jwtService.extractEmail(jwt);
        return userRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.EMAIL_TOKEN_NOT_FOUND));
    }


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user  =  userRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
////        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
////        user.getRoles().forEach((role) -> {
////            authorities.add(new SimpleGrantedAuthority(role.getName()));
////        });
//        return user;}




}
