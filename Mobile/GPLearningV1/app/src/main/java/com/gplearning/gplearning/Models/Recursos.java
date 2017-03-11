package com.gplearning.gplearning.Models;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;


@Entity(nameInDb = "recursos")
public class Recursos {

    @Id(autoincrement = true)
    private Long _id;
    private Long id;
    private String nome;

    private long idTarefa;
    @ToOne(joinProperty = "idTarefa")
    private Tarefa tarefa;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2058883519)
    private transient RecursosDao myDao;
    @Generated(hash = 1018287195)
    private transient Long tarefa__resolvedKey;

    public Recursos() {
    }
    @Generated(hash = 1156193474)
    public Recursos(Long _id, Long id, String nome, long idTarefa) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.idTarefa = idTarefa;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(long idTarefa) {
        this.idTarefa = idTarefa;
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 123720161)
    public Tarefa getTarefa() {
        long __key = this.idTarefa;
        if (tarefa__resolvedKey == null || !tarefa__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TarefaDao targetDao = daoSession.getTarefaDao();
            Tarefa tarefaNew = targetDao.load(__key);
            synchronized (this) {
                tarefa = tarefaNew;
                tarefa__resolvedKey = __key;
            }
        }
        return tarefa;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1764080336)
    public void setTarefa(@NotNull Tarefa tarefa) {
        if (tarefa == null) {
            throw new DaoException(
                    "To-one property 'idTarefa' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.tarefa = tarefa;
            idTarefa = tarefa.get_id();
            tarefa__resolvedKey = idTarefa;
        }
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
    @Generated(hash = 528718134)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRecursosDao() : null;
    }
}
