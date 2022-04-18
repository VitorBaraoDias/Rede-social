package com.example.redessocial.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.PrecomputedText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redessocial.R;
import com.example.redessocial.chat.ActivityChat;
import com.example.redessocial.objetos.DataMensagem;
import com.example.redessocial.objetos.PerfilClass;

import java.util.List;

public class AdapterRecyclerMensagens extends RecyclerView.Adapter<AdapterRecyclerMensagens.ViewHolder> {


    private Context context;
    private List<DataMensagem> dataMensagemList;
    private int idPessoaAtual = 0;


    public AdapterRecyclerMensagens(List<DataMensagem> dataMensagemList,int id) {
        this.context= this.context;
        this.dataMensagemList=dataMensagemList;
        this.idPessoaAtual = id;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout constraintLayoutFilho,constraintLayoutMaster;
        TextView txtMensagem,txtHora;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.cardMensagem);
            txtMensagem= (TextView) itemView.findViewById(R.id.txtTextoMensagem);
            txtHora = (TextView) itemView.findViewById(R.id.HoraMensagem);
            constraintLayoutFilho = (ConstraintLayout) itemView.findViewById(R.id.layoutMensagemFilho);
            constraintLayoutMaster = (ConstraintLayout) itemView.findViewById(R.id.layoutMensagemMaster);
        }
    }
    @NonNull
    @Override
    public AdapterRecyclerMensagens.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.estrutura_recycler_mensagens, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerMensagens.ViewHolder holder, int position) {
        final DataMensagem dataMensagem = dataMensagemList.get(position);
        holder.txtMensagem.setText(dataMensagem.getMensagem());
        holder.txtHora.setText(dataMensagem.getHora());


        if(dataMensagem.getIdMandou()==idPessoaAtual){


            holder.txtMensagem.setTextColor(Color.parseColor("#292828"));
            holder.txtHora.setTextColor(Color.parseColor("#292828"));
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            //layout
            // android:layout_marginStart="100dp"

            final ConstraintLayout constraintLayout = holder.constraintLayoutFilho;
            final ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
            layoutParams.startToStart = ConstraintLayout.LayoutParams.UNSET;
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
            constraintLayout.setLayoutParams(layoutParams);

        }
        else{

            holder.txtMensagem.setTextColor(Color.parseColor("#FFFFFF"));
            holder.txtHora.setTextColor(Color.parseColor("#DDDDDD"));
            holder.cardView.setCardBackgroundColor(Color.parseColor("#3B414A"));
        }
    }

    @Override
    public int getItemCount() {
        return dataMensagemList.size();
    }
}
