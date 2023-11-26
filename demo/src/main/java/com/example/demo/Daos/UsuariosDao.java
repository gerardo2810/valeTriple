package com.example.demo.Daos;

import com.example.demo.Beans.Rol;
import com.example.demo.Beans.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        // Ajusta estos métodos según la estructura exacta de tu clase Usuario y la tabla usuario
        usuario.setIdUsuario(rs.getInt("idusuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setPassword(rs.getString("password"));

        Rol rol = new Rol();
        rol.setIdRol(rs.getInt("idrol"));
        rol.setNombre(rs.getString("nombre"));
        usuario.setIdRol(rol);
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
}
