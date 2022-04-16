package com.example.redessocial.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redessocial.R;
import com.example.redessocial.chat.ActivityChat;
import com.example.redessocial.objetos.PerfilClass;

import java.util.List;

public class AdapterRecyclerPerfil extends RecyclerView.Adapter<AdapterRecyclerPerfil.ViewHolder> {

    private Context context;
    private List<PerfilClass> perfilClassList;
    private int idPessoaAtual = 0;

    public AdapterRecyclerPerfil(List<PerfilClass> perfilClassList1,int id) {
        this.context= this.context;
        this.perfilClassList=perfilClassList1;
        this.idPessoaAtual = id;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView txtNomeProfile;
        ImageView imgSend;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardSearch);
            txtNomeProfile= (TextView) itemView.findViewById(R.id.txtNomePerson);
            imgSend = (ImageView) itemView.findViewById(R.id.imgEnviarDmLista);
        }
    }
    @NonNull
    @Override
    public AdapterRecyclerPerfil.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler, parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerPerfil.ViewHolder holder, int position) {
        final PerfilClass perfilClass = perfilClassList.get(position);
        holder.txtNomeProfile.setText(perfilClass.getUser_name());

        holder.imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), ActivityChat.class).putExtra("dataUser",perfilClass).putExtra("meuId",idPessoaAtual));
            }
        });
    }
    @Override
    public int getItemCount() {
        return perfilClassList.size();
    }
    public void clear() {
        int size = perfilClassList.size();
        perfilClassList.clear();
        notifyItemRangeRemoved(0, size);
    }
}
