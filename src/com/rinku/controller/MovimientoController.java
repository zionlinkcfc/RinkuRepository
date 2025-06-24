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
import com.rinku.dao.MovimientoDao;
import com.rinku.model.Movimiento;

/**
 * Servlet implementation class MovimientoController
 */
@WebServlet("/apiMov/*")
public class MovimientoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    MovimientoDao movimientoDao;
    public void init() {
    	movimientoDao= new MovimientoDao();
    }
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathFull = request.getRequestURL().toString();
		String action = (pathFull.split("/apiMov"))[1];
		System.out.println("action: "+action);
		try {
            switch (action) {
                case "/agregar":
                	register(request, response);
                    break;
                case "/buscar":
                    buscar(request, response);
                    break;
                case "/modificar":
                	//modificar(request, response);
                    break;
                case "/eliminar":
                	//eliminar(request, response);
                    break;
                default:
                   // listUser(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int numEmpleado= request.getParameter("numEmpleado")!=null && request.getParameter("numEmpleado")!="" ? Integer.parseInt(request.getParameter("numEmpleado")):0;
		
		String fecha = request.getParameter("fecha"); 
		int entregas =  request.getParameter("entregas")!=null && request.getParameter("entregas")!="" ? Integer.parseInt(request.getParameter("entregas")):0;
		String cubreTurno= request.getParameter("cTurno");
		String rolCubierto = request.getParameter("rolCubierto");
		
		System.out.println(numEmpleado);
		System.out.println(fecha);
		System.out.println(cubreTurno);
		
		Movimiento movimiento = new Movimiento();
		movimiento.setIdEmpleado(numEmpleado);
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaFormat = formatter.parse(fecha);
			movimiento.setFecha(fechaFormat);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		movimiento.setNumEntregas(entregas);
		movimiento.setCubreTurno(cubreTurno.equals("true")?1:0);
		movimiento.setRolCubierto(cubreTurno.equals("true")?rolCubierto:null);

		response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
		
		if(movimientoDao.guardaMovimiento(movimiento))
		    out.print("1");
		else
		    out.print("0");
        out.flush();
	}
	
	private void buscar(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String numEmpleado = request.getParameter("numEmpleadoMovs");
		System.out.println("NumEmpleado: "+ numEmpleado);
		List<Movimiento> movimientos = movimientoDao.buscaMovimientosEmpleado(Integer.parseInt(numEmpleado));
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();	
		if(movimientos!=null) {
			Gson gson =  new Gson();
			String empJson = gson.toJson(movimientos);
		    out.print(empJson);
		}else
		    out.print("{\"noencontrado\":\"true\"}");
        out.flush();
	}
	
}
