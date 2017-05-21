package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gplearning.gplearning.Converters.EtapaProjetoConverter;
import com.gplearning.gplearning.Enums.EtapaProjeto;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.DaoException;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(nameInDb = "etapa")
public class Etapa {
    @Id
    private Long _id;

    private int id;

    private String nome;
    private Date criacao;
    private Date termino;
    private Date conclusao;
    @Convert(converter = EtapaProjetoConverter.class, columnType = String.class)
    private EtapaProjeto etapa;

    @Transient
    private List<Indicador> indicadores;

    private Long idTurma;
    @ToOne(joinProperty = "idTurma")
    private Turma turma;

    @ToMany(referencedJoinProperty = "atv_id")
    @OrderBy("_id ASC")
    private List<EtapaParametro> etapaParametros;

    @ToMany(referencedJoinProperty = "idEtapa")
    @OrderBy("criacao ASC")
    private List<Comentario> comentarios;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1970263667)
    private transient EtapaDao myDao;

    @Generated(hash = 1191549063)
    private transient Long turma__resolvedKey;

    public Etapa(long _id) {
        this._id = _id;
    }

    public Etapa(String nome, EtapaProjeto etapa) {
        this.nome = nome;
        this.etapa = etapa;
    }


    @Keep
    public Etapa() {
    }

    @Generated(hash = 92658830)
    public Etapa(Long _id, int id, String nome, Date criacao, Date termino, Date conclusao, EtapaProjeto etapa, Long idTurma) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.criacao = criacao;
        this.termino = termino;
        this.conclusao = conclusao;
        this.etapa = etapa;
        this.idTurma = idTurma;
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

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    public Date getConclusao() {
        return conclusao;
    }

    public void setConclusao(Date conclusao) {
        this.conclusao = conclusao;
    }

    public EtapaProjeto getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaProjeto etapa) {
        this.etapa = etapa;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
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
    public List<Indicador> getIndicadores() {
        return indicadores;
    }

    @Keep
    public void setIndicadores(List<Indicador> indicadores) {
        this.indicadores = indicadores;
    }

    @Keep
    public List<EtapaParametro> getEtapaParametros() {
        return etapaParametros;
    }

    @Keep
    public void setEtapaParametros(List<EtapaParametro> etapaParametros) {
        this.etapaParametros = etapaParametros;
    }

    @Keep
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    @Keep
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1746642629)
    public synchronized void resetEtapaParametros() {
        etapaParametros = null;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1940070317)
    public synchronized void resetComentarios() {
        comentarios = null;
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 551121054)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEtapaDao() : null;
    }

}
