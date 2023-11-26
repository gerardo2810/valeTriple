<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.example.demo.Beans.Evaluaciones" %>
<jsp:useBean id="evaluaciones" type="com.example.demo.Beans.Evaluaciones" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <title>Editar empleado</title>
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="emp"/>
    </jsp:include>
    <div class="row mb-4">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Editar evaluaciones</h1>
            <hr>
            <form method="POST" action="CouseTeachServlet">
                <input type="hidden" name="idEvaluaciones" value="<%= evaluaciones.getIdEvaluaciones()%>"/>
                <div class="mb-3">
                    <label class="form-label" for="first_name">First Name</label>
                    <input type="text" class="form-control form-control-sm" id="first_name" name="first_name"
                           value="<%= evaluaciones.getNombreEstudiantes()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="codigo_estudiantes"> Codigo</label>
                    <input type="text" class="form-control form-control-sm" id="codigo_estudiantes" name="codigo_estudiantes"
                           value="<%= evaluaciones.getCodigoEstudiantes()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="email">Email</label>
                    <input type="text" class="form-control form-control-sm" id="email" name="email"
                           value="<%= evaluaciones.getCorreoEstudiantes()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="nota">Salary</label>
                    <input type="text" class="form-control form-control-sm" id="nota" name="nota"
                           value="<%= evaluaciones.getNota()%>">
                </div>
                <a href="<%= request.getContextPath()%>/CouseTeachServlet" class="btn btn-danger">Cancelar</a>
                <input type="submit" value="Guardar" class="btn btn-primary"/>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>

