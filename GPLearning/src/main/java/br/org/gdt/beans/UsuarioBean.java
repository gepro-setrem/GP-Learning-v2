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
    
    private Pessoa usuario;
    private Pessoa pessoa;
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;
    private DataModel usuarios;
    private List<Pessoa> usuariosfiltrados;
    
    @ManagedProperty("#{turmaBLL}")
    private TurmaBLL turmaService;
    private List<Turma> turmas;
    
    private Login perfil;
    @ManagedProperty("#{perfilBLL}")
    private LoginBLL perfilService;
    
    private Status[] status;
    private Role[] roles;
    private Role role;
    
    public UsuarioBean() {
    }
    
    public Pessoa getUsuario() {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String email = request.getRemoteUser();
        usuario = pessoaBLL.findByEmail(email);
        return usuario;
    }
    
    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
    }
    
    public DataModel getUsuarios() {
        usuario = getUsuario();
//        turmas = turmaService.findbyProfessor(usuario);
//        List<Pessoa> usuariosdaturma = new ArrayList<>();
//
//        //Uma turma do tipo Turma, será preenchida com os objetos da datamodel turmas
//        for (Turma turmafor : turmas) {
//            usuariosdaturma.addAll(turmafor.getAcademicos());
//        }

        usuarios = new ListDataModel(pessoaBLL.findAllUsers(usuario));
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
        usuario = getUsuario();
        turmas = turmaService.findbyProfessor(usuario);
        return turmas;
    }
    
    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
    
    public String salvar() {
        if (!pessoa.getNome().isEmpty() && !pessoa.getEmail().isEmpty()) {// && !pessoa.getTurma().equals("")) {
            System.out.println("Entrou no botão salvar");
            Login perfillocal = perfilService.findPessoa(pessoa);
            LoginRole loginRole = new LoginRole();
            loginRole.setLogin(perfil);
            loginRole.setRole(Role.user);
            List<LoginRole> lsLoginRole = new ArrayList<>();
            lsLoginRole.add(loginRole);
            
            if (pessoa.getId() > 0) {
                System.out.println("Entrou para alterar");
                pessoa.setAlteracao(new Date());
                pessoaBLL.update(pessoa);
                perfillocal.setEmail(pessoa.getEmail());
                perfillocal.setLoginRoles(lsLoginRole);
                perfilService.update(perfillocal);
            } else {
                pessoa.setAlteracao(new Date());
                pessoa.setCriacao(new Date());
                pessoa.setStatus(Status.Ativo);
                pessoaBLL.insert(pessoa);
                
                System.out.println("É um usuário novo");
                perfil = new Login();
                perfil.setEmail(pessoa.getEmail());
                perfil.setLoginRoles(lsLoginRole);
                perfil.setPessoa(pessoa);
                perfilService.insert(perfil);
            }
            return "usuariolst";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
            return "usuariofrm";
        }
    }
    
    public String editar() {
        pessoa = (Pessoa) usuarios.getRowData();
        pessoa = pessoaBLL.findById(pessoa.getId());
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
        return "usuariofrm";
    }
    
    public List<Pessoa> getUsuariosfiltrados() {
        return usuariosfiltrados;
    }
    
    public void setUsuariosfiltrados(List<Pessoa> usuariosfiltrados) {
        this.usuariosfiltrados = usuariosfiltrados;
    }
    
    public PessoaBLL getPessoaBLL() {
        return pessoaBLL;
    }
    
    public void setPessoaBLL(PessoaBLL pessoaBLL) {
        this.pessoaBLL = pessoaBLL;
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
        if (pessoa != null && pessoa.getLogin() != null && pessoa.getLogin().getLoginRoles() != null && pessoa.getLogin().getLoginRoles().size() > 0) {
            return pessoa.getLogin().getLoginRoles().get(0).getRole();
        } else {
            return getRoles()[0];
        }
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
    
}
