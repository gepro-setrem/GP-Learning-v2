package br.org.gdt.resource;

import br.org.gdt.bll.EAPBLL;
import br.org.gdt.model.EAP;
import br.org.gdt.model.Projeto;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/eap")
public class EAPResource {

    @Autowired
    private EAPBLL bll;

    @GET
    @Produces("application/json")
    @Path("/index")
    public List<EAP> getAll() {
//        Projeto projeto = new Projeto();
//        projeto.setId(pro_id);
//        return bll.findbyEAP(projeto);
        return bll.findAll();
    }

    @POST
    @Produces("application/json")
    @Path("/salvar")
    public int Salvar(EAP model) {
        if (model.getId() > 0) {
            bll.update(model);
        } else {
            bll.insert(model);
        }
        return model.getId();
    }

}
