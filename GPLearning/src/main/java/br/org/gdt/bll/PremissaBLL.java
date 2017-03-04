package br.org.gdt.bll;

import br.org.gdt.dao.PremissaDAO;
import br.org.gdt.model.Premissa;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("premissaBLL")
public class PremissaBLL extends BLL<Premissa> {

    @Autowired
    private PremissaDAO dao;

    public List<Premissa> findbytermoabertura(TermoAbertura termoabertura) {
        return dao.findbytermoabertura(termoabertura);
    }
}
