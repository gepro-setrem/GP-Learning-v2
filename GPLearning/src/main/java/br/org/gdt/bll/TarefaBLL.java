package br.org.gdt.bll;

import br.org.gdt.dao.TarefaDAO;
import br.org.gdt.model.Tarefa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tarefaBLL")
public class TarefaBLL {

    @Autowired
    private TarefaDAO dao;

    @Transactional
    public void insert(Tarefa item) {
        dao.insert(item);
    }

    @Transactional
    public void update(Tarefa item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public Tarefa findById(long id) {
        return (Tarefa) dao.findById(id);
    }

    public List<Tarefa> findAll() {
        return dao.findAll();
    }
}
