package br.org.gdt.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Comentario implements Serializable {

    @SequenceGenerator(name = "gencomentario", sequenceName = "seqcomentario", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gencomentario")

    @Id
    @Column(name = "com_id")
    private int id;

    @Column(name = "com_tdescricao", length = 2500)
    private String descricao;

    @Column(name = "com_dcriacao")
//    @Temporal(javax.persistence.TemporalType.DATE)
    public Date criacao;

    @ManyToOne
    @JoinColumn(name = "pes_idremetente")
    private Pessoa remetente;

    @ManyToOne
    @JoinColumn(name = "pes_iddestinatario")
    private Pessoa destinatario;

    @ManyToOne
    @JoinColumn(name = "eta_id")
    private Etapa etapa;

    @ManyToOne
    @JoinColumn(name = "pro_id")
    private Projeto projeto;

    public Comentario() {
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

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Pessoa getRemetente() {
        return remetente;
    }

    public void setRemetente(Pessoa remetente) {
        this.remetente = remetente;
    }

    public Pessoa getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Pessoa destinatario) {
        this.destinatario = destinatario;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

}
