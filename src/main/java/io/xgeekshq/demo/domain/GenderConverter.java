package io.xgeekshq.demo.domain;

import io.xgeekshq.demo.model.Gender;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(String value) {
        return Gender.fromValue(value);
    }
}
