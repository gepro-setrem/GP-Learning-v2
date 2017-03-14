package br.org.gdt.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "login", uniqueConstraints = @UniqueConstraint(name = "login_unique_idx", columnNames = {"log_vemail", "pes_id"}))
public class Login implements Serializable {

    @Id
    @Column(name = "log_vemail", length = 200)
    private String email;
    
    @Column(name = "log_vsenha", length = 200)
    private String senha;

    @OneToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @OneToMany(mappedBy = "login", cascade = CascadeType.ALL)
    private List<LoginRole> loginRoles;

    public Login() {
    }

    public Login(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<LoginRole> getLoginRoles() {
        return loginRoles;
    }

    public void setLoginRoles(List<LoginRole> loginRoles) {
        this.loginRoles = loginRoles;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.email);
        hash = 71 * hash + Objects.hashCode(this.pessoa);
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
        final Login other = (Login) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        return true;
    }

}
