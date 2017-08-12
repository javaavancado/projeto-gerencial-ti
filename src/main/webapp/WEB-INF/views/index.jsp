<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<title>Sistema de controle gerencial</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/resources/css/screen.css"/>" />
</head>

<body>
	<div id="container">
		<div align="center">
			<h1>Bem vindo!</h1>
		</div>

		<div align="center">
			
			<a href="rd/index.jsf"> <img
				alt="Módulo Inspeção" title="Módulo Inspeção" width="100"
				height="100"
				src="<c:url value="/static/resources/gfx/inspecao-icon.png"/>" />
			</a>
			<br/>
			<a href="rd/index.jsf">Inspeção</a>

		</div>


		<div id="footer">
			<p>
				Java Avançado TI.<br />
			</p>
		</div>
	</div>
</body>
</html>
