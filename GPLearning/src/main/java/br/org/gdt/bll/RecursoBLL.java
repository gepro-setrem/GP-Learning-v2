package br.org.gdt.bll;

import br.org.gdt.dao.RecursoDAO;
import br.org.gdt.model.Recurso;
import br.org.gdt.model.Tarefa;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("recursoBLL")
public class RecursoBLL extends BLL<Recurso> {

    @Autowired
    private RecursoDAO dao;

    public List<Recurso> findbyRecursos(Tarefa tarefa) {
        List<Recurso> recursos = new ArrayList<>();
        if (tarefa != null && tarefa.getId() > 0) {
            recursos = dao.findbyRecursos(tarefa.getId());
        }
        return recursos;
    }
}
