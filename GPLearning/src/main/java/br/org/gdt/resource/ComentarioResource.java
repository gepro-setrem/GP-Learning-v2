/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.gdt.resource;

import br.org.gdt.bll.AtividadeBLL;
import br.org.gdt.model.Comentario;
import br.org.gdt.bll.ComentarioBLL;
import br.org.gdt.model.Atividade;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/comentario")
public class ComentarioResource {

    @Autowired
    private ComentarioBLL comentarioBLL;

    @Autowired
    private AtividadeBLL atividadeBLL;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/index/{atv_id}")
    public List<Comentario> getAll(@PathParam("atv_id") int atv_id) {
        List<Comentario> lsComentario = new ArrayList<Comentario>();
        if (atv_id > 0) {
            Atividade atv = atividadeBLL.findById(atv_id);
            if (atv != null && atv.getId() > 0) {
                lsComentario = comentarioBLL.findbyAtividade(atv);
            }
        }
        return lsComentario;
    }

}
