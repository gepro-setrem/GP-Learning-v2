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
public class Premissa implements Serializable {

    @SequenceGenerator(name = "genpremissa", sequenceName = "seqpremissa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genpremissa")
    @Id
    @Column(name = "pre_id")
    private int id;

    @Column(name = "pre_tdescricao", length = 500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "trb_id")
    private TermoAbertura termoabertura;

    public Premissa() {
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

    public TermoAbertura getTermoabertura() {
        return termoabertura;
    }

    public void setTermoabertura(TermoAbertura termoabertura) {
        this.termoabertura = termoabertura;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
