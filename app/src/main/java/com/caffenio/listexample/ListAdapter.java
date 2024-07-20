package com.caffenio.listexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.caffenio.listexample.databinding.ItemListaBinding;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> data;
    private Context context;
    private ListAdapterCallback listener;

    public ListAdapter(LayoutInflater layoutInflater, List<String> data, Context context) {
        this.layoutInflater = layoutInflater;
        this.data = data;
        this.context = context;
    }

    public ListAdapter(LayoutInflater layoutInflater, List<String> data, Context context, ListAdapterCallback listener) {
        this.layoutInflater = layoutInflater;
        this.data = data;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListaBinding binding = ItemListaBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final ItemListaBinding binding = DataBindingUtil.getBinding(viewHolder.itemView);
        String name = data.get(position);

        binding.tvName.setText( name );

        binding.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null)
                    listener.onItemSelected(position, name);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        public ViewHolder(View itemView){
            super(itemView);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    ////  Action del click
    public interface ListAdapterCallback {
        void onItemSelected(int position, String name);
    }

}
