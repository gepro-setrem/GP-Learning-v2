package br.org.gdt.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.SequenceGenerator;

@Entity
public class TurmaParametro implements Serializable {

//    @SequenceGenerator(name = "genturmaparametro", sequenceName = "seqturmaparametro", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genturmaparametro")
    @Id
    @Column(name = "trp_vchave", length = 200)
    private String chave;

    @Column(name = "trp_tvalor", length = 2500)
    private String valor;

    @ManyToOne
    @JoinColumn(name = "tur_id")
    private Turma turma;

    public TurmaParametro() {
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

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

}
