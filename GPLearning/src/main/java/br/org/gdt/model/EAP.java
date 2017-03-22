package br.org.gdt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class EAP implements Serializable {

    @SequenceGenerator(name = "geneap", sequenceName = "seqeap", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geneap")

    @Id
    @Column(name = "eap_id")
    private int id;

    @Column(name = "eap_vnome", length = 200)
    private String nome;

    @Column(name = "eap_vdescricao", length = 2500)
    private String descricao;

    @Column(name = "eap_dinicio")
    private Date inicio;

    @Column(name = "eap_dtermino")
    private Date termino;

    @Column(name = "eap_dconclusao")
    private Date conclusao;

    @Column(name = "eap_nvalor")
    private double valor;
    @Column(name = "eap_iordem")
    private int ordem;

    @ManyToOne
    @JoinColumn(name = "pro_id")
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "eap_idpai")
    private EAP pai;

    @OneToMany(mappedBy = "eap")
    private List<Tarefa> tarefas;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
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

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    @Override
    public String toString() {
        return nome;
    }
}
