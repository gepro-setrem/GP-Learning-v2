package br.org.gdt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class TermoAbertura implements Serializable {

    @SequenceGenerator(name = "gentermoabertura", sequenceName = "seqtermoabertura", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gentermoabertura")

    @Id
    @Column(name = "trb_id")
    private int id;

    @Column(name = "trb_tdescricao", length = 2500)
    private String descricao;

    @Column(name = "trb_tjustificativa", length = 2500)
    private String justificativa;

    @Column(name = "trb_dcriacao")
    private Date criacao;

    @Column(name = "trb_dalteracao")
    private Date alteracao;

    @OneToOne
    @JoinColumn(name = "pro_id")
    private Projeto projeto;

    @OneToMany(mappedBy = "termoabertura", cascade = CascadeType.REMOVE)
    private List<Premissa> premissas;

    @OneToMany(mappedBy = "termoabertura", cascade = CascadeType.REMOVE)
    private List<Restricao> restricoes;

    @OneToMany(mappedBy = "termoabertura", cascade = CascadeType.REMOVE)
    private List<Marco> marcos;

    @OneToMany(mappedBy = "termoabertura", cascade = CascadeType.REMOVE)
    private List<RequisitoTermoAbertura> requisitosTermoAberturas;

    public TermoAbertura() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
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

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public List<Premissa> getPremissas() {
        return premissas;
    }

    public void setPremissas(List<Premissa> premissas) {
        this.premissas = premissas;
    }

    public List<Restricao> getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(List<Restricao> restricoes) {
        this.restricoes = restricoes;
    }

    public List<Marco> getMarcos() {
        return marcos;
    }

    public void setMarcos(List<Marco> marcos) {
        this.marcos = marcos;
    }

    public List<RequisitoTermoAbertura> getRequisitosTermoAberturas() {
        return requisitosTermoAberturas;
    }

    public void setRequisitosTermoAberturas(List<RequisitoTermoAbertura> requisitosTermoAberturas) {
        this.requisitosTermoAberturas = requisitosTermoAberturas;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
