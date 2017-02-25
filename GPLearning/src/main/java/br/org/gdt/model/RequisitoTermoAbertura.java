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
public class RequisitoTermoAbertura implements Serializable {

    @SequenceGenerator(name = "genrequisitota", sequenceName = "seqrequisitota", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genrequisitota")
    @Id
    private int idrequisitotermoabertura;
    @Column(length = 500)
    private String descricaorequisito;

    @ManyToOne
    private TermoAbertura termoabertura;

    public RequisitoTermoAbertura() {
    }

    public int getIdrequisitotermoabertura() {
        return idrequisitotermoabertura;
    }

    public void setIdrequisitotermoabertura(int idrequisitotermoabertura) {
        this.idrequisitotermoabertura = idrequisitotermoabertura;
    }

    public String getDescricaorequisito() {
        return descricaorequisito;
    }

    public void setDescricaorequisito(String descricaorequisito) {
        this.descricaorequisito = descricaorequisito;
    }

    public TermoAbertura getTermoabertura() {
        return termoabertura;
    }

    public void setTermoabertura(TermoAbertura termoabertura) {
        this.termoabertura = termoabertura;
    }
    
    

}
