package br.org.gdt.beans;

import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.TurmaParametroBLL;
import br.org.gdt.enumerated.Role;
import br.org.gdt.enumerated.Status;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.TermoAbertura;
import br.org.gdt.model.Turma;
import br.org.gdt.model.TurmaParametro;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
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
public class ProjetoBean {

    private Projeto projeto = new Projeto();
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;
    private DataModel projetos;

    private Pessoa usuario = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;
    private List<Pessoa> usuarios;

    @ManagedProperty("#{turmaParametroBLL}")
    private TurmaParametroBLL turmaParametroBLL;

    public ProjetoBean() {
    }

    public String novo() {
        getUsuario();
        if (usuario.getStatus().equals(Status.Inativo)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você consta como usuário inativo, não podendo fazer novas alterações no sistema!"));
            return "projetolst";
        } else {
            projeto = new Projeto();
            projeto.setGerente(usuario);
            List<Pessoa> lsCom = new ArrayList<>();
            lsCom.add(usuario);
            projeto.setComponentes(lsCom);
            return "projetofrm";
        }
    }

    public String editar() {
        getUsuario();
        if (usuario.getStatus().equals(Status.Inativo)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você consta como usuário inativo, não podendo fazer novas alterações no sistema!"));
            return "projetolst";
        } else {
            projeto = (Projeto) projetos.getRowData();
            projeto = projetoBLL.findById(projeto.getId());
            projeto.setComponentes(pessoaBLL.findbyProjeto(projeto));
            if (projeto.getGerente() == null) {
                projeto.setGerente(usuario);
            }
            return "projetofrm";
        }
    }

    public String acessar() {
        getUsuario();
        if (usuario.getStatus().equals(Status.Inativo)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você consta como usuário inativo, não podendo fazer novas alterações no sistema!"));
            return "projetolst";
        } else {
            projeto = (Projeto) projetos.getRowData();
            projeto = projetoBLL.findById(projeto.getId());
            if (projeto.getTermoabertura() == null) {
                return "inicioprojeto";
            } else {
                return "iniciacao";
            }
        }
    }

    public String salvar() {
        getUsuario();
        if (!projeto.getDescricao().isEmpty() && !projeto.getEmpresa().isEmpty() && !projeto.getNome().isEmpty()
                && projeto.getGerente() != null && projeto.getGerente().getId() > 0
                && projeto.getComponentes() != null && projeto.getComponentes().size() > 0) {
            projeto.setTurma(usuario.getTurma());
            projeto.setAlteracao(new Date());
            if (projeto.getId() > 0) {
                Projeto oldProjeto = projetoBLL.findById(projeto.getId());
                projeto.setEstado(oldProjeto.getEstado());
                projeto.setEscopo(oldProjeto.getEscopo());
                projeto.setPlanoEscopo(oldProjeto.getPlanoEscopo());
                projeto.setPlanoProjeto(oldProjeto.getPlanoProjeto());
                projetoBLL.update(projeto);
            } else {
                projeto.setCriacao(new Date());
                projeto.setEstado("Iniciação");
                projetoBLL.insert(projeto);
            }
            return "projetolst";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário que todos os campos sejam preenchidos!"));
            return "projetofrm";
        }
    }

    public String salvarPlanoEscopo() {
        if (!projeto.getPlanoEscopo().isEmpty()) {
            projeto.setAlteracao(new Date());
            projeto.setEstado("Planejamento");
            projetoBLL.update(projeto);
            return "planejamento";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para seguir, é necessário descrever o plano de gerenciamento de escopo"));
            return "planoescopo";
        }
    }

    public String salvarEscopo() {
        if (!projeto.getEscopo().isEmpty()) {
            projeto.setAlteracao(new Date());
            projeto.setEstado("Planejamento");
            projetoBLL.update(projeto);
            return "planejamento";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para seguir, é necessário descrever o escopo do projeto"));
            return "escopo";
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

    public List<Pessoa> completePessoa(String query) {
        Turma turma = usuario.getTurma();
        usuarios = pessoaBLL.findbyTurmaUsers(turma, Role.user, query);
        return usuarios;
    }

    public DataModel getProjetos() {
        usuario = getUsuario();
        List<Projeto> lsProjeto = projetoBLL.findbyAluno(usuario);
        projetos = new ListDataModel(lsProjeto);
        return projetos;
    }

    public void setProjetos(DataModel projetos) {
        this.projetos = projetos;
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

    public PessoaBLL getPessoaBLL() {
        return pessoaBLL;
    }

    public void setPessoaBLL(PessoaBLL pessoaBLL) {
        this.pessoaBLL = pessoaBLL;
    }

    public List<Pessoa> getUsuarios() {
        Turma turma = usuario.getTurma();
        usuarios = pessoaBLL.findbyTurmaUsers(turma, Role.user, "");
        return usuarios;
    }

    public void setUsuarios(List<Pessoa> usuarios) {
        this.usuarios = usuarios;
    }

    public TurmaParametroBLL getTurmaParametroBLL() {
        return turmaParametroBLL;
    }

    public void setTurmaParametroBLL(TurmaParametroBLL turmaParametroBLL) {
        this.turmaParametroBLL = turmaParametroBLL;
    }

    public String getDescricao(String chave) {
        //getUsuario();
        Turma turma = usuario.getTurma();
        TurmaParametro tp = turmaParametroBLL.getParametro(turma, chave);
        if (tp != null) {
            return tp.getValor();
        }
        return "";
    }

}
