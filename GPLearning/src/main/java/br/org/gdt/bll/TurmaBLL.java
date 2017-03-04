package br.org.gdt.bll;

import br.org.gdt.dao.TurmaDAO;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("turmaBLL")
public class TurmaBLL {

    @Autowired
    private TurmaDAO dao;

    @Transactional
    public void insert(Turma item) {
        dao.insert(item);
    }

    @Transactional
    public void update(Turma item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public Turma findById(long id) {
        return (Turma) dao.findById(id);
    }

    public List<Turma> findAll() {
        return dao.findAll();
    }

    public List<Turma> findbyProfessor(Usuario professor) {
        return dao.findbyProfessor(professor);
    }
}
