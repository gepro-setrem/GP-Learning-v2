package com.gplearning.gplearning.DAO;

import android.content.Context;

import com.gplearning.gplearning.Models.Requisito;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RequisitoDAO extends DefaultDAO {
    private Context context;

    public RequisitoDAO(Context context) {
        this.context = context;
    }

    public List<Requisito> SelecionaRequisitosData(int id) {
        String url = UrlDefault + "/requisito/pessoa/" + id;
        RestTemplate restTemplate = getResTemplateDefault();
        HttpEntity<String> entity = new HttpEntity<>("", getHttpHeaderDefault(context));
        //ResponseEntity<Requisito[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Requisito[].class);
        ResponseEntity<Requisito[]> responseEntity = restTemplate.getForEntity(url, Requisito[].class); //.postForEntity(url, requisito, Requisito[].class);
        Requisito[] requisitos = responseEntity.getBody();
        return Arrays.asList(requisitos);
    }

}
