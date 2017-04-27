package br.org.gdt.bll;

import br.org.gdt.dao.TurmaDAO;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("turmaBLL")
public class TurmaBLL extends BLL<Turma> {

    @Autowired
    private TurmaDAO dao;
    @Autowired
    private TurmaParametroBLL turmaParametroBLL;

    public List<Turma> findbyProfessor(Pessoa professor) {
        List<Turma> turmas = new ArrayList<>();
        if (professor != null && professor.getId() > 0) {
            turmas = dao.findbyProfessor(professor);
        }
        return turmas;
    }
}
