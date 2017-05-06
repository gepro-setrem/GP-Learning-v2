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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

@Entity
public class Projeto implements Serializable {

    @SequenceGenerator(name = "genprojeto", sequenceName = "seqprojeto", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genprojeto")

    @Id
    @Column(name = "pro_id")
    private int id;

    @Column(name = "pro_vnome", length = 200)
    private String nome;

    @Column(name = "pro_tdescricao", length = 2500)
    private String descricao;

    @Column(name = "pro_vempresa", length = 200)
    private String empresa;

    @Column(name = "pro_vestado", length = 200)
    private String estado;

    @Column(name = "pro_tescopo", length = 2500)
    private String escopo;

    @Column(name = "pro_tplano_escopo", length = 2500)
    private String planoEscopo;

    @Column(name = "pro_tplano_projeto", length = 2500)
    private String planoProjeto;

    @Column(name = "pro_dcriacao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date criacao;

    @Column(name = "pro_dalteracao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date alteracao;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa gerente;

    @ManyToOne
    @JoinColumn(name = "tur_id")
    private Turma turma;

    @OneToOne(mappedBy = "projeto", cascade = CascadeType.REMOVE)
    private TermoAbertura termoabertura;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.REMOVE)
    private List<Stakeholder> stakeholders;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.REMOVE)
    private List<Requisito> requisitos;

    @ManyToMany
    private List<Pessoa> componentes;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.REMOVE)
    private List<EAP> eaps;

    public Projeto() {
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public Pessoa getGerente() {
        return gerente;
    }

    public void setGerente(Pessoa gerente) {
        this.gerente = gerente;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public TermoAbertura getTermoabertura() {
        return termoabertura;
    }

    public void setTermoabertura(TermoAbertura termoabertura) {
        this.termoabertura = termoabertura;
    }

    public List<Stakeholder> getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(List<Stakeholder> stakeholders) {
        this.stakeholders = stakeholders;
    }

    public List<Requisito> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(List<Requisito> requisitos) {
        this.requisitos = requisitos;
    }

    public List<Pessoa> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Pessoa> componentes) {
        this.componentes = componentes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    public String getPlanoEscopo() {
        return planoEscopo;
    }

    public void setPlanoEscopo(String planoEscopo) {
        this.planoEscopo = planoEscopo;
    }

    public String getPlanoProjeto() {
        return planoProjeto;
    }

    public void setPlanoProjeto(String planoProjeto) {
        this.planoProjeto = planoProjeto;
    }

    public List<EAP> getEaps() {
        return eaps;
    }

    public void setEaps(List<EAP> eaps) {
        this.eaps = eaps;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.descricao);
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
        final Projeto other = (Projeto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

}
