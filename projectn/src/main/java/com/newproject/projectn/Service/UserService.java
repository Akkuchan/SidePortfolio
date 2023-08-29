package com.newproject.projectn.Service;

import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.repository.AddressRepository;
import com.newproject.projectn.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    AddressRepository addressRepository;
    UserRepository userRepository;
    @Transactional
    public User createUser(User user, List<Boolean> duplicationCheck) {
        for(Boolean check: duplicationCheck){
            if (check) {System.out.println("중복검사PASS");}
            else {throw new BusinessLogicException(ExceptionCode.DUPLICATION_CHECK_IS_WRONG);}
        }
        addressRepository.save(user.getAddress());
        return userRepository.save(user);
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
        List<User> test = userRepository.findAll(PageRequest.of(pageIdx, 30, Sort.by("username").descending()))
                .stream().toList();
        return test;
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

}
