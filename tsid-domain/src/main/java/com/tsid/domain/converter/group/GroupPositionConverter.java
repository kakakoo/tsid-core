package com.tsid.domain.converter.group;

import com.tsid.domain.enums.group.EGroupPositionFlag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GroupPositionConverter implements AttributeConverter<EGroupPositionFlag, String> {

    @Override
    public String convertToDatabaseColumn(EGroupPositionFlag attribute) {
        return attribute.getCode();
    }

    @Override
    public EGroupPositionFlag convertToEntityAttribute(String dbData) {
        return EGroupPositionFlag.ofCode(dbData);
    }
}
