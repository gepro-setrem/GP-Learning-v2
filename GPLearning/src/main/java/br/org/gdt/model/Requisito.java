
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
public class Requisito implements Serializable {
    
    @SequenceGenerator(name = "genrequisito", sequenceName = "seqrequisito", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genrequisito")
    @Id
    private int idRequisito;
    @Column(length = 500)
    private String nomeRequisito;
    @Column(length = 500)
    private String descricaoRequisito;
    
    @ManyToOne
    private Projeto projeto;

    public Requisito() {
    }

    public int getIdRequisito() {
        return idRequisito;
    }

    public void setIdRequisito(int idRequisito) {
        this.idRequisito = idRequisito;
    }

    public String getNomeRequisito() {
        return nomeRequisito;
    }

    public void setNomeRequisito(String nomeRequisito) {
        this.nomeRequisito = nomeRequisito;
    }

    public String getDescricaoRequisito() {
        return descricaoRequisito;
    }

    public void setDescricaoRequisito(String descricaoRequisito) {
        this.descricaoRequisito = descricaoRequisito;
    }

    

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
    
    
    
}
