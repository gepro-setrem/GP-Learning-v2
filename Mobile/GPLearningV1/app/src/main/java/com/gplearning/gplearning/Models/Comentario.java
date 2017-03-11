package com.gplearning.gplearning.Models;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

@Entity(nameInDb = "comentario")
public class Comentario {
    @Id(autoincrement = true)
    private long _id;

    private long id;
    private String texto;
    private Date criacao;

    private Long atv_id;
    @ToOne(joinProperty = "atv_id")
    private Atividade atividade;

    private Long use_id;
    @ToOne(joinProperty = "use_id")
    private Usuario usuario;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 978109081)
    private transient ComentarioDao myDao;

    @Generated(hash = 403996642)
    private transient Long atividade__resolvedKey;

    @Generated(hash = 41136015)
    private transient Long usuario__resolvedKey;

    public Comentario(String texto) {
        this.texto = texto;
    }

    @Generated(hash = 36337182)
    public Comentario(long _id, long id, String texto, Date criacao, Long atv_id, Long use_id) {
        this._id = _id;
        this.id = id;
        this.texto = texto;
        this.criacao = criacao;
        this.atv_id = atv_id;
        this.use_id = use_id;
    }

    @Generated(hash = 751223151)
    public Comentario() {
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Long getAtv_id() {
        return atv_id;
    }

    public void setAtv_id(Long atv_id) {
        this.atv_id = atv_id;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 918348588)
    public Atividade getAtividade() {
        Long __key = this.atv_id;
        if (atividade__resolvedKey == null || !atividade__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AtividadeDao targetDao = daoSession.getAtividadeDao();
            Atividade atividadeNew = targetDao.load(__key);
            synchronized (this) {
                atividade = atividadeNew;
                atividade__resolvedKey = __key;
            }
        }
        return atividade;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1855572062)
    public void setAtividade(Atividade atividade) {
        synchronized (this) {
            this.atividade = atividade;
            atv_id = atividade == null ? null : atividade.get_id();
            atividade__resolvedKey = atv_id;
        }
    }

    public Long getUse_id() {
        return use_id;
    }

    public void setUse_id(Long use_id) {
        this.use_id = use_id;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 917748259)
    public Usuario getUsuario() {
        Long __key = this.use_id;
        if (usuario__resolvedKey == null || !usuario__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UsuarioDao targetDao = daoSession.getUsuarioDao();
            Usuario usuarioNew = targetDao.load(__key);
            synchronized (this) {
                usuario = usuarioNew;
                usuario__resolvedKey = __key;
            }
        }
        return usuario;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1571469125)
    public void setUsuario(Usuario usuario) {
        synchronized (this) {
            this.usuario = usuario;
            use_id = usuario == null ? null : usuario.get_id();
            usuario__resolvedKey = use_id;
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
    @Generated(hash = 1701566171)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getComentarioDao() : null;
    }


}
