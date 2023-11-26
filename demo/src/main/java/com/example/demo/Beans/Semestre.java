package com.example.demo.Beans;

import java.sql.Timestamp;

public class Semestre {
    private int idSemestre;
    private String nombre;
    private Usuario idAdministrador;
    private Boolean habilitado;
    private Timestamp fechaRegistro;
    private Timestamp fechaEdicion;

    public int getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Usuario idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Timestamp getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(Timestamp fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }
}
