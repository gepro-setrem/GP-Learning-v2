package com.gplearning.gplearning.DAO;


import android.content.Context;

import com.gplearning.gplearning.Utils.MetodosPublicos;
import com.gplearning.gplearning.Utils.UrlDomain;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

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

    public HttpHeaders getHttpHeaderDefault(Context context) {
        HttpHeaders headers = new HttpHeaders();
        //   headers.set("Authorization", MetodosPublicos.SelecionaSessaoToken(context));
        HttpAuthentication httpAuthentication = new HttpBasicAuthentication("Authorization", MetodosPublicos.SelecionaSessaoToken(context));
        headers.setAuthorization(httpAuthentication);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        MetodosPublicos.Log("Event", " TOKEN:" + MetodosPublicos.SelecionaSessaoToken(context));
        return headers;
    }

    // HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
}
