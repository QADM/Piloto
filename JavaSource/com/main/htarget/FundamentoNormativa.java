package com.main.htarget;

// Generated 16/10/2013 04:44:18 PM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;

/**
 * FundamentoNormativa generated by hbm2java
 */
public class FundamentoNormativa implements java.io.Serializable {

	private BigDecimal cveFundamentoNormativa;
	private String numeral;
	private String nombre;
	private BigDecimal cveTipoFundamento;
	private Date fechaRegistro;

	public FundamentoNormativa() {
	}

	public FundamentoNormativa(BigDecimal cveFundamentoNormativa,
			String numeral, String nombre, Date fechaRegistro) {
		this.cveFundamentoNormativa = cveFundamentoNormativa;
		this.numeral = numeral;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
	}

	public FundamentoNormativa(BigDecimal cveFundamentoNormativa,
			String numeral, String nombre, BigDecimal cveTipoFundamento,
			Date fechaRegistro) {
		this.cveFundamentoNormativa = cveFundamentoNormativa;
		this.numeral = numeral;
		this.nombre = nombre;
		this.cveTipoFundamento = cveTipoFundamento;
		this.fechaRegistro = fechaRegistro;
	}

	public BigDecimal getCveFundamentoNormativa() {
		return this.cveFundamentoNormativa;
	}

	public void setCveFundamentoNormativa(BigDecimal cveFundamentoNormativa) {
		this.cveFundamentoNormativa = cveFundamentoNormativa;
	}

	public String getNumeral() {
		return this.numeral;
	}

	public void setNumeral(String numeral) {
		this.numeral = numeral;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getCveTipoFundamento() {
		return this.cveTipoFundamento;
	}

	public void setCveTipoFundamento(BigDecimal cveTipoFundamento) {
		this.cveTipoFundamento = cveTipoFundamento;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}