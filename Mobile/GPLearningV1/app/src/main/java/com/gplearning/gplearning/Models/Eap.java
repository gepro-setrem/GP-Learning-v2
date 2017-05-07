package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(nameInDb = "eap")
public class Eap {
    @Id
    private Long _id;

    private Long id;
    private String nome;
    private Date entrega;
    private double valor;

    private Long idPai;
    @ToOne(joinProperty = "idPai")
    private Eap EapPai;

    @ToMany(referencedJoinProperty = "idPai")
    private List<Eap> lsEapSub;

    @ToMany(referencedJoinProperty = "idEap")
    @OrderBy("_id ASC")
    private List<Tarefa> lsTarefas;

    private long pro_id;
    @ToOne(joinProperty = "pro_id")
    private Projeto projeto;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1316680655)
    private transient EapDao myDao;

    @Generated(hash = 886354405)
    private transient Long EapPai__resolvedKey;

    @Generated(hash = 1369604525)
    private transient Long projeto__resolvedKey;

    @Generated(hash = 1760530958)
    public Eap() {
    }

    @Generated(hash = 678906174)
    public Eap(Long _id, Long id, String nome, Date entrega, double valor, Long idPai, long pro_id) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.entrega = entrega;
        this.valor = valor;
        this.idPai = idPai;
        this.pro_id = pro_id;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdPai(Long idPai) {
        this.idPai = idPai;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getEntrega() {
        return entrega;
    }

    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1872235245)
    public Eap getEapPai() {
        Long __key = this.idPai;
        if (EapPai__resolvedKey == null || !EapPai__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EapDao targetDao = daoSession.getEapDao();
            Eap EapPaiNew = targetDao.load(__key);
            synchronized (this) {
                EapPai = EapPaiNew;
                EapPai__resolvedKey = __key;
            }
        }
        return EapPai;
    }

    public long getPro_id() {
        return pro_id;
    }

    public void setPro_id(long pro_id) {
        this.pro_id = pro_id;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1592426740)
    public void setEapPai(Eap EapPai) {
        synchronized (this) {
            this.EapPai = EapPai;
            idPai = EapPai == null ? null : EapPai.get_id();
            EapPai__resolvedKey = idPai;
        }
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1517119008)
    public Projeto getProjeto() {
        long __key = this.pro_id;
        if (projeto__resolvedKey == null || !projeto__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProjetoDao targetDao = daoSession.getProjetoDao();
            Projeto projetoNew = targetDao.load(__key);
            synchronized (this) {
                projeto = projetoNew;
                projeto__resolvedKey = __key;
            }
        }
        return projeto;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1979152853)
    public void setProjeto(@NotNull Projeto projeto) {
        if (projeto == null) {
            throw new DaoException("To-one property 'pro_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.projeto = projeto;
            pro_id = projeto.get_id();
            projeto__resolvedKey = pro_id;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 48231749)
    public List<Eap> getLsEapSub() {
        if (lsEapSub == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EapDao targetDao = daoSession.getEapDao();
            List<Eap> lsEapSubNew = targetDao._queryEap_LsEapSub(_id);
            synchronized (this) {
                if (lsEapSub == null) {
                    lsEapSub = lsEapSubNew;
                }
            }
        }
        return lsEapSub;
    }

    public void setLsEapSub(List<Eap> lsEapSub) {
        this.lsEapSub = lsEapSub;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 805333019)
    public List<Tarefa> getLsTarefas() {
        if (lsTarefas == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TarefaDao targetDao = daoSession.getTarefaDao();
            List<Tarefa> lsTarefasNew = targetDao._queryEap_LsTarefas(_id);
            synchronized (this) {
                if (lsTarefas == null) {
                    lsTarefas = lsTarefasNew;
                }
            }
        }
        return lsTarefas;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 2081873823)
    public synchronized void resetLsEapSub() {
        lsEapSub = null;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 997083221)
    public synchronized void resetLsTarefas() {
        lsTarefas = null;
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

    public Long getIdPai() {
        return this.idPai;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1976154569)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEapDao() : null;
    }
}
