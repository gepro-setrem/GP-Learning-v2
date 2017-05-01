package br.org.gdt.bll;

import br.org.gdt.dao.MarcoDAO;
import br.org.gdt.model.Marco;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("marcoBLL")
public class MarcoBLL extends BLL<Marco> {

    @Autowired
    private MarcoDAO dao;

    public List<Marco> findbyTermoAbertura(TermoAbertura termoabertura) {
        return dao.findbyTermoAbertura(termoabertura);
    }
}
