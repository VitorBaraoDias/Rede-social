package com.example.redessocial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Toast;

import com.example.redessocial.fragmentos.FragDms;
import com.example.redessocial.fragmentos.FragInicial;
import com.example.redessocial.fragmentos.FragPerfil;
import com.example.redessocial.fragmentos.FragSearch;
import com.example.redessocial.fragmentos.VpAdapet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityMenu extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;

    private FragInicial fragInicial;
    private FragSearch fragSearch;
    private FragPerfil fragPerfil;
    private FragDms fragDms;
    VpAdapet vPadapter = new VpAdapet(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //id user atual
        Bundle extras = getIntent().getExtras();
        int id = (extras.getInt("id"));
        //id user atual
        setObj();
        setPager();


        enviarIdUserAtual(id,fragInicial);
        enviarIdUserAtual(id,fragDms);
        enviarIdUserAtual(id,fragSearch);
        enviarIdUserAtual(id,fragPerfil);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.homeId){
                    viewPager.setCurrentItem(0);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    return true;
                }
                else if(item.getItemId()==R.id.seachId){
                    viewPager.setCurrentItem(1);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    return true;
                }
                else if(item.getItemId()==R.id.dmId){
                    viewPager.setCurrentItem(2);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    return true;
                }
                else if(item.getItemId()==R.id.loginId){
                    viewPager.setCurrentItem(3);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    return true;
                }
                return false;
            }
        });
    }
    private void setObj(){
        navigationView = findViewById(R.id.navBarMneu);
        viewPager = findViewById(R.id.pagerMenu);
    }
    private void setPager(){

        fragDms = new FragDms();
        fragInicial = new FragInicial();
        fragSearch = new FragSearch();
        fragPerfil = new FragPerfil();
        vPadapter.addFragment(fragInicial,"");
        vPadapter.addFragment(fragSearch,"SOBRE NÓS");
        vPadapter.addFragment(fragDms,"");
        vPadapter.addFragment(fragPerfil,"SOBRE NÓS");
        viewPager.setAdapter(vPadapter);
    }
    public void enviarIdUserAtual(int id, Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
    }
    public void setCurrentItemPager(int id){
        viewPager.setCurrentItem(id); // viewPager = substitua pelo seu viewPAger
    }
}