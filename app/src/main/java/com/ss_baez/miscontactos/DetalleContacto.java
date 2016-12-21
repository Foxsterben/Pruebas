package com.ss_baez.miscontactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class DetalleContacto extends AppCompatActivity {

    //Declaradas de manera global y privados para que estos elementos solo sean utiles para esta clase
    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        Bundle parametros = getIntent().getExtras(); //Los parametros se llaman extras

        //Aquí se estan cachando los datos que esta mandando el MainActivity o en este caso del contacto adaptador
        String nombre = parametros.getString("nombre"); //Equivale a "Nombre"
        String telefono = parametros.getString("telefono");
        String email = parametros.getString("email");

        //Se tienen que declarar globales para poder manipularlos dentro de cada método
        //Una vez declarados globales, se les elimina el TextView iniciales ya que esto querria decir que este objeto es local a este metodo

        //Antes de declarar los TextView como globales
        //TextView tvNombre   = (TextView) findViewById(R.id.tvNombre);
        //TextView tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        //TextView tvEmail    = (TextView) findViewById(R.id.tvEmail);

        //Después de declarar los TextView como globales
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);

        //Con el metodo setText(); se puede colocar el nombre que se esta cachando que me trae el parametro
        tvNombre.setText(nombre);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);

    }

    //Se le dara vida al email y telefono
    //Se crearan 2 Métodos, uno para ejecutar una llamada y el otra para un email
    //Llamar y enviarMail se estan creando para estos metodos

    public void llamar(View v) {
        String telefono = tvTelefono.getText().toString();
        //El intent será implicito, porque sera un intent que tome un recurso externo de mi app
        //Es decir para que se pueda ejecutar la llamada yo tengo que abrir la app de llamada y enlazarla con mi app
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono))); //Como segundo parametro no se puede poner el # asi que se debe manejar como un recurso accesible de tipo Uri recurso accesible junto con el metodo .parse()
    }

    public void enviarMail(View v) {
        String email = tvEmail.getText().toString();
        Intent emailIntent = new Intent((Intent.ACTION_SEND));
        emailIntent.setData(Uri.parse("mailto:")); //Setdata lo que tendra es la direcion Uri de lo que va a suceder
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email); //Con esto decido a quiens era enviado el correo, ene ste caso al dato que estamos cachando "email"
        //emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email ")); //El chooser
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(DetalleContacto.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }


}