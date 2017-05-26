package com.gplearning.gplearning.DAO;


import android.content.Context;

import com.gplearning.gplearning.Models.Stakeholder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class StakeholderDAO extends DefaultDAO {
    private Context context;

    public StakeholderDAO(Context context) {
        this.context = context;
    }

    public List<Stakeholder> SelecionaStakeholderData(int id) {
        String url = UrlDefault + "/stakeholder/pessoa/" + id;
        RestTemplate restTemplate = getResTemplateDefault();
        HttpEntity<String> entity = new HttpEntity<>("", getHttpHeaderDefault(context));
        ResponseEntity<Stakeholder[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Stakeholder[].class); //.getForEntity(url, Stakeholder[].class); //.postForEntity(url, stakeholder, Stakeholder[].class);
        Stakeholder[] stakeholders = responseEntity.getBody();
        return Arrays.asList(stakeholders);
    }

}
