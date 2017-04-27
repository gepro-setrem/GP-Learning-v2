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

    public List<Pessoa> findByUsers(Turma turma, Role grupo) {
        return dao.findByUsers(turma, grupo);
    }

    public List<Pessoa> findByTurma(List<Turma> turmas) {
        return dao.findByTurma(turmas);
    }

    public Pessoa findbyEmail(String email) {
        if (email != null && !email.isEmpty()) {
            return dao.findbyEmail(email);
        }
        return null;
    }

    public List<Pessoa> findAllUsers(Pessoa pessoa) {
        return dao.findAllUsers(pessoa);
    }
}
