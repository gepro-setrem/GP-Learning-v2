package br.org.gdt.bll;

import br.org.gdt.dao.IndicadorDAO;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.Pessoa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("indicadorBLL")
public class IndicadorBLL extends BLL<Indicador> {

    @Autowired
    private IndicadorDAO dao;

    public List<Indicador> findbyProfessor(Pessoa professor) {
        return dao.findbyProfessor(professor);
    }
}
