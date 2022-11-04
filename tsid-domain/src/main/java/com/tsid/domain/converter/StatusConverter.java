package com.tsid.domain.converter;

import com.tsid.domain.enums.EStatusFlag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<EStatusFlag, String> {

    @Override
    public String convertToDatabaseColumn(EStatusFlag attribute) {
        return attribute.getCode();
    }

    @Override
    public EStatusFlag convertToEntityAttribute(String dbData) {
        return EStatusFlag.ofCode(dbData);
    }
}
