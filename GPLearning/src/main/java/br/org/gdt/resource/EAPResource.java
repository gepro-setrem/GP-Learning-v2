package br.org.gdt.resource;

import br.org.gdt.bll.EAPBLL;
import br.org.gdt.bll.LoginBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.EAP;
import br.org.gdt.model.Login;
import br.org.gdt.model.Projeto;
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

@Path("/eap")
public class EAPResource {

    @Autowired
    private EAPBLL eapBLL;
    @Autowired
    private ProjetoBLL projetoBLL;
    @Autowired
    private LoginBLL loginBLL;
    @Context
    private HttpServletRequest servletRequest;
    @Context
    private HttpServletResponse servletResponse;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/index/{pro_id}")
    public EAP getAll(@PathParam("pro_id") int pro_id) {
        if (Access()) {
            Projeto projeto = projetoBLL.findById(pro_id);
            if (projeto != null) {
                EAP eap = eapBLL.getEAP(projeto);
                if (eap == null) {
                    EAP model = new EAP();
                    model.setProjeto(projeto);
                    model.setNome(projeto.getNome());
                    eapBLL.insert(model);
                    eap = eapBLL.getEAP(projeto);
                }
                return eap;
            }
        }
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/salvar")
    public int Salvar(EAP model) {
        if (Access()) {
            if (model.getId() > 0) {
                eapBLL.update(model);
            } else {
                eapBLL.insert(model);
            }
            return model.getId();
        }
        return 0;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/excluir")
    public Boolean Excluir(@FormParam("eap_id") int eap_id) {
        if (Access()) {
            if (eap_id > 0) {
                eapBLL.delete(eap_id);
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
        servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

}
