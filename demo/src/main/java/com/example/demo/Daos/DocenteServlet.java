package com.example.demo.Daos;

import com.example.demo.Beans.Curso;
import com.example.demo.Beans.Evaluaciones;
import com.example.demo.Beans.Semestre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocenteServlet extends DaoBase{
    public ArrayList<Evaluaciones> listarEvaluacionesPorCurso(int idDocente) {

        String sql = "SELECT e.* " +
                "FROM evaluaciones e " +
                "JOIN curso_has_docente cd ON e.idcurso = cd.idcurso " +
                "JOIN usuario u ON cd.iddocente = u.idusuario " +
                "WHERE u.idusuario = ?";

        ArrayList<Evaluaciones> lista = new ArrayList<>();

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idDocente);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Evaluaciones evaluacion = new Evaluaciones();
                    evaluacion.setIdEvaluaciones(rs.getInt("idevaluaciones"));
                    evaluacion.setNombreEstudiantes(rs.getString("nombre_estudiantes"));
                    evaluacion.setCodigoEstudiantes(rs.getString("codigo_estudiantes"));
                    evaluacion.setCorreoEstudiantes(rs.getString("correo_estudiantes"));
                    evaluacion.setNota(rs.getInt("nota"));

                    // Crea instancias de Curso y Semestre y establece sus atributos
                    Curso curso = new Curso();
                    curso.setIdCurso(rs.getInt("idcurso"));
                    // Puedes establecer otros atributos de Curso según tus necesidades
                    evaluacion.setIdCurso(curso);

                    Semestre semestre = new Semestre();
                    semestre.setIdSemestre(rs.getInt("idsemestre"));
                    // Puedes establecer otros atributos de Semestre según tus necesidades
                    evaluacion.setIdSemestre(semestre);

                    evaluacion.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                    evaluacion.setFechaEdicion(rs.getTimestamp("fecha_edicion"));

                    lista.add(evaluacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
