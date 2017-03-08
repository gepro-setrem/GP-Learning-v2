package br.org.gdt.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Perfil implements Serializable {

    @SequenceGenerator(name = "genperfil", sequenceName = "seqperfil", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genperfil")

    @Id
    @Column(name = "per_id")
    private int id;

    @Column(name = "per_vlogin", length = 200)
    private String login;

    @Column(name = "per_vsenha")
    private String senha;

    @Column(name = "per_vgrupo", length = 200)
    private String grupo;

    @OneToOne
    @JoinColumn(name = "use_id")
    private Usuario usuario;

    public Perfil() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
