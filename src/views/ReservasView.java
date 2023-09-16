package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import Controller.ReservaController;
import modelo.Reserva;


@SuppressWarnings("serial")
public class ReservasView extends JFrame {

	private JPanel contentPane;
	public static JTextField txtValor;
	public static JDateChooser txtDataE;
	public static JDateChooser txtDataS;
	public static JComboBox<String> txtFormaPagamento;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel lblValorSimbolo; 
	private JLabel labelAtras;
	
	private ReservaController reservaControl;
	/**
	 * Inicie o aplicativo.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasView frame = new ReservasView();
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
	public ReservasView() {
		super("Reserva");
		
		this.reservaControl = new ReservaController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/Img/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		Color RedCorRGB = new Color(205, 92, 92);
		Color SuperCorRGB = new Color(178,34,34);
		Color TomatoRGB = new Color(255,99,71);
		

		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SuperCorRGB);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(SuperCorRGB);
		panel.add(separator_1_2);
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SuperCorRGB);
		separator_1_3.setBackground(SuperCorRGB);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SuperCorRGB);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(SuperCorRGB);
		panel.add(separator_1_1);
		
		txtDataE = new JDateChooser();
		txtDataE.getCalendarButton().setBackground(SuperCorRGB);
		txtDataE.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/Img/icon-reservas.png")));
		txtDataE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtDataE.setBounds(68, 161, 289, 35);
		txtDataE.getCalendarButton().setBounds(268, 0, 21, 33);
		txtDataE.setBackground(Color.WHITE);
		txtDataE.setBorder(new LineBorder(SystemColor.window));
		txtDataE.setDateFormatString("yyyy-MM-dd");
		txtDataE.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(txtDataE);
		
		lblValorSimbolo = new JLabel("");
		lblValorSimbolo.setVisible(true);
		lblValorSimbolo.setBounds(121, 332, 17, 25);
		lblValorSimbolo.setForeground(SuperCorRGB);
		lblValorSimbolo.setFont(new Font("Roboto", Font.BOLD, 17));
		
		panel.add(lblValorSimbolo);
		
		JLabel lblCheckIn = new JLabel("DATA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 169, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		panel.add(lblCheckIn);
		
		JLabel lblCheckOut = new JLabel("DATA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 221, 187, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		panel.add(lblCheckOut);
		
		txtDataS = new JDateChooser();
		txtDataS.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/Img/icon-reservas.png")));
		txtDataS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtDataS.setBounds(68, 246, 289, 35);
		txtDataS.getCalendarButton().setBounds(267, 1, 21, 31);
		txtDataS.setBackground(Color.WHITE);
		txtDataS.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtDataS.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				calcularValor(txtDataE, txtDataS);
			}
		});
		txtDataS.setDateFormatString("yyyy-MM-dd");
		txtDataS.getCalendarButton().setBackground(SuperCorRGB);
		txtDataS.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtDataS);
		
	
		
		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(78, 328, 43, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 14));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblValor = new JLabel("VALOR DA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 196, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);
		
		txtFormaPagamento = new JComboBox();
		txtFormaPagamento.setBounds(68, 417, 289, 38);
		txtFormaPagamento.setBackground(SystemColor.text);
		txtFormaPagamento.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPagamento.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPagamento.setModel(new DefaultComboBoxModel(new String[] {"Cartão de Crédito", "Cartão de Débito", "Boleto"}));
		panel.add(txtFormaPagamento);
		
		JLabel lblFormaPago = new JLabel("FORMA DE PAGAMENTO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 213, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);
		
		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(109, 60, 219, 42);
		lblTitulo.setForeground(SuperCorRGB);
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 18));
		panel.add(lblTitulo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(RedCorRGB);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/Img/100pxRGB.png")));
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/Img/check-in.png")));
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(RedCorRGB);
			     labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(RedCorRGB);
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
		panel.add(header);
		
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
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SuperCorRGB);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(SuperCorRGB);
		panel.add(separator_1);
		
		JPanel btnProximo = new JPanel();
		btnProximo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ReservasView.txtDataE.getDate() != null && ReservasView.txtDataS.getDate() != null) {		
					guardarReserva();
				} else {
					JOptionPane.showMessageDialog(null, "Deve preencher todos os campos.");
				}
			}						
		});
		btnProximo.setLayout(null);
		btnProximo.setBackground(SuperCorRGB);
		btnProximo.setBounds(238, 493, 122, 35);
		panel.add(btnProximo);
		btnProximo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel lblSeguinte = new JLabel("PRÓXIMO");
		lblSeguinte.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeguinte.setForeground(Color.WHITE);
		lblSeguinte.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSeguinte.setBounds(0, 0, 122, 35);
		btnProximo.add(lblSeguinte);
		}
	
	public void guardarReserva() {
		 try {
			 String dataE = ((JTextField)txtDataE.getDateEditor().getUiComponent()).getText();
			 String dataS = ((JTextField)txtDataS.getDateEditor().getUiComponent()).getText();
			 Reserva res = new Reserva(java.sql.Date.valueOf(dataE), java.sql.Date.valueOf(dataS), txtValor.getText(), txtFormaPagamento.getSelectedItem().toString());
			 reservaControl.guardar(res);
			 
			 RegistroHospede registro = new RegistroHospede(res.getId());
				registro.setVisible(true);
				dispose();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Deve preencher todos os campos");
		}
		 
	 }
		
		public void calcularValor(JDateChooser dataE, JDateChooser dataS) {
			if(dataE.getDate()!= null && dataS.getDate() != null) {
				if(dataE.getDate().after(dataS.getDate())) {
					JOptionPane.showMessageDialog(null, "A data de entrada não pode ser depois da saída",
				"Erro nas datas", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Calendar inicio = dataE.getCalendar();
				Calendar fim = dataS.getCalendar();
				int dias =  -1; // para que conte desde o dia seguinte
				int noite = 50;  // Valor da Noite
				int valor;
				
				while(inicio.before(fim)||inicio.equals(fim)) {
					dias++;
					inicio.add(Calendar.DATE,1);
				}
				valor = dias * noite;
				System.out.println(valor);
				txtValor.setText(""+valor);
			}
		}
		
		// Para quando Atualizar as datas da tabela , limpando os valores
		
		public void limparValor() {
			txtValor.setText("");
		}
		
		

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
