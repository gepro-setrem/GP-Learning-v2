package br.org.gdt.beans;

import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.model.Pessoa;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class HomeBean {

    private Pessoa usuario = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;
    private String layoutTitle;
    private boolean hasSession;
    private StreamedContent imagem;

    public HomeBean() {

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

    public PessoaBLL getPessoaBLL() {
        return pessoaBLL;
    }

    public void setPessoaBLL(PessoaBLL pessoaBLL) {
        this.pessoaBLL = pessoaBLL;
    }

    public String getLayoutTitle() {
        layoutTitle = "Área do Professor";
        layoutTitle = "Grupos de Processos";
        return layoutTitle;
    }

    public void setLayoutTitle(String layoutTitle) {
        this.layoutTitle = layoutTitle;
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

    public StreamedContent getImagem() throws IOException {
        getUsuario();
        InputStream is = null;
        if (usuario.getImagem() != null) {
            is = new ByteArrayInputStream(usuario.getImagem());
            imagem = new DefaultStreamedContent(is, "", "" + usuario.getId());
        }
        return imagem;
    }

}
