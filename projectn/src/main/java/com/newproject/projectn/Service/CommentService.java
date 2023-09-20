package com.newproject.projectn.Service;

import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import com.newproject.projectn.entitiy.Comment;
import com.newproject.projectn.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    CommentRepository commentRepository;
    public Comment createComment(Comment newComment) {
        return commentRepository.save(newComment);

    }

    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));

    }

    public List<Comment> findCommentList(int pageIdx) {
        return commentRepository.findAll(PageRequest.of(pageIdx, 30, Sort.by("updateTime").descending()))
                .stream().toList();
    }

    public Comment editComment(Comment editingComment) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Comment foundComment = findComment(editingComment.getCommentId());

        Class<? extends Comment> testUser = editingComment.getClass();
        Field[] fields = testUser.getDeclaredFields();

        for(Field eachField: fields){
            eachField.setAccessible(true);
            String capitalizedFieldName = methodNameStartWithCapital(eachField);


            if(eachField.getType().equals(String.class)){
                HashMap<String, Object> fieldValue = getFieldValue(editingComment.getClass(),capitalizedFieldName, foundComment );
                Object value = eachField.get(editingComment);// 수정본에서 value가져오기
                checkIsValueChanged(foundComment, eachField, fieldValue, value);
                System.out.println("Field name: " + eachField.getName() + ", Value: " + value);
            }
        }

        return commentRepository.save(foundComment);

    }

    public String methodNameStartWithCapital(Field field){
        return field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);//username -> Username
    }
    public HashMap<String,Object> getFieldValue(Class<?> objectType, String upperFiledName,Object classObject) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashMap<String,Object> result = new HashMap<String,Object>();

        Method getFiled = objectType.getDeclaredMethod("get" + upperFiledName);//get 메서드 생성
        Object fieldValue = getFiled.invoke(classObject);//비교할 원본 객체에서 각 필드의 value 가져오기
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
