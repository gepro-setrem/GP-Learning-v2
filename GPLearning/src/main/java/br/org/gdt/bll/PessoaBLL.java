package br.org.gdt.bll;

import br.org.gdt.dao.PessoaDAO;
import br.org.gdt.enumerated.Role;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pessoaBLL")
public class PessoaBLL extends BLL<Pessoa> {

    @Autowired
    private PessoaDAO dao;

    public List<Pessoa> findByUsers(Turma turma, Role grupo) {
        return dao.findByUsers(turma, grupo);
    }

    public List<Pessoa> findByTurma(List<Turma> turmas) {
        return dao.findByTurma(turmas);
    }

    public Pessoa findByEmail(String email) {
        return dao.findByEmail(email);
    }

    public List<Pessoa> findAllUsers(Pessoa pessoa) {
        return dao.findAllUsers(pessoa);
    }
}
