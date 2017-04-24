package br.org.gdt.resource;

import br.org.gdt.bll.EAPBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.TarefaBLL;
import br.org.gdt.model.EAP;
import br.org.gdt.model.Projeto;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/index/{pro_id}")
    public EAP getAll(@PathParam("pro_id") int pro_id) {
        Projeto projeto = projetoBLL.findById(pro_id);
        if (projeto != null) {
            EAP eap = tarefaBLL.findbyEAP(projeto);
            return eap;
        }
        return null;
    }
}
