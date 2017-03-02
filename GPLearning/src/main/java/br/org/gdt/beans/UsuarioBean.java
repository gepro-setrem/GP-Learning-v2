package br.org.gdt.beans;

import br.org.gdt.model.Grupo;
//import br.org.gdt.model.Perfil;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Usuario;
import br.org.gdt.bll.GrupoBLL;
//import br.org.gdt.bll.PerfilBLL;
import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.bll.UsuarioBLL;
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

    private Usuario usuario = new Usuario();
    @ManagedProperty("#{usuarioBLL}")
    private UsuarioBLL service;
    private DataModel usuarios;
    private List<Usuario> usuariosfiltrados;

    @ManagedProperty("#{turmaBLL}")
    private TurmaBLL turmaService;
    private List<Turma> turmas;

    private Grupo grupo = new Grupo();
    private List<Grupo> grupos;
    @ManagedProperty("#{grupoBLL}")
    private GrupoBLL grupoService;

//    private Perfil perfil;
//    @ManagedProperty("#{perfilService}")
//    private PerfilService perfilService;

    public UsuarioBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DataModel getUsuarios() {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String emailuser = request.getRemoteUser();
        System.out.println("" + emailuser);
        Usuario userlogado = service.findByEmail(emailuser);
        //sabemos qual é o professor, agora precisamos saber quais as turmas que esse professor tem

        turmas = turmaService.findbyProfessor(userlogado);

        System.out.println("" + turmas);
        List<Usuario> usuariosdaturma = new ArrayList<>();

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
        Usuario usuarioturma = service.findByEmail(emailuser);
        turmas = turmaService.findbyProfessor(usuarioturma);
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Grupo> getGrupos() {
        grupos = grupoService.findAll();
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    //quando é clicado o botão inserir
    public String salvar() {
        if (!usuario.getNome().isEmpty() && !usuario.getEmail().isEmpty()){// && !usuario.getTurma().equals("")) {
            System.out.println("Entrou no botão salvar");
            if (usuario.getId() > 0) {
                System.out.println("Entrou para alterar");

                usuario.setAlteracao(new Date());
                service.update(usuario);

//                Perfil perfillocal = perfilService.findByUsuario(usuario);
//                perfillocal.setEmail(usuario.getEmail());
//                perfillocal.setNomeGrupo(usuario.getGrupo().getNome());
//                perfilService.update(perfillocal);
            } else {
                usuario.setAlteracao(new Date());
                usuario.setCriacao(new Date());
                usuario.setStatus(Usuario.Status.Ativo);
                service.insert(usuario);

//                System.out.println("É um usuário novo");
//                perfil = new Perfil();
//                perfil.setEmail(usuario.getEmail());
//                Grupo grupolocal = usuario.getGrupo();
//                String nomegrupo = grupolocal.getNome();
//                perfil.setNomeGrupo(nomegrupo);
//                System.out.println("Grupo do perfil" + perfil.getNomeGrupo());
//                perfil.setUsuario(usuario);
//                perfilService.insert(perfil);

            }
            return "ListagemUsuarios";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
            return "ManutencaoUsuarios";
        }
    }

    public String select() {
        usuario = (Usuario) usuarios.getRowData();
        usuario = service.findById(usuario.getId());
        System.out.println("O nome do usuario selecionado para Edição é" + usuario.getNome());
        return "ManutencaoUsuarios";
    }

    public String inativar() {
        FacesContext context = FacesContext.getCurrentInstance();
        usuario = (Usuario) usuarios.getRowData();
        usuario = service.findById(usuario.getId());
        usuario.setStatus(Usuario.Status.Inativo);
        service.update(usuario);
        context.addMessage(null, new FacesMessage("Sucesso", "Usuário tornou-se inativo e não poderá criar novos projetos"));
        usuarios = null;
        return "ListagemUsuarios";
    }

    public String novoUsuario() {
        usuario = new Usuario();
        return "ManutencaoUsuarios";
    }

    public List<Usuario> getUsuariosfiltrados() {
        return usuariosfiltrados;
    }

    public void setUsuariosfiltrados(List<Usuario> usuariosfiltrados) {
        this.usuariosfiltrados = usuariosfiltrados;
    }
}
