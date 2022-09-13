<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<title>Login</title>
</head>

<body>
	<%
	String messaggio = (String) request.getAttribute("errorMessage");
	if (messaggio != null)
		out.println("<font color=red size=4px>" + messaggio + "</font>");
	// l'attributo deve corrispondere al parametro passato nel SetAttribute della servlet
	%>
	<div class="container">

		<br>
		<div class="row justify-content-md-center">
			<form action="<%=request.getContextPath()%>/login?flag=mostraPizze"
				method="post">
				<div class="form-group">
					<label for="nomeUtente">Nome utente</label>
					<!--  il bottone manda alla pagina /login (la servlet) -->
					<input type="text" class="form-control" id="nomeUtente"
						name="nomeUtente">
				</div>
				<div class="form-group">
					<label for="passwordUtente">Password</label> <input
						class="form-control" type="password" name="passwordUtente"
						id="passwordUtente" />
				</div>

				<div class="row justify-content-md-center">
					<button class="btn btn-primary" type="submit" value="Login">Login</button>
				</div>
			</form>
		</div>
	</div>

</body>

</html>

<!-- Form di inserimento dati username/password e click di submit -->