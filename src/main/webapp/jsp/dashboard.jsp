<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.advancia.stage.dao.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">

<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<link
	href="https://code.jquery.com/ui/1.12.1/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet" />
<title>Pizzeria</title>
</head>

<body onload="pizzaSorter(this)">
	<!-- load jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>

	<!-- load jQuery UI -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"
		integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="js/sorting.js"></script>
	<%
	String idUtente = null;
	String nomeUtente = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("nomeUtente")) {
		nomeUtente = cookie.getValue();
			}
			if (cookie.getName().equals("idUtente")) {
		idUtente = cookie.getValue();
			}
		}
	}
	if (nomeUtente == null) {
		response.sendRedirect("jsp/index.jsp");
	}

	// l'attributo deve corrispondere al parametro passato nel SetAttribute della servlet
	%>

	<h1 align="center">
		Benvenuto,
		<%=nomeUtente%>!
	</h1>
	<br>
	<h2 align="center">Aggiungi una nuova pizza</h2>
	<div class="container">
		<form action="<%=request.getContextPath()%>/pizzeria?flag=creaPizza"
			method="post">
			<div class="row justify-content-md-center">
				<div class="col-md-auto" id="impasto" style="padding: 40px;">
					<h4>Impasto</h4>
					<div class="form-check">
						<c:forEach var="impasto" items="${ listaImpasti }">
							<input class="form-check-input" type="radio" name="idImpasto"
								id="defaultRadio" value="${impasto.idImpasto}" />
							<label class="form-check-label" for="defaultRadio">
								${impasto.getNomeImpasto()}</label>
							<br>
						</c:forEach>
					</div>
				</div>
				<div class="col-md-auto" id="ingredienti" style="padding: 40px;">

					<h4>Ingredienti</h4>
					<div class="form-check">
						<c:forEach items="${ listaIngredienti }" var="ingrediente">
							<input class="form-check-input" type="checkbox"
								name="idIngrediente" id="defaultCheck"
								value="${ingrediente.idIngrediente}" />
							<label class="form-check-label" for="defaultCheck">
								${ingrediente.getNomeIngrediente()}</label>
							<br>
						</c:forEach>
					</div>
				</div>
			</div>

			<div class="container">

				<br>
				<div class="row justify-content-md-center">
					<h3>Dai un nome alla tua pizza</h3>
					<div class="container">
						<div class="row justify-content-md-center">
							<input type="text" name="nomePizza" value="${pizza.nomePizza}">
							<input type="hidden" name="idPizza" value="${pizza.idPizza}">
							<input type="hidden" name="idUtente"
								value="${cookie['idUtente'].getValue()}"> <input
								type="hidden" name="nomeUtente"
								value="${cookie['nomeUtente'].getValue()}">
							<button class="btn btn-dark btn-sm" type="submit">Aggiungi
								la pizza</button>
						</div>
					</div>
				</div>
			</div>
		</form>

		<br> <br>
		<!--  SEZIONE LISTA PIZZE -->
		<h2 style="text-align: center;">Le tue pizze</h2>
		<table class="table table-striped table-borderless table-sm"
			id="listaPizzeTable">
			<thead>
				<tr>
					<!--When a header is clicked, run the sortTable function, with a parameter,
						0 for sorting by names, 1 for sorting by country: -->
					<th scope="col">Pizza</th>
					<th scope="col">Impasto</th>
					<th scope="col">Ingredienti</th>
					<th scope="col" colspan="2" style="text-align: center;">Azioni</th>
				</tr>
			</thead>
			<tbody id="sortable">
				<c:forEach items="${ pizze }" var="pizza">
					<tr>
						<td style="vertical-align: middle;" scope="row">${pizza.nomePizza}
						<td style="vertical-align: middle;">${pizza.getImpasto().getNomeImpasto()}</td>
						<td style="vertical-align: middle;"><c:forEach
								items="${ pizza.getIngredientes() }" var="ingrediente">
							${ingrediente.getNomeIngrediente()}<br>
							</c:forEach></td>
						<td style="vertical-align: middle;"><a
							class="btn btn-primary btn-sm"
							href="<%=request.getContextPath()%>/pizzeria?flag=modificaPizza&idPizza=${pizza.idPizza}">modifica</a></td>
						<td style="vertical-align: middle;"><a
							class="btn btn-dark btn-sm"
							href="<%=request.getContextPath()%>/pizzeria?flag=eliminaPizza&idPizza=${pizza.idPizza}">elimina</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script>
			$(document).ready(function() {
				$("#sortable").sortable();
				$("#sortable").disableSelection();
			});
		</script>
		<script>
			
		</script>
	</div>
</body>

</html>

<!--  Visualizza il nome dell'utente autenticato -->