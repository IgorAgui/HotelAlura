package Factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory con = new ConnectionFactory();
		Connection cone = con.recuperarConexao();
		
		System.out.println("Conexão Iniciada");
		cone.close();
		
		System.out.println("Conexão Encerrada");
				
	}

}
