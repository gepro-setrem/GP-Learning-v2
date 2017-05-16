package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Models.TermoAbertura;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TermoAberturaDAO extends DefaultDAO {

    public List<TermoAbertura> SelecionaTermoAberturaData(Date date) {
        String url = UrlDefault + "/termoAbertura/index/date";
        RestTemplate restTemplate = getResTemplateDefault();
        TermoAbertura termoAbertura = new TermoAbertura();
        termoAbertura.setCriacao(date);
        ResponseEntity<TermoAbertura[]> responseEntity = restTemplate.postForEntity(url, termoAbertura, TermoAbertura[].class);
        TermoAbertura[] termoAberturas = responseEntity.getBody();
        return Arrays.asList(termoAbertura);
    }
}
