package com.example.demo.Servlet;

import com.example.demo.Beans.Usuario;
import com.example.demo.Daos.CursoDao;
import com.example.demo.Daos.EvaluacionDao;
import com.example.demo.Daos.UsuariosDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DecanoDocenteServlet", value = "/DecanoDocenteServlet")
public class DecanoDocenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action")== null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        UsuariosDao usuariosDao = new UsuariosDao();

        switch (action) {
            case "lista":
                request.setAttribute("listaDocente", usuariosDao.listar());
                view = request.getRequestDispatcher("decano/listTeacher.jsp");
                view.forward(request, response);
                break;

            case "agregar":
                view = request.getRequestDispatcher("decano/newTeacher.jsp");
                view.forward(request, response);
                break;
                /*
            case "editar":
                HttpSession httpSession = request.getSession();
                Usuario usuario = (Usuario) httpSession.getAttribute("usuarioLogueado");

                if(usuario != null && usuario.getIdUsuario() > 0) {
                    if (request.getParameter("id") != null) {
                        String evIdString = request.getParameter("id");
                        int evId = 0;
                        try {
                            evId = Integer.parseInt(evIdString);
                        } catch (NumberFormatException ex) {
                            response.sendRedirect("CouseTeachServlet");
                        }

                        Curso emp = cursoDao.obtenerEvaluacionPorId(evId);

                        if (emp != null) {
                            request.setAttribute("curso", emp);
                            //request.setAttribute("listaTrabajos", jobDao.listarTrabajos());
                            //request.setAttribute("listaDepartamentos", departmentDao.listaDepartamentos());
                            //request.setAttribute("listaJefes", employeeDao.listarEmpleados());
                            view = request.getRequestDispatcher("decano/udpateCourse.jsp");
                            view.forward(request, response);
                        } else {
                            response.sendRedirect("DecanoCourseServlet");
                        }

                    } else {
                        response.sendRedirect("DecanoCourseServlet");
                    }
                } else {
                    response.sendRedirect("DecanoCourseServlet");
                }
                break;

            case "borrar":
                if (request.getParameter("id") != null) {
                    String evIdString = request.getParameter("id");
                    int evId = 0;
                    try {
                        evId = Integer.parseInt(evIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("DecanoCourseServlet?err=Error al borrar el curso");
                    }

                    Curso curso = cursoDao.obtenerEvaluacionPorId(evId);

                    if (curso != null) {
                        //try {
                          //  evaluacionDao.eliminarEvaluacion(evId);
                            //response.sendRedirect("CouseTeachServlet?msg=Empleado borrado exitosamente");
                        //} catch (SQLException e) {
                          //  response.sendRedirect("CouseTeachServlet?err=Error al borrar el empleado");
                        //}
                    }
                } else {
                    response.sendRedirect("DecanoCourseServlet?err=Error al borrar el empleado");
                }
                break;
            default:
                response.sendRedirect("DecanoCourseServlet");*/
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        UsuariosDao usuariosDao = new UsuariosDao();
        HttpSession httpSession = request.getSession();
        Usuario user  = (Usuario) httpSession.getAttribute("usuarioLogueado");

        try{
            switch (action) {
                case "guardar":
                    String nombre = request.getParameter("first_name");
                    String correo = request.getParameter("email");

                    usuariosDao.registrarUsuario(nombre,correo,4,1);
                    response.sendRedirect("DecanoDocenteServlet?msg=Evaluacion creada exitosamente");
                    break;
                case "editar":
                    break;

            }
        }catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al guardar la evaluación en la base de datos.");

        }

    }
}
