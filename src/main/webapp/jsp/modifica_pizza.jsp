<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<title>Modifica pizza</title>
</head>
<body>

	<div class="container" id="pagina">
		<form
			action="<%=request.getContextPath()%>/pizzeria?flag=salvaPizza&idPizza=${pizza.idPizza}"
			method="post">
			<br>
			<h2 align="center">Modifica la tua pizza</h2>
			<div class="row justify-content-md-center">
				<div class="col-md-auto" id="impasto" style="padding: 40px;">
					<h4>Impasto:</h4>
					<c:forEach var="impasto" items="${ impastoScelto }">
						<input type="radio" name="idImpasto" value="${impasto.idImpasto}"
							checked />
							${impasto.getNomeImpasto()}<br>
					</c:forEach>
					<c:forEach var="impasto" items="${ impastiScartati }">
						<input type="radio" name="idImpasto" value="${impasto.idImpasto}" />
							${impasto.getNomeImpasto()}<br>
					</c:forEach>
				</div>
				<div class="col-md-auto" id="ingredienti" style="padding: 40px;">
					<h4>Ingredienti:</h4>
					<c:forEach items="${ ingredientiScelti }" var="ingrediente">
						<input type="checkbox" name="idIngrediente"
							value="${ingrediente.idIngrediente}" checked />
							${ingrediente.getNomeIngrediente()}<br>
					</c:forEach>
					<c:forEach items="${ ingredientiScartati }" var="ingrediente">
						<input type="checkbox" name="idIngrediente"
							value="${ingrediente.idIngrediente}" />
							${ingrediente.getNomeIngrediente()}<br>
					</c:forEach>

				</div>
			</div>
			<div id="nomePizza" style="clear: both; text-align: center;">
				<h3>Dai un nuovo nome alla tua pizza</h3>
				<input type="text" name="nomePizza" value="${pizza.nomePizza}">
				<input type="hidden" name="idPizza" value="${pizza.idPizza}">
				<input type="hidden" name="idUtente"
					value="${cookie['idUtente'].getValue()}"> <input
					type="hidden" name="nomeUtente"
					value="${cookie['nomeUtente'].getValue()}"> <input
					type="submit" value="Salva le modifiche">
			</div>
		</form>
	</div>

</body>
</html>