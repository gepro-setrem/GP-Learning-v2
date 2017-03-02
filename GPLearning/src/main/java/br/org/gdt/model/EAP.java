package br.org.gdt.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "eap")
public class EAP implements Serializable {

    @SequenceGenerator(name = "geneap", sequenceName = "seqeap", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geneap")

    @Id
    @Column(name = "eap_id")
    private int id;

    @Column(name = "eap_vnome", length = 200)
    private String nome;

    @Column(name = "eap_dentrega")
    private Date entrega;

    @Column(name = "eap_dconclusao")
    private Date conclusao;

    @Column(name = "eap_nvalor")
    private double valor;

    @ManyToOne
    @Column(name = "pro_id")
    private Projeto projeto;

    @ManyToOne
    @Column(name = "eap_idpai")
    private EAP pai;

    public EAP() {
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

    public Date getEntrega() {
        return entrega;
    }

    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }

    public Date getConclusao() {
        return conclusao;
    }

    public void setConclusao(Date conclusao) {
        this.conclusao = conclusao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public EAP getPai() {
        return pai;
    }

    public void setPai(EAP pai) {
        this.pai = pai;
    }

    @Override
    public String toString() {
        return nome;
    }
}
