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
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class PerfilBean implements Serializable {

    private Pessoa usuario = new Pessoa();

    private Pessoa user = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;
    private UploadedFile userImage;
    @ManagedProperty("#{loginBLL}")
    private LoginBLL loginBLL;
    private Login login = new Login();
    private boolean hasSession;

    public PerfilBean() {
    }

    public String detalhe() {
        return "perfil";
    }

    public String editar() {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
        user = getUsuario();
        login = user.getLogin();
        return "perfilfrm";
    }

    public String salvar() {
        getUsuario();
        int user_id = usuario.getId();
        if (!user.getNome().isEmpty() && !user.getEmail().isEmpty()) {
            byte[] imgDataBa = new byte[(int) userImage.getSize()];
            DataInputStream dataIs;
            try {
                dataIs = new DataInputStream(userImage.getInputstream());
                dataIs.readFully(imgDataBa);
            } catch (IOException ex) {
                Logger.getLogger(PerfilBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            login.setEmail(user.getEmail());
            Pessoa oldUser = pessoaBLL.findById(user_id);
            boolean changeEmail = !user.getEmail().equals(oldUser.getEmail());
            user.setId(oldUser.getId());
            user.setTurma(oldUser.getTurma());
            user.setStatus(oldUser.getStatus());
            user.setCriacao(oldUser.getCriacao());
            if (imgDataBa != null && imgDataBa.length > 0) {
                user.setImagem(imgDataBa);
            } else {
                user.setImagem(oldUser.getImagem());
            }
            user.setAlteracao(new Date());
//            user.setLogin(null);
            pessoaBLL.update(user);
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
            usuario = null;
            return "perfil";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
            return "perfilfrm";
        }
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
        return this.user;
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

    public Pessoa getUsuario() {
        if (usuario == null || usuario.getId() == 0) {
            ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) external.getRequest();
            String email = request.getRemoteUser();
            usuario = pessoaBLL.findbyEmail(email);
        }
        return usuario;
    }

    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
    }

    public boolean isHasSession() {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String email = request.getRemoteUser();
        hasSession = email != null && !email.isEmpty();
        return hasSession;
    }

    public void setHasSession(boolean hasSession) {
        this.hasSession = hasSession;
    }

}
