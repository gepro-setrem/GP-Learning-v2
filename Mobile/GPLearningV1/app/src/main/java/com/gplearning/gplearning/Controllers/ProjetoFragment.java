package com.gplearning.gplearning.Controllers;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

//import android.support.v4.app.Fragment;
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
import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.Models.ProjetoDao;
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

    private List<Projeto> lsProjetos= new ArrayList<>();
    private ProjetoDao projetoDao;
//    /**
//     * Mandatory empty constructor for the fragment manager to instantiate the
//     * fragment (e.g. upon screen orientation changes).
//     */
//    public ProjetoFragment() {
//    }
//
//    // TODO: Customize parameter initialization
//    @SuppressWarnings("unused")
//    public static ProjetoFragment newInstance(int columnCount) {
//        ProjetoFragment fragment = new ProjetoFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

//        if (getArguments() != null) {
//            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projeto_list, container, false);
        projetoDao = App.getDaoSessionApp(getActivity()).getProjetoDao();

        mListener = new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(Projeto item) {
                Log.i("event", "clicou no projeto:"+ item.getNome());
                Fragment fragment= new EtapasFragment();
              //  FragmentManager manager = getActivity().getSupportFragmentManager();
               // manager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        };

        // Set the adapter
        if (view instanceof RecyclerView) {
            Log.i("Event","Chegou na ProjetoFragment");
            RecyclerView recyclerView = (RecyclerView) view;
            lsProjetos = getProjetos();
            recyclerView.setAdapter(new ProjetoRecyclerViewAdapter(lsProjetos, mListener));
            if(lsProjetos.size()==0){
                ((RecyclerView)view.findViewById(R.id.projetoListview)).setVisibility(View.GONE);
                ((TextView)  getActivity().findViewById(R.id.TxtNenhumRegistro)).setVisibility(View.VISIBLE);
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
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            // throw new RuntimeException(context.toString()
            //      + " must implement OnListFragmentInteractionListener");
        }
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
        List<Projeto>lsProjetos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Projeto pj = new Projeto();
            pj.set_id(Long.valueOf(i+1));
            pj.setNome("Projeto "+i);
            pj.setGerente("Gerente "+i);
            pj.setEmpresa("Empresa Oficial "+i);
            lsProjetos.add(pj);
        }
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
