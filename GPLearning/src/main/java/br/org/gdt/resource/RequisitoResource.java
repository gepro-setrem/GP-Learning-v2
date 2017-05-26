/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.gdt.resource;

import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.RequisitoBLL;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Requisito;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/requisito")
public class RequisitoResource {

    @Autowired
    private PessoaBLL pessoaBLL;
    @Autowired
    private ProjetoBLL projetoBLL;
    @Autowired
    private RequisitoBLL requisitoBLL;
   

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pessoa/{pes_id}")
    public List<Requisito> getAllPessoa(@PathParam("pes_id") int pes_id) throws IOException {
        List<Requisito> lsRequisito = new ArrayList<>();
            Pessoa pessoa = pessoaBLL.findById(pes_id);
            if (pessoa != null) {
                List<Projeto> lsProjeto = projetoBLL.findbyAluno(pessoa);
                if (lsProjeto != null) {
                    for (Projeto prj : lsProjeto) {
                        lsRequisito.addAll(requisitoBLL.findbyProjeto(prj));
                    }
                }
            }
        return lsRequisito;
    }

}
