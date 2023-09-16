package modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Reserva {
	
	private Integer id;
	private Date dataE;
	private Date dataS;
	private String valor;
	private String formaPago;
	
	public Reserva(Date dataE, Date dataS, String valor, String formaPago) {
		super();
		this.dataE = dataE;
		this.dataS = dataS;
		this.valor = valor;
		this.formaPago = formaPago;
	}	



	public Reserva(Integer id, Date dataE, Date dataS, String valor, String formaPago) {
		super();
		this.id = id;
		this.dataE = dataE;
		this.dataS = dataS;
		this.valor = valor;
		this.formaPago = formaPago;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataE() {
		return dataE;
	}

	public void setDataE(Date dataE) {
		this.dataE = dataE;
	}

	public Date getDataS() {
		return dataS;
	}

	public void setDataS(Date dataS) {
		this.dataS = dataS;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	
	
}
