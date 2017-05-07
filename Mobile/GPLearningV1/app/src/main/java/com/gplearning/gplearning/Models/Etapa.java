package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gplearning.gplearning.Converters.EtapaProjetoConverter;
import com.gplearning.gplearning.Enums.EtapaProjeto;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Convert;
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
    private int pontuacaoMedia;

//    @ToMany
//    @JoinEntity(entity = IndicadoresAtividades.class,
//            sourceProperty = "idAtividade",
//            targetProperty = "idIndicador")
//    private List<IndicadoresAtividades> lsIndicadoresAtividades;

    @ToMany(referencedJoinProperty = "idAtividade")
    @OrderBy("_id ASC")
    private List<IndicadoresAtividades> lsIndicadoresAtividades;

//    private long pro_id;
//    @ToOne(joinProperty = "pro_id")
//    private Projeto projeto;

    private Long idTurma;
    @ToOne(joinProperty = "idTurma")
    private Turma turma;

    @ToMany(referencedJoinProperty = "atv_id")
    @OrderBy("_id ASC")
    private List<ParametrosAtividade> lsParametros;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1970263667)
    private transient EtapaDao myDao;

    @Generated(hash = 1191549063)
    private transient Long turma__resolvedKey;

    public int getPontuacaoMedia() {
        return pontuacaoMedia;
    }

    public void setPontuacaoMedia(int pontuacaoMedia) {
        this.pontuacaoMedia = pontuacaoMedia;
    }

    public Etapa(long _id) {
        this._id = _id;
    }

    public Etapa(String nome, EtapaProjeto etapa) {
        this.nome = nome;
        this.etapa = etapa;
    }

    @Keep
    public Etapa(long _id, int id, String nome, Date criacao, Date termino, Date conclusao, EtapaProjeto etapa, Long idTurma) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.criacao = criacao;
        this.termino = termino;
        this.conclusao = conclusao;
        this.etapa = etapa;
        this.idTurma = idTurma;
    }

    @Keep
    public Etapa() {
    }


    public Etapa(Long _id, int id, String nome, Date criacao, Date termino, Date conclusao, EtapaProjeto etapa,
                 int pontuacaoMedia) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.criacao = criacao;
        this.termino = termino;
        this.conclusao = conclusao;
        this.etapa = etapa;
        this.pontuacaoMedia = pontuacaoMedia;
    }

    @Generated(hash = 862528198)
    public Etapa(Long _id, int id, String nome, Date criacao, Date termino, Date conclusao, EtapaProjeto etapa, int pontuacaoMedia,
            Long idTurma) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.criacao = criacao;
        this.termino = termino;
        this.conclusao = conclusao;
        this.etapa = etapa;
        this.pontuacaoMedia = pontuacaoMedia;
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 64157708)
    public List<IndicadoresAtividades> getLsIndicadoresAtividades() {
        if (lsIndicadoresAtividades == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IndicadoresAtividadesDao targetDao = daoSession.getIndicadoresAtividadesDao();
            List<IndicadoresAtividades> lsIndicadoresAtividadesNew = targetDao._queryEtapa_LsIndicadoresAtividades(_id);
            synchronized (this) {
                if (lsIndicadoresAtividades == null) {
                    lsIndicadoresAtividades = lsIndicadoresAtividadesNew;
                }
            }
        }
        return lsIndicadoresAtividades;
    }

    public void setLsIndicadoresAtividades(List<IndicadoresAtividades> lsIndicadoresAtividades) {
        this.lsIndicadoresAtividades = lsIndicadoresAtividades;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 143405805)
    public List<ParametrosAtividade> getLsParametros() {
        if (lsParametros == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ParametrosAtividadeDao targetDao = daoSession.getParametrosAtividadeDao();
            List<ParametrosAtividade> lsParametrosNew = targetDao._queryEtapa_LsParametros(_id);
            synchronized (this) {
                if (lsParametros == null) {
                    lsParametros = lsParametrosNew;
                }
            }
        }
        return lsParametros;
    }

    public void setLsParametros(List<ParametrosAtividade> lsParametros) {
        this.lsParametros = lsParametros;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 129847840)
    public synchronized void resetLsIndicadoresAtividades() {
        lsIndicadoresAtividades = null;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1599991901)
    public synchronized void resetLsParametros() {
        lsParametros = null;
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
