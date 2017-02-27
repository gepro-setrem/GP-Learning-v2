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
@Table(name = "requisitos_termo_abertura")
public class RequisitoTermoAbertura implements Serializable {

    @SequenceGenerator(name = "genrequisitota", sequenceName = "seqrequisitota", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genrequisitota")

    @Id
    @Column(name = "rtb_id")
    private int id;

    @Column(name = "rtb_tdescricao", length = 500)
    private String descricao;

    @ManyToOne
    @Column(name = "trb_id")
    private TermoAbertura termoabertura;

    public RequisitoTermoAbertura() {
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
