/**
 * 
 */
$(document).ready(function(){
	$('#seccionAgregar').show();
	$('#seccionBuscar').hide();
	$('#seccionEditar').hide();
	$('#seccionEliminar').hide();
	
	$("#divTipo").hide();
	$("#rolCubre").hide();
	
	$("#agregarEmpForm").validate({
	      rules: {
	          nombre: {
	              required: true,
	              minlength: 3
	          }
	      },
	      messages: {
	          nombre: {
	              required: "Por favor ingresa el nombre completo del empleado",
				  minlength: "Ingresa un nombre completo"
	          }
	      },
		  submitHandler: function(form) {
				$.ajax({
					url:$(form).attr('action'),
					type:$(form).attr('method'),	
					data: {
					   nombre: $('#nombre').val(),
					   rol: $('input[name="rol"]:checked').val(),
					   tipo: $('input[name="tipo"]:checked').val()
					},
					success: function(data, status){
						if(data=='1'){
							alert("El registro fue guardado con éxito");
							$('#agregarEmpForm')[0].reset();
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
		}); //validate form agregarEmp
	
	$("#buscarEmpForm").validate({
	      rules: {
	          numEmpleado: {
	              required: true,
	              number: true
	          }
	      },
	      messages: {
	          numEmpleado: {
	              required: "Por favor ingresa el número de empleado",
				  number: "Ingresa un nombre completo"
	          }
	      },
		  submitHandler: function(form) {
				$.ajax({
					url:$(form).attr('action'),
					type:$(form).attr('method'),
					datatype: "json",	
					data: {
					   numEmpleado: $('#numEmpleado').val()
					},
					success: function(data, status){
						if(data.numEmpleado!=''){
							$('#lblNombre').text(data.nombre);
							$('#lblRol').text(data.rol);
							$('#lblTipo').text(data.tipo);
							//alert (data);
							console.log(data);
						}
						if(data.noencontrado!=undefined && data.noencontrado=="true")
							alert("No se encontró el empleado número: "+$('#numEmpleado').val());
					},
					error: function(xhr, status, error) {
		                 console.error('AJAX error:', status, error);
		                 alert('Ocurrió un problema al consultar información');
		            }
				 });
				 return false;
			}
		}); //validate form buscarEmp	
		
		
		$('#btnCancelar').on('click', function() {
		    $('#agregarEmpForm')[0].reset();
		});
		$('#btnBorrar').on('click', function() {
			$('#lblNombre').text('');
			$('#lblRol').text('');
			$('#lblTipo').text('');
			$('#buscarEmpForm')[0].reset();
		});
		$('#btnAgregar').on('click', function() {
		    $('#seccionAgregar').show();
			$('#seccionBuscar').hide();
			$('#seccionEditar').hide();
			$('#seccionEliminar').hide();
		});
		$('#btnBuscar').on('click', function() {
		    $('#seccionAgregar').hide();
			$('#seccionBuscar').show();
			$('#seccionEditar').hide();
			$('#seccionEliminar').hide();
		});
		$('#btnModificar').on('click', function() {
		    $('#seccionAgregar').hide();
			$('#seccionBuscar').hide();
			$('#seccionEditar').show();
			$('#seccionEliminar').hide();
		});
		$('#btnEliminar').on('click', function() {
		    $('#seccionAgregar').hide();
			$('#seccionBuscar').hide();
			$('#seccionEditar').hide();
			$('#seccionEliminar').show();
		});
		
		
		$('input[name="rol"]').change(function() {
           var selectedValue = $(this).val();
		   if(selectedValue=="Auxiliar"){
				$("#divTipo").show();
		   }else{
				$('input[name="tipo"][value="Interno"]').prop('checked',true);
				$("#divTipo").hide();
		   }
           console.log("Selected value: " + selectedValue);
       });
	   
	   $('#cTurno').change(function() {
          var isChecked = $('#cTurno').prop('checked'); 
		  console.log(isChecked);
	   if(isChecked){
			$("#rolCubre").show();
	   }else{
			$("#rolCubre").hide();
	   }
         
      });
	  
	  $("#buscarEmpForm2").validate({
	  	      rules: {
	  	          numEmpleadoEdit: {
	  	              required: true,
	  	              number: true
	  	          }
	  	      },
	  	      messages: {
	  	          numEmpleadoEdit: {
	  	              required: "Por favor ingresa el número de empleado",
	  				  number: "Ingresa un nombre completo"
	  	          }
	  	      },
	  		  submitHandler: function(form) {
	  				$.ajax({
	  					url:$(form).attr('action'),
	  					type:$(form).attr('method'),
	  					datatype: "json",	
	  					data: {
	  					   numEmpleado: $('#numEmpleadoEdit').val()
	  					},
	  					success: function(data, status){
	  						if(data.numEmpleado!=''){
	  							$('#nombreEdit').val(data.nombre);
								$('input[name="rolEdit"][value='+data.rol+']').prop("checked", true);
								$('input[name="tipoEdit"][value='+data.tipo+']').prop("checked", true);
	  							//alert (data);
	  							console.log(data);
	  						}
	  						if(data.noencontrado!=undefined && data.noencontrado=="true")
	  							alert("No se encontró el empleado número: "+$('#numEmpleadoEdit').val());
	  					},
	  					error: function(xhr, status, error) {
	  		                 console.error('AJAX error:', status, error);
	  		                 alert('Ocurrió un problema al consultar información');
	  		            }
	  				 });
	  				 return false;
	  			}
	  		});
			
	$("#editarEmpForm").validate({
      rules: {
          nombre: {
              required: true,
              minlength: 3
          }
      },
      messages: {
          nombre: {
              required: "Por favor ingresa el nombre completo del empleado",
			  minlength: "Ingresa un nombre completo"
          }
      },
	  submitHandler: function(form) {
			$.ajax({
				url:$(form).attr('action'),
				type:$(form).attr('method'),	
				data: {
					nombre: $('#nombreEdit').val(),
				    rol: $('input[name="rolEdit"]:checked').val(),
				    tipo: $('input[name="tipoEdit"]:checked').val(),
					numEmpleado: $('#numEmpleadoEdit').val()
				},
				success: function(data, status){
					if(data=='1'){
						alert("El registro fue actualizado con éxito");
						$('#editarEmpForm')[0].reset();
					}
					else
				   		alert("No fué posible actualizar empleado, contacte al administrador");
				},
				error: function(xhr, status, error) {
	                 console.error('AJAX error:', status, error);
	                 alert('Ocurrió un problema al actualizar información');
	            }
			 });
			 return false;
		}
	});
	
	$("#buscarEmpForm3").validate({
      rules: {
          numEmpleadoElim: {
              required: true,
              number: true
          }
      },
      messages: {
          numEmpleadoElim: {
              required: "Por favor ingresa el número de empleado",
			  number: "Ingresa un nombre completo"
          }
      },
	  submitHandler: function(form) {
			$.ajax({
				url:$(form).attr('action'),
				type:$(form).attr('method'),
				datatype: "json",	
				data: {
				   numEmpleado: $('#numEmpleadoElim').val()
				},
				success: function(data, status){
					if(data.numEmpleado!=''){
						$('#nombreElim').val(data.nombre);
						//alert (data);
						console.log(data);
					}
					if(data.noencontrado!=undefined && data.noencontrado=="true")
						alert("No se encontró el empleado número: "+$('#numEmpleadoElim').val());
				},
				error: function(xhr, status, error) {
	                 console.error('AJAX error:', status, error);
	                 alert('Ocurrió un problema al consultar información');
	            }
			 });
			 return false;
		}
	});
	
	
	$("#elimEmpForm").validate({
	      rules: {
	          nombre: {
	              required: true,
	              minlength: 3
	          }
	      },
	      messages: {
	          nombre: {
	              required: "Por favor ingresa el nombre completo del empleado",
				  minlength: "Ingresa un nombre completo"
	          }
	      },
		  submitHandler: function(form) {
				$.ajax({
					url:$(form).attr('action'),
					type:$(form).attr('method'),	
					data: {
						numEmpleado: $('#numEmpleadoElim').val()
					},
					success: function(data, status){
						if(data=='1'){
							alert("El registro fue eliminado con éxito");
							$('#elimEmpForm')[0].reset();
						}
						else
					   		alert("No fué posible eliminar empleado, contacte al administrador");
					},
					error: function(xhr, status, error) {
		                 console.error('AJAX error:', status, error);
		                 alert('Ocurrió un problema al actualizar información');
		            }
				 });
				 return false;
			}
		});
	
});