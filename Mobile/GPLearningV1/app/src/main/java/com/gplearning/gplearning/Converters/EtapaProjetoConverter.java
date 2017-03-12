package com.gplearning.gplearning.Converters;


import com.gplearning.gplearning.Enums.EtapaProjeto;

import org.greenrobot.greendao.converter.PropertyConverter;

public class EtapaProjetoConverter implements PropertyConverter<EtapaProjeto, String>{

    @Override
    public EtapaProjeto convertToEntityProperty(String databaseValue) {
        return EtapaProjeto.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(EtapaProjeto entityProperty) {
      return entityProperty.name();
    }
}
