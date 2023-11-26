<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.demo.Beans.Usuario" %>
<%@ page import="com.example.demo.Beans.Curso" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="listaCursos" type="java.util.ArrayList<com.example.demo.Beans.Curso>" scope="request"/>

<jsp:useBean id="usuarioLogueado" class="com.example.demo.Beans.Usuario" type="com.example.demo.Beans.Usuario" scope="session" />
<!DOCTYPE html>
<html>
<head>
    <title>Lista Cursos</title>
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="emp"/>
    </jsp:include>
    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1>Lista de cursos</h1>
        </div>
        <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
            <a href="<%= request.getContextPath()%>/DecanoCourseServlet?action=agregar" class="btn btn-primary">Agregar
                nuevo curso</a>
        </div>
    </div>
    <% if (request.getParameter("msg") != null) {%>
    <div class="alert alert-success" role="alert"><%=request.getParameter("msg")%>
    </div>
    <% } %>
    <% if (request.getParameter("err") != null) {%>
    <div class="alert alert-danger" role="alert"><%=request.getParameter("err")%>
    </div>
    <% } %>
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Id Curso</th>
            <th>Nombre</th>
            <th>Codigo</th>
            <th>Id Facultad</th>
            <th>Fecha Registro</th>
            <th>Fecha Edicion</th>
            <% if(usuarioLogueado != null && usuarioLogueado.getIdUsuario() > 0) {%>
            <th></th>
            <th></th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Curso curso : listaCursos) {
        %>
        <tr>
            <td><%= i%>
            </td>
            <td><%=curso.getIdCurso()%></td>
            <td><%= curso.getNombre()%>
            </td>
            <td><%= curso.getCodigo()%>
            </td>
            <td><%= curso.getIdFacultad()%>
            </td>
            <td><%= curso.getFechaRegistro()%>
            </td>
            <td><%= curso.getFechaEdicion()%>
            </td>
            <% if(usuarioLogueado != null && usuarioLogueado.getIdUsuario() > 0) {%>
            <td>
                <a href="<%=request.getContextPath()%>/DecanoCourseServlet?action=editar&id=<%= curso.getIdCurso()%>"
                   type="button" class="btn btn-primary">
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>
            <td>
                <a onclick="return confirm('¿Estas seguro de borrar?');"
                   href="<%=request.getContextPath()%>/DecanoCourseServlet?action=editar&id=<%= curso.getIdCurso()%>"
                   type="button" class="btn btn-danger">
                    <i class="bi bi-trash"></i>
                </a>
            </td>
            <% } %>
        </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>