package com.gplearning.gplearning.Controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gplearning.gplearning.Adapters.ProjetoAdapter;
import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.Models.ProjetoDao;
import com.gplearning.gplearning.R;

import java.util.ArrayList;
import java.util.List;

public class ProjetosActivity extends AppCompatActivity {

    private ProjetoDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projetos);

        dao = App.getDaoSessionApp(this).getProjetoDao();

        List<Projeto>lsProjetos = getProjetos(); //dao.queryBuilder().orderAsc(ProjetoDao.Properties._id).build().list();
        ListView listView = (ListView)findViewById(R.id.projetosListview);
       // ArrayAdapter<Projeto>arrayAdapter = new ArrayAdapter<Projeto>(this,android.R.layout.simple_list_item_1,lsProjetos);
        ProjetoAdapter projetoAdapter = new ProjetoAdapter(lsProjetos,this);
        listView.setAdapter(projetoAdapter);

    }

    private List<Projeto> getProjetos() {
        List<Projeto>lsProjetos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Projeto pj = new Projeto();
            pj.setNome("Projeto "+i);
            pj.setGerente("Gerente "+i);
            pj.setEmpresa("Empresa Oficial "+i);
            lsProjetos.add(pj);
        }
        return lsProjetos;
    }



}
