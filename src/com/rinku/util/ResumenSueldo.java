package com.rinku.util;

public class ResumenSueldo {
	private int diasLaborados;
	private double sueldoBase;
	private int totalEntregas;
	private double pagoEntregas;
	private double bonos;
	private double subTotal;
	private double retieneISR;
	private double retieneISRAdicional;
	private double valesDespensa;
	private double sueldoTotal;
		
	public ResumenSueldo() {
		super();
	}
	public int getDiasLaborados() {
		return diasLaborados;
	}
	public void setDiasLaborados(int diasLaborados) {
		this.diasLaborados = diasLaborados;
	}
	public double getSueldoBase() {
		return sueldoBase;
	}
	public void setSueldoBase(double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}
	public int getTotalEntregas() {
		return totalEntregas;
	}
	public void setTotalEntregas(int totalEntregas) {
		this.totalEntregas = totalEntregas;
	}
	public double getPagoEntregas() {
		return pagoEntregas;
	}
	public void setPagoEntregas(double pagoEntregas) {
		this.pagoEntregas = pagoEntregas;
	}
	public double getBonos() {
		return bonos;
	}
	public void setBonos(double bonos) {
		this.bonos = bonos;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public double getRetieneISR() {
		return retieneISR;
	}
	public void setRetieneISR(double retieneISR) {
		this.retieneISR = retieneISR;
	}
	public double getRetieneISRAdicional() {
		return retieneISRAdicional;
	}
	public void setRetieneISRAdicional(double retieneISRAdicional) {
		this.retieneISRAdicional = retieneISRAdicional;
	}
	public double getValesDespensa() {
		return valesDespensa;
	}
	public void setValesDespensa(double valesDespensa) {
		this.valesDespensa = valesDespensa;
	}
	public double getSueldoTotal() {
		return sueldoTotal;
	}
	public void setSueldoTotal(double sueldoTotal) {
		this.sueldoTotal = sueldoTotal;
	}
	@Override
	public String toString() {
		return "ResumenSueldo [diasLaborados=" + diasLaborados + ", sueldoBase=" + sueldoBase + ", totalEntregas="
				+ totalEntregas + ", pagoEntregas=" + pagoEntregas + ", bonos=" + bonos + ", subTotal=" + subTotal
				+ ", retieneISR=" + retieneISR + ", retieneISRAdicional=" + retieneISRAdicional + ", valesDespensa="
				+ valesDespensa + "]";
	}
	
	
}
