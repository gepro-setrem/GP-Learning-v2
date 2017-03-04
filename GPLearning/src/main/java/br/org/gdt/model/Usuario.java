package br.org.gdt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @SequenceGenerator(name = "genusuario", sequenceName = "sequsuario", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genusuario")
    @Id
    @Column(name = "usu_id")
    private int id;

    @Column(name = "usu_vnome", length = 200)
    private String nome;

    @Column(name = "usu_vemail", length = 200)
    private String email;

    @Column(name = "usu_vtelefone", length = 20)
    private String telefone;

    @Column(name = "usu_istatus")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "usu_dcriacao")
    private Date criacao;

    @Column(name = "usu_dalteracao")
    private Date alteracao;

    @Column(name = "usu_vsenha")
    private String senha;

    @Column(name = "usu_ikarma")
    private int karma;

    @ManyToOne
    private Grupo grupo;

    @ManyToOne
    private Turma turma;

    @OneToMany(mappedBy = "professor")
    private List<Turma> turmasprofessor;

    @OneToMany(mappedBy = "gerente")
    private List<Projeto> projetosgerente;

    @ManyToMany(mappedBy = "componentes")
    private List<Projeto> projetos;

    public enum Status {
        Ativo(1),
        Inativo(2);
        private final int valor;

        Status(int opt) {
            valor = opt;
        }

        public int getValor() {
            return valor;
        }
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Date getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(Date alteracao) {
        this.alteracao = alteracao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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

    @Override
    public String toString() {
        return nome;
    }
}
