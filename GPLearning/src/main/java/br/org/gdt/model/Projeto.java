package br.org.gdt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Projeto implements Serializable {

    @SequenceGenerator(name = "genprojeto", sequenceName = "seqprojeto", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genprojeto")
    @Id
    private int pro_id;
    @Column(length = 200)
    private String pro_vnome;
    @Column(length = 2500)
    private String pro_tdescricao;

    @Column(length = 200)
    private String pro_vempresa;

    private Date pro_dcriacao;
    private Date pro_dalteracao;
//    @ManyToOne
//     private Usuario gerente;
//    @ManyToOne
    //    private Turma turma;

    public Projeto() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.pro_id;
        hash = 97 * hash + Objects.hashCode(this.pro_vnome);
        hash = 97 * hash + Objects.hashCode(this.pro_tdescricao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projeto other = (Projeto) obj;
        if (this.pro_id != other.pro_id) {
            return false;
        }
        if (!Objects.equals(this.pro_vnome, other.pro_vnome)) {
            return false;
        }
        if (!Objects.equals(this.pro_tdescricao, other.pro_tdescricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return pro_vnome;
    }

}
