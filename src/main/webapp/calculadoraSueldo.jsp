<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Sistema Calcula Sueldos Rinku</title>
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
		crossorigin="anonymous">
	<link rel="stylesheet" href="css/general.css">
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/calculadora.js"></script>
</head>
<body>
	<div class="container">
		<div class="row text-center col-md-12">
			<a class="col-sm-3 btn btn-secondary" href="adminEmpleados.jsp">Administrar empleados</a><br/>
			<a class="col-sm-3 btn btn-secondary" href="adminMovimientos.jsp">Administrar movimientos</a><br/>
			<a class="col-sm-3 btn btn-secondary" href="calculadoraSueldo.jsp">Calcular sueldo empleado</a>
		</div>
		<div class="row text-center" style="color: blue;">
			<h2>Calcular sueldo</h2>
		</div>
		<div class="row col-md-12 col-md-offset-3" id="seccionBuscarEmpleado">
			<div class="card card-body">
				<h4>Buscar empleado</h4>
				<div class="col-md-12 col-md-offset-3">
					<form id="buscarEmpForm" action= "<%= request.getContextPath() %>/CalculadoraSueldo/buscar" method="post">
						<div class="form-group row">
							<label for="numEmpleado" class="col-sm-3 col-form-label">Número de empleado:</label> 
							<div class="col-sm-5" ><input type="text" class="form-control" id="numEmpleado" name="numEmpleado"></div>
							<button class="btn btn-primary col-sm-2" id="btnBusca">Buscar</button>
							<button type ="button" class="btn btn-danger col-sm-2" id="btnLimpia">Borrar</button>
						</div>
						
					</form>
					<div class="row col-md-12">
						<label class="col-sm-3 col-form-label">Nombre de empleado : </label>
						<label class="col-sm-4 col-form-label" id="lblNombre"></label>
					</div>
					<div class="row col-md-12">
						<label class="col-sm-3 col-form-label">Rol : </label>
						<label class="col-sm-4 col-form-label" id="lblRol"></label>
					</div>
					<div class="row col-md-12">
						<label class="col-sm-3 col-form-label">Tipo : </label>
						<label class="col-sm-4 col-form-label" id="lblTipo"></label>
					</div>
				
					<form id="buscarMovsEmpForm" action="<%= request.getContextPath() %>/CalculadoraSueldo/calcular" method="post" class="row col-md-12">
						<div class="row col-md-6">
							<label class="col-sm-6 col-form-label" for="fechaIni">Fecha inicio: </label>
							<div class="col-sm-6" ><input type="date" class="form-control" id="fechaIni" name="fechaIni"/></div>
						</div>
						<div class="row col-md-6">
							<label class="col-sm-6 col-form-label" for="fechaFin">Fecha fin: </label>
							<div class="col-sm-6" ><input type="date" class="form-control" id="fechaFin" name="fechaFin"/></div>
						</div>
						<button class="btn btn-primary col-sm-2" id="btnCalcula">Calcular sueldo</button>
					</form>
					<h5>Percepciones</h5>
					<div class="row col-md-8">
						<label class="col-sm-4 col-form-label">Días laborados : </label>
						<label class="col-sm-4 col-form-label" id="lblDiasLab"></label>
					</div>
					<div class="row col-md-8">
						<label class="col-sm-4 col-form-label">Sueldo base : </label>
						<label class="col-sm-4 col-form-label" id="lblSueldoBase"></label>
					</div>
					<div class="row col-md-8">
						<label class="col-sm-4 col-form-label">Pago entregas realizadas: </label>
						<label class="col-sm-4 col-form-label" id="lblPEntregas"></label>
					</div>
					<div class="row col-md-8">
						<label class="col-sm-4 col-form-label">Bonos : </label>
						<label class="col-sm-4 col-form-label" id="lblBonos"></label>
					</div>
					<div class="row col-md-8">
						<label class="col-sm-4 col-form-label">Subtotal : </label>
						<label class="col-sm-4 col-form-label" id="lblSubtotal"></label>
					</div>
					<div class="row col-md-8">
						<label class="col-sm-4 col-form-label">Vales de despensa : </label>
						<label class="col-sm-4 col-form-label" id="lblVales"></label>
					</div>
					<h5>Deducciones</h5>
					<div class="row col-md-8">
						<label class="col-sm-4 col-form-label">Retiene ISR (9%): </label>
						<label class="col-sm-4 col-form-label" id="lblretieneISR"></label>
					</div>
					<div class="row col-md-8">
						<label class="col-sm-4 col-form-label">Retiene ISR adicional(3%): </label>
						<label class="col-sm-4 col-form-label" id="lblretieneISRAd"></label>
					</div>
					<div class="row col-md-8">
						<label class="col-sm-4 col-form-label">Sueldo Neto: </label>
						<label class="col-sm-4 col-form-label" id="lblSueldoTotal"></label>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>