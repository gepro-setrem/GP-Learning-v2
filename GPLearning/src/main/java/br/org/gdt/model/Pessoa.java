package br.org.gdt.model;

import br.org.gdt.enumerated.Status;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Entity
public class Pessoa implements Serializable {

    @SequenceGenerator(name = "genpessoa", sequenceName = "seqpessoa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genpessoa")
    @Id
    @Column(name = "pes_id")
    private int id;

    @Column(name = "pes_vnome", length = 200)
    private String nome;

    @Column(name = "pes_vemail", length = 200)
    private String email;

    @Column(name = "pes_vtelefone", length = 20)
    private String telefone;

    @Column(name = "pes_vstatus")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "pes_dcriacao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date criacao;

    @Column(name = "pes_dalteracao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date alteracao;

    @Column(name = "pes_ikarma")
    private int karma;

    @Column(name = "pes_bimagem")
    private byte[] imagem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tur_id")
    private Turma turma;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.REMOVE)//, fetch = FetchType.EAGER)
    private List<Turma> turmasprofessor;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.REMOVE)//, fetch = FetchType.EAGER)
    private List<Indicador> indicadoresprofessor;

    @OneToMany(mappedBy = "gerente", cascade = CascadeType.REMOVE)
    private List<Projeto> projetosgerente;

    @ManyToMany(mappedBy = "componentes", cascade = CascadeType.REMOVE)
    private List<Projeto> projetos;

    //@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @OneToOne(mappedBy = "pessoa")
    private Login login;

    public Pessoa() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public byte[] getImagem() {
        if (imagem != null && imagem.length > 0) {
            return imagem;
        } else {
            return null;
        }
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Turma> getTurmasprofessor() {
        return turmasprofessor;
    }

    public void setTurmasprofessor(List<Turma> turmasprofessor) {
        this.turmasprofessor = turmasprofessor;
    }

    public List<Indicador> getIndicadoresprofessor() {
        return indicadoresprofessor;
    }

    public void setIndicadoresprofessor(List<Indicador> indicadoresprofessor) {
        this.indicadoresprofessor = indicadoresprofessor;
    }

    public List<Projeto> getProjetosgerente() {
        return projetosgerente;
    }

    public void setProjetosgerente(List<Projeto> projetosgerente) {
        this.projetosgerente = projetosgerente;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id;
        return hash;
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
        final Pessoa other = (Pessoa) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    public StreamedContent getStreamedImagem() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
                return new DefaultStreamedContent();
            } else {
                StreamedContent img = null;
                if (imagem != null && imagem.length > 0) {
                    ByteArrayInputStream bais = new ByteArrayInputStream(imagem);
                    img = new DefaultStreamedContent(bais);
                }
                return img;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
