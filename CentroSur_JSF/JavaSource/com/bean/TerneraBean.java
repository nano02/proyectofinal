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
import com.services.TerneraEJBBean;

@ManagedBean(name="ternera")
@SessionScoped
public class TerneraBean {

	
	@EJB
	private TerneraEJBBean terneraEJBBean;
	
	
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
	
	
	public String altaTernera(Long idTernera, String nroCaravana, Long idGuachera, Long idMadre, Long idPadre, RazaTernera raza, TipoParto parto, Double pesoNac, Date fechaNacimiento ) {
		try {
			terneraEJBBean.altaTernera(idTernera, nroCaravana, idGuachera, idMadre, idPadre, fechaNac, idPadre, raza, parto, pesoNac);
			return "mostrar";
		} catch (TamboException e) {
			return null;
		}
		
	}
	

	public String bajaTernera(Long idTernera, Date fechaBaja, String motivo) {
		try {
			terneraEJBBean.bajaTernera(idTernera, fechaBaja, motivo);
			return "eliminar";
		} catch (TamboException e) {
			return null;	
		}
		
	}
	
	public String buscarTerneraCrvn(String nroCaravana) {
		try {
			terneraEJBBean.buscarTerneraPorCaravana(nroCaravana);
			return "buscarCaravana";
		} catch (TamboException e) {
			return null;
		}
	}
	
	public String buscarTerneraId(Long idTernera) {
		try {
			terneraEJBBean.buscarTerneraPorIdTodas(idTernera);
			return "buscarTodasId";
		} catch (TamboException e) {
			return null;
		}
	}
	
	public String buscarTerneraIdViva(Long idTernera) {
		try {
			terneraEJBBean.buscarTerneraPorIdViva(idTernera);
			return "buscarTodasIdVIiva";
		} catch (TamboException e) {
			return null;
		}
	}
	
	public List<Ternera> buscarTodasTerneras(){
		try {
			return terneraEJBBean.buscarTodasTernera();
		} catch (TamboException e) {
			return null;
		}
	}
	
	
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
