<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Segurado"%>
<%@ page import="persistence.Dao"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
<title>Listar Segurado</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-4 col-md-12 col-lg-8 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h5 class="card-title text-center">Listar Segurado</h5>

						<table class="table table-bordered">
								<tr  class="table-info">
									<td></td>
									<td>Nome</td>
									<td>CPF</td>
									<td>RG</td>
									<td>Sexo</td>
									<td>Correntista</td>
									<td>Ações</td>
									
								</tr>

							<c:forEach var="segurado" items="${segurados}">
								<tr>
									<td>${segurado.id}</td>
									<td>${segurado.nome}</td>
									<td>${segurado.cpf}</td>
									<td>${segurado.rg}</td>
									<td>${segurado.sexo}</td>
									<td>${segurado.correntista}</td>
								</tr>
							</c:forEach>

						</table>

						<span><a href="cadastrarsegurado.jsp">Adicionar Novo
								Segurado</a></span>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

