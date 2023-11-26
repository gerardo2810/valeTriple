<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nuevo empleado</title>
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="emp"/>
    </jsp:include>
    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Nuevo de docente</h1>
            <hr>
            <form method="POST" action="DecanoDocenteServlet">
                <div class="mb-3">
                    <label class="form-label" for="first_name">First Name</label>
                    <input type="text" class="form-control form-control-sm" id="first_name" name="first_name">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="email">Email</label>
                    <input type="text" class="form-control form-control-sm" id="email" name="email">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="contraseña">Contraseña</label>
                    <input type="text" class="form-control form-control-sm" id="contraseña" name="contraseña">
                </div>
                <a href="<%= request.getContextPath()%>/DecanoDocenteServlet" class="btn btn-danger">Cancelar</a>
                <input type="submit" value="Guardar" class="btn btn-primary"/>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>

