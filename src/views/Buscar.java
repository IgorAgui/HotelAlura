package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.HospedesController;
import Controller.ReservaController;
import modelo.Hospedes;
import modelo.Reserva;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	private ReservaController reservasControl;
	private HospedesController hospedesControl;
	private ReservasView reservasView;
	String reserva;
	String hospedes;

	/**
	 * Inicie o aplicativo.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crie o quadro.
	 */
	public Buscar() {
		
		this.reservasView = new ReservasView();
		this.reservasControl = new ReservaController();
		this.hospedesControl = new HospedesController();
		
		Color RedCorRGB = new Color(205, 92, 92);
		Color SuperCorRGB = new Color(178,34,34);
		Color TomatoRGB = new Color(255,99,71);
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/Img/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(RedCorRGB);
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(RedCorRGB);
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
		
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		mostrarTableReservas();
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/Img/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);

		
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		mostrarTableHospedes();
		
		
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/Img/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/Img/100Nulo.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(SuperCorRGB);
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SuperCorRGB);
		separator_1_2.setBackground(SuperCorRGB);
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limparTable();
				if(txtBuscar.getText().equals("")) {
					mostrarTableReservas();
					mostrarTableHospedes();
				} else {
					
					if(txtBuscar.getText().matches("\\d+")) {
						mostrarTableReservasID();
						mostrarTableHospedesID();
					} else {
						mostrarTableReservasID();
						mostrarTableHospedesNome();
					}	
				}
			}
		});
		
		
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(RedCorRGB);
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHospedes = tbHospedes.getSelectedRow();
				
				
				if(filaReservas >=0) {
					AtualizarReservas();
					limparTable();
					mostrarTableReservas();
					mostrarTableHospedes();
				}else if(filaHospedes >=0) {
					atualizarHospedes();
					limparTable();
					mostrarTableHospedes();
					mostrarTableReservas();
				}
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(RedCorRGB);
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			int filaReservas = tbReservas.getSelectedRow();
			int filaHospedes = tbHospedes.getSelectedRow();
			
			if(filaReservas >= 0) {
				reserva = tbReservas.getValueAt(filaReservas, 0).toString();
				int confirmar = JOptionPane.showConfirmDialog(null, "Deseja excluir a reserva?");
				if (confirmar == JOptionPane.YES_OPTION) {
					String valor = tbReservas.getValueAt(filaReservas, 0).toString();
					reservasControl.Deletar(Integer.valueOf(valor));
					JOptionPane.showMessageDialog(contentPane, "Reserva excluída com sucesso!");
					limparTable();
					mostrarTableHospedes();
					mostrarTableReservas();
				}
			}else if(filaHospedes >=0) {
				hospedes = tbHospedes.getValueAt(filaHospedes, 0).toString();
				int confirmaHospede = JOptionPane.showConfirmDialog(null, "Deseja excluir o Hospede?");
				
				if(confirmaHospede == JOptionPane.YES_OPTION) {
					String valor = tbHospedes.getValueAt(filaHospedes, 0).toString();
					hospedesControl.Deletar2(Integer.valueOf(valor));
					JOptionPane.showMessageDialog(contentPane, "Hospede excluído");
					limparTable();
					mostrarTableHospedes();
					mostrarTableReservas();
					
				}
			}else {
				JOptionPane.showMessageDialog(null, "Houve um erro ao Deletar");
			}
		}
		});
		
		
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(RedCorRGB);
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}
	
	// ----------------------------------------RESERVAS--------------------------------------------------------------------------------------------------
	
	private List<Reserva> MostrarReservas() {
		return this.reservasControl.mostrar();
	}
	
	private List<Reserva> buscarIDreserva() {
		return this.reservasControl.buscar(txtBuscar.getText());
	}
	
	
	private void mostrarTableReservas() {
		List<Reserva> reserva = MostrarReservas();
		modelo.setRowCount(0);
		try {
			for (Reserva reservas : reserva) {
				modelo.addRow(new Object[] {
						reservas.getId(), reservas.getDataE(), reservas.getDataS(), reservas.getValor(),
						reservas.getFormaPago()
				});
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void mostrarTableReservasID() {
		List<Reserva> reserva = buscarIDreserva();
		try {
			for (Reserva reservas : reserva) {
				modelo.addRow(new Object[] {
						reservas.getId(), reservas.getDataE(), reservas.getDataS(), reservas.getValor(),
						reservas.getFormaPago()
				});
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	private void AtualizarReservas() throws NumberFormatException {
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
    	.ifPresent(fila ->{
    		
    		LocalDate dataE;
    		LocalDate dataS;
    		
    		try {
    			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    			dataE = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString(), dateFormat);
    			dataS = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString(), dateFormat);
    		}catch(DateTimeException e) {
    			throw new RuntimeException(e);
    		}
    		this.reservasView.limparValor();
    		String valor = calcularValorReserva(dataE, dataS);
    		String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(),4);
    		Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(),0).toString());
    		// Não pode mudar o id
    		if(tbReservas.getSelectedColumn() == 0) {
    			JOptionPane.showMessageDialog(this, "Não pode modificar o id");
    		}else {
    			this.reservasControl.atualizarReserva(dataE, dataS, valor, formaPago, id);
    			JOptionPane.showMessageDialog(this, String.format("Registro Modificado com Sucesso"));
    		}
    	});
    }
	
	public String calcularValorReserva(LocalDate dataE, LocalDate dataS) {
		if(dataE  != null && dataS !=null) {
			int dias = (int) ChronoUnit.DAYS.between(dataE, dataS);
			int noite = 50;
			int valor = dias * noite;
			return Integer.toString(valor);
		}else {
			return "";
		}
	}
	
	//---------------------------------------------------------------Hospedes------------------------------------------------------------------------------
	
	private List<Hospedes> mostrarHospedes() {
		return this.hospedesControl.mostrarHospedes();
	}
	
	private List<Hospedes> buscarHospedesId() {
		return this.hospedesControl.buscarHospedes(txtBuscar.getText());
	}
	
	private List<Hospedes> buscarHospedesNome() {
		return this.hospedesControl.buscarHospedesNome(txtBuscar.getText());
	}
	
	private void mostrarTableHospedes() {
		List<Hospedes> hospedes = mostrarHospedes();
		modeloHospedes.setRowCount(0);
		try {
			for (Hospedes hospedes1 : hospedes) {
				modeloHospedes.addRow(new Object[] {
						hospedes1.getId(), hospedes1.getNome(),hospedes1.getSobrenome(),hospedes1.getDataNascimento(),
						hospedes1.getNacionalidade(),hospedes1.getTelefone(),hospedes1.getIdReserva()
				});
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void mostrarTableHospedesID() {
		List<Hospedes> hospedes = buscarHospedesId();
		modeloHospedes.setRowCount(0);
		try {
			for (Hospedes hospedes1 : hospedes) {
				modeloHospedes.addRow(new Object[] {
						hospedes1.getId(), hospedes1.getNome(),hospedes1.getSobrenome(),hospedes1.getDataNascimento(),
						hospedes1.getNacionalidade(),hospedes1.getTelefone(),hospedes1.getIdReserva()
				});
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void mostrarTableHospedesNome() {
		List<Hospedes> hospedes = buscarHospedesNome();
		modeloHospedes.setRowCount(0);
		try {
			for (Hospedes hospedes1 : hospedes) {
				modeloHospedes.addRow(new Object[] {
						hospedes1.getId(), hospedes1.getNome(),hospedes1.getSobrenome(),hospedes1.getDataNascimento(),
						hospedes1.getNacionalidade(),hospedes1.getTelefone(),hospedes1.getIdReserva()
				});
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void mostrarTableResultados(boolean porId) {
	    List<Hospedes> hospedes = porId ? buscarHospedesId() : buscarHospedesNome();
	    modeloHospedes.setRowCount(0);
	    try {
	        for (Hospedes hospede : hospedes) {
	            modeloHospedes.addRow(new Object[] {
	                hospede.getId(), hospede.getNome(), hospede.getSobrenome(), hospede.getDataNascimento(),
	                hospede.getNacionalidade(), hospede.getTelefone(), hospede.getIdReserva()
	            });
	        }
	    } catch (Exception e) {
	        throw e;
	    }
	}

	
	
	private void atualizarHospedes()  {
		Optional.ofNullable(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn()))
		.ifPresentOrElse(filaHospedes ->{
			String nome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1);
			String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2);
			Date data_nascimento = Date.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 3).toString());
			String nacionalidade = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4);
			String telefone = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5);
			Integer idReserva = Integer.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 6).toString());
			Integer id = Integer.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0).toString());
			
			if(tbHospedes.getSelectedColumn() == 0 || tbHospedes.getSelectedColumn() == 6) {
				JOptionPane.showMessageDialog(this, "Não pode Modificar os ID");
			
			}else {
				this.hospedesControl.atualizarHospede(nome, sobrenome, data_nascimento, nacionalidade, telefone, idReserva, id);
				JOptionPane.showMessageDialog(this, String.format("Registro modificado com Sucesso!"));
			}
			
			
		}, ()-> JOptionPane.showInternalMessageDialog(this, "Erro ao Modificar Hospede"));
	}
	
	
	
	
	private void limparTable() {
		((DefaultTableModel) tbHospedes.getModel()).setRowCount(0);
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
