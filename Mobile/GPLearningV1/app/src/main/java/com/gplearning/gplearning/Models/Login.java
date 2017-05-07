package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Login {

    private String email;
    private String senha;
    private List<LoginRole> loginRoles;

    public Login() {
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

    public List<LoginRole> getLoginRoles() {
        return loginRoles;
    }

    public void setLoginRoles(List<LoginRole> loginRoles) {
        this.loginRoles = loginRoles;
    }
}
