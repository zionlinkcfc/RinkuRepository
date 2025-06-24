package com.rinku.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.rinku.dao.EmpleadoDao;
import com.rinku.dao.MovimientoDao;
import com.rinku.model.Empleado;
import com.rinku.model.Movimiento;
import com.rinku.util.ResumenSueldo;

/**
 * Servlet implementation class CalculadoraSueldo
 */
@WebServlet("/CalculadoraSueldo/*")
public class CalculadoraSueldo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    MovimientoDao movimientoDao;
    EmpleadoDao empleadoDao;
    public CalculadoraSueldo() {
        super();
        movimientoDao= new MovimientoDao();
        empleadoDao= new EmpleadoDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathFull = request.getRequestURL().toString();
		String action = (pathFull.split("/CalculadoraSueldo"))[1];
		System.out.println("action: "+action);
		try {
            switch (action) {
                case "/calcular":
                	calcularSueldo(request, response);
                    break;
                case "/buscar":
                	buscarEmp(request, response);
                    break;
                default:
                   // listUser(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }		
		
	}
	
	private void buscarEmp(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String numEmpleado = request.getParameter("numEmpleado");
		
		Empleado empleado = empleadoDao.buscaEmpleado(Integer.parseInt(numEmpleado));
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();	
		if(empleado!=null) {
			String empJson = "{\"nombre\":\""+empleado.getNombreCompleto()+"\",\"numEmpleado\":\""+empleado.getNumeroEmpleado()+"\",\"rol\":\""+empleado.getRol()+"\",\"tipo\":\""+empleado.getTipo()+"\"}";
		    out.print(empJson);
		}else
		    out.print("{\"noencontrado\":\"true\"}");
        out.flush();
	}
	
	private void calcularSueldo(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String numEmpleado = request.getParameter("numEmpleado");
		String fechaIni =  request.getParameter("fechaIni");
		String fechaFin =  request.getParameter("fechaFin");
		String rol = request.getParameter("rol");
		String tipo = request.getParameter("tipo");
		
		List<Movimiento> movimientos=null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaIniFormat = formatter.parse(fechaIni);
			Date fechaFinFormat = formatter.parse(fechaFin);
			
			System.out.println("NumEmpleado: "+ numEmpleado);
			movimientos = movimientoDao.buscaMovsEmpPorFecha(Integer.parseInt(numEmpleado), fechaIniFormat, fechaFinFormat);
		} catch (ParseException e) {
            e.printStackTrace();
        }
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();	
		if(movimientos!=null) {
			ResumenSueldo resumen = new ResumenSueldo();
			//Calculamos sueldo mensual
			int diasLaborados= movimientos.size();
			double sueldoBase = diasLaborados*8*30; //diasLaborados x 8 horas x $30 pesos la hora
			int totalEntregas=0;
			for(Movimiento mov:movimientos) {
				totalEntregas += mov.getNumEntregas();
			}
			double pagoEntregas = totalEntregas*5;  //Cada entrega se paga en $5
			double bonos = 0;
			if(rol.equals("Chofer")) { //Choferes reciben de bono $10 X hora laborada
				bonos = diasLaborados*8*10;
			}else if(rol.equals("Cargador")) { //Cargadores reciben de bono $5 X hora laborada
				bonos = diasLaborados*8*5;
			}
			//bonos para auxiliares 
			if(rol.equals("Auxiliar")) {
				for(Movimiento mov: movimientos) {
					if(mov.getCubreTurno()==1) {  //revisamos si cubrió turno
						if(mov.getRolCubierto().equals("Chofer")) {
							bonos+=80;
						}else if(mov.getRolCubierto().equals("Cargador")) {
							bonos+=40;
						}
					}
				}
			}
			//retenciones
			double subTotal= sueldoBase+pagoEntregas+bonos;
			
			double retieneISR = subTotal * 0.09;
			double retieneISRAdicional = 0;
			if(subTotal> 16000) {
				retieneISRAdicional = subTotal*0.03;
			}
			double valesDespensa=0;
			if(!(rol.equals("Auxiliar") && tipo.equals("Externo"))) {
				valesDespensa = subTotal*0.04;
			}
			double sueldoTotal = (subTotal+valesDespensa)-(retieneISR+retieneISRAdicional);
			resumen.setDiasLaborados(diasLaborados);
			resumen.setSueldoBase(sueldoBase);
			resumen.setTotalEntregas(totalEntregas);
			resumen.setPagoEntregas(pagoEntregas);
			resumen.setBonos(bonos);
			resumen.setSubTotal(subTotal);
			resumen.setRetieneISR(retieneISR);
			resumen.setRetieneISRAdicional(retieneISRAdicional);
			resumen.setValesDespensa(valesDespensa);
			resumen.setSueldoTotal(sueldoTotal);	
			System.out.println(resumen.toString());
			Gson gson =  new Gson();
			String empJson = gson.toJson(resumen);
		    out.print(empJson);
		}else
		    out.print("{\"noencontrado\":\"true\"}");
        out.flush();
	}

}
