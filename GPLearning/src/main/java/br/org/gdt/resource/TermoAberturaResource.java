/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.gdt.resource;

import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.TermoAberturaBLL;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.TermoAbertura;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/termoabertura")
public class TermoAberturaResource {

    @Autowired
    private PessoaBLL pessoaBLL;
    @Autowired
    private ProjetoBLL projetoBLL;
    @Autowired
    private TermoAberturaBLL termoAberturaBLL;
   

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pessoa/{pes_id}")
    public List<TermoAbertura> getAllPessoa(@PathParam("pes_id") int pes_id) throws IOException {
        List<TermoAbertura> lsTermoAbertura = new ArrayList<>();
            Pessoa pessoa = pessoaBLL.findById(pes_id);
            if (pessoa != null) {
                List<Projeto> lsProjeto = projetoBLL.findbyAluno(pessoa);
                if (lsProjeto != null) {
                    for (Projeto prj : lsProjeto) {
                        TermoAbertura ta = termoAberturaBLL.findByProjetoCompleto(prj);
                        lsTermoAbertura.add(ta);
                    }
                }
            }
        return lsTermoAbertura;
    }

}
