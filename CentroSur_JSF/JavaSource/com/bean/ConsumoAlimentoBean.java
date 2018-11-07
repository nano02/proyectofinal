package com.bean;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entities.Consumo;
import com.entities.Ternera;
import com.excepciones.TamboException;
import com.services.TamboBean;
import com.services.TerneraBean;

@ManagedBean(name="consumoAlimento")
@SessionScoped
public class ConsumoAlimentoBean {

	
	
	@EJB
	private TamboBean consumoAlimentoEJBBean;
	private TerneraBean terneraEJBBean;

	private Long idTernera;

	private List<Consumo> consumosTerneras; 
		
	public String consumoAlimentos () throws TamboException{
		try {
			Ternera t = terneraEJBBean.buscarTerneraPorIdTodas(idTernera);
			consumosTerneras = consumoAlimentoEJBBean.consumoPorTernera(idTernera);
			return "consumoAlimentos";
		} catch (Exception e) {
			return null;
		}
		
	}

	
	/*
	 * Getters and Setters
	 */
	
	
	
	public TamboBean getConsumoAlimentoEJBBean() {
		return consumoAlimentoEJBBean;
	}

	public void setConsumoAlimentoEJBBean(TamboBean consumoAlimentoEJBBean) {
		this.consumoAlimentoEJBBean = consumoAlimentoEJBBean;
	}

	public TerneraBean getTerneraEJBBean() {
		return terneraEJBBean;
	}

	public void setTerneraEJBBean(TerneraBean terneraEJBBean) {
		this.terneraEJBBean = terneraEJBBean;
	}

	public Long getIdTernera() {
		return idTernera;
	}

	public void setIdTernera(Long idTernera) {
		this.idTernera = idTernera;
	}

	public List<Consumo> getConsumosTerneras() {
		return consumosTerneras;
	}

	public void setConsumosTerneras(List<Consumo> consumosTerneras) {
		this.consumosTerneras = consumosTerneras;
	}
	
	
	
}