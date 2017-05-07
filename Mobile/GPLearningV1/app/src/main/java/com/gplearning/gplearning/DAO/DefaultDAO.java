package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Models.Pessoa;
import com.gplearning.gplearning.Utils.UrlDomain;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

class DefaultDAO extends UrlDomain {
    ///  public static final String UrlDefaut="http://gplearning.com/";
    String valor = UrlDefault;

//
//    public List<Pessoa> SelectAll() {
//        List<Pessoa> lsUsuario = new ArrayList<>();
//        return lsUsuario;
//    }


    public RestTemplate getResTemplateDefault(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

}
