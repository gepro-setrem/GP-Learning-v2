package br.org.gdt.beans;

import br.org.gdt.bll.AvaliacaoBLL;
import br.org.gdt.bll.IndicadorBLL;
import br.org.gdt.model.Login;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import br.org.gdt.bll.LoginBLL;
import br.org.gdt.bll.LoginRoleBLL;
import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.enumerated.Role;
import br.org.gdt.enumerated.Status;
import br.org.gdt.model.Avaliacao;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.LoginRole;
import br.org.gdt.model.Projeto;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class UsuarioBean {

    private Pessoa usuario = new Pessoa();
    private Pessoa pessoa = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;
    private DataModel usuarios;

    @ManagedProperty("#{turmaBLL}")
    private TurmaBLL turmaBLL;
    private List<Turma> turmas;

    private Login login = new Login();
    @ManagedProperty("#{loginBLL}")
    private LoginBLL loginBLL;

    @ManagedProperty("#{loginRoleBLL}")
    private LoginRoleBLL loginRoleBLL;

    @ManagedProperty("#{projetoBLL}")
    public ProjetoBLL projetoBLL;
    public List<Projeto> projetos;

    @ManagedProperty("#{indicadorBLL}")
    public IndicadorBLL indicadorBLL;

    @ManagedProperty("#{avaliacaoBLL}")
    public AvaliacaoBLL avaliacaoBLL;

    private Status[] status;
    private Role[] roles;
    private Role role;
    private UploadedFile userImage;

    private double pontuacao;
    private int nivel;

    public UsuarioBean() {
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

    public DataModel getUsuarios() {
        getUsuario();
        if (usuarios == null) {
            usuarios = new ListDataModel(pessoaBLL.findbyProfessor(usuario));
        }
        return usuarios;
    }

    public void setUsuarios(DataModel usuarios) {
        this.usuarios = usuarios;
    }

    public List<Turma> getTurmas() {
        getUsuario();
        turmas = turmaBLL.findbyProfessor(usuario);
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public String salvar() {
        if (!pessoa.getNome().isEmpty() && !pessoa.getEmail().isEmpty() && pessoa.getTurma() != null && pessoa.getTurma().getId() > 0) {
            byte[] imgDataBa = new byte[(int) userImage.getSize()];
            DataInputStream dataIs;
            try {
                dataIs = new DataInputStream(userImage.getInputstream());
                dataIs.readFully(imgDataBa);
            } catch (IOException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            login.setEmail(pessoa.getEmail());
            LoginRole loginRole = new LoginRole();
            loginRole.setLogin(login);
            loginRole.setRole(role);
            List<LoginRole> lsLoginRole = new ArrayList<>();
            lsLoginRole.add(loginRole);
            login.setLoginRoles(lsLoginRole);
            pessoa.setAlteracao(new Date());
            pessoa.setImagem(imgDataBa);

            int user_id = pessoa.getId();
            if (user_id > 0) {
                Pessoa oldUser = pessoaBLL.findById(user_id);
                if (!(imgDataBa != null && imgDataBa.length > 0)) {
                    pessoa.setImagem(oldUser.getImagem());
                }
                pessoa.setCriacao(oldUser.getCriacao());
                pessoaBLL.update(pessoa);
            } else {
                pessoa.setCriacao(new Date());
                pessoaBLL.insert(pessoa);
                login.setPessoa(pessoa);
                login.setToken(loginBLL.newToken());
                loginBLL.insert(login);
                loginRoleBLL.insert(loginRole);
            }
            usuarios = null;
            return "usuariolst";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
            return "usuariofrm";
        }
    }

    public String perfil() {
        pessoa = (Pessoa) usuarios.getRowData();
        pessoa = pessoaBLL.findById(pessoa.getId());
        return "usuariodtl";
    }

    public String editar() {
        pessoa = (Pessoa) usuarios.getRowData();
        pessoa = pessoaBLL.findById(pessoa.getId());
        login = loginBLL.findbyPessoa(pessoa);
        if (login == null) {
            login = new Login();
        }
        if (login.getLoginRoles() != null && login.getLoginRoles().size() > 0) {
            role = login.getLoginRoles().get(0).getRole();
        } else {
            role = getRoles()[0];
        }
        return "usuariofrm";
    }

    public String inativar() {
        FacesContext context = FacesContext.getCurrentInstance();
        pessoa = (Pessoa) usuarios.getRowData();
        pessoa = pessoaBLL.findById(pessoa.getId());
        pessoa.setStatus(Status.Inativo);
        pessoaBLL.update(pessoa);
        context.addMessage(null, new FacesMessage("Sucesso", "Usuário tornou-se inativo e não poderá criar novos projetos"));
        usuarios = null;
        return "usuariolst";
    }

    public String novo() {
        pessoa = new Pessoa();
        login = new Login();
        return "usuariofrm";
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

    public Status[] getStatus() {
        return Status.values();
    }

    public void setStatus(Status[] status) {
        this.status = status;
    }

    public Role[] getRoles() {
        return Role.values();
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public UploadedFile getUserImage() {
        return userImage;
    }

    public void setUserImage(UploadedFile userImage) {
        this.userImage = userImage;
    }

    public LoginRoleBLL getLoginRoleBLL() {
        return loginRoleBLL;
    }

    public void setLoginRoleBLL(LoginRoleBLL loginRoleBLL) {
        this.loginRoleBLL = loginRoleBLL;
    }

    public List<Projeto> getProjetos() {
        projetos = projetoBLL.findbyAluno(pessoa);
        List<Projeto> lsProjeto = new ArrayList<>();
        Turma turma = turmaBLL.findById(usuario.getTurma().getId());
        List<Indicador> lsIndicador = indicadorBLL.findbyProfessor(turma.getProfessor());
        for (Projeto projeto : projetos) {
            List<Avaliacao> avaliacoes = new ArrayList<>();
            for (Indicador indicador : lsIndicador) {
                List<Avaliacao> lsAvaliacao = avaliacaoBLL.findbyProjetoIndicador(projeto, indicador);
                int valor = 0;
                for (Avaliacao avaliacao : lsAvaliacao) {
                    valor += avaliacao.getValor();
                }
                if (lsAvaliacao.size() > 0) {
                    valor = valor / lsAvaliacao.size();
                    Avaliacao ava = new Avaliacao();
                    ava.setIndicador(indicador);
                    ava.setValor(valor);
                    avaliacoes.add(ava);
                }
            }
            if (avaliacoes.size() > 0) {
                projeto.setAvaliacoes(avaliacoes);
                lsProjeto.add(projeto);
            }
        }
        projetos = lsProjeto;
        return projetos;
    }

    public ProjetoBLL getProjetoBLL() {
        return projetoBLL;
    }

    public void setProjetoBLL(ProjetoBLL projetoBLL) {
        this.projetoBLL = projetoBLL;
    }

    public IndicadorBLL getIndicadorBLL() {
        return indicadorBLL;
    }

    public void setIndicadorBLL(IndicadorBLL indicadorBLL) {
        this.indicadorBLL = indicadorBLL;
    }

    public AvaliacaoBLL getAvaliacaoBLL() {
        return avaliacaoBLL;
    }

    public void setAvaliacaoBLL(AvaliacaoBLL avaliacaoBLL) {
        this.avaliacaoBLL = avaliacaoBLL;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public int getNivel() {
        pontuacao = pessoaBLL.findPontuacao(pessoa);
        nivel = pessoaBLL.findNivel(pontuacao);
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double getPontuacao() {
        pontuacao = pessoaBLL.findPontuacao(pessoa);
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }
}
