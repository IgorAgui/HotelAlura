package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Usuarios;
import views.Login;
import views.MenuUsuario;

public class usuariosController implements ActionListener {
	
	private Login visualizar;
	
	public usuariosController(Login visualizar) {
		this.visualizar = visualizar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String nome = visualizar.getNome();
		String senha = visualizar.getSenha();
		
	if(Usuarios.validarUsuario(nome,senha)) {
		MenuUsuario menu = new MenuUsuario();
		menu.setVisible(true);
		visualizar.dispose();
		} else {
			JOptionPane.showMessageDialog(visualizar, "Usuário e Senha inválidos");
		}
	
	}

}
