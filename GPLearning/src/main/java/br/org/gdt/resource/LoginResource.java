package br.org.gdt.resource;

import br.org.gdt.bll.LoginBLL;
import br.org.gdt.model.Login;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/login")
public class LoginResource {

    @Autowired
    private LoginBLL bll;

    @GET
    @Produces("application/json")
    public List<Login> getAll() {
        return bll.findAll();
    }

    @POST
    @Produces("application/json")
    @Path("/login")
    public Boolean getLogin(String email, String senha) {
        Login login = new Login(email, senha);
        return bll.findLogin(login) != null;
    }

    public LoginBLL getBll() {
        return bll;
    }

    public void setBll(LoginBLL bll) {
        this.bll = bll;
    }

    @Produces("application/json")
    @Path("/login2")
    public List<Login> getLogin2() {
        return bll.findAll();
    }
}
