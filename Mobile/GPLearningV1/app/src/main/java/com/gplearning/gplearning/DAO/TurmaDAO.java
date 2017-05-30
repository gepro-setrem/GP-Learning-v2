package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Models.Turma;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TurmaDAO extends DefaultDAO {

    public Turma SelecionaTurma(int idTurma) {
        String url = UrlDefault + "/turma/" + idTurma;
        MetodosPublicos.Log("turma", " vai turma :" + url);
        RestTemplate restTemplate = getResTemplateDefault();
        ResponseEntity<Turma> responseEntity = restTemplate.getForEntity(url, Turma.class);
        return responseEntity.getBody();
    }

}
