package com.gplearning.gplearning.DAO;


import android.content.Context;

import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.Models.Etapa;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ComentarioDAO extends DefaultDAO {


    public List<Comentario> SelecionaComentarioPorEtapa(Etapa etapa, Context context) {
        String url = UrlDefault + "/comentario/index/" + etapa.getId();

        RestTemplate restTemplate = getResTemplateDefault();
        HttpEntity<String> entity = new HttpEntity<>("", getHttpHeaderDefault(context));
        MetodosPublicos.Log("DAO", " vai seleecioanr url:" + url);
        ResponseEntity<Comentario[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Comentario[].class);
        // ResponseEntity<Comentario[]> responseEntity = restTemplate.getForEntity(url, Comentario[].class);
        Comentario[] comentarioArray = responseEntity.getBody();
        if (comentarioArray != null && comentarioArray.length > 0)
            return Arrays.asList(comentarioArray);
        return null;
    }


    public int SalvarComentario(Comentario comentario, Context context) {
        try {
            String url = UrlDefault + "/comentario/salvar/";
            RestTemplate restTemplate = getResTemplateDefault();
            HttpHeaders headers = getHttpHeaderDefault(context);
            HttpEntity<Comentario> entity = new HttpEntity<Comentario>(comentario, headers);
            MetodosPublicos.Log("DAO", " vai salvar url:" + url + " token:" + MetodosPublicos.SelecionaSessaoToken(context));
            ResponseEntity<Integer> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Integer.class);
            MetodosPublicos.Log("DAO", " retornou com :" + responseEntity.getBody());
            return responseEntity.getBody();
        } catch (Exception e) {
            MetodosPublicos.Log("ERROR", e.toString());
        }
        return 0;
    }


    public boolean DeletaComentario(Comentario comentario, Context context) {
        if (comentario != null && comentario.getId() > 0) {
            String url = UrlDefault + "/comentario/excluir/";// + comentario.getId();
            RestTemplate restTemplate = getResTemplateDefault();
            MetodosPublicos.Log("DAO", " vai deletar url:" + url);
            HttpEntity<Comentario> entity = new HttpEntity<Comentario>(comentario, getHttpHeaderDefault(context));
            ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Boolean.class);
            // ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url, comentario, Boolean.class);
            return responseEntity.getBody();
        }
        return false;
    }


    public List<Comentario> SelecionaComentarioPorData(Context context, int id) {
        String url = UrlDefault + "/comentario/pessoa/" + id;
        RestTemplate restTemplate = getResTemplateDefault();
        HttpEntity<String> entity = new HttpEntity<String>("", getHttpHeaderDefault(context));
        ResponseEntity<Comentario[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Comentario[].class);
        // ResponseEntity<Comentario[]> responseEntity = restTemplate.getForEntity(url, Comentario[].class); //.postForEntity(url, comentario, Comentario[].class);
        Comentario[] comentarioArray = responseEntity.getBody();
        if (comentarioArray != null && comentarioArray.length > 0)
            return Arrays.asList(comentarioArray);
        return null;

    }


}
