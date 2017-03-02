package br.org.gdt.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "atividade")
public class Atividade {

    @SequenceGenerator(name = "genatividade", sequenceName = "seqatividade", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genatividade")
    
    @Id
    @Column(name = "atv_id")
    private int id;

    @Column(name = "atv_vnome")
    private String nome;
    
    @Column(name = "atv_dcriacao")
    private Date Criacao;
    
    @Column(name = "atv_dtermino")
    private Date termino;
    
    @Column(name = "atv_dconclusao")
    private Date conclusao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getCriacao() {
        return Criacao;
    }

    public void setCriacao(Date Criacao) {
        this.Criacao = Criacao;
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
        final Atividade other = (Atividade) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

}
