<%@page import="java.util.ArrayList" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="curso" type="com.example.demo.Beans.Curso" scope="request"/>
<jsp:useBean scope="request" id="listaTrabajos" type="java.util.ArrayList<com.example.demo.Beans.Curso>"/>
<jsp:useBean id="listaCursos" type="java.util.ArrayList<com.example.demo.Beans.Curso>"
             scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <title>Editar curso</title>
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
            <h1 class='mb-3'>Editar Curso</h1>
            <hr>
            <form method="POST" action="DecanoCourseServlet">
                <input type="hidden" name="idcurso" value="<%= curso.getIdCurso()%>"/>
                <div class="mb-3">
                    <label class="form-label" for="first_name">First Name</label>
                    <input type="text" class="form-control form-control-sm" id="first_name" name="first_name"
                           value="<%= curso.getNombre()%>">
                </div>
                <a href="<%= request.getContextPath()%>/DecanoCourseServlet" class="btn btn-danger">Cancelar</a>
                <input type="submit" value="Guardar" class="btn btn-primary"/>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
