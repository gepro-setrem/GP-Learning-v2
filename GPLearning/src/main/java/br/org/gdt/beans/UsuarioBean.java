package br.org.gdt.beans;

import br.org.gdt.model.Login;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import br.org.gdt.bll.LoginBLL;
import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.enumerated.Role;
import br.org.gdt.enumerated.Status;
import br.org.gdt.model.LoginRole;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class UsuarioBean {

    private Pessoa usuario = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL service;
    private DataModel usuarios;
    private List<Pessoa> usuariosfiltrados;

    @ManagedProperty("#{turmaBLL}")
    private TurmaBLL turmaService;
    private List<Turma> turmas;

    private Login perfil;
    @ManagedProperty("#{perfilBLL}")
    private LoginBLL perfilService;

    public UsuarioBean() {
    }

    public Pessoa getUsuario() {
        return usuario;
    }

    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
    }

    public DataModel getUsuarios() {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String emailuser = request.getRemoteUser();
        System.out.println("" + emailuser);
        Pessoa userlogado = service.findByEmail(emailuser);
        //sabemos qual é o professor, agora precisamos saber quais as turmas que esse professor tem

        turmas = turmaService.findbyProfessor(userlogado);

        System.out.println("" + turmas);
        List<Pessoa> usuariosdaturma = new ArrayList<>();

        //Uma turma do tipo Turma, será preenchida com os objetos da datamodel turmas
        for (Turma turmafor : turmas) {
            usuariosdaturma.addAll(turmafor.getAcademicos());
        }

        usuarios = new ListDataModel(usuariosdaturma);
        // usuarios = new ListDataModel(dao.findByTurma(turmas));
        return usuarios;
    }

    public void setUsuarios(DataModel usuarios) {
        this.usuarios = usuarios;
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public List<Turma> getTurmas() {

        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String emailuser = request.getRemoteUser();
        System.out.println("" + emailuser);
        Pessoa usuarioturma = service.findByEmail(emailuser);
        turmas = turmaService.findbyProfessor(usuarioturma);
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public String salvar() {
        if (!usuario.getNome().isEmpty() && !usuario.getEmail().isEmpty()) {// && !usuario.getTurma().equals("")) {
            System.out.println("Entrou no botão salvar");
            Login perfillocal = perfilService.findPessoa(usuario);
            LoginRole loginRole = new LoginRole();
            loginRole.setLogin(perfil);
            loginRole.setRole(Role.user);
            List<LoginRole> lsLoginRole = new ArrayList<>();
            lsLoginRole.add(loginRole);

            if (usuario.getId() > 0) {
                System.out.println("Entrou para alterar");
                usuario.setAlteracao(new Date());
                service.update(usuario);
                perfillocal.setEmail(usuario.getEmail());
                perfillocal.setLoginRoles(lsLoginRole);
                perfilService.update(perfillocal);
            } else {
                usuario.setAlteracao(new Date());
                usuario.setCriacao(new Date());
                usuario.setStatus(Status.Ativo);
                service.insert(usuario);

                System.out.println("É um usuário novo");
                perfil = new Login();
                perfil.setEmail(usuario.getEmail());
                perfil.setLoginRoles(lsLoginRole);
                perfil.setPessoa(usuario);
                perfilService.insert(perfil);
            }
            return "usuariolst";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
            return "usuariofrm";
        }
    }

    public String editar() {
        usuario = (Pessoa) usuarios.getRowData();
        usuario = service.findById(usuario.getId());
        return "usuariofrm";
    }

    public String inativar() {
        FacesContext context = FacesContext.getCurrentInstance();
        usuario = (Pessoa) usuarios.getRowData();
        usuario = service.findById(usuario.getId());
        usuario.setStatus(Status.Inativo);
        service.update(usuario);
        context.addMessage(null, new FacesMessage("Sucesso", "Usuário tornou-se inativo e não poderá criar novos projetos"));
        usuarios = null;
        return "usuariolst";
    }

    public String novo() {
        usuario = new Pessoa();
        return "usuariofrm";
    }

    public List<Pessoa> getUsuariosfiltrados() {
        return usuariosfiltrados;
    }

    public void setUsuariosfiltrados(List<Pessoa> usuariosfiltrados) {
        this.usuariosfiltrados = usuariosfiltrados;
    }

    public PessoaBLL getService() {
        return service;
    }

    public void setService(PessoaBLL service) {
        this.service = service;
    }

    public TurmaBLL getTurmaService() {
        return turmaService;
    }

    public void setTurmaService(TurmaBLL turmaService) {
        this.turmaService = turmaService;
    }

    public Login getPerfil() {
        return perfil;
    }

    public void setPerfil(Login perfil) {
        this.perfil = perfil;
    }

    public LoginBLL getPerfilService() {
        return perfilService;
    }

    public void setPerfilService(LoginBLL perfilService) {
        this.perfilService = perfilService;
    }

}
