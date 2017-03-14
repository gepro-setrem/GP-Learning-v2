package br.org.gdt.model;

import br.org.gdt.enumerated.Role;
import br.org.gdt.model.LoginRole.LoginRolePK;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(LoginRolePK.class)
public class LoginRole implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "log_vemail")
    private Login login;

    @Id
    @Column(name = "lgr_vrole")
    @Enumerated(EnumType.STRING)
    private Role role;

    public LoginRole() {
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static class LoginRolePK implements Serializable {

        @ManyToOne
        @JoinColumn(name = "email")
        private Login login;

        @Enumerated(EnumType.STRING)
        private Role role;

        public LoginRolePK() {
        }

        public Login getLogin() {
            return login;
        }

        public void setLogin(Login login) {
            this.login = login;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.login);
            hash = 97 * hash + Objects.hashCode(this.role);
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
            final LoginRolePK other = (LoginRolePK) obj;
            if (!Objects.equals(this.login, other.login)) {
                return false;
            }
            if (this.role != other.role) {
                return false;
            }
            return true;
        }
    }
}
