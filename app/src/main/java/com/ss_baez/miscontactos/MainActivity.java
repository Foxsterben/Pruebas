package com.ss_baez.miscontactos;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ss_baez.miscontactos.Adapter.ContactoAdaptador;
import com.ss_baez.miscontactos.Adapter.PageAdapter;
import com.ss_baez.miscontactos.fragment.PerfilFragment;
import com.ss_baez.miscontactos.fragment.RecyclerViewFragment;
import com.ss_baez.miscontactos.pojo.Contacto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //Se van a declarar los Views que están en el layout. primero se declaran de manera global
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Para habilitar menu opciones en el actionbar
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);


        toolbar     = (Toolbar) findViewById(R.id.toolbar);
        tabLayout   = (TabLayout) findViewById(R.id.tabLayout);
        viewPager   = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();

        //Además se va a realizar una validación
        if (toolbar != null){
            //consiga:
            setSupportActionBar(toolbar);
        }


    }
    //Menu de opciones para que lo muestre en la vista
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    //Para controlar que va a suceder dependiendo q se seleccione en el menu opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_about:
                Intent objIntent = new Intent(MainActivity.this, ActivityAbout.class);
                startActivity(objIntent);
                break;

            case R.id.menu_settings:
                Intent objIntent2 = new Intent(MainActivity.this, ActivitySettings.class);
                startActivity(objIntent2);
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    //2Para añadir los fragments a la lista
    private ArrayList<Fragment> agregarFragments(){

        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment()); //Este se mostrara en el primer "tap"
        fragments.add(new PerfilFragment());
        return fragments;
    }

    //1Esto viene después del PageAdapter.class
    private void setUpViewPager(){ //Este metodo lo que hará sera poner en orbita los fragments

        //Para ello se tendran que añadir los fragments a un arreglo de fragments, que se hizo arriba
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        //Aquí se sta pasando la lista de fragments que se quieren agregar a PageAdapter

        //Una vez que ya se tiene el viewpager configurado se debe agregar a:
        tabLayout.setupWithViewPager(viewPager);

        //Aquí se colocan los ioonos de los taps
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_contacts);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
    }




}

