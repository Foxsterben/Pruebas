package com.ss_baez.miscontactos.datebase;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ss_baez.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by SS_Baez on 12/12/2016.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION); //name y version se estara manejando en una clase auxiliar, esta cñase va a funcionar una clase de constantes esta clase tendra los datos principales de la base de datos
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //Lo que se crea aqui es la estructura de la base de datos, tablas, su composicion

        String queryCrearTablaContacto = "CREATE TABLE " + ConstantesBaseDatos.TABLE_CONTACTS + "(" +
                                        ConstantesBaseDatos.TABLE_CONTACTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE       + " TEXT, " +
                                        ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO     + " TEXT, " +
                                        ConstantesBaseDatos.TABLE_CONTACTS_EMAIL        + " TEXT, " +
                                        ConstantesBaseDatos.TABLE_CONTACTS_FOTO         + " INTEGER" +
                                        ")";

        String queryCrearTablaLikesContacto = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_CONTACT + "(" +
                                            ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                            ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + " INTEGER, " +
                                            ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES + " INTEGER, " +
                                            "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + ") " +
                                            "REFERENCES " + ConstantesBaseDatos.TABLE_CONTACTS + "("+ ConstantesBaseDatos.TABLE_CONTACTS_ID +")" +
                                            ")";
        sqLiteDatabase.execSQL(queryCrearTablaContacto);
        sqLiteDatabase.execSQL(queryCrearTablaLikesContacto);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { //Este metodo se ejcutara si se necesita reestructura la base de datos

        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_CONTACTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_LIKES_CONTACT);
        onCreate(sqLiteDatabase);

    }

    //Metodo que generara una consulta a la abse de datos
    public ArrayList<Contacto> obtenerTodosLosContactos(){

        ArrayList<Contacto> contactos = new ArrayList<>();

        //Aquí se va a manejar toda la parte de ejecutar un query
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_CONTACTS;

        //Lo siguiente es abrir la base de datos en forma de escritura o en forma de solo lectura
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //Lo siguiente es ejecutar el query
        Cursor registros = sqLiteDatabase.rawQuery(query, null); //Este objeto ayudará a recorrer los registros

        //Por lo que para recorrer los registros se utilizará
        while (registros.moveToNext()){ //Es aquí donde se va a ir llenando el objetos contactos que esta en el return

            Contacto contactoActual = new Contacto();

            //Lo que se esta haciendo aquí es  construir el objeto que se va almacenar en la lista contactos
            contactoActual.setId(registros.getInt(0));
            contactoActual.setNombre(registros.getString(1));
            contactoActual.setTelefono(registros.getString(2));
            contactoActual.setEmail(registros.getString(3));
            contactoActual.setFoto(registros.getInt(4));

            String queryLikes = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES + ") as likes " +
                                " FROM " + ConstantesBaseDatos.TABLE_LIKES_CONTACT +
                                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + "=" + contactoActual.getId();

            Cursor registrosLikes = sqLiteDatabase.rawQuery(queryLikes, null);

            if (registrosLikes.moveToNext()){

                contactoActual.setLikes(registrosLikes.getInt(0));

            }else{

                contactoActual.setLikes(0);

            }

            contactos.add(contactoActual);

        }

        //Después de haber ejecutado una conexión, un query
        sqLiteDatabase.close();

        return contactos;

    }

    //Este método es genérico y se puede llamar cuantas veces se quiera
    public void insertarContacto(ContentValues contentValues){ //Para insertar un contacto se necesitará un objeto que se llama ContentResolver

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(ConstantesBaseDatos.TABLE_CONTACTS, null, contentValues); //contentValues va a tener asociado el valor con su respectivo campo
        sqLiteDatabase.close();

    }

    public void insertarLikeContacto(ContentValues contentValues){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(ConstantesBaseDatos.TABLE_LIKES_CONTACT, null, contentValues);
        sqLiteDatabase.close();

    }


    public int obtenerLikesContacto(Contacto objcontacto){

        int likes = 0; //variable local

        //"Traeme la suma del campo numero_likes de la tabla likes_contact donde el id coincida con el id que estoy trayendo del oBj contacto"
        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES+")" +
                        " FROM " + ConstantesBaseDatos.TABLE_LIKES_CONTACT +
                        " WHERE " + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + "=" + objcontacto.getId();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor registros = sqLiteDatabase.rawQuery(query, null);

        if (registros.moveToNext()){

            likes = registros.getInt(0);

        }

        sqLiteDatabase.close();

        return likes;

    }

}
