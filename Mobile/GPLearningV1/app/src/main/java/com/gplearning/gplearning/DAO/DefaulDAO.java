package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Models.Usuario;
import com.gplearning.gplearning.Utils.UrlDomain;

import java.util.ArrayList;
import java.util.List;

class DefaulDAO extends UrlDomain {
    ///  public static final String UrlDefaut="http://gplearning.com/";
    String valor = UrlDefault;


    public List<Usuario> SelectAll() {
        List<Usuario> lsUsuario = new ArrayList<>();
        return lsUsuario;
    }

}
