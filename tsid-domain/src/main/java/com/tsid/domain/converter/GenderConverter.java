package com.tsid.domain.converter;

import com.tsid.domain.enums.EGenderFlag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<EGenderFlag, String> {

    @Override
    public String convertToDatabaseColumn(EGenderFlag attribute) {
        return attribute.getCode();
    }

    @Override
    public EGenderFlag convertToEntityAttribute(String dbData) {
        return EGenderFlag.ofCode(dbData);
    }
}
