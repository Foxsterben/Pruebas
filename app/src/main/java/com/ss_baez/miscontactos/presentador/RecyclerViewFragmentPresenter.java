package com.ss_baez.miscontactos.presentador;

import android.content.Context;

import com.ss_baez.miscontactos.Adapter.ContactoAdaptador;
import com.ss_baez.miscontactos.datebase.ConstructorContactos;
import com.ss_baez.miscontactos.fragment.IRecyclerViewFragmentView;
import com.ss_baez.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by SS_Baez on 10/12/2016.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorContactos constructorContactos;
    private ArrayList<Contacto> contactos;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {

        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerContactosBaseDatos();
    }


    @Override
    public void obtenerContactosBaseDatos() {

        constructorContactos = new ConstructorContactos(context);
        contactos = constructorContactos.obtenerDatos(); /** Aquí se inicializa con lo que trae el constructorContactos */
        mostrarContactosRV();

    }

    @Override
    public void mostrarContactosRV() {

            iRecyclerViewFragmentView.vinicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(contactos));  //Esto viene del RecyclerViewFragment.class vinicializarAdaptadorRV y crearAdaptador

            //Esto es para mostrar los elementos en el RecyclerView
            iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
