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
	<script type="text/javascript" src="js/empleados.js"></script>
</head>

</head>
<body>
	<div class="container">
		<div class="row text-center col-md-12">
			<a class="col-sm-3 btn btn-secondary" href="adminEmpleados.jsp">Administrar empleados</a><br/>
			<a class="col-sm-3 btn btn-secondary" href="adminMovimientos.jsp">Administrar movimientos</a><br/>
			<a class="col-sm-3 btn btn-secondary" href="calculadoraSueldo.jsp">Calcular sueldo empleado</a>
		</div>
		<div class="row text-center" style="color: blue;">
			<h2>Administrar empleados</h2>
		</div>
		<hr>
		<div class="row col-md-12">
			<button class="btn btn-primary col-md-3" id="btnAgregar">Agregar</button>
			<button class="btn btn-warning col-md-3" id="btnBuscar">Buscar</button>
			<button class="btn btn-danger col-md-3" id="btnModificar">Modificar</button>
			<button class="btn btn-secondary col-md-3" id="btnEliminar">Eliminar</button>
		</div>
		<div class="row col-md-12 col-md-offset-3" id="seccionAgregar"><!-- Formulario agregar empleado -->

			<div class="card card-body">

				<h4>Registro de empleado</h4>
				<div class="col-md-12 col-md-offset-3">

					 <form id="agregarEmpForm" action= "<%= request.getContextPath() %>/api/agregar" method="post">

						<div class="form-group row">
							<label for="nombre" class="col-sm-3 col-form-label">Nombre Completo:</label> 
							<div class="col-sm-9" ><input type="text" class="form-control" id="nombre" name="nombre"></div>
						</div>
						<div class="row col-md-12 col-md-offset-3">
						<div class="form-check row col-md-6 col-md-offset-3">
							<label>Rol:</label> <br/>
							<input type="radio" id="rol" name= "rol" class="form-check-input" value="Chofer" checked/><label class="form-check-label" for="rol">Chofer</label><br/>
							<input type="radio" id="rol2" name= "rol" class="form-check-input" value="Cargador"/><label class="form-check-label" for="rol2">Cargador</label><br/>
							<input type="radio" id="rol3" name= "rol" class="form-check-input" value="Auxiliar"/><label class="form-check-label" for="rol3">Auxiliar</label>
						</div>
						
						<div class="form-check row col-md-6 col-md-offset-3" id="divTipo">
							<label>Tipo:</label> <br/>
							<input type="radio" id="tipo" name= "tipo" class="form-check-input" value="Interno" checked/><label class="form-check-label" for="tipo">Interno</label><br/>
							<input type="radio" id="tipo2" name= "tipo" class="form-check-input" value="Externo"/><label class="form-check-label" for="tipo2">Externo</label><br/>
						</div>
						</div>

						<button class="btn btn-primary" id="btnGuardar">Guardar</button>
						<button class="btn btn-danger" id="btnCancelar">Cancelar</button>
					</form>
				</div>
			</div>
		</div> 
		
		<div class="row col-md-12 col-md-offset-3" id="seccionBuscar"><!-- Formulario buscar empleado -->
			<div class="card card-body">
				<h4>Buscar empleado</h4>
				<div class="col-md-12 col-md-offset-3">
					<form id="buscarEmpForm" action= "<%= request.getContextPath() %>/api/buscar" method="post">
						<div class="form-group row">
							<label for="numEmpleado" class="col-sm-3 col-form-label">Número de empleado:</label> 
							<div class="col-sm-5" ><input type="text" class="form-control" id="numEmpleado" name="numEmpleado"></div>
							<button class="btn btn-primary col-sm-2" id="btnBusca">Buscar</button>
							<button class="btn btn-danger col-sm-2" id="btnBorrar">Borrar</button>
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
				</div>
				<div class="col-md-12 col-md-offset-3">
					
				</div>
			</div>
		</div>

		<div class="row col-md-12 col-md-offset-3" id="seccionEditar"><!-- Formulario editar empleado -->

			<div class="card card-body">

				<h4>Modificar empleado</h4>
				<div class="col-md-12 col-md-offset-3">
					
					<form id="buscarEmpForm2" action= "<%= request.getContextPath() %>/api/buscar" method="post">
						<div class="form-group row">
							<label for="numEmpleado" class="col-sm-3 col-form-label">Número de empleado:</label> 
							<div class="col-sm-5" ><input type="text" class="form-control" id="numEmpleadoEdit" name="numEmpleadoEdit"></div>
							<button class="btn btn-primary col-sm-2" id="btnBusca">Buscar</button>
						</div>
						
					</form>
					
					 <form id="editarEmpForm" action= "<%= request.getContextPath() %>/api/modificar" method="post">

						<div class="form-group row">
							<label for="nombre" class="col-sm-3 col-form-label">Nombre Completo:</label> 
							<div class="col-sm-9" ><input type="text" class="form-control" id="nombreEdit" name="nombre"></div>
						</div>
						<div class="row col-md-12 col-md-offset-3">
						<div class="form-check row col-md-6 col-md-offset-3">
							<label>Rol:</label> <br/>
							<input type="radio" id="rol" name= "rolEdit" class="form-check-input" value="Chofer" checked/><label class="form-check-label" for="rol">Chofer</label><br/>
							<input type="radio" id="rol2" name= "rolEdit" class="form-check-input" value="Cargador"/><label class="form-check-label" for="rol2">Cargador</label><br/>
							<input type="radio" id="rol3" name= "rolEdit" class="form-check-input" value="Auxiliar"/><label class="form-check-label" for="rol3">Auxiliar</label>
						</div>
						
						<div class="form-check row col-md-6 col-md-offset-3" id="divTipo">
							<label>Tipo:</label> <br/>
							<input type="radio" id="tipo" name= "tipoEdit" class="form-check-input" value="Interno" checked/><label class="form-check-label" for="tipo">Interno</label><br/>
							<input type="radio" id="tipo2" name= "tipoEdit" class="form-check-input" value="Externo"/><label class="form-check-label" for="tipo2">Externo</label><br/>
						</div>
						</div>
						<button class="btn btn-primary" id="btnGuardar">Guardar</button>
					</form>
				</div>
			</div>
		</div> 
		
	</div>
</body>
</html>