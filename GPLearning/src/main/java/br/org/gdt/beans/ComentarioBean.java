package br.org.gdt.beans;

import br.org.gdt.bll.ComentarioBLL;
import br.org.gdt.bll.EtapaBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.enumerated.EtapaProjeto;
import br.org.gdt.model.Comentario;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class ComentarioBean {

    private Pessoa usuario = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;

    @ManagedProperty("#{comentarioBLL}")
    private ComentarioBLL comentarioBLL;
    private Comentario comentario = new Comentario();

    @ManagedProperty("#{etapaBLL}")
    private EtapaBLL etapaBLL;
    private Etapa etapa = new Etapa();

    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;
    private Projeto projeto = new Projeto();

    public ComentarioBean() {
    }

    public void salvar() {
        if (comentario != null && etapa != null) {
            if (comentario.getDescricao() != null && !comentario.getDescricao().isEmpty()) {
                comentario.setRemetente(usuario);
                comentario.setCriacao(new Date());
                comentario.setEtapa(etapa);
                comentarioBLL.insert(comentario);
                atualizar();
                comentario.setDescricao("");
                comentario.setId(0);
            }
        }
    }

    public void excluir(Comentario comentario) {
        if (comentario.getId() > 0) {
            comentarioBLL.delete(comentario.getId());
        }
        atualizar();
    }

    public void atualizar() {
        if (etapa != null) {
            List<Comentario> lsComentario = comentarioBLL.findbyEtapa(etapa, true);
            etapa.setComentarios(lsComentario);
        }
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

    public ComentarioBLL getComentarioBLL() {
        return comentarioBLL;
    }

    public void setComentarioBLL(ComentarioBLL comentarioBLL) {
        this.comentarioBLL = comentarioBLL;
    }

    public EtapaBLL getEtapaBLL() {
        return etapaBLL;
    }

    public void setEtapaBLL(EtapaBLL etapaBLL) {
        this.etapaBLL = etapaBLL;
    }

    public ProjetoBLL getProjetoBLL() {
        return projetoBLL;
    }

    public void setProjetoBLL(ProjetoBLL projetoBLL) {
        this.projetoBLL = projetoBLL;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Etapa getEtapa() {
        return etapa;
    }

//    public void setEtapa(Etapa etapa) {
//        this.etapa = etapa;
//    }
    public void setEtapa(EtapaProjeto etapaProjeto) {
        //EtapaProjeto etType = EtapaProjeto.valueOf(type);
        if (projeto != null && etapaProjeto != null) {
            etapa = etapaBLL.findbyTurmaEtapa(projeto.getTurma(), etapaProjeto);
            atualizar();
        }
    }

    public StreamedContent getImagem() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Get ID value from actual request param.
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            int id2 = Integer.valueOf(id);
            byte[] imagem = pessoaBLL.findbyImagem(id2);
            ByteArrayInputStream bais = new ByteArrayInputStream(imagem);
            return new DefaultStreamedContent(bais);
        }
    }
}
