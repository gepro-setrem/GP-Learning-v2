package br.org.gdt.bll;

import br.org.gdt.dao.TarefaDAO;
import br.org.gdt.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tarefaBLL")
public class TarefaBLL extends BLL<Tarefa> {

    @Autowired
    private TarefaDAO dao;
}
