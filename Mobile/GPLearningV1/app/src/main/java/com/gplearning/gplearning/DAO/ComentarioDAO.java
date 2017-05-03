package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Models.Atividade;
import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.Utils.UrlDomain;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ComentarioDAO extends UrlDomain {


    public List<Comentario> SelecionaComentarioPorAtividade(Atividade atividade) {
        UrlDefault += "/Comentario/index/" + atividade.getId();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        ResponseEntity<Comentario[]> responseEntity = restTemplate.getForEntity(UrlDefault, Comentario[].class);
        Comentario[] comentarioArray = responseEntity.getBody();
        MediaType contentType = responseEntity.getHeaders().getContentType();
        HttpStatus statusCode = responseEntity.getStatusCode();
        if (comentarioArray != null && comentarioArray.length > 0)
            return Arrays.asList(comentarioArray);
        return null;
    }

}
