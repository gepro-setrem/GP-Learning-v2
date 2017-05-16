package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Models.Avaliacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class AvaliacaoDAO extends DefaultDAO {

    public List<Avaliacao> SelecionaAvaliacoesPessoa(Long idPessoa) {
        String url = UrlDefault + "/avaliacao/index/" + idPessoa;
        RestTemplate restTemplate = getResTemplateDefault();
        ResponseEntity<Avaliacao[]> responseEntity = restTemplate.getForEntity(url, Avaliacao[].class);
        return Arrays.asList(responseEntity.getBody());
    }


}
