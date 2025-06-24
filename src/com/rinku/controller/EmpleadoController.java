package com.rinku.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;

import com.rinku.dao.EmpleadoDao;
import com.rinku.model.Empleado;


@WebServlet("/api/*")
public class EmpleadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private EmpleadoDao empleadoDao;

	public void init() {
		empleadoDao = new EmpleadoDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathFull = request.getRequestURL().toString();
		String action = (pathFull.split("/api"))[1];
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
                	modificar(request, response);
                    break;
                case "/eliminar":
                	eliminar(request, response);
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
		String nombreCompleto = request.getParameter("nombre");
		String rol = request.getParameter("rol");
		String tipoEmpleado = request.getParameter("tipo");
		
		System.out.println(nombreCompleto);
		System.out.println(rol);
		
		Empleado empleado = new Empleado();
		empleado.setNombreCompleto(nombreCompleto);
		empleado.setRol(rol);
		empleado.setTipo(tipoEmpleado);

		response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
		
		if(empleadoDao.guardaEmpleado(empleado))
		    out.print("1");
		else
		    out.print("0");
        out.flush();
	}
	
	private void buscar(HttpServletRequest request, HttpServletResponse response)
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
	
	private void modificar(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String numEmpleado = request.getParameter("numEmpleado");
		String nombre =  request.getParameter("nombre");
		String rol = request.getParameter("rol");
		String tipoEmpleado = request.getParameter("tipo");
		
		
		Empleado empleado = new Empleado();
		empleado.setNombreCompleto(nombre);
		empleado.setNumeroEmpleado(Integer.parseInt(numEmpleado));
		empleado.setRol(rol);
		empleado.setTipo(tipoEmpleado);
		
		
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();	

		if(empleadoDao.modificaEmpleado(empleado))
		    out.print("1");
		else
		    out.print("0");
        out.flush();
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String numEmpleado = request.getParameter("numEmpleado");
		
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();	

		if(empleadoDao.eliminaEmpleado(Integer.parseInt(numEmpleado)))
		    out.print("1");
		else
		    out.print("0");
        out.flush();
	}

}
