/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.gdt.resource;

import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.model.LoginRole;
import br.org.gdt.model.Pessoa;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/pessoa")
public class PessoaResource {

    @Autowired
    private PessoaBLL pessoaBLL;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/index/{pes_id}")
    public Pessoa getPessoa(@PathParam("pes_id") int pes_id) {
        Pessoa pessoa = null;
        if (pes_id > 0) {
            pessoa = pessoaBLL.findById(pes_id);
            if (pessoa != null) {
                pessoa.setIndicadoresprofessor(null);
                pessoa.setProjetos(null);
                pessoa.setProjetosgerente(null);
                pessoa.setTurma(null);
                pessoa.setTurmasprofessor(null);
                if (pessoa.getLogin() != null) {
                    pessoa.getLogin().setPessoa(null);
                    if (pessoa.getLogin().getLoginRoles() != null) {
                        for (LoginRole lr : pessoa.getLogin().getLoginRoles()) {
                            lr.setLogin(null);
                        }
                    }
                }
            }
        }
        return pessoa;
    }
}
