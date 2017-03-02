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
import javax.persistence.Table;

@Entity
@Table(name = "comentario")
public class Comentario implements Serializable {

    @SequenceGenerator(name = "gencomentario", sequenceName = "seqcomentario", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gencomentario")
    
    @Id
    @Column(name = "com_id")
    private int id;

    @Column(name = "com_ttexto", length = 2500)
    private String texto;

    @Column(name = "com_dcriacao")
    private Date criacao;

    @ManyToOne
    @Column(name = "usu_id")
    private Usuario aluno;

    public Comentario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

}
