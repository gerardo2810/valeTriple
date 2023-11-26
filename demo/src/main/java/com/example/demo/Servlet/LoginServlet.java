package com.example.demo.Servlet;

import com.example.demo.Beans.Usuario;
import com.example.demo.Daos.UsuariosDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Usuario usuarioLogged = (Usuario) httpSession.getAttribute("usuarioLogueado");

        if(usuarioLogged != null && usuarioLogged.getIdUsuario() > 0){

            if(request.getParameter("a") != null){//logout
                httpSession.invalidate();
            }
            response.sendRedirect(request.getContextPath());
        }else{
            request.getRequestDispatcher("loginFrom.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username: " + username + " | password: " + password);
        UsuariosDao usuariosDao = new UsuariosDao();

        if(usuariosDao.validarUsuarioPassword(username,password)){
            System.out.println("usuario y password válidos");
            Usuario usuario = usuariosDao.obtenerUsuarioPorCorreo(username);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("usuarioLogueado",usuario);
            response.sendRedirect(request.getContextPath());
        }else{
            System.out.println("usuario o password incorrectos");
            request.setAttribute("err","Usuario o password incorrectos");
            request.getRequestDispatcher("loginFrom.jsp").forward(request,response);
        }
    }
}
