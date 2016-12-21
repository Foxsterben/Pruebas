package com.ss_baez.miscontactos.fragment;

import com.ss_baez.miscontactos.Adapter.ContactoAdaptador;
import com.ss_baez.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by SS_Baez on 09/12/2016.
 */

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos);

    public void vinicializarAdaptadorRV(ContactoAdaptador objAdaptador);

}
