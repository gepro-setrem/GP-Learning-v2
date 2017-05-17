package br.org.gdt.resource;

import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/projeto")
public class ProjetoResource {

    @Autowired
    private ProjetoBLL projetoBLL;

    @Autowired
    private PessoaBLL pessoaBLL;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/index/aluno/{pes_id}")
    public List<Projeto> getAllbyAluno(@PathParam("pes_id") int pes_id) {
        if (pes_id > 0) {
            Pessoa aluno = pessoaBLL.findById(pes_id);
            if (aluno != null) {
                return projetoBLL.findbyAluno(aluno);
            }
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/index/professor/{pes_id}")
    public List<Projeto> getAllbyProfessor(@PathParam("pes_id") int pes_id) {
        if (pes_id > 0) {
            Pessoa professor = pessoaBLL.findById(pes_id);
            if (professor != null) {
                return projetoBLL.findbyProfessor(professor);
            }
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/index/projeto/{pro_id}")
    public Projeto getProjeto(@PathParam("pro_id") int pro_id) {
        if (pro_id > 0) {
            return projetoBLL.findProjetoCompleto(pro_id);
        }
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/date")
    public List<Projeto> getProjetoDate(Projeto projeto) {
        if (projeto != null) {
            return projetoBLL.findProjetoDate(projeto);
        }
        return null;
    }

}
