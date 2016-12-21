package com.ss_baez.miscontactos.pojo;

/**
 * Created by SS_Baez on 16/11/2016.
 */

public class Contacto {

    private String nombre;
    private String telefono;
    private String email;
    private int foto; //por el momento las imagenes van a estar viviendo en la carpeta drawable
    //Para el contador de likes
    private int likes;
    //El ID no debe de estar presente en el constructor
    private int id;

    public Contacto(int foto, String nombre, String telefono, String email, int likes) { //Método constructor
        this.foto= foto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.likes = likes;
    }

    public Contacto() {
        //Vacío por la datebase
    }

    //Aquí inician los getter & setters

    public int getFoto() { return foto; }

    public void setFoto(int foto) { this.foto = foto; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
