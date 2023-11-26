<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.demo.Beans.Usuario" %>
<%@ page import="com.example.demo.Beans.Curso" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="listaUsuarios" type="java.util.ArrayList<com.example.demo.Beans.Usuario>"
             scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <title>Nuevo Curso</title>
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
            <h1 class='mb-3'>Nuevo Curso</h1>
            <hr>
            <form method="POST" action="DecanoCourseServlet">
                <div class="mb-3">
                    <label class="form-label" for="codigo">First Name</label>
                    <input type="text" class="form-control form-control-sm" id="codigo" name="codigo">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="nombre">Last Name</label>
                    <input type="text" class="form-control form-control-sm" id="nombre" name="nombre">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="idusuario">Docentes</label>
                    <select name="" id="idusuario" class="form-select">
                        <% for (Usuario usuario : listaUsuarios) {%>
                        <option value="<%=usuario.getIdUsuario()%>"><%=usuario.getNombre()%>
                        </option>
                        <% }%>
                    </select>
                </div>
                <a href="<%= request.getContextPath()%>/DecanoCourseServlet" class="btn btn-danger">Cancelar</a>
                <input type="submit" value="Guardar" class="btn btn-primary"/>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
