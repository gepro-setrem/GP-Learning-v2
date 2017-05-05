package br.org.gdt.beans;

import br.org.gdt.bll.LoginBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.model.Login;
import br.org.gdt.model.LoginRole;
import br.org.gdt.model.Pessoa;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class PerfilBean implements Serializable {

    private Pessoa usuario = new Pessoa();

    private Pessoa user = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;
    private UploadedFile userImage;
    @ManagedProperty("#{loginBLL}")
    private LoginBLL loginBLL;
    private Login login = new Login();

    public PerfilBean() {
    }

    public String detalhe() {
        return "perfil";
    }

    public String editar() {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
        usuario = getUser();
        login = usuario.getLogin();
        return "perfilfrm";
    }

    public String salvar() {
        getUser();
        int user_id = user.getId();
        if (!usuario.getNome().isEmpty() && !usuario.getEmail().isEmpty()) {
            byte[] imgDataBa = new byte[(int) userImage.getSize()];
            DataInputStream dataIs;
            try {
                dataIs = new DataInputStream(userImage.getInputstream());
                dataIs.readFully(imgDataBa);
            } catch (IOException ex) {
                Logger.getLogger(Projeto2Bean.class.getName()).log(Level.SEVERE, null, ex);
            }
            login.setEmail(usuario.getEmail());
            Pessoa oldUser = pessoaBLL.findById(user_id);
            boolean changeEmail = !usuario.getEmail().equals(oldUser.getEmail());
            usuario.setId(oldUser.getId());
            usuario.setTurma(oldUser.getTurma());
            usuario.setStatus(oldUser.getStatus());
            usuario.setCriacao(oldUser.getCriacao());
            if (imgDataBa != null && imgDataBa.length > 0) {
                usuario.setImagem(imgDataBa);
            } else {
                usuario.setImagem(oldUser.getImagem());
            }
            usuario.setAlteracao(new Date());
//            usuario.setLogin(null);
            pessoaBLL.update(usuario);
            if (!login.getSenha().isEmpty() || changeEmail) {
                Login oldLogin = loginBLL.findbyPessoa(oldUser);
                if (changeEmail) {
                    loginBLL.delete(oldLogin);
                    oldLogin.setEmail(login.getEmail());
                }
                if (!login.getSenha().isEmpty()) {
                    oldLogin.setSenha(login.getSenha());
                }
                for (LoginRole lr : oldLogin.getLoginRoles()) {
                    lr.setLogin(oldLogin);
                }
                loginBLL.update(oldLogin);
            }
            if (changeEmail) {
                ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) external.getRequest();
                request.getSession().invalidate();
            }
            return "perfil";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
            return "perfilfrm";
        }
    }

    public Pessoa getUsuario() {
        return usuario;
    }

    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
    }

    public PessoaBLL getPessoaBLL() {
        return pessoaBLL;
    }

    public void setPessoaBLL(PessoaBLL pessoaBLL) {
        this.pessoaBLL = pessoaBLL;
    }

    public UploadedFile getUserImage() {
        return userImage;
    }

    public void setUserImage(UploadedFile userImage) {
        this.userImage = userImage;
    }

    public Pessoa getUser() {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String email = request.getRemoteUser();
        user = pessoaBLL.findbyEmail(email);
        return user;
    }

    public void setUser(Pessoa user) {
        this.user = user;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public LoginBLL getLoginBLL() {
        return loginBLL;
    }

    public void setLoginBLL(LoginBLL loginBLL) {
        this.loginBLL = loginBLL;
    }

}
