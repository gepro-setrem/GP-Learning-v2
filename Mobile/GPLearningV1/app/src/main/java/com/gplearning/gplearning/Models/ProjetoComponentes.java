package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity(nameInDb = "projeto_componentes")
public class ProjetoComponentes {

    @Id
    private Long _id;

    private Long idProjeto;
    private Long idPessoa;

    public ProjetoComponentes() {
    }

    public ProjetoComponentes(Long idProjeto, Long idPessoa) {
        this.idProjeto = idProjeto;
        this.idPessoa = idPessoa;
    }

    @Generated(hash = 643500044)
    public ProjetoComponentes(Long _id, Long idProjeto, Long idPessoa) {
        this._id = _id;
        this.idProjeto = idProjeto;
        this.idPessoa = idPessoa;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

}
