package com.bean;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import com.entities.Alimento;
import com.entities.Ternera;
import com.services.TamboBean;

@ManagedBean(name="alimento")
@SessionScoped
public class ConsumoAlimentoBean {

	
	
	@EJB
	private TamboBean consumoAlimento;
	
	
	private Long cantidad;
	private Date fecha;
	private Alimento alimento;
	private Ternera ternera;
	
	
	
	
	
	
	
	
	
	
	
	public TamboBean getConsumoAlimento() {
		return consumoAlimento;
	}
	public void setConsumoAlimento(TamboBean consumoAlimento) {
		this.consumoAlimento = consumoAlimento;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Alimento getAlimento() {
		return alimento;
	}
	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}
	public Ternera getTernera() {
		return ternera;
	}
	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}
	
	
}
