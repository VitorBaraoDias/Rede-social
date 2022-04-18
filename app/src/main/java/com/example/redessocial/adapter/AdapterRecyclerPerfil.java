package com.example.redessocial.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redessocial.R;
import com.example.redessocial.bd.dao.ImgPerfilDao;
import com.example.redessocial.chat.ActivityChat;
import com.example.redessocial.objetos.PerfilClass;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
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
        ImageView imgSend,imgPerfil;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPerfil = (ImageView) itemView.findViewById(R.id.imgProfile);
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

        if(perfilClass.getImagem() == null){
            holder.imgPerfil.setImageResource(R.drawable.ic_baseline_account_circle_24);
        }
        else{
            holder.imgPerfil.setImageBitmap(
                    Bitmap.createScaledBitmap(
                            BitmapFactory.decodeByteArray(perfilClass.getImagem(), 0, perfilClass.getImagem().length),200, 200, false));

        }
        System.out.println("aaaaaaa"+ Arrays.toString(perfilClass.getImagem()));
        holder.txtNomeProfile.setText(perfilClass.getUser_name());
        holder.imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfilClass.setImagem(null);
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
