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
public class AtividadeParametro implements Serializable {

    @SequenceGenerator(name = "genatividadeparametro", sequenceName = "seqatividadeparametro", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genatividadeparametro")

    @Id
    @Column(name = "tvp_id")
    private int id;

    @Column(name = "tvp_vchave", length = 200)
    private String chave;

    @Column(name = "tvp_vvalor", length = 200)
    private String valor;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private Atividade atividade;

    public AtividadeParametro() {
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

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

}
