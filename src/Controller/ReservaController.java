package Controller;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import DAO.ReservaDAO;
import Factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaD;
	
	public ReservaController() {
		Connection con = new ConnectionFactory().recuperarConexao();
		this.reservaD = new ReservaDAO(con);
	}
	
	public void guardar(Reserva reserva) {
		this.reservaD.guardar(reserva);
	}
	
	public List<Reserva> mostrar() {
		return this.reservaD.mostrar();
	}
	
	public List<Reserva> buscar(String id) {
		return this.reservaD.buscarId(id);
	}
	
	public void atualizarReserva(LocalDate dataE, LocalDate dataS, String valor, String formaPago, Integer id) {
		this.reservaD.Atualizar(dataE, dataS, valor, formaPago, id);
	}
	
	public void Deletar(Integer id) {
		this.reservaD.Deletar(id);
	}

}
