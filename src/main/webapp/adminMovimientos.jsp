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
	<link rel="stylesheet" href="css/datatables.min.css">
	<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/datatables.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.26.0/moment.min.js"></script>
	<script type="text/javascript">
	    var contextPath = "<%= request.getContextPath() %>";
	</script>
	<script type="text/javascript" src="js/movimientos.js"></script>
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
			<h2>Captura de movimientos</h2>
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

				<h4>Registro de movimientos</h4>
				<div class="col-md-12 col-md-offset-3">

					 <form id="agregarMovForm" action= "<%= request.getContextPath() %>/apiMov/agregar" method="post">
						<div class="form-group row">
							<label for="numEmpleado" class="col-sm-3 col-form-label">Número de empleado:</label> 
							<div class="col-sm-5" ><input type="text" class="form-control" id="numEmpleado" name="numEmpleado"></div>
							<button type="button" class="btn btn-primary" id="btnBuscarEmp">Buscar</button>
						</div> 
						<div class="form-group row">
							<label for="nombre" class="col-sm-3 col-form-label">Nombre Completo:</label> 
							<div class="col-sm-8" ><input type="text" class="form-control" id="nombre" name="nombre" disabled></div>
						</div>
						<div class="col-md-12 col-md-offset-3 row">
							<div class="form-group row col-md-6 col-md-offset-3">
								<label for="rol" class="col-sm-2 col-form-label">Rol:</label>
								<div class="col-sm-10" ><input type="text" class="form-control" id="rol" name="rol" disabled></div>
							</div>
							
							<div class="form-group row col-md-6 col-md-offset-3" id="tipoMov">
								<label for="tipo" class="col-sm-2 col-form-label">Tipo:</label>
								<div class="col-sm-10" ><input type="text" class="form-control" id="tipo" name="tipo" disabled></div>
							</div>
						</div>
						<div class="col-md-12 col-md-offset-3 row">
							<div class="form-group col-sm-6 row">
								<label for="fecha" class="col-sm-6 col-form-label">Fecha:</label> 
								<div class="col-sm-6" ><input type="date" class="form-control" id="fecha" name="fecha"></div>
							</div>
							<div class="form-group col-sm-6 row">
								<label for="fecha" class="col-sm-6 col-form-label">Cantidad de entregas:</label> 
								<div class="col-sm-6" ><input type="text" class="form-control" id="entregas" name="entregas"></div>
							</div>
						</div>
						<div class="col-md-12 col-md-offset-3 row" id="rolCubre">
							<div class="form-group col-sm-6 row">
								<label for="cTurno" class="col-sm-3 col-form-label">Cubrió turno:</label> 
								<div class="col-sm-3" ><input type="checkbox" class="form-control" id="cTurno" name="cTurno" ></div>
							</div>
							
							<div class="form-check row col-md-6 col-md-offset-3" id="radioRolCub">
								<label for="rolCubierto">Rol cubierto:</label> <br/>
								<input type="radio" id="rolCubierto" name= "rolCubierto" class="form-check-input" value="Chofer" checked/><label class="form-check-label" for="rolCubierto">Chofer</label><br/>
								<input type="radio" id="rolCubierto2" name= "rolCubierto" class="form-check-input" value="Cargador"/><label class="form-check-label" for="rolCubierto2">Cargador</label><br/>
							</div>
						</div>
						
						<button class="btn btn-primary" id="btnGuardar">Guardar</button>
						<button class="btn btn-danger" id="btnCancelar">Cancelar</button>
					</form>
				</div>
			</div>
		</div> 
		
		<div class="row col-md-12 col-md-offset-3" id="seccionBuscar"><!-- Formulario agregar empleado -->
			<div class="card card-body">
				<h4>Buscar movimientos</h4>
				<div class="col-md-12 col-md-offset-3">
					<form id="buscarEmpForm" action= "<%= request.getContextPath() %>/apiMov/buscar" method="post">
						<div class="form-group row">
							<label for="numEmpleadoMovs" class="col-sm-3 col-form-label">Número de empleado:</label> 
							<div class="col-sm-5" ><input type="text" class="form-control" id="numEmpleadoMovs" name="numEmpleadoMovs"></div>
							<button class="btn btn-primary col-sm-2" id="btnBusca">Buscar</button>
							<button type="button" class="btn btn-danger col-sm-2" id="btnBorrar">Borrar</button>
						</div>
						
					</form>
					<table id="movimientosTbl" class="display" style="width:80%">
				        <thead>
				            <tr>
				                <th>Fecha</th>
				                <th>Entregas</th>
				                <th>Cubrió Turno</th>
				                <th>Rol Cubierto</th>
				            </tr>
				        </thead>
				    </table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>