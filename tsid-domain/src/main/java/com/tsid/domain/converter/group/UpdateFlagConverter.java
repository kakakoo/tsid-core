package com.tsid.domain.converter.group;

import com.tsid.domain.enums.group.EUpdateFlag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UpdateFlagConverter implements AttributeConverter<EUpdateFlag, String> {

    @Override
    public String convertToDatabaseColumn(EUpdateFlag attribute) {
        return attribute.getCode();
    }

    @Override
    public EUpdateFlag convertToEntityAttribute(String dbData) {
        return EUpdateFlag.ofCode(dbData);
    }
}
