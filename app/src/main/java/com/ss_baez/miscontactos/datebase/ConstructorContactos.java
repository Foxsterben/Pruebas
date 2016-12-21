package com.ss_baez.miscontactos.datebase;

import android.content.ContentValues;
import android.content.Context;

import com.ss_baez.miscontactos.R;
import com.ss_baez.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by SS_Baez on 10/12/2016.
 */

public class ConstructorContactos {


    /** Esta clase interactua con los métodos de la base de datos*/
    private static final int LIKE = 1;
    private Context context;
    public ConstructorContactos(Context context) {
        this.context = context;
    }


    public ArrayList<Contacto> obtenerDatos(){


        /**Datos Dummy*/
        /*ArrayList<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto(R.drawable.candy, "S.S. Báez", "5533886766", "ss.baez@comunidad.unam.mx", 2));
        contactos.add(new Contacto(R.drawable.forest_mushroom_icon, "Ricardo Báez", "5512325304", "purpura031154@hotmail.com", 3));
        contactos.add(new Contacto(R.drawable.yammi_banana_icon, "Mauricio Lechuga", "5523153825", "mau-lechuga@hotmail.com", 4));
        contactos.add(new Contacto(R.drawable.shock_rave_bonus_icon, "Daniela Victoria", "5551043785", "daniela.victoria@gmail.com", 5));
        contactos.add(new Contacto(R.drawable.cross_icon_1, "Chaks Godín", "5570098214", "ealc120591@hotmail.con", 3));
        contactos.add(new Contacto(R.drawable.skull_bw, "Ricardo Ochoa", "5555518545", "ricardobiliberto@hotmail.com", 6));
        return contactos;*/

        /** Aquí en vez de obtener los datos de un ArrayList dummy se va a ejecutar la conexión con la base de datos*/

        BaseDatos sqLiteDatabase = new BaseDatos(context);
        insertarTresContactos(sqLiteDatabase);

        return sqLiteDatabase.obtenerTodosLosContactos(); /** obtenerTodosLosContactos viene de BaseDAtos */
    }

    /** Método para insertar contactos*/

    public void insertarTresContactos(BaseDatos sqLiteDatabase){ //Este metodo tambien se va a definir en BaseDatos.class

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "S.S. Báez");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "5533886766");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "ss.baez@comunidad.unam.mx");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.candy);

        sqLiteDatabase.insertarContacto(contentValues); /** insertarContacto viene de BaseDatos.class */

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Ricardo Báez");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "5512325304");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "purpura031154@hotmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.forest_mushroom_icon);

        sqLiteDatabase.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Mauricio Lechuga");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "5523153825");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "mau-lechuga@hotmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.yammi_banana_icon);

        sqLiteDatabase.insertarContacto(contentValues); /** insertarContacto viene de BaseDatos */

    }

    public void darLikeContacto(Contacto objcontacto){

        BaseDatos sqLiteDatabase = new BaseDatos(context); //Aquí se abre y se instancia la base de datos

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO, objcontacto.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES, LIKE); //LIKE=1

        sqLiteDatabase.insertarLikeContacto(contentValues); /** insertarLikeContacto viene de BaseDatos */

    }

    public int obtenerLikesContacto(Contacto objcontacto){ //primero se crea en BaseDatos.class

        BaseDatos sqLiteDatabase = new BaseDatos(context);
        return sqLiteDatabase.obtenerLikesContacto(objcontacto); /** obtenerLikesContacto viene de BaseDatos */

    }

}
