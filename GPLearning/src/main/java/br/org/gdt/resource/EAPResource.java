package br.org.gdt.resource;

import br.org.gdt.bll.EAPBLL;
import br.org.gdt.model.EAP;
import br.org.gdt.model.Projeto;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/eap")
public class EAPResource {

    @Autowired
    private EAPBLL eapBLL;

    @GET
    @Produces("application/json")
    @Path("/index/{pro_id}")
    public List<EAP> getAll(@PathParam("pro_id") int pro_id) {
        Projeto projeto = new Projeto();
        projeto.setId(pro_id);
        List<EAP> ls = eapBLL.findbyEAP(projeto);
        return ls;
    }

    @POST
    @Produces("application/json")
    @Path("/salvar")
    public int Salvar(EAP model) {
        if (model.getId() > 0) {
            EAP eap = eapBLL.findById(model.getId());
            eap.setNome(model.getNome());
            eap.setDescricao(model.getDescricao());
            eap.setInicio(model.getInicio());
            eap.setTermino(model.getTermino());
            eap.setValor(model.getValor());
            eapBLL.update(eap);
        } else {
            eapBLL.insert(model);
        }
        return model.getId();
    }

    @POST
    @Produces("application/json")
    @Path("/excluir")
    public Boolean Excluir(@PathParam("eap_id") int eap_id) {
        eapBLL.delete(eap_id);
        return true;
    }

}
