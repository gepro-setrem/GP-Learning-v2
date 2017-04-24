package br.org.gdt.resource;

import br.org.gdt.bll.LoginBLL;
import br.org.gdt.model.Login;
import br.org.gdt.model.LoginRole;
import br.org.gdt.model.Pessoa;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/login")
public class LoginResource {

    @Autowired
    private LoginBLL loginBLL;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login/{email}/{senha}")
    public Pessoa login(@PathParam("email") String email, @PathParam("senha") String senha) {
        Pessoa user = loginBLL.findLogin(email, senha);
        return user;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Pessoa getLogin(@FormParam("email") String email, @FormParam("senha") String senha) {
        Pessoa user = loginBLL.findLogin(email, senha);
        return user;
    }
}
