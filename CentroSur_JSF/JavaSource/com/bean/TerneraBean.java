package com.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entities.Consumo;
import com.entities.ConsumosMedicamento;
import com.entities.Guachera;
import com.entities.Madre;
import com.entities.Padre;
import com.entities.Peso;
import com.entities.Tratamiento;
import com.services.TerneraEJBBean;

@ManagedBean(name="ternera")
@SessionScoped
public class TerneraBean {

	
	@EJB
	private TerneraEJBBean terneraEJBBean;
	
	
	private long idTernera;
	private Long baja;
	private Date fechaNac;
	private String nroCaravana;
	private Long parto;
	private Long raza;
	private List<Consumo> consumos;
	private List<ConsumosMedicamento> consumosMedicamentos;
	private List<Peso> pesos;
	private Guachera guachera;
	private Madre madre;
	private Padre padre;
	private List<Tratamiento> tratamientos;
	
	
	public String altaTernera() {
		try {
			terneraEJBBean.altaTernera(null);
			return "mostrar";
		} catch (Exception e) {
			return null;
		}
		
	}
	


	public String bajaTernera() {
		try {
			terneraEJBBean.bajaTernera(null);
			return "eliminar";
		} catch (Exception e) {
			return null;	
		}
		
	}
	
	public String buscarTerneraCrvn() {
		try {
			terneraEJBBean.buscarTerneraPorCaravana(nroCaravana);
			return "buscarCaravana";
		} catch (Exception e) {
			return null;
		}
	}
	
	public String buscarTerneraId() {
		try {
			terneraEJBBean.buscarTerneraPorIdTodas(idTernera);
			return "buscarTodasId";
		} catch (Exception e) {
			return null;
		}
	}
	
	public String buscarTerneraIdViva() {
		try {
			terneraEJBBean.buscarTerneraPorIdViva(idTernera);
			return "buscarTodasIdVIiva";
		} catch (Exception e) {
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
	public List<Consumo> getConsumos() {
		return consumos;
	}
	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}
	public List<ConsumosMedicamento> getConsumosMedicamentos() {
		return consumosMedicamentos;
	}
	public void setConsumosMedicamentos(List<ConsumosMedicamento> consumosMedicamentos) {
		this.consumosMedicamentos = consumosMedicamentos;
	}
	public List<Peso> getPesos() {
		return pesos;
	}
	public void setPesos(List<Peso> pesos) {
		this.pesos = pesos;
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
	public List<Tratamiento> getTratamientos() {
		return tratamientos;
	}
	public void setTratamientos(List<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}


	public TerneraBean(Date fechaNac, String nroCaravana, Long parto, Long raza, List<Peso> pesos, Guachera guachera,
			Madre madre, Padre padre) {
		super();
		this.fechaNac = fechaNac;
		this.nroCaravana = nroCaravana;
		this.parto = parto;
		this.raza = raza;
		this.pesos = pesos;
		this.guachera = guachera;
		this.madre = madre;
		this.padre = padre;
	}
	
}
