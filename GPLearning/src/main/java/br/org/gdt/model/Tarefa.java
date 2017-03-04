package br.org.gdt.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tarefas")
public class Tarefa implements Serializable {

    @SequenceGenerator(name = "gentarefa", sequenceName = "seqtarefa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gentarefa")

    @Id
    @Column(name = "tar_id")
    private int id;

    @Column(name = "tar_vnome", length = 200)
    private String nome;

    @Column(name = "tar_dcriacao")
    private Date criacao;

    @Column(name = "tar_dtermino")
    private Date termino;

    @Column(name = "tar_dconclusao")
    private Date conclusao;

    @ManyToOne
//    @Column(name = "eap_id")
    private EAP eap;

    public Tarefa() {
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

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    public Date getConclusao() {
        return conclusao;
    }

    public void setConclusao(Date conclusao) {
        this.conclusao = conclusao;
    }

    public EAP getEap() {
        return eap;
    }

    public void setEap(EAP eap) {
        this.eap = eap;
    }

    @Override
    public String toString() {
        return nome;
    }
}
