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
import javax.persistence.Temporal;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Avaliacao implements Serializable {

    @SequenceGenerator(name = "genavaliacao", sequenceName = "seqavaliacao", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genavaliacao")

    @Id
    @Column(name = "ava_id")
    private int id;

    @Column(name = "ava_ivalor", length = 2500)
    private int valor;

    @ManyToOne
    @JoinColumn(name = "tpp_id")
    private EtapaParametro etapaParametro;

    @ManyToOne
    @JoinColumn(name = "ndt_id")
    private IndicadorEtapa indicadorEtapa;

    @ManyToOne
    @JoinColumn(name = "pro_id")
    private Projeto projeto;

    public Avaliacao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public EtapaParametro getEtapaParametro() {
        return etapaParametro;
    }

    public void setEtapaParametro(EtapaParametro etapaParametro) {
        this.etapaParametro = etapaParametro;
    }

    public IndicadorEtapa getIndicadorEtapa() {
        return indicadorEtapa;
    }

    public void setIndicadorEtapa(IndicadorEtapa indicadorEtapa) {
        this.indicadorEtapa = indicadorEtapa;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

}
