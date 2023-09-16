package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import modelo.Hospedes;

public class HospedesDAO {
	
	private Connection con;
	
	public HospedesDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Hospedes hospedes) {
		
		try {
			String sql = "INSERT INTO hospedes (nome, sobrenome, data_nascimento, nacionalidade"
					+",telefone, id_reserva) VALUES(?,?,?,?,?,?)";
			try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, hospedes.getNome());
				pstm.setString(2, hospedes.getSobrenome());
				pstm.setObject(3, hospedes.getDataNascimento());
				pstm.setString(4, hospedes.getNacionalidade());
				pstm.setString(5, hospedes.getTelefone());
				pstm.setInt(6, hospedes.getIdReserva());
				pstm.execute();
				try(ResultSet rst = pstm.getGeneratedKeys()){
					while (rst.next()) {
						hospedes.setId(rst.getInt(1));
					}
				}
			}
		} catch(SQLException e) {
			throw new RuntimeException();
		}
		
	}
	
	public List<Hospedes> mostrar() {
		List<Hospedes> hospedes = new ArrayList<Hospedes>();
		try {
			String sql = "SELECT id, nome, sobrenome, data_nascimento, nacionalidade,"
					+ "telefone, id_reserva FROM hospedes";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				pstm.execute();
				
				transformarResultado(hospedes,pstm);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public List<Hospedes> buscarId(String id) {
		List<Hospedes> hospedes = new ArrayList<Hospedes>();
		try {
			String sql = "SELECT id, nome, sobrenome, data_nascimento, nacionalidade,"
					+ "telefone, id_reserva FROM hospedes WHERE id=?";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				pstm.setString(1, id);
				pstm.execute();
				
				transformarResultado(hospedes,pstm);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	
	public List<Hospedes> buscarNome(String nome) {
		List<Hospedes> hospedes = new ArrayList<Hospedes>();
		try {
			String sql = "SELECT id, nome, sobrenome, data_nascimento, nacionalidade,"
					+ "telefone, id_reserva FROM hospedes WHERE nome=?";
			
			try(PreparedStatement pstm = con.prepareStatement(sql)){
				pstm.setString(1, nome);
				pstm.execute();
				
				transformarResultado(hospedes,pstm);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	
	
	public void AtualizarHospede(String nome, String sobrenome, Date dataNascimento, String nacionalidade,
			String telefone, Integer idReserva, Integer id) {
		try(PreparedStatement stm = con.prepareStatement(""
			+ "UPDATE hospedes SET nome=?, sobrenome=?, data_nascimento=?, nacionalidade=?,"
			+"telefone=?, id_reserva=? WHERE id=?")){
			stm.setString(1, nome);
			stm.setString(2, sobrenome);
			stm.setObject(3, dataNascimento);
			stm.setString(4, nacionalidade);
			stm.setString(5, telefone);
			stm.setInt(6, idReserva);
			stm.setInt(7, id);
			stm.execute();
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	public void Deletar2(Integer id) {
		try {
			Statement state = con.createStatement();  	
			state.execute("SET FOREIGN_KEY_CHECKS=0");
			PreparedStatement stm = con.prepareStatement("DELETE FROM hospedes WHERE id = ?");
			stm.setInt(1, id);
			stm.execute();
			state.execute("SET FOREIGN_KEY_CHECKS=1");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	
	private void transformarResultado(List<Hospedes> hospedes, PreparedStatement pstm) throws SQLException {
		try(ResultSet rst = pstm.executeQuery()){
			
			while(rst.next()) {
				Hospedes produto = new Hospedes(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getDate(4), rst.getString(5),rst.getString(6),rst.getInt(7));
				hospedes.add(produto);
			}
		}
	}	

}
