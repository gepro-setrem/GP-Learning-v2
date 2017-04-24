package br.org.gdt.resource;

import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.Projeto;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/projeto")
public class ProjetoResource {

    @Autowired
    private ProjetoBLL projetoBLL;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/index/{pes_id}")
    public List<Projeto> getAll(@PathParam("pes_id") int pes_id) {
        Projeto projeto = projetoBLL.findById(pes_id);
        return null;
    }
}
