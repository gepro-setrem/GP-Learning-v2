package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Models.TermoAbertura;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TermoAberturaDAO extends DefaultDAO {

    public List<TermoAbertura> SelecionaTermoAberturaData(int idExterno) {
        String url = UrlDefault + "/termoabertura/pessoa/"+idExterno;
        RestTemplate restTemplate = getResTemplateDefault();
        ResponseEntity<TermoAbertura[]> responseEntity = restTemplate.getForEntity(url, TermoAbertura[].class);
        TermoAbertura[] termoAberturas = responseEntity.getBody();
        return Arrays.asList(termoAberturas);
    }
}
