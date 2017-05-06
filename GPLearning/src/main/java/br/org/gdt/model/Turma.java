package br.org.gdt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

@Entity
public class Turma implements Serializable {

    @SequenceGenerator(name = "genturma", sequenceName = "seqturma", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genturma")
    @Id
    @Column(name = "tur_id")
    private int id;

    @Column(name = "tur_vnome", length = 200)
    private String nome;

    @Column(name = "tur_tdescricao", length = 2500)
    private String descricao;

    @Column(name = "tur_iano")
    private int ano;

    @Column(name = "tur_dcriacao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date criacao;

    @Column(name = "tur_dalteracao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date alteracao;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<TurmaParametro> turmaParametros;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<Etapa> etapas;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.REMOVE)//, fetch = FetchType.EAGER)
    private List<Pessoa> academicos;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    public Pessoa professor;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.REMOVE)//, fetch = FetchType.EAGER)
    private List<Projeto> projetos;

    public Turma() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
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

    public List<TurmaParametro> getTurmaParametros() {
        return turmaParametros;
    }

    public void setTurmaParametros(List<TurmaParametro> turmaParametros) {
        this.turmaParametros = turmaParametros;
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }

    public List<Pessoa> getAcademicos() {
        return academicos;
    }

    public void setAcademicos(List<Pessoa> academicos) {
        this.academicos = academicos;
    }

    public Pessoa getProfessor() {
        return professor;
    }

    public void setProfessor(Pessoa professor) {
        this.professor = professor;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.nome);
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
        final Turma other = (Turma) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
}
