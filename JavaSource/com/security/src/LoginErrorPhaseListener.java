package com.security.src;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

public class LoginErrorPhaseListener implements PhaseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2925434709522323947L;

	@Override
	public void beforePhase(final PhaseEvent arg0) {
	 Exception e = (Exception) FacesContext.getCurrentInstance()
	  .getExternalContext().getSessionMap()
	  .get(WebAttributes.AUTHENTICATION_EXCEPTION);
	 
	 if (e instanceof BadCredentialsException) {
	  FacesContext
	   .getCurrentInstance()
	   .getExternalContext()
	   .getSessionMap()
	   .put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
	  FacesContext.getCurrentInstance().addMessage(
	   null,
	   new FacesMessage(FacesMessage.SEVERITY_ERROR,
	    "Invalid username or password",
	    null));
	 }
	}

	@Override
	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RENDER_RESPONSE;
	}

}
