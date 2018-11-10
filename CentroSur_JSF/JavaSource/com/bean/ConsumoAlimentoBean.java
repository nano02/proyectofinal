package com.bean;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entities.Consumo;
import com.entities.Ternera;
import com.excepciones.TamboException;
import com.services.TamboBeanRemote;
import com.services.TerneraBeanRemote;

@ManagedBean(name="consumoAlimento")
@SessionScoped
public class ConsumoAlimentoBean {

	
	
	@EJB
	private TamboBeanRemote consumoAlimentoEJBBean;
	@EJB
	private TerneraBeanRemote terneraEJBBean;

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
	
	public TamboBeanRemote getConsumoAlimentoEJBBean() {
		return consumoAlimentoEJBBean;
	}


	public void setConsumoAlimentoEJBBean(TamboBeanRemote consumoAlimentoEJBBean) {
		this.consumoAlimentoEJBBean = consumoAlimentoEJBBean;
	}


	public TerneraBeanRemote getTerneraEJBBean() {
		return terneraEJBBean;
	}


	public void setTerneraEJBBean(TerneraBeanRemote terneraEJBBean) {
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