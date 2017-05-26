package com.gplearning.gplearning.DAO;


import android.content.Context;

import com.gplearning.gplearning.Models.TermoAbertura;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class TermoAberturaDAO extends DefaultDAO {
//    private Context context;
//
//    public TermoAberturaDAO(Context context) {
//        this.context = context;
//    }

    public List<TermoAbertura> SelecionaTermoAberturaData(Context context, int idExterno) {
        String url = UrlDefault + "/termoabertura/pessoa/" + idExterno;
        RestTemplate restTemplate = getResTemplateDefault();
        HttpEntity<String> entity = new HttpEntity<>("", getHttpHeaderDefault(context));
        MetodosPublicos.Log("SeleP", "Vai selecionar pela URL: " + url);
        // ResponseEntity<TermoAbertura[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, TermoAbertura[].class, idExterno);
        ResponseEntity<TermoAbertura[]> responseEntity = restTemplate.getForEntity(url, TermoAbertura[].class);
        TermoAbertura[] termoAberturas = responseEntity.getBody();
        return Arrays.asList(termoAberturas);
    }
}
