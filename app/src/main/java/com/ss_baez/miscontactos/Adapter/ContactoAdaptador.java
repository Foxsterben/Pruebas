package com.ss_baez.miscontactos.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ss_baez.miscontactos.datebase.ConstructorContactos;
import com.ss_baez.miscontactos.pojo.Contacto;
import com.ss_baez.miscontactos.DetalleContacto;
import com.ss_baez.miscontactos.R;

import java.util.ArrayList;

/**
 * Created by SS_Baez on 22/11/2016.
 */

//Esta clase Adaptador va ayudar a que podamos sincronizar todos los views que se tienen por un lado, del recycler View con los datos obtenidos de cualquier lugar
public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder> {


    //Se va a declarar un objeto de tipo ArrayList y una coleccion de Contacto
    ArrayList<Contacto> contactos;
    Activity objActivity;

    //Se genera un metodo constructor de contactoadaptador. y le voy a decir que reciba un ArrayList de Contactos
    public ContactoAdaptador(ArrayList<Contacto> contactos, Activity objActivity){ //Construye nuestra lista de contactos
        this.contactos = contactos;
        this.objActivity = objActivity;

       /* Este metodo constructor hara:
       *
       * Cuando se este llamando a la clase ContactoAdaptador, se va a invocar el metodo constructor y entonces en algun momento tenemos
       * que estar pasando una lista de contactos a este adaptador para que sucedan todos los metodos de abajo, entonces la lista de contactos
       * que se esta pasando cuando estamos llamando a la clase ContactoAdaptador tenemos que pasarsela al objeto que esta declarado a nivel de
       * toda la clase*/

    }

    //Se va a encargar de tomar el layout, que define cada elemento de toda la lista de RecyclerView, y lo va a sincronizar con el ViewHolder -clase anidada-
    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//Va a inflar el layout y lo pasará al viewholder para que el obtenga cada elemento, los views

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto, parent, false); //esta linea de codigo le estara dando vida al layout, se esta asociando el layout cardview_contacto con el recyclesview
        return new ContactoViewHolder(v);
    }

    //Se va a encargar de colocar todos los datos de una lista o coleccion de datos a cada View de nuestro ViewHolder
    //Asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(final ContactoViewHolder holder, int position) { //un objeto holder de tipo ContactViewHolder
        final Contacto objcontacto = contactos.get(position);
        holder.imgFotoCV.setImageResource(objcontacto.getFoto());
        holder.tvNombreCV.setText(objcontacto.getNombre());
        holder.tvTelefonoCV.setText(objcontacto.getTelefono());

        //Aquí se va a estar seteando al TextView tvLikes, el valor que debe de tener en ese momento
        holder.tvLikes.setText(String.valueOf(objcontacto.getLikes()) + " " + objActivity.getString(R.string.likes));


        //onClickListener a la imagen
        holder.imgFotoCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(objActivity, objcontacto.getNombre(), Toast.LENGTH_SHORT).show();

                //Este intent nos llevará a la actividad DetalleContacto, envía los parametros
                Intent objIntent = new Intent(objActivity, DetalleContacto.class);
                objIntent.putExtra("nombre", objcontacto.getNombre());
                objIntent.putExtra("telefono", objcontacto.getTelefono());
                objIntent.putExtra("email", objcontacto.getEmail());
                objActivity.startActivity(objIntent);

            }
        });

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(objActivity, "Diste Like a " + objcontacto.getNombre(), Toast.LENGTH_SHORT).show();

                //Con esto se inserta un like en la base de datos
                ConstructorContactos constructorContactos = new ConstructorContactos(objActivity); //El activity va a representar el contexto
                constructorContactos.darLikeContacto(objcontacto);

                holder.tvLikes.setText(String.valueOf(constructorContactos.obtenerLikesContacto(objcontacto)) + " " + objActivity.getString(R.string.likes));
            }
        });


    }

    //Va a devolver el resultado de toda la cantidad de elementos que tenemos
    @Override
    public int getItemCount() { //Cantidad de elementos que contiene mi lista contactos
        return contactos.size();
    }

    //Esta es la clase anidada
    //ViewHolder ayudara a manejar toda la conexion entre el Adaptador y todos los elementos Views que componen el renglon del RecyclerView
    //o el elemento que se quiere mostrar en el recycler View

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{ //Esta clase esta anidada
        //Se van a declarar todos los Views que se tienen definidos en el CardViewContacto
        private ImageView imgFotoCV;
        private TextView tvNombreCV;
        private TextView tvTelefonoCV;
        private ImageButton btnLike;
        private TextView tvLikes;

        public ContactoViewHolder(View itemView) { //En este metodo constructor es donde se van a asociar los obj de arriba con sus respectivos Views
            super(itemView);
            imgFotoCV       = (ImageView) itemView.findViewById(R.id.imgFotoCV);
            tvNombreCV      = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvTelefonoCV    = (TextView) itemView.findViewById(R.id.tvTelefonoCV);
            btnLike         = (ImageButton) itemView.findViewById(R.id.btnLike);
            tvLikes         = (TextView) itemView.findViewById(R.id.tvLikes);

        }
    }

}
