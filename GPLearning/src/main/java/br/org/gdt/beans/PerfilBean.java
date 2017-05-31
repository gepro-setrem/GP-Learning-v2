package br.org.gdt.beans;

import br.org.gdt.bll.AvaliacaoBLL;
import br.org.gdt.bll.EtapaBLL;
import br.org.gdt.bll.IndicadorBLL;
import br.org.gdt.bll.LoginBLL;
import br.org.gdt.bll.LoginRoleBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.model.Avaliacao;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.Login;
import br.org.gdt.model.LoginRole;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Turma;
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
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class PerfilBean {

    private Pessoa usuario = new Pessoa();
    private Pessoa user = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;
    private UploadedFile userImage;
    @ManagedProperty("#{loginBLL}")
    private LoginBLL loginBLL;
    private Login login = new Login();
    private boolean hasSession;
    @ManagedProperty("#{loginRoleBLL}")
    private LoginRoleBLL loginRoleBLL;
    private String token;

    private int nivel = 0;

    @ManagedProperty("#{projetoBLL}")
    public ProjetoBLL projetoBLL;
    public List<Projeto> projetos;

    @ManagedProperty("#{avaliacaoBLL}")
    public AvaliacaoBLL avaliacaoBLL;

    @ManagedProperty("#{etapaBLL}")
    public EtapaBLL etapaBLL;

    @ManagedProperty("#{indicadorBLL}")
    public IndicadorBLL indicadorBLL;

    @ManagedProperty("#{turmaBLL}")
    public TurmaBLL turmaBLL;

    public PerfilBean() {
    }

    public String detalhe() {
        return "perfil";
    }

    public String editar() {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
        user = getUsuario();
        login = user.getLogin();
        return "perfilfrm";
    }

    public String salvar() {
        getUsuario();
        int user_id = usuario.getId();
        if (!user.getNome().isEmpty() && !user.getEmail().isEmpty()) {
            byte[] imgDataBa = new byte[(int) userImage.getSize()];
            DataInputStream dataIs;
            try {
                dataIs = new DataInputStream(userImage.getInputstream());
                dataIs.readFully(imgDataBa);
            } catch (IOException ex) {
                Logger.getLogger(PerfilBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            login.setEmail(user.getEmail());
            Pessoa oldUser = pessoaBLL.findById(user_id);
            boolean changeEmail = !user.getEmail().equals(oldUser.getEmail());
            user.setId(oldUser.getId());
            user.setTurma(oldUser.getTurma());
            user.setStatus(oldUser.getStatus());
            user.setCriacao(oldUser.getCriacao());
            if (imgDataBa != null && imgDataBa.length > 0) {
                user.setImagem(imgDataBa);
            } else {
                user.setImagem(oldUser.getImagem());
            }
            user.setAlteracao(new Date());
//            user.setLogin(null);
            pessoaBLL.update(user);
            if (!login.getSenha().isEmpty() || changeEmail) {
                Login oldLogin = loginBLL.findbyPessoa(oldUser);

                if (!login.getSenha().isEmpty()) {
                    oldLogin.setSenha(login.getSenha());
                }
                if (changeEmail) {
                    loginBLL.delete(oldLogin);
                    oldLogin.setEmail(login.getEmail());
                    loginBLL.insert(oldLogin);
                    for (LoginRole lr : oldLogin.getLoginRoles()) {
                        lr.setLogin(oldLogin);
                        loginRoleBLL.insert(lr);
                    }
                } else {
                    loginBLL.update(oldLogin);
                }
            }
            if (changeEmail) {
                ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) external.getRequest();
                request.getSession().invalidate();
                return "login";
            }
            usuario = null;
            return "perfil";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
            return "perfilfrm";
        }
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
        return this.user;
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

    public LoginBLL getLoginBLL() {
        return loginBLL;
    }

    public void setLoginBLL(LoginBLL loginBLL) {
        this.loginBLL = loginBLL;
    }

    public Pessoa getUsuario() {
        if (usuario == null || usuario.getId() == 0) {
            ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) external.getRequest();
            String email = request.getRemoteUser();
            usuario = pessoaBLL.findbyEmail(email);
            getNivel();
        }
        return usuario;
    }

    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
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

    public ProjetoBLL getProjetoBLL() {
        return projetoBLL;
    }

    public void setProjetoBLL(ProjetoBLL projetoBLL) {
        this.projetoBLL = projetoBLL;
    }

    public List<Projeto> getProjetos() {
        projetos = projetoBLL.findbyAluno(getUsuario());
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

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public AvaliacaoBLL getAvaliacaoBLL() {
        return avaliacaoBLL;
    }

    public void setAvaliacaoBLL(AvaliacaoBLL avaliacaoBLL) {
        this.avaliacaoBLL = avaliacaoBLL;
    }

    public EtapaBLL getEtapaBLL() {
        return etapaBLL;
    }

    public void setEtapaBLL(EtapaBLL etapaBLL) {
        this.etapaBLL = etapaBLL;
    }

    public IndicadorBLL getIndicadorBLL() {
        return indicadorBLL;
    }

    public void setIndicadorBLL(IndicadorBLL indicadorBLL) {
        this.indicadorBLL = indicadorBLL;
    }

    public TurmaBLL getTurmaBLL() {
        return turmaBLL;
    }

    public void setTurmaBLL(TurmaBLL turmaBLL) {
        this.turmaBLL = turmaBLL;
    }

    public String getToken() {
        getUsuario();
        if (usuario != null) {
            Login log = usuario.getLogin();
            token = log.getToken();
            if (token == null || token.isEmpty()) {
                token = loginBLL.newToken();
                log.setToken(token);
                loginBLL.update(log);
            }
        }
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginRoleBLL getLoginRoleBLL() {
        return loginRoleBLL;
    }

    public void setLoginRoleBLL(LoginRoleBLL loginRoleBLL) {
        this.loginRoleBLL = loginRoleBLL;
    }

    public int getNivel() {
        if (nivel == 0 && usuario != null) {
            int pontuacao = pessoaBLL.findPontuacao(usuario);
            nivel = pessoaBLL.findNivel(pontuacao);
            Pessoa pessoa = pessoaBLL.findById(usuario.getId());
            pessoa.setKarma(pontuacao);
            pessoaBLL.update(pessoa);
        }
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void refreshNivel() {
        nivel = 0;
        getNivel();
    }
}
