package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Models.Eap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class EapDAO extends DefaultDAO {

    public List<Eap> SelecionaEaps(int pessoaId) {
        String url = UrlDefault + "/tarefa/pessoa/" + pessoaId;
        RestTemplate restTemplate = getResTemplateDefault();
        ResponseEntity<Eap[]> responseEntity = restTemplate.getForEntity(url, Eap[].class);
        Eap[] eapArray = responseEntity.getBody();
        if (eapArray != null && eapArray.length > 0)
            return Arrays.asList(eapArray);

        return null;
    }

}
