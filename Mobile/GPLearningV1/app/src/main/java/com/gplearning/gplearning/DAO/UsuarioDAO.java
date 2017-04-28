package com.gplearning.gplearning.DAO;


import android.util.Log;

import com.gplearning.gplearning.Models.Pessoa;
import com.gplearning.gplearning.Utils.UrlDomain;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends UrlDomain {

    String url = UrlDefault;

    public long Login(String email, String senha) {
        url+="/login/login/"+email+"/"+senha;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Log.i("login","Url: "+url);
        Pessoa user = restTemplate.getForObject(url, Pessoa.class); //("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        if (user != null && user.getIdExterno()!=null && user.getIdExterno() > 0) {
            return user.getIdExterno();
        }
        return 0;
    }


    public List<Pessoa> Select() {

        List<Pessoa> lsUsuario = new ArrayList<>();

        // String url = UrlDefau;
        return lsUsuario;
    }


}
