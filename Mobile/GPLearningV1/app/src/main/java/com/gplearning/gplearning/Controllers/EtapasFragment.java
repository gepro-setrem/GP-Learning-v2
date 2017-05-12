package com.gplearning.gplearning.Controllers;

//import android.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Enums.EtapaProjeto;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Etapa;
import com.gplearning.gplearning.Models.EtapaDao;
import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.Models.ProjetoDao;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import java.lang.reflect.Field;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EtapasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EtapasFragment#} factory method to
 * create an instance of this fragment.
 */
public class EtapasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "projetoId";

    private Long idProjeto;
    private EtapaDao etapaDao;
    private OnFragmentInteractionListener mListener;

    public EtapasFragment() {
    }

    //    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @return A new instance of fragment EtapasFragment.
//     */
    // TODO: Rename and change types and number of parameters
    public static EtapasFragment newInstance(Long idProjeto) {
        EtapasFragment fragment = new EtapasFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, idProjeto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            idProjeto = getArguments().getLong(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_etapas, container, false);

        getActivity().setTitle(R.string.project_detail);
        DaoSession session = ((App) getActivity().getApplication()).getDaoSession();
        //  PessoaDao pessoaDao = session.getPessoaDao();
        //  List<Pessoa> lsPessoas = pessoaDao.loadAll();
        ProjetoDao projetoDao = session.getProjetoDao();
        Projeto projeto = projetoDao.load(idProjeto);

        ((TextView) view.findViewById(R.id.etapaProjetoNomeTxt)).setText(projeto.getNome());
        ((TextView) view.findViewById(R.id.etapaProjetoEmpresaTxt)).setText(projeto.getEmpresa());
        if (projeto.getGerente() != null)
            ((TextView) view.findViewById(R.id.etapaProjetoGerenteTxt)).setText(projeto.getGerente().getNome());

        etapaDao = session.getEtapaDao();
        Long idTurma = projeto.getIdTurma();
        List<Etapa> lsEtapas = etapaDao.queryBuilder().where(EtapaDao.Properties.IdTurma.eq(idTurma)).list();

        MetodosPublicos.Log("Etapa", " idTurma:" + idTurma + " com etapas total:" + lsEtapas.size());
        PassaValoresEtapas(view, lsEtapas);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /// Icones
    /// http://www.flaticon.com/packs/business-and-finance-11
    /// http://www.flaticon.com/packs/reports-and-analytics/2
    private void PassaValoresEtapas(View view, List<Etapa> lsEtapas) {


        // descricao
        //justificativa

        // premissas

        //restrições
        //marcos

        //requisitos Termo de abertura
        //partes interessadas
        //

        //Planejamento de Escopo --http://www.flaticon.com/free-icon/brainstorm_201557#term=strategy&page=1&position=2
        // requisitos --  http://www.flaticon.com/free-icon/clipboard_235252
        // escopo  --  http://www.flaticon.com/free-icon/notebook_330705


//        ((ImageButton) getActivity().findViewById(R.id.etapaProjetoDescricaoTAbtn)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), EtapaActivity.class);
//                intent.putExtra("ID", 0);
//                startActivity(intent);
//            }
//        });

        // AtribuiEventoClick(((ImageButton) getActivity().findViewById(R.id.etapaProjetoDescricaoTAbtn)), Long.valueOf(0));

        // Etapa atv = atividadeDao.queryBuilder().where(AtividadeDao.Properties.Pro_id.eq(idProjeto), AtividadeDao.Properties.Etapa.eq(EtapaProjeto.TermoAberturaDescricao)).unique();
        if (lsEtapas != null && lsEtapas.size() > 0) {

            for (final Etapa atv : lsEtapas) {
                if (atv.getEtapa() == EtapaProjeto.DescricaoProjeto) {
                    // ((RatingBar) getActivity().findViewById(R.id.etapaProjetoDescricaoTARatingBar)).setRating(atv.getPontuacaoMedia());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoDescricaoTAbtn)), atv.get_id());
                } else if (atv.getEtapa() == EtapaProjeto.JustificativaProjeto) {
                    //((RatingBar) getActivity().findViewById(R.id.etapaProjetoJustificativaTARatingBar)).setRating(atv.getPontuacaoMedia());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoJustificativaTAbtn)), atv.get_id());

                } else if (atv.getEtapa() == EtapaProjeto.Premissas) {
                    //   ((RatingBar) getActivity().findViewById(R.id.etapaProjetoPremissasTARatingBar)).setRating(atv.getPontuacaoMedia());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoPremissasTAbtn)), atv.get_id());

                } else if (atv.getEtapa() == EtapaProjeto.Restricoes) {
                    // ((RatingBar) getActivity().findViewById(R.id.etapaProjetoRestricoesTARatingBar)).setRating(atv.getPontuacaoMedia());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoRestricoesTAbtn)), atv.get_id());

                } else if (atv.getEtapa() == EtapaProjeto.Marcos) {
                    //  ((RatingBar) getActivity().findViewById(R.id.etapaProjetoMarcosTARatingBar)).setRating(atv.getPontuacaoMedia());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoMarcosTAbtn)), atv.get_id());

                } else if (atv.getEtapa() == EtapaProjeto.RequisitosTermoAbertura) {
                    //((RatingBar) getActivity().findViewById(R.id.etapaProjetoRequisitosTARatingBar)).setRating(atv.getPontuacaoMedia());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoRequisitosTAbtn)), atv.get_id());

                } else if (atv.getEtapa() == EtapaProjeto.Stakeholders) {
                    //((RatingBar) getActivity().findViewById(R.id.etapaProjetoStakeholdersTARatingBar)).setRating(atv.getPontuacaoMedia());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoStakeholdersTAbtn)), atv.get_id());

                } else if (atv.getEtapa() == EtapaProjeto.PlanoGerenciamentoEscopo) {
                    // ((RatingBar) getActivity().findViewById(R.id.etapaProjetoEscopoRatingBar)).setRating(atv.getPontuacaoMedia());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoEscopobtn)), atv.get_id());
                } else if (atv.getEtapa() == EtapaProjeto.Requisitos) {
                    //    ((RatingBar) getActivity().findViewById(R.id.etapaProjetoRequisitosRatingBar)).setRating(atv.getPontuacaoMedia());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoRequisitosbtn)), atv.get_id());
                } else if (atv.getEtapa() == EtapaProjeto.Escopo) {
                    //   ((RatingBar) getActivity().findViewById(R.id.etapaProjetoEscopoRatingBar)).setRating(atv.getPontuacaoMedia());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoEscopobtn)), atv.get_id());
//                    ((ImageButton) getActivity().findViewById(R.id.etapaProjetoEscopobtn)).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent intent = new Intent(getActivity(), EtapaActivity.class);
//                            intent.putExtra("ID", atv.get_id());
//                            startActivity(intent);
//                        }
//                    });
                }
            }
            //        atv = atividadeDao.queryBuilder().where(AtividadeDao.Properties.Pro_id.eq(idProjeto), AtividadeDao.Properties.Etapa.eq(EtapaProjeto.TermoAberturaJustificativa)).unique();
            //        if (atv != null)
            //            ((RatingBar) getActivity().findViewById(R.id.etapaprojetoJu)).setRating(atv.getPontuacaoMedia());
        }
    }


    private void AtribuiEventoClick(ImageButton imageButton, final Long idEtapa) {
        MetodosPublicos.Log("Event", "Atribui evento image View");
        (imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EtapaActivity.class);
                intent.putExtra(MetodosPublicos.key_idEtapa, idEtapa);
                intent.putExtra(MetodosPublicos.key_idProjeto, idProjeto);
                MetodosPublicos.Log("Event", "Clicou etapaID:" + idEtapa + " idProjeto:" + idProjeto);

                startActivity(intent);
            }
        });
    }

}


