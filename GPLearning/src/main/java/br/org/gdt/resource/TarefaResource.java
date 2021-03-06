package br.org.gdt.resource;

import br.org.gdt.bll.EAPBLL;
import br.org.gdt.bll.LoginBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.RecursoBLL;
import br.org.gdt.bll.TarefaBLL;
import br.org.gdt.model.EAP;
import br.org.gdt.model.Login;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Recurso;
import br.org.gdt.model.Tarefa;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/tarefa")
public class TarefaResource {

    @Autowired
    private TarefaBLL tarefaBLL;
    @Autowired
    private EAPBLL eapBLL;
    @Autowired
    private ProjetoBLL projetoBLL;
    @Autowired
    private RecursoBLL recursoBLL;
    @Autowired
    private PessoaBLL pessoaBLL;
    @Autowired
    private LoginBLL loginBLL;
    @Context
    private HttpServletRequest servletRequest;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/index/{pro_id}")
    public EAP getAll(@PathParam("pro_id") int pro_id) {
        if (Access()) {
            Projeto projeto = projetoBLL.findById(pro_id);
            if (projeto != null) {
                EAP eap = tarefaBLL.findbyEAP(projeto);
                return eap;
            }
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pessoa/{pes_id}")
    public List<EAP> getAllApi(@PathParam("pes_id") int pes_id) {
        List<EAP> lsEaps = new ArrayList<>();
        if (pes_id > 0) {
            Pessoa pessoa = pessoaBLL.findById(pes_id);
            if (pessoa != null) {
                List<Projeto> lsProjetos = projetoBLL.findbyAluno(pessoa);
                if (lsProjetos != null) {
                    for (Projeto p : lsProjetos) {
                        EAP eap = tarefaBLL.findbyEAP(p);
                        if (eap != null) {
                            eap.setProjeto(CleanProjeto(p));
                            lsEaps.add(eap);
                        }
                    }
                }
                List<Projeto> lsProjetosProfessor = projetoBLL.findbyProfessor(pessoa, true);
                if (lsProjetosProfessor != null) {
                    for (Projeto p : lsProjetosProfessor) {
                        if (!lsProjetos.contains(p)) {
                            EAP eap = tarefaBLL.findbyEAP(p);
                            if (eap != null) {
                                eap.setProjeto(CleanProjeto(p));
                                lsEaps.add(eap);
                            }
                        }
                    }
                }
            }
        }
        return lsEaps;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/salvar")
    public int Salvar(Tarefa model
    ) {
        if (Access()) {
            if (model.getRecursos() != null) {
                for (Recurso recurso : model.getRecursos()) {
                    recurso.setTarefa(model);
                }
            }
            if (model.getId() > 0) {
                List<Recurso> lsRecurso = recursoBLL.findbyTarefa(model);
                for (Recurso recurso : lsRecurso) {
                    recursoBLL.delete(recurso.getId());
                }
                tarefaBLL.update(model);
            } else {
                tarefaBLL.insert(model);
            }
            return model.getId();
        }
        return 0;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/excluir")
    public Boolean Excluir(@FormParam("id") int id
    ) {
        if (Access()) {
            if (id > 0) {
                tarefaBLL.delete(id);
            }
            return true;
        }
        return false;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/marcar")
    public Boolean Marcar(@FormParam("id") int id,
            @FormParam("marco") boolean marco
    ) {
        if (Access()) {
            if (id > 0) {
                Tarefa tarefa = tarefaBLL.findById(id);
                tarefa.setMarco(marco);
                tarefaBLL.update(tarefa);
            }
            return true;
        }
        return false;
    }

    public boolean Access() {
        try {
            String token = servletRequest.getHeader("Authorization");
            Login login = loginBLL.findToken(token);
            if (login != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private Projeto CleanProjeto(Projeto p) {
        p.setAvaliacoes(null);
        p.setComentarios(null);
        p.setComponentes(null);
        p.setEaps(null);
        p.setGerente(null);
        p.setRequisitos(null);
        p.setTermoabertura(null);
        p.setTurma(null);
        return p;
    }

}
