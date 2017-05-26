package com.gplearning.gplearning.DAO;


import android.content.Context;

import com.gplearning.gplearning.Models.Avaliacao;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class AvaliacaoDAO extends DefaultDAO {
    private Context context;

    public AvaliacaoDAO(Context context) {
        this.context = context;
    }

    public List<Avaliacao> SelecionaAvaliacoesPessoa(int idPessoa) {
        String url = UrlDefault + "/avaliacao/pessoa/" + idPessoa;
        RestTemplate restTemplate = getResTemplateDefault();
        HttpEntity<String> entity = new HttpEntity<>("", getHttpHeaderDefault(context));
        ResponseEntity<Avaliacao[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Avaliacao[].class); //.getForEntity(url, Avaliacao[].class);
        return Arrays.asList(responseEntity.getBody());
    }


}
