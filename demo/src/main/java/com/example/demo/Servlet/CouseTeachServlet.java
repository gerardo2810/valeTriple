package com.example.demo.Servlet;

import com.example.demo.Beans.Evaluaciones;
import com.example.demo.Beans.Usuario;
import com.example.demo.Daos.EvaluacionDao;
import com.example.demo.Daos.SemestreDao;
import com.example.demo.Daos.UsuariosDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "CouseTeachServlet", value = "/CouseTeachServlet")
public class CouseTeachServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        RequestDispatcher view;
        UsuariosDao usuariosDao = new UsuariosDao();
        EvaluacionDao evaluacionDao = new EvaluacionDao();
        SemestreDao semestreDao = new SemestreDao();

        switch (action) {
            case "lista":
                request.setAttribute("listaEvaluaciones", evaluacionDao.listar());
                view = request.getRequestDispatcher("docente/listExam.jsp");
                view.forward(request, response);
                break;

            case "agregar":
                view = request.getRequestDispatcher("docente/newExam.jsp");
                view.forward(request, response);
                break;
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

                        Evaluaciones emp = evaluacionDao.obtenerEvaluacionPorId(evId);

                        if (emp != null) {
                            request.setAttribute("evaluaciones", emp);
                            /*request.setAttribute("listaTrabajos", jobDao.listarTrabajos());
                            request.setAttribute("listaDepartamentos", departmentDao.listaDepartamentos());
                            request.setAttribute("listaJefes", employeeDao.listarEmpleados());*/
                            view = request.getRequestDispatcher("docente/udpateExam.jsp");
                            view.forward(request, response);
                        } else {
                            response.sendRedirect("CouseTeachServlet");
                        }

                    } else {
                        response.sendRedirect("CouseTeachServlet");
                    }
                } else {
                    response.sendRedirect("CouseTeachServlet");
                }
                break;

            case "borrar":
                if (request.getParameter("id") != null) {
                    String evIdString = request.getParameter("id");
                    int evId = 0;
                    try {
                        evId = Integer.parseInt(evIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("CouseTeachServlet?err=Error al borrar el empleado");
                    }

                    Evaluaciones evaluaciones = evaluacionDao.obtenerEvaluacionPorId(evId);

                    if (evaluaciones != null) {
                        /*try {
                            evaluacionDao.eliminarEvaluacion(evId);
                            response.sendRedirect("CouseTeachServlet?msg=Empleado borrado exitosamente");
                        } catch (SQLException e) {
                            response.sendRedirect("CouseTeachServlet?err=Error al borrar el empleado");
                        }*/
                    }
                } else {
                    response.sendRedirect("CouseTeachServlet?err=Error al borrar el empleado");
                }
                break;
            default:
                response.sendRedirect("CouseTeachServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        EvaluacionDao evaluacionesDao = new EvaluacionDao();
        HttpSession httpSession = request.getSession();
        Usuario user  = (Usuario) httpSession.getAttribute("usuarioLogueado");

        try{
            switch (action) {
                case "guardar":
                    String nombre = request.getParameter("first_name");
                    String codigo = request.getParameter("codigo");
                    String correo = request.getParameter("email");
                    int nota = Integer.parseInt(request.getParameter("nota"));

                    evaluacionesDao.registrarEvaluacion(nombre,codigo,correo,nota,4,1);
                    response.sendRedirect("NotasYEvaluacionesServlet?msg=Empleado creado exitosamente");
                    break;
                case "editar":

            }
        }catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al guardar la evaluación en la base de datos.");

        }
    }
}
