package br.org.gdt.beans;

import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Turma;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class ProjetoProfessorBean {

    private Projeto projeto;
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;
    private DataModel projetos;

    private Pessoa usuario;
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;

    @ManagedProperty("#{turmaBLL}")
    private TurmaBLL turmaBLL;
    private List<Turma> turmas;

    public ProjetoProfessorBean() {
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
        if (true) {//(usuariolocal.getLogin().getLoginRoles(). getGrupo().getId() == 1) {
            projetos = new ListDataModel(projetoBLL.findbyturmasprofessor(usuario));
        } else {
            projetos = new ListDataModel(projetoBLL.findMyProjects(usuario));
        }
        return projetos;
    }

    public void setProjetos(DataModel projetos) {
        this.projetos = projetos;
    }

    public Pessoa getUsuariologado() {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String email = request.getRemoteUser();
        usuario = pessoaBLL.findByEmail(email);
        return usuario;
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

    public TurmaBLL getTurmaBLL() {
        return turmaBLL;
    }

    public void setTurmaBLL(TurmaBLL turmaBLL) {
        this.turmaBLL = turmaBLL;
    }

    public List<Turma> getTurmas() {
        turmas = turmaBLL.findbyProfessor(usuario);
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

}
