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
import android.widget.RatingBar;
import android.widget.TextView;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Enums.EtapaProjeto;
import com.gplearning.gplearning.Models.Avaliacao;
import com.gplearning.gplearning.Models.AvaliacaoDao;
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
    DaoSession session;
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
        session = ((App) getActivity().getApplication()).getDaoSession();
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
            /// inicio
            for (final Etapa eta : lsEtapas) {
                if (eta.getEtapa() == EtapaProjeto.DescricaoProjeto) {
                    // ((RatingBar) getActivity().findViewById(R.id.etapaProjetoDescricaoTARatingBar)).setRating(eta.getPontuacaoMedia());
                    CalculaMediaEtapa(((RatingBar) view.findViewById(R.id.etapaProjetoDescricaoTARatingBar)), eta.get_id());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoDescricaoTAbtn)), eta.get_id());
                } else if (eta.getEtapa() == EtapaProjeto.JustificativaProjeto) {
                    //((RatingBar) getActivity().findViewById(R.id.etapaProjetoJustificativaTARatingBar)).setRating(eta.getPontuacaoMedia());
                    CalculaMediaEtapa(((RatingBar) view.findViewById(R.id.etapaProjetoJustificativaTARatingBar)), eta.get_id());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoJustificativaTAbtn)), eta.get_id());
                } else if (eta.getEtapa() == EtapaProjeto.Premissas) {
                    //   ((RatingBar) getActivity().findViewById(R.id.etapaProjetoPremissasTARatingBar)).setRating(eta.getPontuacaoMedia());
                    CalculaMediaEtapa(((RatingBar) view.findViewById(R.id.etapaProjetoPremissasTARatingBar)), eta.get_id());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoPremissasTAbtn)), eta.get_id());

                } else if (eta.getEtapa() == EtapaProjeto.Restricoes) {
                    // ((RatingBar) getActivity().findViewById(R.id.etapaProjetoRestricoesTARatingBar)).setRating(eta.getPontuacaoMedia());
                    CalculaMediaEtapa(((RatingBar) view.findViewById(R.id.etapaProjetoRestricoesTARatingBar)), eta.get_id());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoRestricoesTAbtn)), eta.get_id());

                } else if (eta.getEtapa() == EtapaProjeto.Marcos) {
                    //  ((RatingBar) getActivity().findViewById(R.id.etapaProjetoMarcosTARatingBar)).setRating(eta.getPontuacaoMedia());
                    CalculaMediaEtapa(((RatingBar) view.findViewById(R.id.etapaProjetoMarcosTARatingBar)), eta.get_id());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoMarcosTAbtn)), eta.get_id());

                } else if (eta.getEtapa() == EtapaProjeto.RequisitosTermoAbertura) {
                    //((RatingBar) getActivity().findViewById(R.id.etapaProjetoRequisitosTARatingBar)).setRating(eta.getPontuacaoMedia());
                    CalculaMediaEtapa(((RatingBar) view.findViewById(R.id.etapaProjetoRequisitosTARatingBar)), eta.get_id());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoRequisitosTAbtn)), eta.get_id());

                } else if (eta.getEtapa() == EtapaProjeto.Stakeholders) {
                    //((RatingBar) getActivity().findViewById(R.id.etapaProjetoStakeholdersTARatingBar)).setRating(eta.getPontuacaoMedia());
                    CalculaMediaEtapa(((RatingBar) view.findViewById(R.id.etapaProjetoStakeholdersTARatingBar)), eta.get_id());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoStakeholdersTAbtn)), eta.get_id());

                } else if (eta.getEtapa() == EtapaProjeto.PlanoGerenciamentoEscopo) {
                    MetodosPublicos.Log("Event","PlanoGerenciamentoEscopo - clickou");
                    // ((RatingBar) getActivity().findViewById(R.id.etapaProjetoEscopoRatingBar)).setRating(eta.getPontuacaoMedia());
                    CalculaMediaEtapa(((RatingBar) view.findViewById(R.id.etapaProjetoEscopoRatingBar)), eta.get_id());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoEscopobtn)), eta.get_id());
                } else if (eta.getEtapa() == EtapaProjeto.Requisitos) {
                    //    ((RatingBar) getActivity().findViewById(R.id.etapaProjetoRequisitosRatingBar)).setRating(eta.getPontuacaoMedia());
                    CalculaMediaEtapa(((RatingBar) view.findViewById(R.id.etapaProjetoRequisitosRatingBar)), eta.get_id());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoRequisitosbtn)), eta.get_id());
                } else if (eta.getEtapa() == EtapaProjeto.Escopo) {
                    //   ((RatingBar) getActivity().findViewById(R.id.etapaProjetoEscopoRatingBar)).setRating(eta.getPontuacaoMedia());
                    CalculaMediaEtapa(((RatingBar) view.findViewById(R.id.etapaProjetoEscopoRatingBar)), eta.get_id());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoEscopobtn)), eta.get_id());
                } else if (eta.getEtapa() == EtapaProjeto.Eap) {
                    CalculaMediaEtapa(((RatingBar) view.findViewById(R.id.etapaProjetoEapRatingBar)), eta.get_id());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoEapbtn)), eta.get_id());
                } else if (eta.getEtapa() == EtapaProjeto.Cronograma) {
                    CalculaMediaEtapa(((RatingBar) view.findViewById(R.id.etapaProjetoCronogramaRatingBar)), eta.get_id());
                    AtribuiEventoClick(((ImageButton) view.findViewById(R.id.etapaProjetoCronogramabtn)), eta.get_id());
                }
            }
            //fim
        }
    }

    private void AtribuiEventoClick(ImageButton imageButton, final Long idEtapa) {
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

    private void CalculaMediaEtapa(RatingBar ratingBar, Long idEtapa) {
        AvaliacaoDao avaliacaoDao = session.getAvaliacaoDao();
        if (ratingBar != null && idEtapa > 0) {
            List<Avaliacao> lsAvaliacao = avaliacaoDao.queryBuilder().where(AvaliacaoDao.Properties.IdEtapa.eq(idEtapa), AvaliacaoDao.Properties.IdProjeto.eq(idProjeto)).list();
            if (lsAvaliacao != null) {
                float media = 0;
                for (Avaliacao avaliacao : lsAvaliacao) {
                    media += avaliacao.getValor();
                }
                media = media / lsAvaliacao.size();
                ratingBar.setRating(media);
                MetodosPublicos.Log("Event", "Atribui Avaliação Media:" + media);

            }
        }
    }

}


