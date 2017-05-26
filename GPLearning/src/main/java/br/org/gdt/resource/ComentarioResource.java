/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.gdt.resource;

import br.org.gdt.bll.EtapaBLL;
import br.org.gdt.model.Comentario;
import br.org.gdt.bll.ComentarioBLL;
import br.org.gdt.bll.LoginBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    private EtapaBLL etapaBLL;
    @Autowired
    private LoginBLL loginBLL;
    @Autowired
    private ProjetoBLL projetoBLL;
    @Autowired
    private PessoaBLL pessoaBLL;
//    @Context
//    private HttpServletRequest servletRequest;
//    @Context
//    private HttpServletResponse servletResponse;
//    @Autowired
//    private FilterApi filter;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/etapa/{eta_id}")
    public List<Comentario> getAll(@PathParam("eta_id") int eta_id) {
        System.out.println("chegou no metodo id:" + eta_id);
        List<Comentario> lsComentario = new ArrayList<Comentario>();
        if (eta_id > 0) {
            Etapa etapa = etapaBLL.findById(eta_id);
            if (etapa != null && etapa.getId() > 0) {
                lsComentario = comentarioBLL.findbyEtapa(etapa, false, true);
            }
        }
        return lsComentario;
        //  return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pessoa/{pes_id}")
    public List<Comentario> getbyPessoa(@PathParam("pes_id") int pes_id) {
        System.out.println("chegou no metodo id:" + pes_id);
        List<Comentario> lsComentarios = new ArrayList<>();
        Pessoa aluno = pessoaBLL.findById(pes_id);
        if (aluno != null) {
            List<Etapa> lsEtapas = etapaBLL.findbyTurma(aluno.getTurma());
            List<Projeto> lsProjetos = projetoBLL.findbyAluno(aluno);
            if (lsProjetos != null) {
                for (Projeto projeto : lsProjetos) {
                    for (Etapa etapa : lsEtapas) {
                        lsComentarios.addAll(comentarioBLL.findbyProjetoEtapa(projeto, etapa, true, true));
                    }
                }
            }
            return lsComentarios;
        }
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/date")
    public List<Comentario> getAllFromDate(Comentario comentario) {
        //  System.out.println("chegou no metodo date:" + date.toString());
        List<Comentario> lsComentario = new ArrayList<>();
        if (null != comentario && comentario.getCriacao() != null) {
            lsComentario = comentarioBLL.findbyDate(comentario.getCriacao());
        }
        return lsComentario;
        // return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/salvar")
    public int Salvar(Comentario comentario) {
        if (comentario != null && comentario.getDescricao() != null) {
            if (comentario.getId() > 0) {
                comentarioBLL.update(comentario);
            } else {
                comentarioBLL.insert(comentario);
            }
            return comentario.getId();
        }
        return 0;
    }

    @POST
    @Path("/excluir")
    public Boolean Excluir(Comentario com) {
        System.out.println("Vai deletar o Comentario!");
        if (com != null && com.getId() > 0) {
            Comentario comentario = comentarioBLL.findById(com.getId());
            comentarioBLL.delete(comentario);
            return true;
        }
        return false;
    }

//    public boolean Access() throws IOException {
//        try {
//            String authorization = servletRequest.getHeader("Authorization");
//            if (authorization != null && authorization.startsWith("Basic")) {
//                String base64Credentials = authorization.substring("Basic".length()).trim();
//                String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
//                String token = credentials.substring("Authorization:".length()).trim();
//                Login login = loginBLL.findToken(token);
//                System.out.println(" Authorization:" + authorization + " token:" + credentials);
//                if (login != null) {
//                    System.out.println("autorizado");
//                    return true;
//                }
//            }
//        } catch (Exception e) {
//        }
//        servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso não Autorizado");
//        System.out.println("Acesso não Autorizado");
//        return false;
//    }
}
