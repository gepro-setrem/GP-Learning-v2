package br.org.gdt.bll;

import br.org.gdt.dao.TarefaDAO;
import br.org.gdt.model.Tarefa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tarefaBLL")
public class TarefaBLL extends BLL<Tarefa>{
}
