package br.org.gdt.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "perfis")
public class Perfil implements Serializable {

    @SequenceGenerator(name = "genperfil", sequenceName = "seqperfil", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genperfil")

    @Id
    @Column(name = "per_id")
    private int id;

    @Column(name = "per_vemail", length = 200)
    private String email;

    @Column(name = "per_vgrupo", length = 200)
    private String grupo;

    @OneToOne
    private Usuario usuario;

    public Perfil() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
