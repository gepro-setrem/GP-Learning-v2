package br.org.gdt.beans;

import br.org.gdt.bll.EtapaBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.TermoAberturaBLL;
import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.enumerated.EtapaProjeto;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.TermoAbertura;
import br.org.gdt.model.Turma;
import br.org.gdt.model.EAP;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@SessionScoped
public class ProjetoProfessorBean {

    private Projeto projeto = new Projeto();
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;
    private DataModel projetos;

    private Pessoa usuario = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;

    @ManagedProperty("#{turmaBLL}")
    private TurmaBLL turmaBLL;
    private List<Turma> turmas;

    @ManagedProperty("#{etapaBLL}")
    private EtapaBLL etapaBLL;
    private List<Etapa> etapas;

    private EtapaProjeto[] etapaProjetos;

    @ManagedProperty("#{termoAberturaBLL}")
    private TermoAberturaBLL termoAberturaBLL;
    private TermoAbertura termoabertura = new TermoAbertura();

    public ProjetoProfessorBean() {
    }

    public String avaliar() {
        projeto = (Projeto) projetos.getRowData();
        projeto = projetoBLL.findProjetoCompleto(projeto.getId());
        termoabertura = termoAberturaBLL.findByProjetoCompleto(projeto);
        return "avaliacao";
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public ProjetoBLL getProjetoBLL() {
        return projetoBLL;
    }

    public void setProjetoBLL(ProjetoBLL projetoBLL) {
        this.projetoBLL = projetoBLL;
    }

    public DataModel getProjetos() {
        getUsuario();
        if (projetos == null) {
            projetos = new ListDataModel(projetoBLL.findbyProfessor(usuario));
        }
        return projetos;
    }

    public void setProjetos(DataModel projetos) {
        this.projetos = projetos;
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

    public TurmaBLL getTurmaBLL() {
        return turmaBLL;
    }

    public void setTurmaBLL(TurmaBLL turmaBLL) {
        this.turmaBLL = turmaBLL;
    }

    public List<Turma> getTurmas() {
        getUsuario();
        if (turmas == null) {
            turmas = turmaBLL.findbyProfessor(usuario);
        }
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public EtapaProjeto[] getEtapaProjetos() {
        this.etapaProjetos = EtapaProjeto.values();
        return this.etapaProjetos;
    }

    public void setEtapaProjetos(EtapaProjeto[] etapaProjetos) {
        this.etapaProjetos = etapaProjetos;
    }

    public EtapaBLL getEtapaBLL() {
        return etapaBLL;
    }

    public void setEtapaBLL(EtapaBLL etapaBLL) {
        this.etapaBLL = etapaBLL;
    }

    public List<Etapa> getEtapas() {
        if (projeto != null && projeto.getTurma() != null && projeto.getTurma().getId() > 0) {
            Turma turma = turmaBLL.findById(projeto.getTurma().getId());
            etapas = etapaBLL.findbyTurma(turma);
        }
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }

    public TermoAberturaBLL getTermoAberturaBLL() {
        return termoAberturaBLL;
    }

    public void setTermoAberturaBLL(TermoAberturaBLL termoAberturaBLL) {
        this.termoAberturaBLL = termoAberturaBLL;
    }

    public TermoAbertura getTermoabertura() {
        return termoabertura;
    }

    public void setTermoabertura(TermoAbertura termoabertura) {
        this.termoabertura = termoabertura;
    }

    public String getEAP() {
        if (projeto != null && projeto.getEaps() != null && projeto.getEaps().size() > 0) {
            EAP eap = projeto.getEaps().get(0);
            String html = getNode(eap, "1", 1);
            html = "<ul class=\"eap_pai\">" + html + "</ul>";
            return html;
        }
        return null;
    }

    private String getNode(EAP eap, String number, int vez) {
        String html = "";
        if (eap != null) {
            String label = number + " - " + eap.getNome();
            html += "<li class=\"eap_item " + (vez == 2 ? "eap_column" : "") + "\"><div class=\"eap\"><div class=\"eap_nome\">" + label + "</div></div><ul class=\"eap_pai\">";
            vez++;
            if (eap.getEaps() != null) {
                int i = 1;
                for (EAP child : eap.getEaps()) {
                    html += getNode(child, number + "." + i, vez);
                    i++;
                }
            }
            html += "</ul></li>";
        }
        return html;
    }

}
