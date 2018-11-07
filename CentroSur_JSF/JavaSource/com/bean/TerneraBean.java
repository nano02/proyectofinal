package com.bean;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.entities.Guachera;
import com.entities.Madre;
import com.entities.Padre;
import com.entities.Ternera;
import com.enums.RazaTernera;
import com.enums.TipoParto;
import com.excepciones.TamboException;

@ManagedBean(name="ternera")
@SessionScoped
public class TerneraBean {

	
	@EJB
	private TerneraBean terneraEJBBean;
	
	
	private Long idTernera;
	private Long baja;
	private Date fechaNac;
	private String nroCaravana;
	private Long parto;
	private Long raza;
	private Guachera guachera;
	private Madre madre;
	private Padre padre;
	private Double pesoNacimiento;
	
	
	public String altaTernera(Long idTernera, String nroCaravana, Long idGuachera, Long idMadre, Long idPadre, RazaTernera raza, TipoParto parto, Double pesoNac, Date fechaNacimiento ) throws TamboException {
		terneraEJBBean.altaTernera(idTernera, nroCaravana, idGuachera, idMadre, idPadre, raza, parto, pesoNac, fechaNacimiento);
		return "mostrar";
	}
	

	public String bajaTernera(Long idTernera, Date fechaBaja, String motivo) throws TamboException {
		terneraEJBBean.bajaTernera(idTernera, fechaBaja, motivo);
		return "eliminar";	
	}
	
	public String buscarTerneraCrvn(String nroCaravana) throws TamboException {
		terneraEJBBean.buscarTerneraCrvn(nroCaravana);
		return "buscarCaravana";
	}
	
	public String buscarTerneraId(Long idTernera) throws TamboException {
		terneraEJBBean.buscarTerneraId(idTernera);
		return "buscarTodasId";
	}
	
	public String buscarTerneraIdViva(Long idTernera) throws TamboException {
		terneraEJBBean.buscarTerneraIdViva(idTernera);
		return "buscarTodasIdVIiva";
	}
	
	public List<Ternera> buscarTodasTerneras() throws TamboException{
		return terneraEJBBean.buscarTodasTerneras();
	}
	
	
	
	
	
	/*
	 * Getter y Setter
	*/
	public long getIdTernera() {
		return idTernera;
	}
	public void setIdTernera(long idTernera) {
		this.idTernera = idTernera;
	}
	public Long getBaja() {
		return baja;
	}
	public void setBaja(Long baja) {
		this.baja = baja;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getNroCaravana() {
		return nroCaravana;
	}
	public void setNroCaravana(String nroCaravana) {
		this.nroCaravana = nroCaravana;
	}
	public Long getParto() {
		return parto;
	}
	public void setParto(Long parto) {
		this.parto = parto;
	}
	public Long getRaza() {
		return raza;
	}
	public void setRaza(Long raza) {
		this.raza = raza;
	}
	
	public Double getPesoNacimiento() {
		return pesoNacimiento;
	}
	
	public void setPesoNacimiento(Double pesoNacimiento) {
		this.pesoNacimiento = pesoNacimiento;
	}

	public Guachera getGuachera() {
		return guachera;
	}
	public void setGuachera(Guachera guachera) {
		this.guachera = guachera;
	}
	public Madre getMadre() {
		return madre;
	}
	public void setMadre(Madre madre) {
		this.madre = madre;
	}
	public Padre getPadre() {
		return padre;
	}
	public void setPadre(Padre padre) {
		this.padre = padre;
	}
	
	
}
