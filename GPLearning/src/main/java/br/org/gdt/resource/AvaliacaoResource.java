/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.gdt.resource;

import br.org.gdt.bll.EtapaBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.AvaliacaoBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.Avaliacao;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/avaliacao")
public class AvaliacaoResource {

    @Autowired
    private AvaliacaoBLL avaliacaoBLL;
    @Autowired
    private PessoaBLL pessoaBLL;
    @Autowired
    private ProjetoBLL projetoBLL;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pessoa/{pes_id}")
    public List<Avaliacao> getAvaliacoesPessoa(@PathParam("pes_id") int pes_id) {
        List<Avaliacao> lsAvaliacao = new ArrayList<>();
        if (pes_id > 0) {
            Pessoa aluno = pessoaBLL.findById(pes_id);
            if (aluno != null) {
                List<Projeto> lsProjetos = projetoBLL.findbyAluno(aluno);
                if (lsProjetos != null) {
                    for (Projeto prj : lsProjetos) {
                        lsAvaliacao.addAll(avaliacaoBLL.findbyProjeto(prj));
                    }
                }
            }
        }
        return lsAvaliacao;
    }
}
