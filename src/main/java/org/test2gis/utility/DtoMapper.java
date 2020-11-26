package org.test2gis.utility;

import java.lang.reflect.Type;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.test2gis.model.annotation.ColumnBooking;

@Component
public class DtoMapper {

  public <T> T mapToDto(Map<String, Object> input, Class clazz) throws Exception {

    T result = (T) clazz.getDeclaredConstructor().newInstance();

    ReflectionUtils.doWithFields(result.getClass(), (field) -> {
      Type fieldType = field.getType();
      Object finalValue;

      ColumnBooking annotation = field.getAnnotation(ColumnBooking.class);

      Object value = input.get(annotation != null ? annotation.name() : "");

      if (value == null) return;

      if (Boolean.class.equals(fieldType)) {
        finalValue = Boolean.valueOf(value.toString());
      } else if (Number.class.equals(fieldType)) {
        if (value instanceof Number) {
          finalValue = value;
        } else {
          finalValue = Integer.parseInt((String) value);
        }
      } else if (Integer.class.equals(fieldType)) {
        if (value instanceof Integer) {
          finalValue = value;
        } else {
          finalValue = Integer.parseInt((String) value);
        }
      } else {
        finalValue = value;
      }

      ReflectionUtils.makeAccessible(field);
      ReflectionUtils.setField(field, result, finalValue);
    });

    return result;
  }

}
