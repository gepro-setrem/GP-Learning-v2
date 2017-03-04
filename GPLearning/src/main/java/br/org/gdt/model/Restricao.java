package br.org.gdt.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ta_restricoes")
public class Restricao implements Serializable {

    @SequenceGenerator(name = "genrestricao", sequenceName = "seqrestricao", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genrestricao")
    
    @Id
    @Column(name = "res_id")
    private int id;

    @Column(name = "res_tdescricao", length = 500)
    private String descricao;

    @ManyToOne
//    @Column(name = "trb_id")
    private TermoAbertura termoabertura;

    public Restricao() {
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
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.termoabertura);
        hash = 97 * hash + Objects.hashCode(this.descricao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Restricao other = (Restricao) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.termoabertura, other.termoabertura)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
