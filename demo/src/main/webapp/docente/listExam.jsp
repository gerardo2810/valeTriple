<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.demo.Beans.Usuario" %>
<%@ page import="com.example.demo.Beans.Evaluaciones" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="listaEvaluaciones" type="java.util.ArrayList<com.example.demo.Beans.Evaluaciones>" scope="request"/>
<jsp:useBean id="textoBusqueda" scope="request" type="java.lang.String" class="java.lang.String"/>
<jsp:useBean id="usuarioLogueado" class="com.example.demo.Beans.Usuario" type="com.example.demo.Beans.Usuario" scope="session" />
<!DOCTYPE html>
<html>
<head>
    <title>Lista empleados</title>
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="emp"/>
    </jsp:include>
    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1>Lista de empleados</h1>
        </div>
        <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
            <a href="<%= request.getContextPath()%>/CouseTeachServlet?action=agregar" class="btn btn-primary">Agregar
                nueva evaluacion</a>
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
    <form method="post" action="<%=request.getContextPath()%>/CouseTeachServlet?action=buscar">
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Buscar por nombre" name="textoBuscar"
                   value="<%=textoBusqueda%>"/>
            <button class="input-group-text" type="submit">
                <i class="bi bi-search"></i>
            </button>
            <a class="input-group-text" href="<%=request.getContextPath()%>/CouseTeachServlet">
                <i class="bi bi-x-circle"></i>
            </a>
        </div>
    </form>
    <table class="table">
        <thead>
        <tr>
            <th>Id Evaluaciones</th>
            <th>Nombre </th>
            <th>Codigo </th>
            <th>Coreeo</th>
            <th>Nota</th>
            <th>Curso</th>
            <th>Semestre </th>
            <th>Fecha registro </th>
            <% if(usuarioLogueado != null && usuarioLogueado.getIdUsuario() > 0) {%>
            <th></th>
            <th></th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <%
            for (Evaluaciones e : listaEvaluaciones) {
        %>
        <tr>
            <td><%= e.getIdEvaluaciones()%>
            </td>
            <td><%= e.getNombreEstudiantes()%>
            </td>
            <td><%= e.getCodigoEstudiantes()%>
            </td>
            <td><%= e.getCorreoEstudiantes()%>
            </td>
            <td><%= e.getIdCurso().getNombre() %>
            </td>
            <td><%= e.getIdSemestre().getNombre() %>
            </td>
            <td><%= e.getNota() %>
            </td>
            <% if(usuarioLogueado != null && usuarioLogueado.getIdUsuario() > 0) {%>
            <td>
                <a href="<%=request.getContextPath()%>/CouseTeachServlet?action=editar&id=<%= e.getIdEvaluaciones()%>"
                   type="button" class="btn btn-primary">
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>
            <td>
                <a onclick="return confirm('¿Estas seguro de borrar?');"
                   href="<%=request.getContextPath()%>/CouseTeachServlet?action=borrar&id=<%= e.getIdEvaluaciones()%>"
                   type="button" class="btn btn-danger">
                    <i class="bi bi-trash"></i>
                </a>
            </td>
            <% } %>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>

