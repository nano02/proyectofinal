package com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.entities.Guachera;
import com.entities.Madre;
import com.entities.Padre;
import com.entities.Ternera;
import com.enums.RazaTernera;
import com.enums.TipoParto;
import com.excepciones.TamboException;
import com.services.TerneraBeanRemote;

@ManagedBean(name="ternera")
@SessionScoped
public class TerneraBean {

	
	@EJB
	private TerneraBeanRemote terneraEJBBean;
	
	
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
	
	private List<SelectItem> listarRazas;
	
	
	
	public String altaTernera(String nroCaravana, Long idGuachera, Long idMadre, Long idPadre, RazaTernera raza, TipoParto parto, Double pesoNac, Date fechaNacimiento ) throws TamboException {
		terneraEJBBean.altaTernera(nroCaravana, idGuachera, idMadre, idPadre, fechaNacimiento, idPadre, raza, parto, pesoNac);
		return "mostrar";
	}
	

	public String bajaTernera(Long idTernera, Date fechaBaja, String motivo) throws TamboException {
		terneraEJBBean.bajaTernera(idTernera, fechaBaja, motivo);
		return "eliminar";	
	}
	
	public String buscarTerneraCrvn(String nroCaravana) throws TamboException {
		terneraEJBBean.buscarTerneraPorCaravana(nroCaravana);
		return "buscarCaravana";
	}
	
	public String buscarTerneraId(Long idTernera) throws TamboException {
		terneraEJBBean.buscarTerneraPorIdTodas(idTernera);
		return "buscarTodasId";
	}
	
	public String buscarTerneraIdViva(Long idTernera) throws TamboException {
		terneraEJBBean.buscarTerneraPorIdViva(idTernera);
		return "buscarTodasIdVIiva";
	}
	
	public List<Ternera> buscarTodasTerneras() throws TamboException{
		return terneraEJBBean.buscarTodasTernera();
	}
	
	
	public class SelectBooleanView {
		 
	    private boolean value1;  
	    private boolean value2;
	 
	    public boolean isValue1() {
	        return value1;
	    }
	 
	    public void setValue1(boolean value1) {
	        this.value1 = value1;
	    }
	 
	    public boolean isValue2() {
	        return value2;
	    }
	 
	    public void setValue2(boolean value2) {
	        this.value2 = value2;
	    }
	     
	    public void addMessage() {
	        String summary = value2 ? "Checked" : "Unchecked";
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
	    }
	}


	@PostConstruct
	public void cargarCombo() {
		FacesMessage message = null;
		try {
			ArrayList<SelectItem> razas = new ArrayList<>();
			razas.add(new SelectItem(RazaTernera.HOLANDO, RazaTernera.HOLANDO.toString()));
			razas.add(new SelectItem(RazaTernera.JERSEY, RazaTernera.JERSEY.toString()));
			razas.add(new SelectItem(RazaTernera.CRUZA, RazaTernera.CRUZA.toString()));
			listarRazas = razas;
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cargar combo: ", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
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


	public TerneraBeanRemote getTerneraEJBBean() {
		return terneraEJBBean;
	}


	public void setTerneraEJBBean(TerneraBeanRemote terneraEJBBean) {
		this.terneraEJBBean = terneraEJBBean;
	}


	public List<SelectItem> getListarRazas() {
		return listarRazas;
	}


	public void setListarRazas(List<SelectItem> listarRazas) {
		this.listarRazas = listarRazas;
	}


	public void setIdTernera(Long idTernera) {
		this.idTernera = idTernera;
	}

	
	
}
