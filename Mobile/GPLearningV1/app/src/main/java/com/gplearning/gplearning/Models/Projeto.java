package com.gplearning.gplearning.Models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

import java.util.Date;

@Entity(nameInDb = "projeto")
public class Projeto {

    @Id(autoincrement = true)
    private Long _id;

    private Long idExterno;
    private String nome;
    private String descricao;
    private String gerente;
    private String empresa;
    private Date criacao;
    private Date alteracao;
    private String comentarioInstrutor;

    public Projeto() {}

    public Projeto(String nome) {
        this.nome = nome;
    }

    @Keep
    public Projeto(long _id, long idExterno, String nome, String descricao,
                   String gerente, String empresa, Date criacao, Date alteracao,
                   String comentarioInstrutor) {
        this._id = _id;
        this.idExterno = idExterno;
        this.nome = nome;
        this.descricao = descricao;
        this.gerente = gerente;
        this.empresa = empresa;
        this.criacao = criacao;
        this.alteracao = alteracao;
        this.comentarioInstrutor = comentarioInstrutor;
    }

    @Generated(hash = 174373330)
    public Projeto(Long _id, Long idExterno, String nome, String descricao, String gerente,
            String empresa, Date criacao, Date alteracao, String comentarioInstrutor) {
        this._id = _id;
        this.idExterno = idExterno;
        this.nome = nome;
        this.descricao = descricao;
        this.gerente = gerente;
        this.empresa = empresa;
        this.criacao = criacao;
        this.alteracao = alteracao;
        this.comentarioInstrutor = comentarioInstrutor;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getId() {
        return idExterno;
    }

    public void setId(Long idExterno) {
        this.idExterno = idExterno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Date getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(Date alteracao) {
        this.alteracao = alteracao;
    }

    public String getComentarioInstrutor() {
        return comentarioInstrutor;
    }

    public void setComentarioInstrutor(String comentarioInstrutor) {
        this.comentarioInstrutor = comentarioInstrutor;
    }

    public Long getIdExterno() {
        return this.idExterno;
    }

    public void setIdExterno(Long idExterno) {
        this.idExterno = idExterno;
    }



}
