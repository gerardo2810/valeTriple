package com.example.demo.Servlet;

import com.example.demo.Beans.Curso;
import com.example.demo.Beans.Usuario;
import com.example.demo.Daos.CursoDao;
import com.example.demo.Daos.EvaluacionDao;
import com.example.demo.Daos.UsuariosDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DecanoCourseServlet", value = "/DecanoCourseServlet")
public class DecanoCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        RequestDispatcher view;
        UsuariosDao usuariosDao = new UsuariosDao();
        CursoDao cursoDao = new CursoDao();

        switch (action) {
            case "lista":
                request.setAttribute("listaCursos", cursoDao.listar());
                view = request.getRequestDispatcher("decano/listCouse.jsp");
                view.forward(request, response);
                break;

            case "agregar":
                request.setAttribute("listaUsuarios", usuariosDao.listar());
                view = request.getRequestDispatcher("decano/newCourse.jsp");
                view.forward(request, response);
                break;
            case "editar":
                int idCurso=Integer.parseInt(request.getParameter("id"));
                Curso curso = cursoDao.obtenerEvaluacionPorId(idCurso);
                request.setAttribute("curso",curso);
                view=request.getRequestDispatcher("decano/udpateCourse");
                view.forward(request,response);
                break;
            case "borrar":
                /*cursoDao.eliminarCurso(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("DecanoCourseServlet?msg=Curso borrado ");*/
            default:
                response.sendRedirect("DecanoCourseServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        CursoDao cursoDao = new CursoDao();
        HttpSession httpSession = request.getSession();
        Usuario user  = (Usuario) httpSession.getAttribute("usuarioLogueado");

        try{
            switch (action) {
                case "guardar":
                    String nombre = request.getParameter("first_name");
                    String codigo = request.getParameter("codigo");

                    cursoDao.registrarCursos(nombre,codigo,4,1);
                    response.sendRedirect("DecanoServlet?msg=Curso creado exitosamente");
                    break;
                case "editar":
                    String nom=request.getParameter("n");
                    break;

            }
        }catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al guardar el curso en la base de datos.");

        }
    }
}
