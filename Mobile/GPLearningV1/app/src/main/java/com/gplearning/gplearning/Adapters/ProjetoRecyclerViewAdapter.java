package com.gplearning.gplearning.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gplearning.gplearning.Controllers.ProjetoFragment.OnListFragmentInteractionListener;
import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ProjetoRecyclerViewAdapter extends RecyclerView.Adapter<ProjetoRecyclerViewAdapter.ViewHolder> {

    private List<Projeto> mValues = new ArrayList<>();
    private OnListFragmentInteractionListener listenerClick;
    private OnListFragmentInteractionListener listenerLongClick;

    public ProjetoRecyclerViewAdapter(List<Projeto> items, OnListFragmentInteractionListener listenerClick, OnListFragmentInteractionListener listenerLongClick) {
        mValues = items;
        this.listenerClick = listenerClick;
        this.listenerLongClick = listenerLongClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_listview1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getNome());
        holder.mContentView.setText(mValues.get(position).getGerente());

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (listenerLongClick != null) {
                    listenerLongClick.onListFragmentInteraction(holder.mItem);
                    return true;
                }
                return false;
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listenerClick) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listenerClick.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Projeto mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.itemListviewText1);
            mContentView = (TextView) view.findViewById(R.id.itemListviewText2);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
