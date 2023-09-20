package com.newproject.projectn.Service;

import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.address.Address;
import com.newproject.projectn.entitiy.address.City;
import com.newproject.projectn.entitiy.address.State;
import com.newproject.projectn.repository.AddressRepository;
import com.newproject.projectn.repository.CityRepository;
import com.newproject.projectn.repository.KindergartenRepository;
import com.newproject.projectn.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KindergartenService {

    KindergartenRepository kindergartenRepository;
    AddressRepository addressRepository;
    StateRepository stateRepository;
    CityRepository cityRepository;
    @Transactional
    public Kindergarten createKindergarten(Kindergarten kindergarten, long cityId, String details, String zipCode) {

        checkKindergartenExistByName(kindergarten.getName());

        City city = cityRepository.findById(cityId).orElseThrow();

        Address address = addressRepository.save(new Address(city, details, zipCode));

        kindergarten.setAddress(address);
        return kindergartenRepository.save(kindergarten);


    }



    public Kindergarten findKindergarten(Long id) {
        return findKindergartenById(id);
    }



    public List<Kindergarten> findKindergartenList(int pageIdx) {

        return kindergartenRepository.findAll(PageRequest.of(pageIdx, 30, Sort.by("updateTime").descending()))
                .stream().toList();
    }

    public List<Kindergarten> findKindergartenListByStateAndCity(String state, String city, @Nullable String kindergartenName, int pageIdx) {/// 리팩토링 필요 for문으로 db와 통신 너무 많이 함
        State foundState = stateRepository.findByStateName(state).orElseThrow();
        City city1 = cityRepository.findByStateAndCityName(foundState, city).orElseThrow();
        List<Address> addresses = addressRepository.findByCity(city1);

        List<Kindergarten> kindergartenList = new ArrayList<>();


        for(Address ad : addresses){
            kindergartenList.add(kindergartenRepository.findAllByAddress(ad).orElseThrow());
        }

        if(!Objects.equals(kindergartenName, "") || !kindergartenName.isEmpty()){
            return kindergartenList.stream().filter((kindergarten) -> kindergarten.getName().equals(kindergartenName)).collect(Collectors.toList());
        }

        return kindergartenList;

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


