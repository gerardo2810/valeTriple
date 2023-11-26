<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.demo.Beans.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="listaDocente" type="java.util.ArrayList<com.example.demo.Beans.Usuario>" scope="request"/>
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
            <h1>Lista de Docentes</h1>
        </div>
        <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
            <a href="<%= request.getContextPath()%>/DecanoDocenteServlet?action=agregar" class="btn btn-primary">Agregar
                nuevo docente</a>
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
            <th>Nombre</th>
            <th>Correo</th>
            <th>Rol</th>
            <th>Cantidad ingresos</th>
            <th>Fecha Registro </th>
            <th>Fecha Edision </th>
            <% if(usuarioLogueado != null && usuarioLogueado.getIdUsuario() > 0) {%>
            <th></th>
            <th></th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Usuario usuario : listaDocente) {
        %>
        <tr>
            <td><%= i%>
            </td>
            <td><%= usuario.getNombre()%>
            </td>
            <td><%= usuario.getCorreo()%>
            </td>
            <td><%=usuario.getIdRol()%></td>
            <td><%=  usuario.getCantidadIngresos()%>
            </td>
            <td><%= usuario.getFechaRegistro()%>
            </td>
            <td><%= usuario.getFechaEdicion()%>
            </td>
            <% if(usuarioLogueado != null && usuarioLogueado.getIdUsuario() > 0) {%>
            <td>
                <a href="<%=request.getContextPath()%>/DecanoDocenteServlet?action=editar&id=<%= usuario.getIdUsuario()%>"
                   type="button" class="btn btn-primary">
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>
            <td>
                <a onclick="return confirm('¿Estas seguro de borrar?');"
                   href="<%=request.getContextPath()%>/DecanoDocenteServlet?action=editar&id=<%= usuario.getIdUsuario()%>"
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

