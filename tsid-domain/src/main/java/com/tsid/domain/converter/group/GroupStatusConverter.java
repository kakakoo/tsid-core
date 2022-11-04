package com.tsid.domain.converter.group;

import com.tsid.domain.enums.group.EGroupStatusFlag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GroupStatusConverter implements AttributeConverter<EGroupStatusFlag, String> {

    @Override
    public String convertToDatabaseColumn(EGroupStatusFlag attribute) {
        return attribute.getCode();
    }

    @Override
    public EGroupStatusFlag convertToEntityAttribute(String dbData) {
        return EGroupStatusFlag.ofCode(dbData);
    }
}
