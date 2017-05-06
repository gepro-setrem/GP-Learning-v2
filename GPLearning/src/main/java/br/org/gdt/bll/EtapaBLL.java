package br.org.gdt.bll;

import br.org.gdt.dao.EtapaDAO;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Turma;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("etapaBLL")
public class EtapaBLL extends BLL<Etapa> {

    @Autowired
    private EtapaDAO dao;

    public List<Etapa> findbyTurma(Turma turma) {
        List<Etapa> lsEtapa = new ArrayList<>();
        if (turma != null && turma.getId() > 0) {
            lsEtapa = dao.findbyTurma(turma);
        }
        return lsEtapa;
    }
}
