package br.org.gdt.bll;

import br.org.gdt.dao.PessoaDAO;
import br.org.gdt.enumerated.Role;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pessoaBLL")
public class PessoaBLL extends BLL<Pessoa> {

    @Autowired
    private PessoaDAO dao;

    public List<Pessoa> findbyProjeto(Projeto projeto) {
        List<Pessoa> lsPessoa = new ArrayList<>();
        if (projeto != null && projeto.getId() > 0) {
            lsPessoa = dao.findbyProjeto(projeto);
        }
        return lsPessoa;
    }

    public List<Pessoa> findByUsers(Turma turma, Role role) {
        List<Pessoa> lsPessoa = new ArrayList<>();
        if (turma != null && turma.getId() > 0 && role != null) {
            lsPessoa = dao.findbyTurmaUsers(turma, role);
        }
        return lsPessoa;
    }

    public List<Pessoa> findbyTurma(Turma turma) {
        List<Pessoa> lsPessoa = new ArrayList<>();
        if (turma != null && turma.getId() > 0) {
            lsPessoa = dao.findbyTurma(turma);
        }
        return lsPessoa;
    }

    public Pessoa findbyEmail(String email) {
        if (email != null && !email.isEmpty()) {
            return dao.findbyEmail(email);
        }
        return null;
    }

    public List<Pessoa> findbyProfessor(Pessoa professor) {
        List<Pessoa> lsPessoa = new ArrayList<>();
        if (professor != null && professor.getId() > 0) {
            lsPessoa = dao.findbyProfessor(professor);
        }
        return lsPessoa;
    }
}
