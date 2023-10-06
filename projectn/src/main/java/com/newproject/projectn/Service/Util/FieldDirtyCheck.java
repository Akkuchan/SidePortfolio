package com.newproject.projectn.Service.Util;

import com.newproject.projectn.entitiy.post.Post;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class FieldDirtyCheck {

    public void dirtyCheck(Object fieldCheckObject, Object dbObject ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {


        Class<?> editPost = fieldCheckObject.getClass();
        Field[] fields = editPost.getDeclaredFields();

        List<Class<?>> allowedTypes = new ArrayList<>();
        allowedTypes.add(String.class);
        allowedTypes.add(Integer.class);
        allowedTypes.add(Boolean.class);

        for(Field eachField: fields){
            eachField.setAccessible(true);
            String capitalizedFieldName = methodNameStartWithCapital(eachField);

            if(allowedTypes.contains(eachField.getType())){
                HashMap<String, Object> fieldValue = getFieldValue(capitalizedFieldName, dbObject );
                Object value = eachField.get(fieldCheckObject);// 수정본에서 value가져오기
                checkIsValueChanged(dbObject, eachField, fieldValue, value);
                System.out.println("Field name: " + eachField.getName() + ", Value: " + value);
            }

        }
    }








    public String methodNameStartWithCapital(Field field){
        return field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);//username -> Username
    }

    public HashMap<String,Object> getFieldValue(String upperFiledName, Object dbObject) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashMap<String,Object> result = new HashMap<String,Object>();

        Method getFiled = dbObject.getClass().getDeclaredMethod("get" + upperFiledName);//get 메서드 생성
        Object fieldValue = getFiled.invoke(dbObject);//비교할 원본 객체에서 각 필드의 value 가져오기
        result.put(upperFiledName,fieldValue );
        return result;
    }



    private void checkIsValueChanged(Object foundObject, Field eachField, HashMap<String, Object> fieldValue, Object value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(!fieldValue.equals(value)){
            String setMethodName = "set" + methodNameStartWithCapital(eachField);// set메서드 설정위해 이름 가져오기
            Method setFiled = foundObject.getClass().getDeclaredMethod(setMethodName, eachField.getType());
            setFiled.invoke(foundObject, value);
        }
    }




}
