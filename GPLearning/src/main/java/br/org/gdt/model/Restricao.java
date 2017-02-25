/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.gdt.model;

import java.io.Serializable;
import java.util.Objects;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Restricao implements Serializable {
    
    @SequenceGenerator(name = "genrestricao", sequenceName = "seqrestricao", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genrestricao")
    @Id
    private int idRestricao;

    @ManyToOne
    @Named("trb_id")
    private TermoAbertura termoabertura;

    @Column(length = 500)
    private String descricaoRestricao;

    public Restricao() {
    }

    public int getIdRestricao() {
        return idRestricao;
    }

    public void setIdRestricao(int idRestricao) {
        this.idRestricao = idRestricao;
    }

    public TermoAbertura getTermoabertura() {
        return termoabertura;
    }

    public void setTermoabertura(TermoAbertura termoabertura) {
        this.termoabertura = termoabertura;
    }

    public String getDescricaoRestricao() {
        return descricaoRestricao;
    }

    public void setDescricaoRestricao(String descricaoRestricao) {
        this.descricaoRestricao = descricaoRestricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idRestricao;
        hash = 97 * hash + Objects.hashCode(this.termoabertura);
        hash = 97 * hash + Objects.hashCode(this.descricaoRestricao);
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
        if (this.idRestricao != other.idRestricao) {
            return false;
        }
        if (!Objects.equals(this.termoabertura, other.termoabertura)) {
            return false;
        }
        if (!Objects.equals(this.descricaoRestricao, other.descricaoRestricao)) {
            return false;
        }
        return true;
    }

    
    

    
}
