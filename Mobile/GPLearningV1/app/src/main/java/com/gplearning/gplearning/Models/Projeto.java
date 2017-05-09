package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(nameInDb = "projeto")
public class Projeto {

    @Id
    private Long _id;

    private int id;
    private String nome;
    private String descricao;
    private String empresa;
    private String estado;
    private String escopo;

    private String planoEscopo;
    private String planoProjeto;

    private Date criacao;
    private Date alteracao;
    private String comentarioInstrutor;

    private Long idGerente;
    @ToOne(joinProperty = "idGerente")
    private Pessoa gerente;

    private Long idTurma;
    @ToOne(joinProperty = "idTurma")
    private Turma turma;

    private Long idTermoAbertura;
    @ToOne(joinProperty = "idTermoAbertura")
    private TermoAbertura termoAbertura;

    @ToMany(referencedJoinProperty = "IdProjeto")
    @OrderBy("_id ASC")
    private List<Stakeholder> stakeholders;

    @ToMany(referencedJoinProperty = "IdProjeto")
    @OrderBy("_id ASC")
    private List<Requisito> requisitos;


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

    @Generated(hash = 1091460758)
    private transient Long termoAbertura__resolvedKey;

    public Projeto() {
    }

    public Projeto(String nome) {
        this.nome = nome;
    }

    @Keep
    public Projeto(Long _id, int id, String nome, String descricao, String empresa, String estado, String escopo, String planoEscopo, String planoProjeto, Date criacao, Date alteracao, String comentarioInstrutor, Long idGerente, Long idTurma, Long idTermoAbertura) {
        this._id = _id;
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
        this.idGerente = idGerente;
        this.idTurma = idTurma;
        this.idTermoAbertura = idTermoAbertura;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Long getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(Long idGerente) {
        this.idGerente = idGerente;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public Long getIdTermoAbertura() {
        return idTermoAbertura;
    }

    public void setIdTermoAbertura(Long idTermoAbertura) {
        this.idTermoAbertura = idTermoAbertura;
    }

    @Keep
    public TermoAbertura getTermoAbertura() {
        return termoAbertura;
    }

    @Keep
    public void setTermoAbertura(TermoAbertura termoAbertura) {
        this.termoAbertura = termoAbertura;
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

    @Keep
    public List<Stakeholder> getStakeholders() {
        return stakeholders;
    }

    @Keep
    public void setStakeholders(List<Stakeholder> stakeholders) {
        this.stakeholders = stakeholders;
    }

    @Keep
    public List<Requisito> getRequisitos() {
        return requisitos;
    }

    @Keep
    public void setRequisitos(List<Requisito> requisitos) {
        this.requisitos = requisitos;
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

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1273581113)
    public synchronized void resetStakeholders() {
        stakeholders = null;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1681398261)
    public synchronized void resetRequisitos() {
        requisitos = null;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 770581243)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getProjetoDao() : null;
    }


}
