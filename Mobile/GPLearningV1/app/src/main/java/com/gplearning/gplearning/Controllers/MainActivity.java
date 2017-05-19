package com.gplearning.gplearning.Controllers;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Enums.Fragments;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Pessoa;
import com.gplearning.gplearning.Models.PessoaDao;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;
import com.gplearning.gplearning.Utils.Sincronizacao;

import org.springframework.web.client.ResourceAccessException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import android.support.v4.app.Fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //  MetodosPublicos.SalvaUltimaSincronizacao(this, RecursosEnum.Comentario, new Date());

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        MenuItem nav_sync = menu.findItem(R.id.nav_last_sync);
        try {
            Date data = MetodosPublicos.SelecionaUltimaSincronizacao(this);
            if (data != null) {
                DateFormat df = new SimpleDateFormat("dd/MM HH:mm");
                nav_sync.setTitle(getString(R.string.synchronized_in) + " " + df.format(data));
                // nav_sync.getTitle()
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!MetodosPublicos.UsuarioAdmin(this)) {
            MenuItem nav_area = menu.findItem(R.id.nav_area);
            nav_area.setVisible(false);
        }


        try {
            AtualizaHeaderMain();
        } catch (ParseException e) {
            MetodosPublicos.Log("ERRO", "erro atualiza perfil:" + e.toString());
        }

        if (!MetodosPublicos.ExisteSessao(this)) {
            Log.i("Event", "string null, vai para login");
            Intent intentL = new Intent(this, LoginActivity.class);
            startActivity(intentL);
        } else {

            if (!MetodosPublicos.ExisteModoAcesso(this)) { //modoAcesso == null) {
                changefragment(Fragments.nivelAcesso.toString());
            } else {
                Intent intent = getIntent();
                if (intent != null) {
                    Log.i("Event", "Tem Intent");
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        Log.i("Event", "Tem bundle");
                        if (bundle.containsKey("PAGE")) {
                            String page = bundle.getString("PAGE"); //intent.getLongExtra("ID", 0);
                            Log.i("Event", "PAGE " + page);
                            changefragment(page);
                        }
                    } else {
                        Log.i("Event", "CArrega projetos");
                        changefragment(Fragments.projetos.toString());
                    }
                }
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
//        if (getFragmentManager().getBackStackEntryCount() > 0) {
//            getFragmentManager().popBackStack();
//        } else {
//            super.onBackPressed();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            // MetodosPublicos.SalvaSessao(this, null, null, null, 0);

            new getAssync().execute();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//http://stackoverflow.com/questions/24777985/how-to-implement-onfragmentinteractionlistener


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_project) {
            changefragment(Fragments.projetos.toString());
//        } else if (item.getItemId() == R.id.nav_comments) {
//            changefragment(Fragments.comentarios.toString());
        } else if (item.getItemId() == R.id.nav_area) {
            changefragment(Fragments.nivelAcesso.toString());
        } else if (item.getItemId() == R.id.nav_profile) {
            changefragment(Fragments.perfil.toString());
        }

        return true;
    }

    public void changefragment(String fragments) {
        Fragment fragment = null;
        Bundle args = new Bundle();

        if (fragments.equals(Fragments.projetos.toString())) {
            // Handle the camera action
            fragment = new ProjetoFragment();
        } else if (fragments == Fragments.comentarios.toString()) {
            // Handle the camera action
            //  fragment = new ProjetoFragment();
        } else if (fragments == Fragments.nivelAcesso.toString()) {
            Intent intent = new Intent(this, NivelAcessoActivity.class);
            startActivity(intent);
        } else if (fragments == Fragments.perfil.toString()) {
            Intent intent = new Intent(this, PerfilActivity.class);
            intent.putExtra("ID", MetodosPublicos.SelecionaSessaoId(this));
            startActivity(intent);
        }
//        else { //if (fragments == Fragments.nivelAcesso) {
//            Intent intent = new Intent(this, NivelAcessoActivity.class);
//            startActivity(intent);
//        }

        if (fragment != null) {
            fragment.setArguments(args);
//            FragmentTransaction transaction = getSupportFragmentManager(); //getFragmentManager().beginTransaction();
//            transaction.add(R.id.content_frame, fragment);
//           // transaction.addToBackStack(null);
//            // Commit the transaction
//            transaction.commit();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    public class getAssync extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                if (MetodosPublicos.IsConnected(MainActivity.this)) {
                    //  RestTemplate restTemplate = new RestTemplate();
                    //  restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    //  Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
                    //   Log.i("WB", quote.toString());
                    Sincronizacao sc = new Sincronizacao();
                    sc.SincronizaAplicativoData(MainActivity.this);
                    return true;
                }else{
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.ConstraintLayoutMAIN), getString(R.string.app_offline), Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            } catch (ResourceAccessException e) {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.ConstraintLayoutMAIN), getString(R.string.synchronization_error_connect), Snackbar.LENGTH_SHORT); //(context, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);
                snackbar.show();
            } catch (Exception e) {
                MetodosPublicos.Log("ERROR", e.toString());
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.ConstraintLayoutMAIN), getString(R.string.synchronization_error), Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.ConstraintLayoutMAIN), getString(R.string.synchronization), Snackbar.LENGTH_SHORT); //(context, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
    }


    public void AtualizaHeaderMain() throws ParseException {
        if (MetodosPublicos.ExisteSessao(this)) {
            MetodosPublicos.Log("Img", "ATUALIZA CAMINHO IMAGEM PERFIL E ATUALZIA APP DATA");
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            View hView = navigationView.getHeaderView(0);
            ((TextView) hView.findViewById(R.id.headerName)).setText(MetodosPublicos.SelecionaSessaoNome(this));
            ((TextView) hView.findViewById(R.id.headerEmail)).setText(MetodosPublicos.SelecionaSessaoEmail(this));

            DaoSession daoSession = ((App) getApplicationContext()).getDaoSession();
            PessoaDao pessoaDao = daoSession.getPessoaDao();
            Pessoa pessoa = pessoaDao.load(MetodosPublicos.SelecionaSessaoId(this));
            if (pessoa != null) {
                MetodosPublicos.CarregaimagemPerfil(this, ((ImageView) hView.findViewById(R.id.headerImage)), pessoa.get_id());
                // String path = MetodosPublicos.SelecionaCaminhoImagem(this, pessoa.get_id());
            }
            new getAssync().execute();
            //  Sincronizacao.SincronizaAplicativoData(MainActivity.this);
        }

    }

}
