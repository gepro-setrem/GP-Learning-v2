package com.gplearning.gplearning.DAO;


import android.util.Log;

import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Pessoa;
import com.gplearning.gplearning.Models.PessoaDao;
import com.gplearning.gplearning.Utils.UrlDomain;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends UrlDomain {

    String url = UrlDefault;

    public Pessoa Login(DaoSession session, String email, String senha) {
        url += "/login/login/" + email + "/" + senha;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        Log.i("login", "Url: " + url);
        Pessoa user = restTemplate.getForObject(url, Pessoa.class); //("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

//        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//        map.add("email", email);
//        map.add("senha", senha);

        //  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        //  ResponseEntity<String> response = restTemplate.postForObject(url, request , ResponseEntity.class );
        //  Pessoa user = null;// = response.
        if (user != null && user.getId() > 0) {
            PessoaDao dao = session.getPessoaDao();

            dao.insert(user);

            return user;
        }
        return null;
    }


    public List<Pessoa> Select() {

        List<Pessoa> lsUsuario = new ArrayList<>();

        // String url = UrlDefau;
        return lsUsuario;
    }


}
