package com.gplearning.gplearning.Converters;


import com.gplearning.gplearning.Enums.PapelUsuario;
import org.greenrobot.greendao.converter.PropertyConverter;

public class PapelUsuarioConverter implements PropertyConverter<PapelUsuario, String>{

    @Override
    public PapelUsuario convertToEntityProperty(String databaseValue) {
        return PapelUsuario.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(PapelUsuario entityProperty) {
      return entityProperty.name();
    }
}
