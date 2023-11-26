<%@page import="java.util.ArrayList" %>
<%@page import="com.example.demo.Beans.Evaluaciones" %>
<%@ page import="com.example.demo.Beans.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean type="java.util.ArrayList<com.example.demo.Beans.Evaluaciones>" scope="request" id="lista"/>
<jsp:useBean type="com.example.demo.Beans.Usuario" scope="session" id="docente"/>

<!DOCTYPE html>
<html>
<head>
    <title>Lista Evaluaciones</title>
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="evaluacion"/>
    </jsp:include>
    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1>Lista de Evaluaciones</h1>
        </div>
        <!-- Puedes agregar un enlace para agregar nuevas evaluaciones aquí si es necesario -->
    </div>
    <% if (session.getAttribute("msg") != null) {%>
    <div class="alert alert-success" role="alert"><%=session.getAttribute("msg")%>
    </div>
    <%
            session.removeAttribute("msg");
        } %>
    <% if (request.getParameter("err") != null) {%>
    <div class="alert alert-danger" role="alert"><%=request.getParameter("err")%>
    </div>
    <% } %>
    <table class="table">
        <tr>
            <th>#</th>
            <th>Nombre Estudiante</th>
            <th>Código Estudiante</th>
            <th>Correo Estudiante</th>
            <th>Nota</th>
            <!-- Agrega más columnas según sea necesario -->
            <th></th>
            <th></th>
        </tr>
        <%
            int i = 1;
            for (Evaluaciones evaluacion : lista) {
                // Verifica si la evaluación pertenece al curso del Docente
                if (evaluacion.getIdCurso().equals(usuario.getCurso())) {
        %>
        <tr>
            <td><%=i%></td>
            <td><%=evaluacion.getNombreEstudiantes()%></td>
            <td><%=evaluacion.getCodigoEstudiantes()%></td>
            <td><%=evaluacion.getCorreoEstudiantes()%></td>
            <td><%=evaluacion.getNota()%></td>
            <!-- Agrega más celdas según sea necesario -->
            <td>
                <!-- Enlace para editar evaluación -->
                <a href="<%=request.getContextPath()%>/DocenteServlet?action=editar&idEvaluacion=<%=evaluacion.getIdEvaluaciones()%>">
                    Editar
                </a>
            </td>
            <td>
                <!-- Enlace para eliminar evaluación -->
                <a href="<%=request.getContextPath()%>/DocenteServlet?action=borrar&idEvaluacion=<%=evaluacion.getIdEvaluaciones()%>">
                    Borrar
                </a>
            </td>
        </tr>
        <%
                    i++;
                }
            }
        %>
    </table>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
