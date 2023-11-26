package com.example.demo.Servlet;

import com.example.demo.Beans.Evaluaciones;
import com.example.demo.Daos.EvaluacionDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "DocenteServlet", value = "/DocenteServlet")
public class DocenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action")== null ? "lista" : request.getParameter("action");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
