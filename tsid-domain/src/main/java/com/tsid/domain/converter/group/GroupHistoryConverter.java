package com.tsid.domain.converter.group;

import com.tsid.domain.enums.group.EGroupHistoryFlag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GroupHistoryConverter implements AttributeConverter<EGroupHistoryFlag, String> {

    @Override
    public String convertToDatabaseColumn(EGroupHistoryFlag attribute) {
        return attribute.getCode();
    }

    @Override
    public EGroupHistoryFlag convertToEntityAttribute(String dbData) {
        return EGroupHistoryFlag.ofCode(dbData);
    }
}
