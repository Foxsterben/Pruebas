package com.ss_baez.miscontactos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ss_baez.miscontactos.Adapter.ContactoAdaptador;
import com.ss_baez.miscontactos.R;
import com.ss_baez.miscontactos.pojo.Contacto;
import com.ss_baez.miscontactos.presentador.IRecyclerViewFragmentPresenter;
import com.ss_baez.miscontactos.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by SS_Baez on 01/12/2016.
 */

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {

    private ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;
    private IRecyclerViewFragmentPresenter presenter;

    //Método que se estara trabajando


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        //Lo que va a contener el metodo onCreateView va a ser precisamente la inflacion, inflar el layout con nuestro fragment
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false); //Se le va asignar esta clase de java al layout fragment_recyclerview y se va a comportar como un contenedor


        listaContactos = (RecyclerView) v.findViewById(R.id.rvContactos);//esto es para enlazar el recyclerview del MainActivity ya que lo hicimos un objeto

        //Se manda a llamar el Presentador
        presenter = new RecyclerViewFragmentPresenter(this, getContext());

        //Todo_ el layout esta contenido en "v"
        return v;
    }
    /*
    public void inicializarListaContactos(){
        contactos = new ArrayList<Contacto>();
        contactos.add(new Contacto(R.drawable.candy, "S.S. Báez", "5533886766", "ss.baez@comunidad.unam.mx", likes));
        contactos.add(new Contacto(R.drawable.forest_mushroom_icon, "Ricardo Báez", "5512325304", "purpura031154@hotmail.com", likes));
        contactos.add(new Contacto(R.drawable.yammi_banana_icon, "Mauricio Lechuga", "5523153825", "mau-lechuga@hotmail.com", likes));
        contactos.add(new Contacto(R.drawable.shock_rave_bonus_icon, "Daniela Victoria", "5551043785", "daniela.victoria@gmail.com", likes));
        contactos.add(new Contacto(R.drawable.cross_icon_1, "Chaks Godín", "5570098214", "ealc120591@hotmail.con", likes));
        contactos.add(new Contacto(R.drawable.skull_bw, "Ricardo Ochoa", "5555518545", "ricardobiliberto@hotmail.com", likes));
    }
    */


    /** Inicia Interface*/



    @Override
    public void generarLinearLayoutVertical() {

        //Aquí se va ir definiendo de que forma se quiere mostrar el recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);           //se define la orientacion en la que se quiere mostrar la lista
        //GridLayoutManager glm = new GridLayoutManager(this, 2);   //Recibe dos parametros, el contexto y el numero de columnas que quiero ir mostrando
        //Puedo pasarle con el metodo setLayoutManager mi linearlayout-llm-
        listaContactos.setLayoutManager(llm);   //Con esto le estoy diciendo que el RecyclerView se comporte como un linearlayout -llm- que adq las caract de este objeto

    }

    @Override
    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos) {

        ContactoAdaptador objAdaptador = new ContactoAdaptador(contactos, getActivity());

        return objAdaptador;
    }

    @Override
    public void vinicializarAdaptadorRV(ContactoAdaptador objAdaptador) {

        listaContactos.setAdapter(objAdaptador);

    }
}
