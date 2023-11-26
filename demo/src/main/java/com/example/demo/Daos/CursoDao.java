package com.example.demo.Daos;

import com.example.demo.Beans.Curso;
import com.example.demo.Beans.Evaluaciones;
import com.example.demo.Beans.Facultad;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CursoDao extends DaoBase{
    public ArrayList<Curso> listar() {

        ArrayList<Curso> lista = new ArrayList<>();

        String sql = "SELECT idcurso, codigo,nombre, idfacultad, fecha_registro,fecha_edicion FROM curso";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt(1));
                curso.setCodigo(rs.getString(2));
                curso.setNombre(rs.getString(3));
                curso.setIdFacultad(rs.getInt(4));
                curso.setFechaRegistro(rs.getTimestamp(5));
                curso.setFechaEdicion(rs.getTimestamp(6));
                lista.add(curso);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluacionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void registrarCursos(String nombre, String codigo, int idFaci,int idCurso) throws SQLException {
        String query = "INSERT INTO curso (codigo, nombre,idfacultad,fecha_registro,fecha_edicion) " +
                "VALUES (?, ?, ?, ?,?,?)";

        try (Connection conn= this.getConection();
             PreparedStatement statement = conn.prepareStatement(query);) {
            statement.setString(1, nombre);
            statement.setString(2, codigo);
            statement.setInt(4, idFaci);


            statement.executeUpdate();
        }
    }


    public void editarCursos(int idCurso,String nombre, String codigo, int idFacultad, Timestamp fechaRegistro, Timestamp fechaEdicion) throws SQLException {
        String query = "UPDATE curso SET codigo=?, nombre=?, idfacultad=?, fecha_registro=?,fecha_edicion=? " +
                "WHERE idcurso=?";

        try (Connection conn = this.getConection();
             PreparedStatement statement = conn.prepareStatement(query);) {
            statement.setString(1, codigo);
            statement.setString(2, nombre);
            statement.setInt(3, idFacultad);
            statement.setTimestamp(4, fechaRegistro);
            statement.setTimestamp(6, fechaEdicion);
            statement.executeUpdate();
        }
    }
    public void eliminarCurso(int idCurso) throws SQLException {
        String query = "DELETE FROM curso WHERE idcurso=?";

        try (Connection conn = this.getConection();
             PreparedStatement statement = conn.prepareStatement(query);) {
            statement.setInt(1, idCurso);

            statement.executeUpdate();
        }
    }
    public Curso obtenerEvaluacionPorId(int idCurso) {
        Curso curso = null;
        String sql = "SELECT * FROM curso WHERE idCurso = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idCurso);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    curso = mapResultSetToCurso(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluacionDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return curso;
    }
    private Curso mapResultSetToCurso(ResultSet resultSet) throws SQLException {
        Curso curso = new Curso();
        curso.setIdCurso(resultSet.getInt("idcurso"));
        curso.setCodigo(resultSet.getString("codigo"));
        curso.setNombre(resultSet.getString("nombre"));
        curso.setFechaEdicion(resultSet.getTimestamp("fecha_edicion"));

        return curso;
    }

}
