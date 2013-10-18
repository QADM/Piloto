package com.managed.src;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.dao.impl.src.TipoFundamentoHibernateDAO;
import com.dao.src.TipoFundamentoDAO;
import com.main.htarget.TipoFundamento;
import com.main.resources.HibernateUtil;

@ManagedBean
@RequestScoped
public class CatalogoBean {
	private String nombre;
	private String numeral;
	private List<String> tipos = new ArrayList<String>();
	
	private InputText itNombre;
	private InputText itNumeral;
	private SelectOneMenu soTipo;
	
	public CatalogoBean()
	{
		tipos = consultaTipos();
		itNombre = new InputText();
		itNumeral = new InputText();
		soTipo = new SelectOneMenu();
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
	public List<String> getTipos() {
		return tipos;
	}
	public void setTipos(List<String> tipos) {
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
		
	}
	
	private List<String> consultaTipos() {
		System.out.println("Consultando Tipos...");
		List<String> tipos = new ArrayList<String>();
		TipoFundamentoDAO tfDAO = new TipoFundamentoHibernateDAO();
		Session s = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Conectado: "+s.isConnected());
		System.out.println("Abierta: "+s.isOpen());
		Query query = s.createQuery("from TipoFundamento");		
		System.out.println("Tamaño de consulta: "+query.list().size());
		if(query.list().size() > 0)
		{
			System.out.println("Encontro registros...");
			for(int i = 0; i<query.list().size();i++)
			{
				TipoFundamento tipo = (TipoFundamento) query.list().get(i);
				tipos.add(tipo.getTipoFundamento());
			}
		}
		s.close();
		return tipos;
	}
}
