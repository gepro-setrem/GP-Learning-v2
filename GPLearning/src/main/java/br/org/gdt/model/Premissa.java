/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.gdt.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Premissa implements Serializable {

    @SequenceGenerator(name = "genpremissa", sequenceName = "seqpremissa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genpremissa")
    @Id
    private int idPremissa;

    @ManyToOne
    private TermoAbertura termoabertura;

    @Column(length = 500)
    private String descricaoPremissa;

    public int getIdPremissa() {
        return idPremissa;
    }

    public void setIdPremissa(int idPremissa) {
        this.idPremissa = idPremissa;
    }

    public TermoAbertura getTermoabertura() {
        return termoabertura;
    }

    public void setTermoabertura(TermoAbertura termoabertura) {
        this.termoabertura = termoabertura;
    }

    public String getDescricaoPremissa() {
        return descricaoPremissa;
    }

    public void setDescricaoPremissa(String descricaoPremissa) {
        this.descricaoPremissa = descricaoPremissa;
    }

    public Premissa() {
    }

}
