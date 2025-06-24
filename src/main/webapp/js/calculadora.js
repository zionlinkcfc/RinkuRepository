/**
 * 
 */

$(document).ready(function(){
	let formatoMoneda = new Intl.NumberFormat('es-MX', {
	  style: 'currency',
	  currency: 'MXN',
	  minimumFractionDigits: 2 
	});
	
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
	
	
	$("#buscarMovsEmpForm").validate({
		  rules: {
			  fechaIni: {
					required: true
			  },
			  fechaFin: {
  					required: true
  			  }
		  },
		  messages: {
			  fechaIni: {
					required: "Por favor ingresa la fecha de inicio"
			  },
			  fechaFin: {
  					required: "Por favor ingresa la fecha final"
  			  }
		  },
		  submitHandler: function(form) {
				$.ajax({
					url:$(form).attr('action'),
					type:$(form).attr('method'),
					datatype: "json",	
					data: {
					   numEmpleado: $('#numEmpleado').val(),
					   fechaIni: $('#fechaIni').val(),
					   fechaFin: $('#fechaFin').val(),
					   rol: $('#lblRol').text(),
					   tipo: $('#lblTipo').text()
					},
					success: function(data, status){
						if(data!=null && data.lenght!=0){
							$('#lblDiasLab').text(data.diasLaborados);
							$('#lblSueldoBase').text(formatoMoneda.format(data.sueldoBase));
							$('#lblPEntregas').text(formatoMoneda.format(data.pagoEntregas));
							$('#lblBonos').text(formatoMoneda.format(data.bonos));
							$('#lblSubtotal').text(formatoMoneda.format(data.subTotal));
							$('#lblVales').text(formatoMoneda.format(data.valesDespensa));
							$('#lblretieneISR').text(formatoMoneda.format(data.retieneISR));
							$('#lblretieneISRAd').text(formatoMoneda.format(data.retieneISRAdicional));
							$('#lblSueldoTotal').text(formatoMoneda.format(data.sueldoTotal));
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
});