package com.tsid.domain.converter.group;

import com.tsid.domain.enums.group.EGroupUpdateFlag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GroupUpdateStatusConverter implements AttributeConverter<EGroupUpdateFlag, String> {

    @Override
    public String convertToDatabaseColumn(EGroupUpdateFlag attribute) {
        return attribute.getCode();
    }

    @Override
    public EGroupUpdateFlag convertToEntityAttribute(String dbData) {
        return EGroupUpdateFlag.ofCode(dbData);
    }
}
