package com.example.demo.Daos;

import com.example.demo.Beans.Evaluaciones;
import com.example.demo.Beans.Rol;
import com.example.demo.Beans.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuariosDao extends DaoBase{
    public ArrayList<Usuario> buscarUsuariosPorNombre(String nombre) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuario u \n"
                + "left join rol r ON (r.idrol = u.idrol) \n"
                + "WHERE u.nombre = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    fetchUsuarioData(usuario, rs);

                    listaUsuarios.add(usuario);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaUsuarios;
    }

    private void fetchUsuarioData(Usuario usuario, ResultSet rs) throws SQLException {
        usuario.setIdUsuario(rs.getInt("idusuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setPassword(rs.getString("password"));;
        usuario.setIdRol(rs.getInt("idrol"));
    }
    public ArrayList<Usuario> listar() {

        ArrayList<Usuario> lista = new ArrayList<>();

        String sql = "SELECT nombre, correo, idrol, cantidad_ingresos, fecha_registro,fecha_edicion FROM usuario";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNombre(rs.getString(1));
                usuario.setCorreo(rs.getString(2));
                usuario.setIdRol(rs.getInt(3));
                usuario.setCantidadIngresos(rs.getInt(4));
                usuario.setFechaRegistro(rs.getString(5));
                usuario.setFechaEdicion(rs.getString(6));
                lista.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public boolean validarUsuarioPassword(String username, String password){

        String sql = "SELECT * FROM employees_credentials where email = ? and password = ?";

        boolean exito = false;

        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,username);
            pstmt.setString(2,password);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    exito = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exito;
    }
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        Usuario usuario = null;

        String sql = "SELECT * FROM usuario u \n"
                + "left join rol r ON (r.idrol = u.idrol) \n"
                + "WHERE u.correo = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    usuario = new Usuario();
                    fetchUsuarioData(usuario, rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuario;
    }
    public void registrarUsuario(String nombre,String correo, int nota,int idrol) throws SQLException {
        String query = "INSERT INTO usuario (nombre_estudiantes, correo_estudiantes, password,idrol,cantidad_ingresos,fecha_registro,fecha_edicion) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn= this.getConection();
             PreparedStatement statement = conn.prepareStatement(query);) {
            statement.setString(1, nombre);
            statement.setString(2, correo);
            statement.setInt(3, nota);
            statement.setInt(4, idrol);

            statement.executeUpdate();
        }
    }
    public void editarUsuario(int idUsuario,String nombre, String codigo, String correoEstudiante, int nota) throws SQLException {
        String query = "UPDATE usuario SET nombre=?, correo=?, password=?, idrol=?, cantidad_ingresos=?, fecha_registro=?,fecha_edicion=?" +
                "WHERE idusuario=?";

        try (Connection conn = this.getConection();
             PreparedStatement statement = conn.prepareStatement(query);) {
            statement.setString(1, nombre);
            statement.setInt(6, idUsuario);
            statement.executeUpdate();
        }
    }

    public void eliminarUsuario(int idUsuario) throws SQLException {
        String query = "DELETE FROM evaluaciones WHERE idevaluaciones=? AND idsemestre=?";

        try (Connection conn = this.getConection();
             PreparedStatement statement = conn.prepareStatement(query);) {
            statement.setInt(1, idUsuario);
            statement.executeUpdate();
        }
    }


}
