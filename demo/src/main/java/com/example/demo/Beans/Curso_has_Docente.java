package com.example.demo.Beans;

public class Curso_has_Docente {
    private Curso idCurso;
    private Usuario idDocente;

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public Usuario getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Usuario idDocente) {
        this.idDocente = idDocente;
    }
}
