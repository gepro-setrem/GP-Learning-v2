package br.org.gdt.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Usuario implements Serializable {

    @SequenceGenerator(name = "genusuario", sequenceName = "sequsuario", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genusuario")
    @Id
    private int idUsuario;
    @Column(unique = true)
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    private String status;
    private String dataCadastro;
    private String dataModificacao;
    private int karma;
//    @OneToOne
//    private Perfil perfil;
//
//    public Perfil getPerfil() {
//        return perfil;
//    }
//
//    public void setPerfil(Perfil perfil) {
//        this.perfil = perfil;
//    }

    @ManyToOne
    private Turma turma;

    @OneToMany(mappedBy = "professor")
    private List<Turma> turmasprofessor;

    @OneToMany(mappedBy = "gerenteProjeto")
    private List<Projeto> projetosgerente;

    @ManyToMany(mappedBy = "componentes")
    private List<Projeto> projetos;

    @ManyToOne
    private Grupo grupo;

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(String dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Turma> getTurmasprofessor() {
        return turmasprofessor;
    }

    public void setTurmasprofessor(List<Turma> turmasprofessor) {
        this.turmasprofessor = turmasprofessor;
    }

    public List<Projeto> getProjetosgerente() {
        return projetosgerente;
    }

    public void setProjetosgerente(List<Projeto> projetosgerente) {
        this.projetosgerente = projetosgerente;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

}
