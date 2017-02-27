package br.org.gdt.bll;

import br.org.gdt.dao.TurmaDAO;
import br.org.gdt.model.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("turmaBLL")
public class TurmaBLL extends BLL<Turma> {

    @Autowired
    private TurmaDAO dao;
}
