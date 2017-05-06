package br.org.gdt.model;

import br.org.gdt.enumerated.EtapaProjeto;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

@Entity
public class Etapa implements Serializable {

    @SequenceGenerator(name = "genetapa", sequenceName = "seqetapa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genetapa")

    @Id
    @Column(name = "atv_id")
    private int id;

    @Column(name = "atv_vnome", length = 200)
    private String nome;

    @Column(name = "atv_dcriacao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date criacao;

    @Column(name = "atv_dtermino")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date termino;

    @Column(name = "atv_dconclusao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date conclusao;

    @Column(name = "atv_vetapa")
    @Enumerated(EnumType.STRING)
    private EtapaProjeto etapa;

    @ManyToOne
    @JoinColumn(name = "tur_id")
    private Turma turma;

    @OneToMany(mappedBy = "etapa")
    private List<EtapaParametro> etapaParametros;

    @OneToMany(mappedBy = "etapa")
    private List<Comentario> comentarios;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public EtapaProjeto getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaProjeto etapa) {
        this.etapa = etapa;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<EtapaParametro> getEtapaParametros() {
        return etapaParametros;
    }

    public void setEtapaParametros(List<EtapaParametro> etapaParametros) {
        this.etapaParametros = etapaParametros;
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

    public List<EtapaParametro> getAtividadeParametros() {
        return etapaParametros;
    }

    public void setAtividadeParametros(List<EtapaParametro> etapaParametros) {
        this.etapaParametros = etapaParametros;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Etapa other = (Etapa) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

}
