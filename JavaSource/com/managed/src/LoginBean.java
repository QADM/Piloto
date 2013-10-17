package com.managed.src;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.Query;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;

import javax.servlet.http.HttpSession;

import com.main.resources.HibernateUtil;
//import com.main.target.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = -2152389656664659476L;

	private String username;
	private String password;
	private InputText itUsername;
	private InputText itPassword;
	private boolean loggedIn = false;
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	public LoginBean() {
		FacesContext.getCurrentInstance();
	}
	
	public InputText getItPassword() {
		return itPassword;
	}
	
	public InputText getItUsername() {
		return itUsername;
	}
	
	public void setItPassword(InputText itPassword) {
		this.itPassword = itPassword;
	}
	
	public void setItUsername(InputText itUsername) {
		this.itUsername = itUsername;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login(ActionEvent actionEvent) throws IOException {
		RequestContext context = RequestContext.getCurrentInstance();
		
		if(username != null && password != null) {
			Query query = HibernateUtil.getSessionFactory().openSession().createQuery("from Usuario where idUsuario = :user and password = :pass");
			query.setParameter("user", this.username);
			query.setParameter("pass", this.password);
			List<Object> usuarios = query.list();
			if(usuarios.size() == 0){
				loggedIn = false;
				limpiarDialogoLogin();
			}
			else
			{
				loggedIn = true;
			}
		}
		context.addCallbackParam("loggedIn", loggedIn);

	}
	
	public void limpiarDialogoLogin()
	{
		this.username = "";
		this.password = "";
		
		this.itUsername = new InputText();
		this.itPassword = new InputText();
	}
	
	public void logout() throws IOException {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.invalidate();
		loggedIn = false;
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
	}

}
						