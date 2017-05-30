package com.gplearning.gplearning.DAO;


import android.content.Context;

import com.gplearning.gplearning.Enums.PapelUsuario;
import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ProjetoDAO extends DefaultDAO {

    private Context context;

    public ProjetoDAO(Context ctx) {
        this.context = ctx;
    }


    public Projeto SelecionaProjetoCompleto(int pro_id) {
        String url = UrlDefault + "/projeto/" + pro_id;
        MetodosPublicos.Log("projetoCompleto", " vai selecionar projetoCompleto :" + url);
        RestTemplate restTemplate = getResTemplateDefault();

       // HttpEntity<String> entity = new HttpEntity<>("", getHttpHeaderDefault(context));
        // ResponseEntity<Projeto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Projeto.class, pro_id);
        ResponseEntity<Projeto> responseEntity = restTemplate.getForEntity(url, Projeto.class);
        return responseEntity.getBody();
    }

    private List<Projeto> SelecionaProjetos(PapelUsuario papel, int id) {
        String url = UrlDefault + "/projeto/" + (papel == PapelUsuario.user ? "aluno" : "professor") + "/" + id;
        RestTemplate restTemplate = getResTemplateDefault();
       // HttpEntity<String> entity = new HttpEntity<>("", getHttpHeaderDefault(context));
        MetodosPublicos.Log("SeleP", "Vai selecionar pela URL: " + url);
        // ResponseEntity<Projeto[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Projeto[].class, id);//.getForEntity(url, Projeto[].class);
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


//    public List<Projeto> SelecionaProjetosData(Date date, int id) {
//        String url = UrlDefault + "/projeto/date";
//        RestTemplate restTemplate = new RestTemplate(true);// getResTemplateDefault();
//        //  restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
//        // Projeto projeto = new Projeto();
//        //  projeto.setAlteracao(date);
//
//        //  HttpHeaders headers = new HttpHeaders();
//        // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        //  headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
//        //  map.add("pes_id", id);
//        //  HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<MultiValueMap<String, Integer>>(map, headers);
//        //   ResponseEntity<Projeto[]> responseEntity = restTemplate.postForEntity(url, request, Projeto[].class);
//
//        //  HttpHeaders headers = new HttpHeaders();
////        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
////        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
////        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//
//
//        // HttpHeaders headers = new HttpHeaders();
////        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
////        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
////                .queryParam("pes_id", id)
////                .queryParam("data", date);
////        HttpEntity<?> entity = new HttpEntity<>(headers);
////        HttpEntity<Projeto[]> responseEntity = restTemplate.exchange(
////                builder.build().encode().toUri(),
////                HttpMethod.GET,
////                entity,
////                Projeto[].class);
//
////        ResponseEntity<Projeto[]> responseEntity = restTemplate.postForEntity(url, projeto, Projeto[].class);
//
//
//        //---------------------------------------------
//        MultiValueMap<String, String> valueMaps = new LinkedMultiValueMap<String, String>();
//        valueMaps.add("pes_id", id + "");
//        valueMaps.add("data", MetodosPublicos.DateToString(date));
//        ResponseEntity<Projeto[]> responseEntity = restTemplate.postForEntity(url, valueMaps, Projeto[].class);
//
//
//        Projeto[] projetos = responseEntity.getBody();
//        if (projetos != null && projetos.length > 0)
//            return Arrays.asList(projetos);
//        else return null;
//    }

}
