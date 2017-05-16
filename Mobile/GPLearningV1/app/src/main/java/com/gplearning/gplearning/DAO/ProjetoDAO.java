package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Enums.PapelUsuario;
import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProjetoDAO extends DefaultDAO {

    private List<Projeto> SelecionaProjetos(PapelUsuario papel, int id) {
        String url = UrlDefault + "/projeto/index/" + (papel == PapelUsuario.user ? "aluno" : "professor") + "/" + id;
        List<Projeto> lsProjetos = new ArrayList<>();
        RestTemplate restTemplate = getResTemplateDefault();

        ResponseEntity<Projeto[]> responseEntity = restTemplate.getForEntity(url, Projeto[].class);
        Projeto[] projetos = responseEntity.getBody();
        MetodosPublicos.Log("SeleP", " selcet de projetos com:" + projetos.length);
        return Arrays.asList(projetos);
    }

    public List<Projeto> SelecionaProjetosAluno(int id) {
        return SelecionaProjetos(PapelUsuario.user, id);
    }

    public List<Projeto> SelecionaProjetosProfessor(int id) {
        return SelecionaProjetos(PapelUsuario.admin, id);
    }


    public Projeto SelecionaProjetoCompleto(int pro_id) {
        String url = UrlDefault + "/projeto/index/projeto/" + pro_id;
        RestTemplate restTemplate = getResTemplateDefault();
        Projeto projeto = restTemplate.getForObject(url, Projeto.class);
        return projeto;
    }

    public List<Projeto> SelecionaProjetosData(Date date) {
        String url = UrlDefault + "/projeto/index/date";
        RestTemplate restTemplate = getResTemplateDefault();
        Projeto projeto = new Projeto();
        projeto.setCriacao(date);
        ResponseEntity<Projeto[]> responseEntity = restTemplate.postForEntity(url, projeto, Projeto[].class);
        Projeto[] projetos = responseEntity.getBody();
        return Arrays.asList(projetos);
    }

}
