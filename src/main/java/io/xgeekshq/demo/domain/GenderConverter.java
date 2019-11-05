package io.xgeekshq.demo.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import io.xgeekshq.demo.model.Gender;

/**
 * GenderConverter
 */
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