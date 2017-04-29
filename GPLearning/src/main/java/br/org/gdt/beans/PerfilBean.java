package br.org.gdt.beans;

import br.org.gdt.bll.LoginBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.model.Login;
import br.org.gdt.model.LoginRole;
import br.org.gdt.model.Pessoa;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

@ManagedBean
@RequestScoped
public class PerfilBean implements Serializable {

    private Pessoa usuario = new Pessoa();
    private Login login = new Login();
    private Pessoa user = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;
    private UploadedFile userImage;

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
        int user_id = usuario.getId();
        if (!usuario.getNome().isEmpty() && !usuario.getEmail().isEmpty() && usuario.getId() > 0) {// && !usuario.getTurma().equals("")) {
            byte[] imgDataBa = new byte[(int) userImage.getSize()];
            DataInputStream dataIs;
            try {
                dataIs = new DataInputStream(userImage.getInputstream());
                dataIs.readFully(imgDataBa);
            } catch (IOException ex) {
                Logger.getLogger(Projeto2Bean.class.getName()).log(Level.SEVERE, null, ex);
            }
            Pessoa oldUser = pessoaBLL.findById(user_id);
            Login oldLogin = oldUser.getLogin();
            oldLogin.setEmail(usuario.getEmail());
            oldLogin.setSenha(login.getSenha());
            usuario.setTurma(oldUser.getTurma());
            usuario.setStatus(oldUser.getStatus());
            usuario.setCriacao(oldUser.getCriacao());
            if (imgDataBa != null && imgDataBa.length > 0) {
                usuario.setImagem(imgDataBa);
            } else {
                usuario.setImagem(oldUser.getImagem());
            }
            usuario.setAlteracao(new Date());

            usuario.setLogin(oldLogin);
            pessoaBLL.update(usuario);
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
}
