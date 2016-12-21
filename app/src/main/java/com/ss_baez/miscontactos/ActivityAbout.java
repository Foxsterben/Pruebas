package com.ss_baez.miscontactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        Button btn2 = (Button) findViewById(R.id.btn2);
        registerForContextMenu(btn2); //Con esto se habilita el menu de contexto
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
                Toast.makeText(this, "Menú Eliminar", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onContextItemSelected(item);
    }


    //Intent para el boton
    public void irDetalleContacto(View view){
        Intent objIntent = new Intent(ActivityAbout.this, ActivityTres.class);
        startActivity(objIntent);
    }

    //Menu Popup para la imagen
    public void levantarMenuPopup(View v){
        ImageView imagen = (ImageView) findViewById(R.id.img_metro);
        PopupMenu objPopupMenu = new PopupMenu(this, imagen);
        objPopupMenu.getMenuInflater().inflate(R.menu.menu_popup, objPopupMenu.getMenu());

        //Cachar cuando alguien de click
        objPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_view:
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.menu_view), Toast.LENGTH_LONG).show(); //Si no es This, es getBaseContext(),
                        break;

                    case R.id.menu_viewdetail:
                        Toast.makeText(getBaseContext(), "View Detail", Toast.LENGTH_LONG).show();
                        break;
                }

                return true;
            }
        });

        objPopupMenu.show();
    }


}
