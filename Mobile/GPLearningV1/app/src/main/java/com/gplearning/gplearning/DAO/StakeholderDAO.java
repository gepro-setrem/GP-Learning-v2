package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Models.Stakeholder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StakeholderDAO extends DefaultDAO {

    public List<Stakeholder> SelecionaStakeholderData(Date date) {
        String url = UrlDefault + "/stakeholder/index/date";
        RestTemplate restTemplate = getResTemplateDefault();
        Stakeholder stakeholder = new Stakeholder();
        // stakeholder.setCriacao(date);
        ResponseEntity<Stakeholder[]> responseEntity = restTemplate.postForEntity(url, stakeholder, Stakeholder[].class);
        Stakeholder[] stakeholders = responseEntity.getBody();
        return Arrays.asList(stakeholders);
    }

}
