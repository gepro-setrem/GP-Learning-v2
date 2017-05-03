package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(nameInDb = "projeto")
public class Projeto {

//    @Id(autoincrement = true)
//    private Long _id;

    @Id
    private Long id;
    private String nome;
    private String descricao;
    private String empresa;
    private String estado;
    private String escopo;

    private String planoEscopo;
    private String planoProjeto;

    //  private Pessoa gerente;
    private Date criacao;
    private Date alteracao;
    private String comentarioInstrutor;

    private long ger_id;
    @ToOne(joinProperty = "ger_id")
    private Pessoa gerente;

    private long tur_id;
    @ToOne(joinProperty = "tur_id")
    private Turma turma;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 235931277)
    private transient ProjetoDao myDao;

    @Generated(hash = 68582640)
    private transient Long gerente__resolvedKey;

    @Generated(hash = 1191549063)
    private transient Long turma__resolvedKey;

    public Projeto() {
    }

    public Projeto(String nome) {
        this.nome = nome;
    }


    @Keep
    public Projeto(Long id, String nome, String descricao, String empresa,
                   String estado, String escopo, Date criacao, Date alteracao,
                   String comentarioInstrutor, long ger_id, long tur_id) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.empresa = empresa;
        this.estado = estado;
        this.escopo = escopo;
        this.criacao = criacao;
        this.alteracao = alteracao;
        this.comentarioInstrutor = comentarioInstrutor;
        this.ger_id = ger_id;
        this.tur_id = tur_id;
    }

    @Generated(hash = 731642018)
    public Projeto(Long id, String nome, String descricao, String empresa, String estado,
                   String escopo, String planoEscopo, String planoProjeto, Date criacao,
                   Date alteracao, String comentarioInstrutor, long ger_id, long tur_id) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.empresa = empresa;
        this.estado = estado;
        this.escopo = escopo;
        this.planoEscopo = planoEscopo;
        this.planoProjeto = planoProjeto;
        this.criacao = criacao;
        this.alteracao = alteracao;
        this.comentarioInstrutor = comentarioInstrutor;
        this.ger_id = ger_id;
        this.tur_id = tur_id;
    }

    public Long getIdExterno() {
        return this.id;
    }

    public void setIdExterno(Long idExterno) {
        this.id = idExterno;
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
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

    public long getGer_id() {
        return ger_id;
    }

    public void setGer_id(long ger_id) {
        this.ger_id = ger_id;
    }

    public long getTur_id() {
        return tur_id;
    }

    public void setTur_id(long tur_id) {
        this.tur_id = tur_id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Keep
    public Pessoa getGerente() {
        return gerente;
    }

    @Keep
    public void setGerente(Pessoa gerente) {
        this.gerente = gerente;
    }

    @Keep
    public Turma getTurma() {
        return turma;
    }

    @Keep
    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public String getPlanoEscopo() {
        return this.planoEscopo;
    }

    public void setPlanoEscopo(String planoEscopo) {
        this.planoEscopo = planoEscopo;
    }

    public String getPlanoProjeto() {
        return this.planoProjeto;
    }

    public void setPlanoProjeto(String planoProjeto) {
        this.planoProjeto = planoProjeto;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 770581243)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getProjetoDao() : null;
    }
}
