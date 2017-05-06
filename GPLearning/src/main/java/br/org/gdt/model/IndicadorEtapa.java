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
public class IndicadorEtapa implements Serializable {

    @SequenceGenerator(name = "genindicadoret", sequenceName = "seqindicadoret", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genindicadoret")

    @Id
    @Column(name = "ndt_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ind_id")
    private Indicador indicador;

    @ManyToOne
    @JoinColumn(name = "eta_id")
    private Etapa etapa;

    public IndicadorEtapa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }
    
    

}
