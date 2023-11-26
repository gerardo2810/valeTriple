package com.example.demo.Daos;

import com.example.demo.Beans.Curso;
import com.example.demo.Beans.Facultad;
import com.example.demo.Beans.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class DecanoServlet extends DaoBase{
    /*Metodo para que me muestre todo la lista de cursos de su faculta*/
    public ArrayList<Curso> listarCursosPorFacultad(int idDecano) {

        String sql = "SELECT c.* " +
                "FROM curso c " +
                "JOIN facultad_has_decano fd ON c.idfacultad = fd.idfacultad " +
                "WHERE fd.iddecano = ?";

        ArrayList<Curso> lista = new ArrayList<>();

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idDecano);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Curso curso = new Curso();
                    curso.setIdCurso(rs.getInt("idcurso"));
                    curso.setCodigo(rs.getString("codigo"));
                    curso.setNombre(rs.getString("nombre"));

                    // Crea una instancia de Facultad y establece sus atributos
                    Facultad facultad = new Facultad();
                    facultad.setIdFacultad(rs.getInt("idfacultad"));
                    // Puedes establecer otros atributos de Facultad según tus necesidades
                    curso.setIdFacultad(facultad);

                    curso.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                    curso.setFechaEdicion(rs.getTimestamp("fecha_edicion"));

                    lista.add(curso);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }



}
