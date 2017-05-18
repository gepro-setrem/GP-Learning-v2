package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.Models.Etapa;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ComentarioDAO extends DefaultDAO {


    public List<Comentario> SelecionaComentarioPorEtapa(Etapa etapa) {
        String url = UrlDefault + "/comentario/index/" + etapa.getId();

        RestTemplate restTemplate = getResTemplateDefault();

        MetodosPublicos.Log("DAO", " vai seleecioanr url:" + url);
        ResponseEntity<Comentario[]> responseEntity = restTemplate.getForEntity(url, Comentario[].class);
        Comentario[] comentarioArray = responseEntity.getBody();
        if (comentarioArray != null && comentarioArray.length > 0)
            return Arrays.asList(comentarioArray);
        return null;
    }


    public int SalvarComentario(Comentario comentario) {
        try {
            String url = UrlDefault + "/comentario/salvar/";
            RestTemplate restTemplate = getResTemplateDefault();
            MetodosPublicos.Log("DAO", " vai salvar url:" + url);
            ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(url, comentario, Integer.class);
            MetodosPublicos.Log("DAO", " retornou com :" + responseEntity.getBody());
            return responseEntity.getBody();
        } catch (Exception e) {
            MetodosPublicos.Log("ERROR", e.toString());
        }
        return 0;
    }


    public boolean DeletaComentario(Comentario comentario) {
        if (comentario != null && comentario.getId() > 0) {
            String url = UrlDefault + "/comentario/excluir/";// + comentario.getId();
            RestTemplate restTemplate = getResTemplateDefault();
            // restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

            MetodosPublicos.Log("DAO", " vai deletar url:" + url);
            // ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(url, Boolean.class);
            ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url, comentario, Boolean.class);
            return responseEntity.getBody();


            //   MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
            //  map.add("com_id", comentario.getId());


            //  RestTemplate restTemplate = new RestTemplate();
            //  restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            //  restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            //  MetodosPublicos.Log("DAO", " vai salvar url:" + url);

            //  ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(url, comentario, Integer.class);
            //   MetodosPublicos.Log("DAO", " retornou com :" + responseEntity.getBody());

            //----------------
//            HttpHeaders headers = new HttpHeaders();
//            ///    headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(map, headers);
//            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, request, Boolean.class);
//            MetodosPublicos.Log("DAO", " retornou com :" + response);
//            return response.getBody();


//            restTemplate.setMessageConverters(
//                    Arrays.<HttpMessageConverter<?>>asList(new HttpMessageConverter[]{new FormHttpMessageConverter(), new StringHttpMessageConverter()}));
//            Boolean resp = restTemplate.postForObject(url, map, Boolean.class);
//
//            return resp;

            //  boolean responseEntity = restTemplate.postForObject(url, null, Boolean.class, vars);
            //   MetodosPublicos.Log("DAO", " retornou com :" + responseEntity);
            //    return restTemplate.exchange(url, HttpMethod.POST, map, Boolean.class);


//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<Integer> request = new HttpEntity<Integer>(comentario.getId(), headers);
//            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
//            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//            ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.POST, request, Boolean.class, map);


            //--------------------------------------------
//            HttpMessageConverter<?> formHttpMessageConverter = new FormHttpMessageConverter();
//            HttpMessageConverter<?> stringHttpMessageConverter = new StringHttpMessageConverter();
//            List<HttpMessageConverter> msgConverters = new ArrayList<HttpMessageConverter>();
//            msgConverters.add(formHttpMessageConverter);
//            msgConverters.add(stringHttpMessageConverter);
//// Prepare acceptable media type
//            List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
//            acceptableMediaTypes.add(MediaType.ALL);
//// Prepare header
//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(acceptableMediaTypes);
//            HttpEntity<MultiValueMap<String, Integer>> httpEntity = new HttpEntity<MultiValueMap<String, Integer>>(map, headers);
//            ResponseEntity<Boolean> resp = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Boolean.class);
////return  response;
//            MetodosPublicos.Log("DAO", " retornou com :" + resp);

        }
        return false;
    }


    public List<Comentario> SelecionaComentarioPorData(int id) {
        String url = UrlDefault + "/comentario/pessoa/" + id;
        // Comentario comentario = new Comentario();
        // comentario.setCriacao(date);
        RestTemplate restTemplate = getResTemplateDefault();
        //  MetodosPublicos.Log("DAO", " vai seleecioanr url:" + url + " com a data:" + date);
        ResponseEntity<Comentario[]> responseEntity = restTemplate.getForEntity(url, Comentario[].class); //.postForEntity(url, comentario, Comentario[].class);
        Comentario[] comentarioArray = responseEntity.getBody();
        if (comentarioArray != null && comentarioArray.length > 0)
            return Arrays.asList(comentarioArray);
        return null;

    }


}
