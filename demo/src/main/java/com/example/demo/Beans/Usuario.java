package com.example.demo.Beans;

import java.sql.Timestamp;
import java.time.DateTimeException;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String correo;
    private String password;
    private Rol idRol;
    private Timestamp ultimoIngreso;
    private int cantidadIngresos;
    private Timestamp fechaRegistro;
    private Timestamp fechaEdicion;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public Timestamp getUltimoIngreso() {
        return ultimoIngreso;
    }

    public void setUltimoIngreso(Timestamp ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }

    public int getCantidadIngresos() {
        return cantidadIngresos;
    }

    public void setCantidadIngresos(int cantidadIngresos) {
        this.cantidadIngresos = cantidadIngresos;
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
