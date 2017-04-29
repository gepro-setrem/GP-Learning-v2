package br.org.gdt.beans;

import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.enumerated.Role;
import br.org.gdt.enumerated.Status;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Turma;
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

    private Projeto projeto;
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;
    private DataModel projetos;

    private Pessoa usuario;
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;
    private List<Pessoa> usuarios;

    public ProjetoBean() {
    }

    public String novo() {
        usuario = getUsuario();
        if (usuario.getStatus().equals(Status.Inativo)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você consta como usuário inativo, não podendo fazer novas alterações no sistema!"));
            return "projetolst";
        } else {
            projeto = new Projeto();
            return "projetofrm";
        }
    }

    public String editar() {
        usuario = getUsuario();
        if (usuario.getStatus().equals(Status.Inativo)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você consta como usuário inativo, não podendo fazer novas alterações no sistema!"));
            return "produtolst";
        } else {
            projeto = (Projeto) projetos.getRowData();
            projeto = projetoBLL.findById(projeto.getId());
            return "produtofrm";
        }
    }

    public String acessar() {
        usuario = getUsuario();
        if (usuario.getStatus().equals(Status.Inativo)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você consta como usuário inativo, não podendo fazer novas alterações no sistema!"));
            return "produtolst";
        } else {
            projeto = (Projeto) projetos.getRowData();
            projeto = projetoBLL.findById(projeto.getId());
            if (projeto.getTermoabertura() == null) {
                return "ExplicacaoGerenciamentoProjeto";
            } else {
                return "ProcessosIniciacao";
            }
        }

    }

    public String salvar() {
        usuario = getUsuario();
        if (!projeto.getDescricao().equals("") && !projeto.getEmpresa().equals("") && !projeto.getNome().equals("")) {
            projeto.setTurma(usuario.getTurma());
            projeto.setAlteracao(new Date());
            if (projeto.getId() > 0) {
                projetoBLL.update(projeto);
            } else {
                projeto.setCriacao(new Date());
                projeto.setEstado("Iniciação");
                projetoBLL.insert(projeto);
            }
            return "produtolst";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário que todos os campos sejam preenchidos!"));
            return "TermoAberturaDescricao";
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
        usuarios = pessoaBLL.findbyTurmaUsers(turma, Role.user);
        return usuarios;
    }

    public void setUsuarios(List<Pessoa> usuarios) {
        this.usuarios = usuarios;
    }
}
