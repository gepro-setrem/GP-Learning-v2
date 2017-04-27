package br.org.gdt.beans;

import br.org.gdt.bll.LoginBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.model.Pessoa;
import java.io.DataInputStream;
import java.io.IOException;
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
public class PerfilBean {

    private Pessoa usuario;
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
        return "perfilfrm";
    }

    public String salvar() {
        if (!usuario.getNome().isEmpty() && !usuario.getEmail().isEmpty() && usuario.getId() > 0) {// && !usuario.getTurma().equals("")) {
            byte[] imgDataBa = new byte[(int) userImage.getSize()];
            DataInputStream dataIs;
            try {
                dataIs = new DataInputStream(userImage.getInputstream());
                dataIs.readFully(imgDataBa);
            } catch (IOException ex) {
                Logger.getLogger(ProjetoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setImagem(imgDataBa);
            usuario.setAlteracao(new Date());
            pessoaBLL.update(usuario);
            return "perfil";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
            return "perfilfrm";
        }
    }

    public Pessoa getUsuario() {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String email = request.getRemoteUser();
        usuario = pessoaBLL.findbyEmail(email);
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
}
