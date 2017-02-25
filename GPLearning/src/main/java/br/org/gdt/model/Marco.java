/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.gdt.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Marco implements Serializable {

    @SequenceGenerator(name = "gencronmarco", sequenceName = "seqcronmarco", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gencronmarco")
    @Id
    private int idMarco;
    @Column(length = 2500)
    private String objetivo;

    private String dataPrevista;

    @ManyToOne
    private TermoAbertura termoabertura;

    public Marco() {
    }

    public int getIdMarco() {
        return idMarco;
    }

    public void setIdMarco(int idMarco) {
        this.idMarco = idMarco;
    }

    public TermoAbertura getTermoabertura() {
        return termoabertura;
    }

    public void setTermoabertura(TermoAbertura termoabertura) {
        this.termoabertura = termoabertura;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(String dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

}
