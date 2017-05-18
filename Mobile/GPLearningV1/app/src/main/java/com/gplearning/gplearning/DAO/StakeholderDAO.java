package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Models.Stakeholder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class StakeholderDAO extends DefaultDAO {

    public List<Stakeholder> SelecionaStakeholderData(int id) {
        String url = UrlDefault + "/stakeholder/pessoa/" + id;
        RestTemplate restTemplate = getResTemplateDefault();
        // Stakeholder stakeholder = new Stakeholder();
        // stakeholder.setCriacao(date);
        ResponseEntity<Stakeholder[]> responseEntity = restTemplate.getForEntity(url, Stakeholder[].class); //.postForEntity(url, stakeholder, Stakeholder[].class);
        Stakeholder[] stakeholders = responseEntity.getBody();
        return Arrays.asList(stakeholders);
    }

}
