package com.miempresa.mascotaideal;

public class Mascota {

    private int id;
    private String nombre;
    private int edad;
    private String raza;
    private String tamano;
    private int usuario;
    private long latData;
    private long lonData;
    private String ciudad;

    public Mascota() {
    }

    public Mascota(int id, String nombre, int edad, String raza, String tamano, int usuario, long latData, long lonData, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
        this.tamano = tamano;
        this.usuario = usuario;
        this.latData = latData;
        this.lonData = lonData;
        this.ciudad = ciudad;
    }

    public Mascota(String nombre, int edad, String raza, String tamano, int usuario, long latData, long lonData, String ciudad) {
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
        this.tamano = tamano;
        this.usuario = usuario;
        this.latData = latData;
        this.lonData = lonData;
        this.ciudad = ciudad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public long getLatData() {
        return latData;
    }

    public void setLatData(long latData) {
        this.latData = latData;
    }

    public long getLonData() {
        return lonData;
    }

    public void setLonData(long lonData) {
        this.lonData = lonData;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", raza='" + raza + '\'' +
                ", tamano='" + tamano + '\'' +
                ", usuario=" + usuario +
                ", latData=" + latData +
                ", lonData=" + lonData +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
