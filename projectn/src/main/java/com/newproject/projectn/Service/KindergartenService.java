package com.newproject.projectn.Service;

import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.repository.AddressRepository;
import com.newproject.projectn.repository.KindergartenRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class KindergartenService {

    KindergartenRepository kindergartenRepository;
    AddressRepository addressRepository;
    @Transactional
    public Kindergarten createKindergarten(Kindergarten kindergarten) {

        checkKindergartenExistByName(kindergarten.getName());

        addressRepository.save(kindergarten.getAddress());
        return kindergartenRepository.save(kindergarten);


    }



    public Kindergarten findKindergarten(Long id) {
        return findKindergartenById(id);
    }



    public List<Kindergarten> findKindergartenList(int pageIdx) {

        return kindergartenRepository.findAll(PageRequest.of(pageIdx, 30, Sort.by("updateTime").descending()))
                .stream().toList();
    }

    public Kindergarten editKindergarten(Kindergarten editingKindergarten) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Kindergarten foundKindergarten = kindergartenRepository.findById(editingKindergarten.getKindergartenId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));

        Class<? extends Kindergarten> kindergarten = editingKindergarten.getClass();
        Field[] fields = kindergarten.getDeclaredFields();

        for(Field eachField: fields){
            eachField.setAccessible(true);
            String capitalizedFieldName = methodNameStartWithCapital(eachField);

            if(eachField.getType().equals(String.class)){
                HashMap<String, Object> fieldValue = getFieldValue(editingKindergarten.getClass() ,capitalizedFieldName, foundKindergarten );
                Object value = eachField.get(editingKindergarten);// 수정본에서 value가져오기
                checkIsValueChanged(foundKindergarten, eachField, fieldValue, value);
                System.out.println("Field name: " + eachField.getName() + ", Value: " + value);
            }

            if(eachField.getType().equals(Boolean.class)){
                HashMap<String, Object> fieldValue = getFieldValue(editingKindergarten.getClass(), capitalizedFieldName, foundKindergarten );
                Object value = eachField.get(editingKindergarten);// 수정본에서 value가져오기
                checkIsValueChanged(foundKindergarten, eachField, fieldValue, value);
                System.out.println("Field name: " + eachField.getName() + ", Value: " + value);
            }

            if(eachField.getType().equals(LocalTime.class)){
                HashMap<String, Object> fieldValue = getFieldValue(editingKindergarten.getClass(), capitalizedFieldName, foundKindergarten );
                Object value = eachField.get(editingKindergarten);// 수정본에서 value가져오기
                checkIsValueChanged(foundKindergarten, eachField, fieldValue, value);
                System.out.println("Field name: " + eachField.getName() + ", Value: " + value);
            }

            if(eachField.getType().equals(Integer.class)){
                HashMap<String, Object> fieldValue = getFieldValue(editingKindergarten.getClass(), capitalizedFieldName, foundKindergarten );
                Object value = eachField.get(editingKindergarten);// 수정본에서 value가져오기
                checkIsValueChanged(foundKindergarten, eachField, fieldValue, value);
                System.out.println("Field name: " + eachField.getName() + ", Value: " + value);
            }
        }

        return kindergartenRepository.save(editingKindergarten);
    }

    private void checkKindergartenExistByName(String kindergartenName) {
         kindergartenRepository.existsByName(kindergartenName).orElseThrow(() -> new BusinessLogicException(ExceptionCode.DUPLICATE_NAME));
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

    public void removeKindergarten(Long kindergartenId) {

        kindergartenRepository.deleteById(kindergartenId);
    }
    public Kindergarten findKindergartenById(Long id) {
        return kindergartenRepository.findById(id).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
    }

}


