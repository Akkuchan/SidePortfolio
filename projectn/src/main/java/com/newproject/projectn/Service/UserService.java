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
    public User createUser(User user) {
        //username, email, 닉네임 등 중복검사 체크 + 중복시 에러 추가하기(Bisness 에러 추가)

        addressRepository.save(user.getAddress());
        return userRepository.save(user);
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
                HashMap<String, Object> result = getFieldValue(capitalizedFieldName, foundUser, editUserInfo );
                Object value = eachField.get(editUserInfo);// 수정본에서 value가져오기

                if(!result.get(methodNameStartWithCapital(eachField)).equals(value)){
                    String setMethodName = "set" + methodNameStartWithCapital(eachField);// set메서드 설정위해 이름 가져오기
                    Method setFiled = foundUser.getClass().getDeclaredMethod(setMethodName, eachField.getType());
                    setFiled.invoke(foundUser,value);
                }
                System.out.println("Field name: " + eachField.getName() + ", Value: " + value);
            }

            if(eachField.getType().equals(Boolean.class)){
                HashMap<String, Object> fieldValue = getFieldValue(capitalizedFieldName, foundUser, editUserInfo );
                Object value = eachField.get(editUserInfo);// 수정본에서 value가져오기

                if(!fieldValue.equals(value)){
                    String setMethodName = "set" + methodNameStartWithCapital(eachField);// set메서드 설정위해 이름 가져오기
                    Method setFiled = foundUser.getClass().getDeclaredMethod(setMethodName, eachField.getType());
                    setFiled.invoke(foundUser,value);
                }
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

    public HashMap<String,Object> getFieldValue(String upperFiledName, User dbUser, User editUserInfo) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashMap<String,Object> result = new HashMap<String,Object>();

        Method getFiled = dbUser.getClass().getDeclaredMethod("get" + upperFiledName);//get 메서드 생성
        Object fieldValue = getFiled.invoke(dbUser);//비교할 원본 객체에서 각 필드의 value 가져오기
        result.put(upperFiledName,fieldValue );
        return result;
    }


}
