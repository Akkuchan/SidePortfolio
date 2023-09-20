package com.newproject.projectn.Service.post;

import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.post.FAQ;
import com.newproject.projectn.entitiy.post.Post;
import com.newproject.projectn.repository.post.FaqRepository;
import com.newproject.projectn.repository.post.PostRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class FaqService {
    PostRepository postRepository;
    FaqRepository faqRepository;
    public FAQ createFaq(FAQ newFaq) {


       return faqRepository.save(newFaq);

    }

    public FAQ findFaq(Long faqId) {
        Post faq1 = postRepository.findById(faqId).orElseThrow();
        FAQ faq2 = faqRepository.findById(faqId).orElseThrow();

        return faq2;
    }

    public FAQ editFaq(FAQ editFaq) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        FAQ foundFaq = faqRepository.findById(editFaq.getPostId()).orElseThrow();

        Class<? extends Post> editPost = editFaq.getClass();
        Field[] fields = editPost.getDeclaredFields();

        List<Class<?>> allowedTypes = new ArrayList<>();
        allowedTypes.add(String.class);
        allowedTypes.add(Integer.class);
        allowedTypes.add(Boolean.class);

        for(Field eachField: fields){
            eachField.setAccessible(true);
            String capitalizedFieldName = methodNameStartWithCapital(eachField);

            if(allowedTypes.contains(eachField.getType())){
                HashMap<String, Object> fieldValue = getFieldValue(capitalizedFieldName, foundFaq );
                Object value = eachField.get(editFaq);// 수정본에서 value가져오기
                checkIsValueChanged(foundFaq, eachField, fieldValue, value);
                System.out.println("Field name: " + eachField.getName() + ", Value: " + value);
            }

        }


       return faqRepository.save(editFaq);
    }

    public String methodNameStartWithCapital(Field field){
        return field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);//username -> Username
    }

    public HashMap<String,Object> getFieldValue(String upperFiledName, Post dbPost) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashMap<String,Object> result = new HashMap<String,Object>();

        Method getFiled = dbPost.getClass().getDeclaredMethod("get" + upperFiledName);//get 메서드 생성
        Object fieldValue = getFiled.invoke(dbPost);//비교할 원본 객체에서 각 필드의 value 가져오기
        result.put(upperFiledName,fieldValue );
        return result;
    }



    private void checkIsValueChanged(Post foundPost, Field eachField, HashMap<String, Object> fieldValue, Object value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(!fieldValue.equals(value)){
            String setMethodName = "set" + methodNameStartWithCapital(eachField);// set메서드 설정위해 이름 가져오기
            Method setFiled = foundPost.getClass().getDeclaredMethod(setMethodName, eachField.getType());
            setFiled.invoke(foundPost, value);
        }
    }

    public void deleteFqa(Long faqId) {

        faqRepository.deleteById(faqId);
    }
}
