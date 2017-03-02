package br.org.gdt.bll;

import br.org.gdt.dao.TurmaDAO;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("turmaBLL")
public class TurmaBLL extends BLL<Turma> {

    @Autowired
    private TurmaDAO dao;

    public List<Turma> findbyProfessor(Usuario professor) {
        return dao.findbyProfessor(professor);
    }
}
