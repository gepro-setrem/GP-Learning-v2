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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

@Entity
public class Tarefa implements Serializable {

    @SequenceGenerator(name = "gentarefa", sequenceName = "seqtarefa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gentarefa")

    @Id
    @Column(name = "tar_id")
    private int id;

    @Column(name = "tar_vnome", length = 200)
    private String nome;

    @Column(name = "tar_dinicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicio;

    @Column(name = "tar_dtermino")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date termino;

    @Column(name = "tar_dconclusao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date conclusao;

    @Column(name = "tar_bmarco")
    private Boolean marco;

    @ManyToOne
    @JoinColumn(name = "tar_idpai")
    private Tarefa pai;

    @ManyToOne
    @JoinColumn(name = "eap_id")
    private EAP eap;

    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.REMOVE)
    private List<Recurso> recursos;

    @OneToMany(mappedBy = "pai", cascade = CascadeType.REMOVE)
    private List<Tarefa> tarefas;

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

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Boolean getMarco() {
        return marco;
    }

    public void setMarco(Boolean marco) {
        this.marco = marco;
    }

    public Tarefa getPai() {
        return pai;
    }

    public void setPai(Tarefa pai) {
        this.pai = pai;
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

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public String toString() {
        return nome;
    }
}
