package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Factory.ConnectionFactory;

public class Usuarios {
	
	private String nome;
	private String senha;
	
	public Usuarios(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public static boolean validarUsuario(String nome, String senha) {
		ConnectionFactory con = new ConnectionFactory();
		Connection connec = null;
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			connec = con.recuperarConexao();
			state = connec.prepareStatement("SELECT * FROM usuarios WHERE nome=? AND senha=?");
			state.setString(1, nome);
			state.setString(2, senha);
			result = state.executeQuery();
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(result !=null)
					result.close();
				if(state !=null)
					state.close();
				if(connec !=null)
					connec.close();
				} catch (SQLException e2)  {
					e2.printStackTrace();
				}
		}
		
	}
	
	
	
}
