package org.erhc.orderflow.core.application.service;

import org.erhc.orderflow.core.domain.model.AuditChange;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuditDiffCalculator {

    public List<AuditChange> calculate(Object before, Object after){

        if(before == null || after == null) {
            throw new IllegalArgumentException("Objects to compare must not be null");
        }

        if(!before.getClass().equals(after.getClass())){
            throw new IllegalArgumentException("Objects must be of same type");
        }

        List<AuditChange> changes = new ArrayList<>();

        Class<?> clazz = before.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields){
            field.setAccessible(true);

            try{
                Object oldValue = field.get(before);
                Object newValue = field.get(after);

                if(!Objects.equals(oldValue, newValue)){
                    changes.add(new AuditChange(
                            field.getName(),
                            valueToString(oldValue),
                            valueToString(newValue)
                    ));
                }

            } catch (Exception e){
                throw new RuntimeException("Error calculating audit diff", e);
            }

        }
        return changes;
    }

    private String valueToString(Object value){
        return value == null ? null : value.toString();
    }


}
