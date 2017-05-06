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
public class EtapaParametro implements Serializable {

    @SequenceGenerator(name = "genetapaparametro", sequenceName = "seqetapaparametro", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genetapaparametro")

    @Id
    @Column(name = "tpp_id")
    private int id;

    @Column(name = "tpp_vchave", length = 200)
    private String chave;

    @Column(name = "tpp_vvalor", length = 2500)
    private String valor;

    @Column(name = "tpp_iminimo")
    private int minimo;

    @ManyToOne
    @JoinColumn(name = "eta_id")
    private Etapa etapa;

    public EtapaParametro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

}
