package com.example.redessocial.fragmentos;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.redessocial.R;
import com.example.redessocial.adapter.AdapterRecyclerPerfil;
import com.example.redessocial.bd.dao.UserDao;
import com.example.redessocial.objetos.PerfilClass;
import com.example.redessocial.objetos.User;

import java.util.ArrayList;
import java.util.List;

public class FragSearch extends Fragment {

    private RecyclerView recyclerSearch;
    private EditText txtSearch;
    private View view;
    List<PerfilClass> perfilList = new ArrayList<PerfilClass>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_frag_search, container, false);

        UserDao userDao = new UserDao(getContext());

        int id_user = getArguments().getInt("id");

        setObj();

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(txtSearch.getText().toString().equals(""))
                {
                    perfilList.clear();
                   clearAdapter(perfilList,id_user);
                }
                else{
                    String nome = txtSearch.getText().toString();
                    perfilList=userDao.getUsers(nome,id_user);
                }
                setAdapter(perfilList,id_user);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        return view;
    }
    private void setObj(){
        txtSearch = view.findViewById(R.id.txtSearch);
        recyclerSearch = view.findViewById(R.id.recyclerSearch);
    }
    private void setAdapter(List<PerfilClass>perfilClassList,int id){
        AdapterRecyclerPerfil adapterRecyclerPerfil = new AdapterRecyclerPerfil(perfilClassList,id);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerSearch.setLayoutManager(layoutManager);
        recyclerSearch.setItemAnimator(new DefaultItemAnimator());
        //VAI FAZER COM QUE APAREÇA OS DADOS DA LISTA
        recyclerSearch.setAdapter(adapterRecyclerPerfil);
    }
    @SuppressLint("NotifyDataSetChanged")
    private void clearAdapter(List<PerfilClass>perfilClassList,int id){
        perfilList.clear();
        AdapterRecyclerPerfil adapterRecyclerPerfil = new AdapterRecyclerPerfil(perfilClassList,id);
        adapterRecyclerPerfil.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerSearch.setLayoutManager(layoutManager);
        recyclerSearch.setItemAnimator(new DefaultItemAnimator());
        //VAI FAZER COM QUE APAREÇA OS DADOS DA LISTA
        recyclerSearch.setAdapter(adapterRecyclerPerfil);
        Toast.makeText(getContext(), String.valueOf(perfilClassList.size()), Toast.LENGTH_SHORT).show();
    }
}