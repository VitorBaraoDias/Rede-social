package com.example.redessocial.fragmentos;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.redessocial.R;
import com.example.redessocial.adapter.AdapterRecyclerListaDm;
import com.example.redessocial.adapter.AdapterRecyclerMensagens;
import com.example.redessocial.bd.dao.MensagemDao;
import com.example.redessocial.objetos.DataMensagem;
import com.example.redessocial.objetos.DmLista;

import java.util.List;

public class FragDms extends Fragment {

    private View view;
    private TextView txtNome;
    private RecyclerView recyclerListaDm;
    private List<DmLista> dmListaList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_frag_dms, container, false);

        int id_user = getArguments().getInt("id");
        setObj();
        setLista(id_user);
        setAdapter(dmListaList,id_user);

        return view;
    }
    private void setObj(){
        recyclerListaDm = view.findViewById(R.id.recyclerListaDm);
    }
    private void setAdapter(List<DmLista> dmListaList, int idUserAtual) {
        AdapterRecyclerListaDm adapterRecyclerListaDm = new AdapterRecyclerListaDm(dmListaList,idUserAtual);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerListaDm.setLayoutManager(layoutManager);
        recyclerListaDm.setItemAnimator(new DefaultItemAnimator());
        //VAI FAZER COM QUE APAREÇA OS DADOS DA LISTA
        recyclerListaDm.setAdapter(adapterRecyclerListaDm);
    }
    @SuppressLint("NewApi")
    private void setLista(int userAtual) {
        MensagemDao mensagemDao = new MensagemDao(getContext());
        dmListaList = mensagemDao.getAbasConversas(userAtual);
    }
}