package com.ss_baez.miscontactos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres);

        TextView tv_hola = (TextView) findViewById(R.id.tv_hola);
        registerForContextMenu(tv_hola);

        //Para habilitar menu opciones en el actionbar
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

    }


    //Menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_contexto, menu);

    }

    //Menu contexto - para establecer a que se le dara click
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_edit:
                //Puede ir aquí un intent
                Toast.makeText(this, getResources().getString(R.string.menu_edit), Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_delete:
                //Puede ir aquí un intent
                Toast.makeText(this, "Eliminar", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onContextItemSelected(item);
    }



}
