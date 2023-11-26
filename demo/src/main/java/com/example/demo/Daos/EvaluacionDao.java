package com.example.demo.Daos;
import com.example.demo.Beans.Curso;
import com.example.demo.Beans.Evaluaciones;


import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EvaluacionDao extends DaoBase{
    public ArrayList<Evaluaciones> listar() {

        ArrayList<Evaluaciones> lista = new ArrayList<>();

        String sql = "SELECT idevaluaciones, nombre_estudiantes, codigo_estudiantes, correo_estudiantes, nota FROM evaluaciones";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Evaluaciones evaluaciones = new Evaluaciones();
                evaluaciones.setIdEvaluaciones(rs.getInt(1));
                evaluaciones.setNombreEstudiantes(rs.getString(2));
                evaluaciones.setCodigoEstudiantes(rs.getString(3));
                evaluaciones.setCorreoEstudiantes(rs.getString(4));
                evaluaciones.setNota(rs.getInt(5));

                lista.add(evaluaciones);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluacionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void registrarEvaluacion(String nombreEstudiante, String codigo, String correoEstudiante, int nota,int idSem,int idCurso) throws SQLException {
        String query = "INSERT INTO evaluaciones (nombre_estudiantes, codigo_estudiantes, correo_estudiantes, nota,idcurso,idsemestre,fecha_registro,fecha_edicion) " +
                "VALUES (?, ?, ?, ?,?,?,now(),now())";

        try (Connection conn= this.getConection();
             PreparedStatement statement = conn.prepareStatement(query);) {
            statement.setString(1, nombreEstudiante);
            statement.setString(2, codigo);
            statement.setString(3, correoEstudiante);
            statement.setInt(4, nota);
            statement.setInt(5, idCurso);
            statement.setInt(6, idSem);

            statement.executeUpdate();
        }
    }


    public void editarEvaluacion(int idEvaluaciones,String nombreEstudiante, String codigo, String correoEstudiante, int nota) throws SQLException {
        String query = "UPDATE evaluaciones SET nombre_estudiantes=?, codigo_estudiantes=?, correo_estudiantes=?, nota=? " +
                "WHERE idevaluaciones=?";
        try (Connection conn = this.getConection();
             PreparedStatement statement = conn.prepareStatement(query);) {
            statement.setString(1, nombreEstudiante);
            statement.setString(2, codigo);
            statement.setString(3, correoEstudiante);
            statement.setInt(4, nota);
            statement.setInt(5, idEvaluaciones);
            statement.executeUpdate();
        }
    }

    // Método para obtener una lista de evaluaciones por curso y semestre
    public ArrayList<Evaluaciones> obtenerEvaluacionesPorCursoYSemestre(int idCurso, int idSemestre) throws SQLException {
        ArrayList<Evaluaciones> evaluacionesList = new ArrayList<>();
        String query = "SELECT * FROM evaluaciones WHERE idcurso=? AND idsemestre=?";

        try (Connection conn = this.getConection();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, idCurso);
            statement.setInt(2, idSemestre);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Evaluaciones evaluacion = mapResultSetToEvaluacion(resultSet);
                    evaluacionesList.add(evaluacion);
                }
            }
        }

        return evaluacionesList;
    }

    public void eliminarEvaluacion(int idEvaluacion, int idSemestreHabilitado) throws SQLException {
        String query = "DELETE FROM evaluaciones WHERE idevaluaciones=? AND idsemestre=?";

        try (Connection conn = this.getConection();
                PreparedStatement statement = conn.prepareStatement(query);) {
            statement.setInt(1, idEvaluacion);
            statement.setInt(2, idSemestreHabilitado);

            statement.executeUpdate();
        }
    }
    public Evaluaciones obtenerEvaluacionPorId(int idEvaluacion) {
        Evaluaciones evaluacion = null;

        String sql = "SELECT * FROM evaluaciones WHERE idevaluaciones = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idEvaluacion);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    evaluacion = mapResultSetToEvaluacion(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluacionDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return evaluacion;
    }


    private Evaluaciones mapResultSetToEvaluacion(ResultSet resultSet) throws SQLException {
        Evaluaciones evaluacion = new Evaluaciones();
        evaluacion.setIdEvaluaciones(resultSet.getInt("idevaluaciones"));
        evaluacion.setNombreEstudiantes(resultSet.getString("nombre_estudiantes"));
        evaluacion.setCodigoEstudiantes(resultSet.getString("codigo_estudiantes"));
        evaluacion.setCorreoEstudiantes(resultSet.getString("correo_estudiantes"));
        evaluacion.setNota(resultSet.getInt("nota"));

        return evaluacion;
    }
}
