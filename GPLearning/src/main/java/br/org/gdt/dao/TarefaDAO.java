package br.org.gdt.dao;

import br.org.gdt.model.Tarefa;
import org.springframework.stereotype.Repository;

@Repository("tarefaDAO")
public class TarefaDAO extends DAO<Tarefa> {

    public TarefaDAO() {
        classe = Tarefa.class;
    }
}
