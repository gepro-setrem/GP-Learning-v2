package com.gplearning.gplearning.Controllers;

//import android.app.Fragment;
//import android.app.FragmentTransaction;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gplearning.gplearning.Adapters.ProjetoAdapter;
import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.DAO.ProjetoDAO;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.Models.ProjetoDao;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ProjetoFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private ProjetoAdapter projetoAdapter;
    // private OnListFragmentInteractionListener listenerClick;
    //  private OnListFragmentInteractionListener listenerLongClick;
    private View view;
    private RecyclerView recyclerView;
    public List<Projeto> lsProjetos = new ArrayList<>();
    private ProjetoDao dao;


    public ProjetoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_projeto_list, container, false);
//        listenerclick = new onlistfragmentinteractionlistener() {
//            @override
//            public void onlistfragmentinteraction(projeto item) {
//                log.i("event", "clicou no projeto:" + item.getnome());
//                fragment fragment = etapasfragment.newinstance(item.get_id());
//                fragmentmanager manager = getactivity().getsupportfragmentmanager();
//                manager.begintransaction().replace(r.id.content_frame, fragment).commit();
//
//            }
//        };

        // set the adapter
        if (view instanceof RecyclerView) {
            Log.i("Event", "Chegou na ProjetoFragment");
            recyclerView = (RecyclerView) view.findViewById(R.id.projetoListview);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            //((LinearLayoutManager) layoutManager).setStackFromEnd(true);
            recyclerView.setLayoutManager(layoutManager);
            projetoAdapter = new ProjetoAdapter(lsProjetos, getActivity()); //new ProjetoRecyclerViewAdapter(lsProjetos, listenerClick, listenerLongClick);
            recyclerView.setAdapter(projetoAdapter);
            recyclerView.addOnItemTouchListener(new MetodosPublicos.RecyclerItemClickListener(getActivity(), recyclerView, new MetodosPublicos.RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Fragment fragment = EtapasFragment.newInstance(lsProjetos.get(position).get_id());
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                    Log.i("Event", "Clicou");
                }

                @Override
                public void onLongItemClick(View view, int position) {
                    Log.i("Event", "Long Click");
                }
            }));
            DaoSession daoSession = ((App) getActivity().getApplication()).getDaoSession();
            dao = daoSession.getProjetoDao();
            new CarregaProjetos().execute();
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private List<Projeto> getProjetos() {
        List<Projeto> lsProjetos = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            Projeto pj = new Projeto();
//            pj.set_id(Long.valueOf(i+1));
//            pj.setNome("Projeto "+i);
//            pj.setGerente("Gerente "+i);
//            pj.setEmpresa("Empresa Oficial "+i);
//            lsProjetos.add(pj);
//        }
        //   PessoaDao

//        Projeto pj = new Projeto();
//        pj.setId(Long.valueOf(1));
//        pj.setNome("Projeto de melhoria de rede de computadores de uma fábrica de móveis");
//        Pessoa pessoa = new Pessoa();
//        pessoa.setNome("Tiago Luis cesa Seibel");
//        pj.setGerente(pessoa);
//        pj.setEmpresa("Jaeli Móveis");
//        lsProjetos.add(pj);
//
//        pj = new Projeto();
//        pj.setId(Long.valueOf(2));
//        pj.setNome("Instalação e Configuração da Rede de computadores");
//        pj.setGerente(pessoa);
//        pj.setEmpresa("Empresa CTT");
//        lsProjetos.add(pj);
//
//
//        pj = new Projeto();
//        pj.setId(Long.valueOf(3));
//        pj.setNome("Projeto CTT Logística");
//        pj.setGerente(pessoa);
//        pj.setEmpresa("CTT Logística");
//        lsProjetos.add(pj);
//
//
//        pj = new Projeto();
//        pj.setId(Long.valueOf(4));
//        pj.setNome("Projeto de software para empresa ABC");
//        pj.setGerente(pessoa);
//        pj.setEmpresa("Empresa ABC");
//        lsProjetos.add(pj);
//
//
//        pj = new Projeto();
//        pj.setId(Long.valueOf(1));
//        pj.setNome("VesteFin");
//        pj.setGerente(pessoa);
//        pj.setEmpresa("VesteBem");
//        lsProjetos.add(pj);

        return lsProjetos;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Projeto item);
    }

    private class CarregaProjetos extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
            ProjetoDAO projetoDAO = new ProjetoDAO();

            ///select no sqlite
            if (MetodosPublicos.ModoAcessoAluno(getActivity())) {
                lsProjetos.addAll(projetoDAO.SelecionaProjetosAluno(MetodosPublicos.SelecionaSessaoidExterno(getActivity())));//getProjetos()); //dao.loadAll();
            } else {
                lsProjetos.addAll(projetoDAO.SelecionaProjetosProfessor(MetodosPublicos.SelecionaSessaoidExterno(getActivity())));
            }
            MetodosPublicos.Log("projetos", " retorno com:" + lsProjetos.size());
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            ProgressBar pg = ((ProgressBar) getActivity().findViewById(R.id.projetoProgressbar));
            if (pg != null)
                pg.setVisibility(View.GONE);
            MetodosPublicos.Log("projetos", " PostExecuted");

            if (lsProjetos.size() == 0)
                ((TextView) getActivity().findViewById(R.id.TxtNenhumRegistro)).setVisibility(View.VISIBLE);
            else {
                ((RecyclerView) view.findViewById(R.id.projetoListview)).setVisibility(View.VISIBLE);
                projetoAdapter.notifyDataSetChanged();
                projetoAdapter.notifyItemInserted(1);
            }
        }
    }


}
