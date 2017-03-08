package br.org.gdt.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Stakeholder implements Serializable {

    @SequenceGenerator(name = "genstakeholder", sequenceName = "seqstakeholder", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genstakeholder")

    @Id
    @Column(name = "sta_id")
    private int id;

    @Column(name = "sta_vnome", length = 200)
    private String nome;

    @Column(name = "sta_vcontribuicao", length = 500)
    private String contribuicao;

    @Column(name = "sta_vpapel", length = 500)
    private String papel;

    @ManyToOne
    @JoinColumn(name = "pro_id")
    private Projeto projeto;

    public Stakeholder() {
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

    public String getContribuicao() {
        return contribuicao;
    }

    public void setContribuicao(String contribuicao) {
        this.contribuicao = contribuicao;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @Override
    public String toString() {
        return nome;
    }
}
