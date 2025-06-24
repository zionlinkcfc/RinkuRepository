/**
 * 
 */
$(document).ready(function(){
	$('#rolCubre').hide();
	$('#radioRolCub').hide();
	$('#seccionAgregar').show();
	$('#seccionBuscar').hide();
	
	$('#btnAgregar').on("click",function(){
		$('#seccionAgregar').show();
		$('#seccionBuscar').hide();
	});
	$('#btnBuscar').on("click",function(){
		$('#seccionAgregar').hide();
		$('#seccionBuscar').show();
		if(table!=undefined){
			table.clear().draw();
			table.destroy();
		}
	});
	$('#btnBorrar').on("click",function(){
		$("#buscarEmpForm")[0].reset();		
		if(table!=undefined){
			table.clear().draw();
			table.destroy();
		}
	});
	var table;
	
	$('#btnBuscarEmp').on('click',function(){
		console.log('Click!!');
		$.ajax({
			url:contextPath+'/api/buscar',
			type:"POST",
			datatype: "json",	
			data: {
			   numEmpleado: $('#numEmpleado').val()
			},
			success: function(data, status){
				if(data.numEmpleado!=''){
					$('#nombre').val(data.nombre);
					$('#rol').val(data.rol);
					$('#tipo').val(data.tipo);
					if(data.rol=='Auxiliar'){
						$('#rolCubre').show();
					}
					
					//alert (data);
					console.log(data);
				}else{
					alert('Ingresa un número de empleado para registrar entregas');
				}
				if(data.noencontrado!=undefined && data.noencontrado=="true")
					alert("No se encontró el empleado número: "+$('#numEmpleado').val());
			},
			error: function(xhr, status, error) {
                 console.error('AJAX error:', status, error);
                 alert('Ocurrió un problema al consultar información');
            }
		 });
	});
	$("#agregarMovForm").validate({
		  rules: {
		      numEmpleado: {
		          required: true,
				  number: true
		      },
			  fecha: {
					required: true
			  },
			  entregas:{
					required: true,
					number:true
			  }
		  },
		  messages: {
		      numEmpleado: {
		          required: "Por favor ingresa el número de empleado",
				  number: "Ingresa un número de empleado válido"
		      },
			  fecha: {
					required: "Ingresa la fecha del movimiento"
  			  },
			  entregas:{
					required: "Ingresa la cantidad de entregas realizadas",
					number: "Ingresa una cantidad de entregas válida"
			  }
		  },
		  submitHandler: function(form) {
				$.ajax({
					url:$(form).attr('action'),
					type:$(form).attr('method'),	
					data: {
					   numEmpleado: $('#numEmpleado').val(),
					   fecha: $('#fecha').val(),
					   entregas: $('#entregas').val(),
					   cTurno: $('#cTurno').is(':checked'),
					   rolCubierto:$('input[name="rolCubierto"]:checked').val(),
					},
					success: function(data, status){
						if(data=='1'){
							alert("El registro fue guardado con éxito");
							$('#agregarMovForm')[0].reset();
						}
						else
					   		alert("No fué posible agregar empleado, contacte al administrador");
					},
					error: function(xhr, status, error) {
		                 console.error('AJAX error:', status, error);
		                 alert('Ocurrió un problema al enviar información');
		            }
				 });
				 return false;
			}
		}); //validate form agregarMovForm
		
		$('#cTurno').on("change",function(){
			if($(this).is(':checked')){
				$('#radioRolCub').show();
			}else{
				$('#radioRolCub').hide();
			}
		});
		
		$("#buscarEmpForm").validate({
				  rules: {
				      numEmpleadoMovs: {
				          required: true,
						  number: true
				      }
				  },
				  messages: {
				      numEmpleadoMovs: {
				          required: "Por favor ingresa el número de empleado",
						  number: "Ingresa un número de empleado válido"
				      }
				  },
				  submitHandler: function(form) {
						$.ajax({
							url:$(form).attr('action'),
							type:$(form).attr('method'),	
							data: {
							   numEmpleadoMovs: $('#numEmpleadoMovs').val()
							},
							success: function(data, status){
								console.log(data);
								table = $('#movimientosTbl').DataTable({
									language: {
									    url: '//cdn.datatables.net/plug-ins/2.3.2/i18n/es-ES.json'
									 },
									data: data,
									columns:[
										{   data: 'fecha',
								            render: function (data, type, row) {
								                if (type === 'display' || type === 'filter') {
								                    return moment(data).format('DD/MM/YYYY'); 
								                }
								                return data;
								            }
										},
										{data: 'numEntregas'},
										{data: 'cubreTurno'},
										{data: 'rolCubierto', defaultContent:'-'}
									]
								});
							},
							error: function(xhr, status, error) {
				                 console.error('AJAX error:', status, error);
				                 alert('Ocurrió un problema al consultar información');
				            }
						 });
						 return false;
					}
				}); //validate form agregarMovForm
		
});