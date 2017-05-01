package com.gplearning.gplearning.Converters;


import com.gplearning.gplearning.Enums.StatusPessoa;

import org.greenrobot.greendao.converter.PropertyConverter;

public class StatusPessoaConverter implements PropertyConverter<StatusPessoa, String> {

    @Override
    public StatusPessoa convertToEntityProperty(String databaseValue) {
        return StatusPessoa.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(StatusPessoa entityProperty) {
        return entityProperty.name();
    }
}