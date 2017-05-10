package com.gplearning.gplearning.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ComentarioAdapter extends RecyclerView.Adapter {

    private List<Comentario> lsComentario = new ArrayList<>();
    private Context context;
    java.text.DateFormat dateFormat;

    public ComentarioAdapter(List<Comentario> lsComentario, Context ctx) {
        this.lsComentario = lsComentario;
        this.context = ctx;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //android.text.format.DateFormat.getDateFormat(context.getApplicationContext());
    }

    public void swap(List<Comentario> comentarios) {
        lsComentario.clear();
        lsComentario.addAll(comentarios);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listview_comentario, parent, false);
        ViewHolderAdapter holder = new ViewHolderAdapter(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolderAdapter holder = (ViewHolderAdapter) viewHolder;
        Comentario comentario = lsComentario.get(position);
        holder.texto.setText(comentario.getDescricao());
        holder.data.setText(dateFormat.format(comentario.getCriacao()));
        MetodosPublicos.Log("Event", " idRemetente:" + comentario.getIdRemetente() + " remetente null:" + (comentario.getRemetente() == null));
       // Pessoa user =
        if (comentario.getRemetente() != null) {
            holder.usuario.setText(comentario.getRemetente().getNome());
//            byte[] image = comentario.getRemetente().getImagem();
//            if (image != null && image.length > 0) {
//                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
//                holder.imagem.setImageBitmap(bitmap);
//            }
        }
    }

    @Override
    public long getItemId(int i) {
        return lsComentario.get(i).get_id();
    }

    @Override
    public int getItemCount() {
        return lsComentario.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        final TextView texto;
        final TextView usuario;
        final TextView data;
        final ImageView imagem;

        public ViewHolderAdapter(View view) {
            super(view);
            texto = (TextView) view.findViewById(R.id.itemListviewComentTexto);
            usuario = (TextView) view.findViewById(R.id.itemListviewComentNome);
            data = (TextView) view.findViewById(R.id.itemListviewComentData);
            imagem = (ImageView) view.findViewById(R.id.itemListviewComentImagem);
        }

    }
}
