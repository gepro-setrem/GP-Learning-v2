package com.gplearning.gplearning.Controllers;

//import android.app.Fragment;
//import android.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gplearning.gplearning.Adapters.ProjetoRecyclerViewAdapter;
import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.Models.ProjetoDao;
import com.gplearning.gplearning.Models.UsuarioDao;
import com.gplearning.gplearning.R;

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
    private OnListFragmentInteractionListener mListener;
    private View view;
    private List<Projeto> lsProjetos = new ArrayList<>();
    private ProjetoDao dao;


    public ProjetoFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_projeto_list, container, false);
        DaoSession daoSession = ((App) getActivity().getApplication()).getDaoSession();
        dao = daoSession.getProjetoDao();

        mListener = new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(Projeto item) {
                Log.i("event", "clicou no projeto:" + item.getNome());
                Fragment fragment = EtapasFragment.newInstance(item.get_id());
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            }
        };

        // Set the adapter
        if (view instanceof RecyclerView) {
            Log.i("Event", "Chegou na ProjetoFragment");
            RecyclerView recyclerView = (RecyclerView) view;


            //dao.queryBuilder().where(ProjetoDao.Properties.G)
            lsProjetos = getProjetos();
            recyclerView.setAdapter(new ProjetoRecyclerViewAdapter(lsProjetos, mListener));
            if (lsProjetos.size() == 0) {
                ((RecyclerView) view.findViewById(R.id.projetoListview)).setVisibility(View.GONE);
                ((TextView) getActivity().findViewById(R.id.TxtNenhumRegistro)).setVisibility(View.VISIBLE);
            }
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


        Projeto pj = new Projeto();
        pj.set_id(Long.valueOf(1));
        pj.setNome("Projeto de melhoria de rede de computadores de uma fábrica de móveis");
        pj.setGerente("Tiago Luis cesa Seibel");
        pj.setEmpresa("Jaeli Móveis");
        lsProjetos.add(pj);

        pj = new Projeto();
        pj.set_id(Long.valueOf(2));
        pj.setNome("Instalação e Configuração da Rede de computadores");
        pj.setGerente("Tiago Luis cesa Seibel");
        pj.setEmpresa("Empresa CTT");
        lsProjetos.add(pj);


        pj = new Projeto();
        pj.set_id(Long.valueOf(3));
        pj.setNome("Projeto CTT Logística");
        pj.setGerente("Tiago Luis cesa Seibel");
        pj.setEmpresa("CTT Logística");
        lsProjetos.add(pj);


        pj = new Projeto();
        pj.set_id(Long.valueOf(4));
        pj.setNome("Projeto de software para empresa ABC");
        pj.setGerente("Tiago Luis cesa Seibel");
        pj.setEmpresa("Empresa ABC");
        lsProjetos.add(pj);


        pj = new Projeto();
        pj.set_id(Long.valueOf(1));
        pj.setNome("VesteFin");
        pj.setGerente("Tiago Luis cesa Seibel");
        pj.setEmpresa("VesteBem");
        lsProjetos.add(pj);


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
}
