package com.managed.src;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;

import com.dao.impl.src.FundamentoNormativaHibernateDAO;
import com.dao.src.FundamentoNormativaDAO;
import com.main.htarget.FundamentoNormativa;
import com.main.htarget.TipoFundamento;
import com.main.resources.HibernateUtil;

@ManagedBean
@RequestScoped
public class CatalogoBean {
	private String nombre;
	private String numeral;
	private List<SelectItem> tipos = new ArrayList<SelectItem>();
	private List<FundamentoNormativa> fundamentos = new ArrayList<FundamentoNormativa>();
	private BigDecimal tipo;
	
	
	private InputText itNombre;
	private InputText itNumeral;
	private SelectOneMenu soTipo;
	
	public CatalogoBean()
	{
		consultaFundamentos();
		tipos = consultaTipos();
		this.itNombre = new InputText();
		this.itNumeral = new InputText();
		this.soTipo = new SelectOneMenu();
		soTipo.setValue(tipos);
	}
	
	public BigDecimal getTipo() {
		return tipo;
	}
	
	public List<FundamentoNormativa> getFundamentos() {
		return fundamentos;
	}
	
	public void setFundamentos(List<FundamentoNormativa> fundamentos) {
		this.fundamentos = fundamentos;
	}
	
	public void setTipo(BigDecimal tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumeral() {
		return numeral;
	}
	public void setNumeral(String numeral) {
		this.numeral = numeral;
	}
	public List<SelectItem> getTipos() {
		return tipos;
	}
	public void setTipos(List<SelectItem> tipos) {
		this.tipos = tipos;
	}
	public InputText getItNombre() {
		return itNombre;
	}
	public void setItNombre(InputText itNombre) {
		this.itNombre = itNombre;
	}
	public InputText getItNumeral() {
		return itNumeral;
	}
	public void setItNumeral(InputText itNumeral) {
		this.itNumeral = itNumeral;
	}
	public SelectOneMenu getSoTipo() {
		return soTipo;
	}
	public void setSoTipo(SelectOneMenu soTipo) {
		this.soTipo = soTipo;
	}
	
	public void crearFundamento()
	{
		RequestContext context = RequestContext.getCurrentInstance();
		boolean registro = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
		FundamentoNormativaDAO fnDAO = new FundamentoNormativaHibernateDAO();
		Random r = new Random();
		if(this.nombre != "" && this.numeral != "" && !this.tipo.equals(0)){
			FundamentoNormativa fn = new FundamentoNormativa(new BigDecimal(r.nextInt(9999)), numeral, nombre, tipo, new Date());
			fnDAO.makePersistent(fn);
			limpiarDialogo();
			registro = true;
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,null, "Introduce los datos"));
		}
		s.close();
		consultaFundamentos();
		context.addCallbackParam("register", registro);
	}
	
	private void limpiarDialogo() {
		this.nombre = "";
		this.numeral = "";
		this.tipo = null;
		
		this.itNumeral.clearInitialState();
		this.itNumeral.clearInitialState();
		this.soTipo.clearInitialState();
	}

	public List<SelectItem> consultaTipos() {
		List<SelectItem> tipos = new ArrayList<SelectItem>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		Query query = s.createQuery("from TipoFundamento");		
		if(query.list().size() > 0)
		{
			for(int i = 0; i<query.list().size();i++)
			{
				TipoFundamento tipo = (TipoFundamento) query.list().get(i);
				tipos.add(new SelectItem(tipo.getCveTipo()));
			}
		}
		s.close();
		return tipos;
	}
	
	public void consultaFundamentos(){
		fundamentos.clear();
		Session s = HibernateUtil.getSessionFactory().openSession();
		Query query = s.createQuery("from FundamentoNormativa");		
		if(query.list().size() > 0)
		{
			for(int i = 0; i<query.list().size();i++)
			{
				FundamentoNormativa f = (FundamentoNormativa) query.list().get(i);
				fundamentos.add(f);
			}
		}
		s.close();
	}
}
