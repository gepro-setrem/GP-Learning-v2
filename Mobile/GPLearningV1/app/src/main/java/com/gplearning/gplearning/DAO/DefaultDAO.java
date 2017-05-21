package com.gplearning.gplearning.DAO;


import android.content.Context;

import com.gplearning.gplearning.Utils.MetodosPublicos;
import com.gplearning.gplearning.Utils.UrlDomain;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

class DefaultDAO extends UrlDomain {
    ///  public static final String UrlDefaut="http://gplearning.com/";
    String valor = UrlDefault;

//
//    public List<Pessoa> SelectAll() {
//        List<Pessoa> lsUsuario = new ArrayList<>();
//        return lsUsuario;
//    }


    public RestTemplate getResTemplateDefault() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

    public HttpEntity getHttpHeaderDefault(Context context) {
        HttpAuthentication httpAuthentication = new HttpBasicAuthentication("Authorization", MetodosPublicos.SelecionaSessaoToken(context));
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAuthorization(httpAuthentication);
        return new HttpEntity<Object>(requestHeaders);

    }

    // HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
}
