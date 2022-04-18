package com.example.redessocial.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redessocial.R;
import com.example.redessocial.chat.ActivityChat;
import com.example.redessocial.objetos.DmLista;
import com.example.redessocial.objetos.PerfilClass;

import java.util.List;

public class AdapterRecyclerListaDm extends RecyclerView.Adapter<AdapterRecyclerListaDm.ViewHolder>{


    private Context context;
    private List<DmLista> dmListaList;
    private int idPessoaAtual = 0;

    public AdapterRecyclerListaDm(List<DmLista> dmListaList,int id) {
        this.context = this.context;
        this.dmListaList=dmListaList;
        this.idPessoaAtual = id;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNome,txtLastMsgConversa;
        ImageView imgDm,imgPessoaListaDm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = (TextView) itemView.findViewById(R.id.txtNomePersonDm);
            txtLastMsgConversa= (TextView) itemView.findViewById(R.id.txtLastMsgConversa);
            imgDm= (ImageView)  itemView.findViewById(R.id.imgEnviarDmLista);
            imgPessoaListaDm = (ImageView)  itemView.findViewById(R.id.imgProfileDm);
        }
    }
    @NonNull
    @Override
    public AdapterRecyclerListaDm.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.estrutura_recycler_mensagens_dm, parent,false);
        return new AdapterRecyclerListaDm.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerListaDm.ViewHolder holder, int position) {


        final DmLista dmLista = dmListaList.get(position);
        holder.txtNome.setText(dmLista.getNomeConversando());
        holder.txtLastMsgConversa.setText(dmLista.getMensagem());

        if(dmLista.getImagem() == null){
            holder.imgPessoaListaDm.setImageResource(R.drawable.ic_baseline_account_circle_24);
        }
        else{
            holder.imgPessoaListaDm.setImageBitmap(
                    Bitmap.createScaledBitmap(
                            BitmapFactory.decodeByteArray(dmLista.getImagem(), 0, dmLista.getImagem().length),200, 200, false));

        }
        PerfilClass perfilClass = new PerfilClass(dmLista.getNomeConversando(),dmLista.getIdUser());
        holder.imgDm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), ActivityChat.class).putExtra("dataUser",perfilClass).putExtra("meuId",idPessoaAtual));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dmListaList.size();
    }


}
