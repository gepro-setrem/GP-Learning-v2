package br.org.gdt.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ParteInteressada implements Serializable {

    @SequenceGenerator(name = "genparteinteressada", sequenceName = "seqparteinteressada", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genparteinteressada")
    @Id
    private int idParteInteressada;

    @ManyToOne
    private Projeto projeto;

    @Column(length = 255)
    private String nomeParteInteressada;
    @Column(length = 500)
    private String papelProjeto;
    @Column(length = 500)
    private String contribuicaoProjeto;

    public ParteInteressada() {
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getNomeParteInteressada() {
        return nomeParteInteressada;
    }

    public void setNomeParteInteressada(String nomeParteInteressada) {
        this.nomeParteInteressada = nomeParteInteressada;
    }

    public String getPapelProjeto() {
        return papelProjeto;
    }

    public void setPapelProjeto(String papelProjeto) {
        this.papelProjeto = papelProjeto;
    }

    public String getContribuicaoProjeto() {
        return contribuicaoProjeto;
    }

    public void setContribuicaoProjeto(String contribuicaoProjeto) {
        this.contribuicaoProjeto = contribuicaoProjeto;
    }

    public int getIdParteInteressada() {
        return idParteInteressada;
    }

    public void setIdParteInteressada(int idParteInteressada) {
        this.idParteInteressada = idParteInteressada;
    }
}
