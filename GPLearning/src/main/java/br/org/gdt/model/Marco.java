package br.org.gdt.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Marco implements Serializable {

    @SequenceGenerator(name = "genmarco", sequenceName = "seqmarco", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genmarco")

    @Id
    @Column(name = "mar_id")
    private int id;

    @Column(name = "mar_tobjetivo", length = 2500)
    private String objetivo;

    @Column(name = "mar_dprevisao")
    private Date previsao;

    @ManyToOne
    @JoinColumn(name = "trb_id")
    private TermoAbertura termoabertura;

    public Marco() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Date getPrevisao() {
        return previsao;
    }

    public void setPrevisao(Date previsao) {
        this.previsao = previsao;
    }

    public TermoAbertura getTermoabertura() {
        return termoabertura;
    }

    public void setTermoabertura(TermoAbertura termoabertura) {
        this.termoabertura = termoabertura;
    }

    @Override
    public String toString() {
        return objetivo;
    }
}
