package com.rinku.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "movimientos")
public class Movimiento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idMovimiento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMovimiento;
	
	@Column(name="IdEmpleado")
	private int idEmpleado;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="entregas")
	private int numEntregas;
	
	@Column(name="cubreTurno")
	private int cubreTurno;
	
	@Column(name="rolCubierto")
	private String rolCubierto;

	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNumEntregas() {
		return numEntregas;
	}

	public void setNumEntregas(int numEntregas) {
		this.numEntregas = numEntregas;
	}

	public int getCubreTurno() {
		return cubreTurno;
	}

	public void setCubreTurno(int cubreTurno) {
		this.cubreTurno = cubreTurno;
	}

	public String getRolCubierto() {
		return rolCubierto;
	}

	public void setRolCubierto(String rolCubierto) {
		this.rolCubierto = rolCubierto;
	}
	
	
}
