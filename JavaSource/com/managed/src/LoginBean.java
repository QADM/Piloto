package com.managed.src;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.inputtext.InputText;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Scope("request")
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

	public void login() throws IOException, ServletException {
		//RequestContext context = RequestContext.getCurrentInstance();
		
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		 
		 RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
		  .getRequestDispatcher("/j_spring_security_check");
		 
		 dispatcher.forward((ServletRequest) context.getRequest(),
		  (ServletResponse) context.getResponse());
		 
		 FacesContext.getCurrentInstance().responseComplete();
		//context.addCallbackParam("loggedIn", loggedIn);

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
						