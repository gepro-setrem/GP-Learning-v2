package br.org.gdt.beans;

import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.TermoAbertura;
import br.org.gdt.bll.TermoAberturaBLL;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class TermoAberturaBean {

    private static final String viewDescricao = "termoaberturadescricao";
    private static final String viewJustificativa = "termoaberturajustificativa";
    private static final String viewPremissas = "termoaberturapremissas";

    private Projeto projeto = new Projeto();
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;

    private Pessoa usuario = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;

    private TermoAbertura termoabertura = new TermoAbertura();
    @ManagedProperty("#{termoAberturaBLL}")
    private TermoAberturaBLL termoAberturaBLL;

    public TermoAberturaBean() {
    }

    public String salvarDescricao() {
        if (!termoabertura.getDescricao().isEmpty()) {
            termoabertura.setAlteracao(new Date());
            termoabertura.setProjeto(projeto);
            if (termoabertura.getId() > 0) {
                TermoAbertura taOld = termoAberturaBLL.findById(termoabertura.getId());
                termoabertura.setJustificativa(taOld.getJustificativa());
                termoAberturaBLL.update(termoabertura);
            } else {
                termoabertura.setCriacao(new Date());
                termoAberturaBLL.insert(termoabertura);
            }
            return viewJustificativa;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para Seguir, você deve preencher a descrição do projeto!"));
            return viewDescricao;
        }
    }

    public String salvarJustificativa() {
        if (!termoabertura.getJustificativa().isEmpty()) {
            termoabertura.setAlteracao(new Date());
            termoabertura.setProjeto(projeto);
            if (termoabertura.getId() > 0) {
                TermoAbertura taOld = termoAberturaBLL.findById(termoabertura.getId());
                termoabertura.setDescricao(taOld.getDescricao());
                termoAberturaBLL.update(termoabertura);
            } else {
                termoabertura.setCriacao(new Date());
                termoAberturaBLL.insert(termoabertura);
            }

            return viewPremissas;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para Seguir, você deve preencher a justificativa do projeto!"));
            return viewJustificativa;
        }
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        int ta_id = 0;
        if (projeto != null && projeto.getTermoabertura() != null) {
            ta_id = projeto.getTermoabertura().getId();
        }
        if (ta_id > 0) {
            termoabertura = termoAberturaBLL.findById(ta_id);
        } else {
            termoabertura = new TermoAbertura();
        }
        this.projeto = projeto;
    }

    public ProjetoBLL getProjetoBLL() {
        return projetoBLL;
    }

    public void setProjetoBLL(ProjetoBLL projetoBLL) {
        this.projetoBLL = projetoBLL;
    }

    public TermoAbertura getTermoabertura() {
        return termoabertura;
    }

    public void setTermoabertura(TermoAbertura termoabertura) {
        this.termoabertura = termoabertura;
    }

    public TermoAberturaBLL getTermoAberturaBLL() {
        return termoAberturaBLL;
    }

    public void setTermoAberturaBLL(TermoAberturaBLL termoAberturaBLL) {
        this.termoAberturaBLL = termoAberturaBLL;
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

}
