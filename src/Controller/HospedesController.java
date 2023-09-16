package Controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import DAO.HospedesDAO;
import Factory.ConnectionFactory;
import modelo.Hospedes;

public class HospedesController {
	
	private HospedesDAO hospedesDao;
	
	public HospedesController() {
		Connection con = new ConnectionFactory().recuperarConexao();
		this.hospedesDao = new HospedesDAO(con);
	}
	
	public void salvar(Hospedes hospedes) {
		this.hospedesDao.guardar(hospedes);
	}
	
	public List<Hospedes> mostrarHospedes() {
		return this.hospedesDao.mostrar();
	}
	
	public List<Hospedes> buscarHospedes(String id) {
		return this.hospedesDao.buscarId(id);
	}
	
	public List<Hospedes> buscarHospedesNome(String nome) {
		return this.hospedesDao.buscarNome(nome);
	}
	
	public void atualizarHospede(String nome, String sobrenome, Date dataNascimento, String nacionalidade,
			String telefone, Integer idReserva, Integer id) {
		this.hospedesDao.AtualizarHospede(nome, sobrenome, dataNascimento, nacionalidade, telefone, idReserva, id);
	}
	
	public void Deletar2(Integer id) {
		this.hospedesDao.Deletar2(id);
	}

}
