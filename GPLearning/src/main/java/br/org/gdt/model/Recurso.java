package br.org.gdt.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "recursos")
public class Recurso implements Serializable {

    @SequenceGenerator(name = "genrecurso", sequenceName = "seqrecurso", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genrecurso")
    @Id
    @Column(name = "rec_id")
    private int id;

    @Column(name = "rec_vnome", length = 200)
    private int nome;

    public Recurso() {
    }
    
    
}
