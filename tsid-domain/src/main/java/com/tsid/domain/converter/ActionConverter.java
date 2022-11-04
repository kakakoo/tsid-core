package com.tsid.domain.converter;

import com.tsid.domain.enums.EActionFlag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ActionConverter implements AttributeConverter<EActionFlag, String> {

    @Override
    public String convertToDatabaseColumn(EActionFlag attribute) {
        return attribute.getCode();
    }

    @Override
    public EActionFlag convertToEntityAttribute(String dbData) {
        return EActionFlag.ofCode(dbData);
    }
}
