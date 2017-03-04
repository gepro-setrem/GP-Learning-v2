package br.org.gdt.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "requisitos")
public class Requisito implements Serializable {

    @SequenceGenerator(name = "genrequisito", sequenceName = "seqrequisito", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genrequisito")

    @Id
    @Column(name = "req_id")
    private int id;

    @Column(name = "req_vnome", length = 500)
    private String nome;

    @Column(name = "req_tdescricao", length = 500)
    private String descricao;

    @ManyToOne
//    @Column(name = "pro_id")
    private Projeto projeto;

    public Requisito() {
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
