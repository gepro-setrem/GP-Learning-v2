package com.gplearning.gplearning.DAO;


import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Etapa;
import com.gplearning.gplearning.Models.EtapaDao;
import com.gplearning.gplearning.Models.Indicador;
import com.gplearning.gplearning.Models.IndicadorDao;
import com.gplearning.gplearning.Models.Pessoa;
import com.gplearning.gplearning.Models.PessoaDao;
import com.gplearning.gplearning.Models.Turma;
import com.gplearning.gplearning.Models.TurmaDao;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DefaultDAO {

    String url = UrlDefault;

    public Pessoa Login(DaoSession session, String email, String senha) {
        try {
            url += "/login/login/" + email + "/" + senha;
            RestTemplate restTemplate = getResTemplateDefault();

            MetodosPublicos.Log("login", "Url: " + url);
            Pessoa user = restTemplate.getForObject(url, Pessoa.class); //("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);

            //  Pessoa user = null;// = response.
            if (user != null && user.getId() > 0) {
                PessoaDao dao = session.getPessoaDao();
                if (user.getTurma() != null) {
                    user.setIdTurma(SalvaTurmaSqLite(session, user.getTurma()));
                }
                dao.insert(user);
                return user;
            }
        } catch (Exception e) {
            MetodosPublicos.Log("ERROR LOGIN", e.toString());
        }
        return null;
    }


    public List<Pessoa> Select() {

        List<Pessoa> lsUsuario = new ArrayList<>();

        // String url = UrlDefau;
        return lsUsuario;
    }


    private Long SalvaTurmaSqLite(DaoSession session, Turma turma) {
        TurmaDao daoTurma = session.getTurmaDao();
        EtapaDao daoEtapa = session.getEtapaDao();
        IndicadorDao daoIndicador = session.getIndicadorDao();

        if (turma != null) {
            long idTurma = daoTurma.insert(turma);
            MetodosPublicos.Log("turma", "salvo turmaid:" + idTurma);
            if (turma.getEtapas() != null) {
                for (Etapa etapa : turma.getEtapas()) {
                    etapa.setIdTurma(idTurma);
                    daoEtapa.insert(etapa);
                    MetodosPublicos.Log("turma", "salvo etapa:" + etapa.get_id());
                    if (etapa.getIndicadores() != null) {
                        for (Indicador indicador : etapa.getIndicadores()) {
                            if (daoIndicador.queryBuilder().where(IndicadorDao.Properties.Id.eq(indicador.getId())).list().size() == 0) {
                                daoIndicador.insert(indicador);
                                MetodosPublicos.Log("turma", "salvo indicador:" + indicador.get_id());
                            }
                        }
                    }
                    MetodosPublicos.Log("turma", "salvo etapa:" + etapa.get_id());
                }
            }
            return idTurma;
        }
        return null;
    }

}



