import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JButton;

import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.JPanel;

import java.awt.Component;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class ver1_GUI {

	private JFrame frame;
	private JTextField Mem_Address;
	private JTextField Mem_value;
	private JTextField PSWH;
	private JTextField PSWL;
	private JTextField PCH;
	private JTextField B;
	private JTextField C;
	private JTextField D;
	private JTextField E;
	private JTextField H;
	private JTextField L;
	private JTextField SPH;
	private JTextField SPL;
	private JTextField IR;
	private JTextField S;
	private JTextField Z;
	private JTextField AC;
	private JTextField P;
	private JTextField C_flag;
	private JTextField PCL;
	private JTextField PortAtxt;
	private JTextField PortBTxt;
	private JTextField PortCtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ver1_GUI window = new ver1_GUI();
					window.frame.setVisible(true);
					UIManager.setLookAndFeel(
	                        UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ver1_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(UIManager.getColor("Button.background"));
		frame.setBounds(0, 0, 1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel tools_panel = new JPanel();
		tools_panel.setBounds(0, 0, 1201, 33);
		tools_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(tools_panel);
		
		
		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setToolTipText("Execute");
		btnNewButton.setBounds(496, 5, 39, 25);
		try
		{
			Image imgs = ImageIO.read(getClass().getResource("exec2.png"));
			btnNewButton.setIcon(new ImageIcon(imgs));
		}
		catch  (IOException ex) {}
		tools_panel.setLayout(null);
		btnNewButton.setBorder(null);
//		btnNewButton.setAlignmentY(Component.TOP_ALIGNMENT);
		tools_panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBounds(547, 5, 39, 25);
		btnNewButton_1.setToolTipText("Stop");
		try
		{
			Image imgs = ImageIO.read(getClass().getResource("stop1.png"));
			btnNewButton_1.setIcon(new ImageIcon(imgs));
		}
		catch  (IOException ex) {}
		btnNewButton_1.setBorder(null);
		tools_panel.add(btnNewButton_1);
		
		JButton btnP = new JButton();
		btnP.setBounds(598, 5, 39, 25);
		btnP.setToolTipText("Previous");
		try
		{
			Image imgs = ImageIO.read(getClass().getResource("prev.png"));
			btnP.setIcon(new ImageIcon(imgs));
		}
		catch  (IOException ex) {}
		btnP.setBorder(null);
		tools_panel.add(btnP);
		
		JButton btnSingleStep = new JButton();
		btnSingleStep.setBounds(649, 5, 39, 25);
		btnSingleStep.setToolTipText("Single Step");
		btnSingleStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		try
		{
			Image imgs = ImageIO.read(getClass().getResource("SS1.png"));
			btnSingleStep.setIcon(new ImageIcon(imgs));
		}
		catch  (IOException ex) {}
		btnSingleStep.setBorder(null);
		tools_panel.add(btnSingleStep);
		
		JButton btnNext = new JButton();
		btnNext.setBounds(700, 5, 32, 25);
		btnNext.setToolTipText("Next");
		try
		{
			Image imgs = ImageIO.read(getClass().getResource("next.png"));
			btnNext.setIcon(new ImageIcon(imgs));
		}
		catch  (IOException ex) {}
		btnNext.setBorder(null);
		tools_panel.add(btnNext);
		
		JPanel PPI_panel = new JPanel();
		PPI_panel.setBounds(0, 32, 239, 250);
		PPI_panel.setBackground(UIManager.getColor("Button.background"));
		//339
		PPI_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(PPI_panel);
		PPI_panel.setLayout(null);
		
		JLabel lblPpi = new JLabel("PPI");
		lblPpi.setBounds(81, 12, 70, 15);
		
		PPI_panel.add(lblPpi);
		
//		final JToggleButton tglbtnPorta = new JToggleButton("Mode");
//		
//		tglbtnPorta.setBounds(143, 65, 73, 25);
//		tglbtnPorta.setToolTipText("Select the mode of Port A");
//		PPI_panel.add(tglbtnPorta);
//		tglbtnPorta.addChangeListener(new ChangeListener() {
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				// TODO Auto-generated method stub
//	
//				if (tglbtnPorta.isSelected()){
//					tglbtnPorta.setText("IN");
//					PortAtxt.setEditable(true);
//				} else {
//					tglbtnPorta.setText("OUT");
//					PortAtxt.setEditable(false);
//				}
//			}
//							
//			});
//		
//		final JToggleButton tglbtnPortb = new JToggleButton("Mode");
//		
//		tglbtnPortb.setBounds(143, 112, 73, 25);
//		tglbtnPortb.setToolTipText("Select the mode of Port B");
//		PPI_panel.add(tglbtnPortb);
//		tglbtnPortb.addChangeListener(new ChangeListener(){

//			@Override
//			public void stateChanged(ChangeEvent arg0) {
//				// TODO Auto-generated method stub
//				if (tglbtnPortb.isSelected()){
//					tglbtnPortb.setText("IN");
//					PortBTxt.setEditable(true);
//				} else {
//					tglbtnPortb.setText("OUT");
//					PortBTxt.setEditable(false);
//				}
//				
//			}
//			
//			
//		});
//		
//		final JToggleButton tglbtnPortc = new JToggleButton("Mode");
//		tglbtnPortc.setBounds(143, 154, 73, 25);
//		tglbtnPortc.setToolTipText("Select the mode of Port C");
//		PPI_panel.add(tglbtnPortc);
//		tglbtnPortc.addChangeListener(new ChangeListener(){

//			@Override
//			public void stateChanged(ChangeEvent e) {
//				// TODO Auto-generated method stub
//				if (tglbtnPortc.isSelected()){
//					tglbtnPortc.setText("IN");
//					PortCtxt.setEditable(true);
//				} else {
//					tglbtnPortc.setText("OUT");
//					PortCtxt.setEditable(false);
//				}
//				
//			}
//			
//		});
		
		JLabel lblPortA = new JLabel("Port A");
		lblPortA.setBounds(12, 70, 51, 15);
		PPI_panel.add(lblPortA);
		
		JLabel lblPortB = new JLabel("Port B");
		lblPortB.setBounds(12, 112, 51, 15);
		PPI_panel.add(lblPortB);
		
		JLabel lblPortC = new JLabel("Port C");
		lblPortC.setBounds(12, 159, 51, 15);
		PPI_panel.add(lblPortC);
		
		

		PortAtxt = new JTextField();
		PortAtxt.setEditable(false);
		PortAtxt.setBounds(120, 68, 70, 19);
		PPI_panel.add(PortAtxt);
		PortAtxt.setColumns(10);
		
		PortBTxt = new JTextField();
		PortBTxt.setEditable(false);
		PortBTxt.setBounds(120, 115, 70, 19);
		PPI_panel.add(PortBTxt);
		PortBTxt.setColumns(10);
		
		PortCtxt = new JTextField();
		PortCtxt.setEditable(false);
		PortCtxt.setColumns(10);
		PortCtxt.setBounds(120, 157, 70, 19);
		PPI_panel.add(PortCtxt);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(61, 198, 117, 25);
		PPI_panel.add(btnUpdate);
		
		JScrollPane Message_scrollPane = new JScrollPane();
		Message_scrollPane.setBounds(0, 500, 1200, 199);
		Message_scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(Message_scrollPane);
		
		JTextArea ErrorMessages = new JTextArea();
		ErrorMessages.setText("Error Messages");
		ErrorMessages.setEditable(false);
		Message_scrollPane.setViewportView(ErrorMessages);
		
		JPanel Memory_panel = new JPanel();
		Memory_panel.setBounds(0, 282, 239, 216);
		Memory_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(Memory_panel);
		Memory_panel.setLayout(null);
		
		JLabel lblMemory = new JLabel("MEMORY");
		lblMemory.setBounds(81, 12, 70, 15);
		Memory_panel.add(lblMemory);
		
		Mem_Address = new JTextField();
		Mem_Address.setBounds(113, 62, 70, 25);
		Mem_Address.setToolTipText("Memory location");
		Mem_Address.setText("0000");
		Mem_Address.setEditable(false);
		Mem_Address.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Mem_Address.setBackground(UIManager.getColor("Button.highlight"));
		Memory_panel.add(Mem_Address);
		Mem_Address.setColumns(10);
		
		Mem_value = new JTextField();
		Mem_value.setBounds(113, 129, 70, 25);
		Mem_value.setToolTipText("Edit new value and click update");
		Mem_value.setText("0000");
		Mem_value.setEditable(false);
		Mem_value.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Mem_value.setBackground(UIManager.getColor("Button.highlight"));
		Memory_panel.add(Mem_value);
		Mem_value.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(32, 67, 70, 15);
		Memory_panel.add(lblLocation);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(32, 134, 70, 15);
		Memory_panel.add(lblValue);
		
		JPanel Reg_panel = new JPanel();
		Reg_panel.setBounds(996, 32, 204, 290);
		Reg_panel.setToolTipText("Current register status");
		Reg_panel.setBackground(UIManager.getColor("Button.background"));
		Reg_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(Reg_panel);
		Reg_panel.setLayout(null);
		
		JLabel lblRegisters = new JLabel("REGISTERS");
		lblRegisters.setBounds(65, 0, 90, 29);
		Reg_panel.add(lblRegisters);
		
		JLabel Psw_lbl = new JLabel("PSW");
		Psw_lbl.setBounds(12, 62, 49, 20);
		Reg_panel.add(Psw_lbl);
		
		JLabel PC_lbl = new JLabel("PC");
		PC_lbl.setBounds(12, 93, 70, 20);
		Reg_panel.add(PC_lbl);
		
		JLabel Bc_lbl = new JLabel("BC");
		Bc_lbl.setBounds(12, 124, 70, 20);
		Reg_panel.add(Bc_lbl);
		
		JLabel De_lbl = new JLabel("DE");
		De_lbl.setBounds(12, 155, 70, 20);
		Reg_panel.add(De_lbl);
		
		JLabel Hl_lbl = new JLabel("HL");
		Hl_lbl.setBounds(12, 186, 70, 20);
		Reg_panel.add(Hl_lbl);
		
		JLabel Sp_lbl = new JLabel("SP");
		Sp_lbl.setBounds(12, 217, 70, 20);
		Reg_panel.add(Sp_lbl);
		
		JLabel Ir_lbl = new JLabel("IR");
		Ir_lbl.setBounds(12, 248, 70, 20);
		Reg_panel.add(Ir_lbl);
		
		PSWH = new JTextField();
		PSWH.setBackground(UIManager.getColor("Button.background"));
		PSWH.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		PSWH.setText("00");
		PSWH.setEditable(false);
		PSWH.setBounds(90, 63, 29, 19);
		Reg_panel.add(PSWH);
		PSWH.setColumns(10);
		
		PSWL = new JTextField();
		PSWL.setBackground(UIManager.getColor("Button.background"));
		PSWL.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		PSWL.setText("00");
		PSWL.setEditable(false);
		PSWL.setColumns(10);
		PSWL.setBounds(131, 63, 29, 19);
		Reg_panel.add(PSWL);
		
		PCH = new JTextField();
		PCH.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		PCH.setBackground(UIManager.getColor("Button.background"));
		PCH.setText("00");
		PCH.setEditable(false);
		PCH.setColumns(10);
		PCH.setBounds(90, 94, 29, 19);
		Reg_panel.add(PCH);
		
		B = new JTextField();
		B.setBackground(UIManager.getColor("Button.background"));
		B.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		B.setText("00");
		B.setEditable(false);
		B.setColumns(10);
		B.setBounds(90, 125, 29, 19);
		Reg_panel.add(B);
		
		C = new JTextField();
		C.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		C.setBackground(UIManager.getColor("Button.background"));
		C.setText("00");
		C.setEditable(false);
		C.setColumns(10);
		C.setBounds(131, 125, 29, 19);
		Reg_panel.add(C);
		
		D = new JTextField();
		D.setBackground(UIManager.getColor("Button.background"));
		D.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		D.setText("00");
		D.setEditable(false);
		D.setColumns(10);
		D.setBounds(90, 156, 29, 19);
		Reg_panel.add(D);
		
		E = new JTextField();
		E.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		E.setBackground(UIManager.getColor("Button.background"));
		E.setText("00");
		E.setEditable(false);
		E.setColumns(10);
		E.setBounds(131, 156, 28, 19);
		Reg_panel.add(E);
		
		H = new JTextField();
		H.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		H.setBackground(UIManager.getColor("Button.background"));
		H.setText("00");
		H.setEditable(false);
		H.setColumns(10);
		H.setBounds(90, 187, 29, 19);
		Reg_panel.add(H);
		
		L = new JTextField();
		L.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		L.setBackground(UIManager.getColor("Button.background"));
		L.setText("00");
		L.setEditable(false);
		L.setColumns(10);
		L.setBounds(131, 187, 29, 19);
		Reg_panel.add(L);
		
		SPH = new JTextField();
		SPH.setBackground(UIManager.getColor("Button.background"));
		SPH.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		SPH.setText("00");
		SPH.setEditable(false);
		SPH.setColumns(10);
		SPH.setBounds(90, 218, 29, 19);
		Reg_panel.add(SPH);
		
		SPL = new JTextField();
		SPL.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		SPL.setBackground(UIManager.getColor("Button.background"));
		SPL.setText("00");
		SPL.setEditable(false);
		SPL.setColumns(10);
		SPL.setBounds(131, 218, 29, 19);
		Reg_panel.add(SPL);
		
		IR = new JTextField();
		IR.setBackground(UIManager.getColor("Button.background"));
		IR.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		IR.setText("00");
		IR.setEditable(false);
		IR.setColumns(10);
		IR.setBounds(109, 249, 29, 19);
		Reg_panel.add(IR);
		
		PCL = new JTextField();
		PCL.setText("00");
		PCL.setEditable(false);
		PCL.setColumns(10);
		PCL.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		PCL.setBackground(UIManager.getColor("Button.background"));
		PCL.setBounds(131, 94, 29, 19);
		Reg_panel.add(PCL);
		
		JPanel Flags_panel = new JPanel();
		Flags_panel.setBounds(996, 318, 204, 180);
		Flags_panel.setToolTipText("Current flag status");
		Flags_panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(Flags_panel);
		Flags_panel.setLayout(null);
		
		JLabel lblFlags = new JLabel("FLAGS SET");
		lblFlags.setBounds(61, 27, 99, 23);
		Flags_panel.add(lblFlags);
		
		JLabel S_lbl = new JLabel("S");
		S_lbl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		S_lbl.setBounds(30, 75, 25, 20);
		S_lbl.setVisible(false);
		Flags_panel.add(S_lbl);
		

		JLabel Z_lbl = new JLabel("Z");
		Z_lbl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Z_lbl.setBounds(60, 75, 25, 20);
		Z_lbl.setVisible(false);
		Flags_panel.add(Z_lbl);
		
		JLabel Ac_lbl = new JLabel("AC");
		Ac_lbl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Ac_lbl.setBounds(90, 75, 25, 20);
		Ac_lbl.setVisible(false);
		Flags_panel.add(Ac_lbl);
		
		JLabel P_lbl = new JLabel("P");
		P_lbl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		P_lbl.setBounds(120, 75, 25, 20);
		P_lbl.setVisible(false);
		Flags_panel.add(P_lbl);
		
		JLabel C_lbl = new JLabel("C");
		C_lbl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		C_lbl.setBounds(150, 75, 25, 20);
		C_lbl.setVisible(false);
		Flags_panel.add(C_lbl);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(244, 32, 750, 466);
		frame.getContentPane().add(tabbedPane);
		
		JScrollPane opcode_scrollPane = new JScrollPane();
		tabbedPane.addTab("OP-Code", null, opcode_scrollPane, null);
		
		final JTextArea Opcode = new JTextArea();
		Opcode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Opcode.setText("");
			}
		});
		Opcode.setText("Op-Code text");
		opcode_scrollPane.setViewportView(Opcode);
		
		JScrollPane Hex_scrollPane = new JScrollPane();
		tabbedPane.addTab("HEX", null, Hex_scrollPane, null);
		
		final JTextArea Hex = new JTextArea();
		Hex.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hex.setText("");
			}
		});
		Hex.setText("HEX code");
		Hex_scrollPane.setViewportView(Hex);
		
//		S = new JTextField();
//		S.setText("0");
//		S.setEditable(false);
//		S.setColumns(10);
//		S.setBorder(null);
//		S.setBackground(UIManager.getColor("Button.background"));
//		S.setBounds(100, 48, 38, 19);
//		Flags_panel.add(S);
//		
//		Z = new JTextField();
//		Z.setText("0");
//		Z.setEditable(false);
//		Z.setColumns(10);
//		Z.setBorder(null);
//		Z.setBackground(UIManager.getColor("Button.background"));
//		Z.setBounds(100, 79, 38, 19);
//		Flags_panel.add(Z);
//		
//		AC = new JTextField();
//		AC.setText("0");
//		AC.setEditable(false);
//		AC.setColumns(10);
//		AC.setBorder(null);
//		AC.setBackground(UIManager.getColor("Button.background"));
//		AC.setBounds(100, 110, 38, 19);
//		Flags_panel.add(AC);
//		
//		P = new JTextField();
//		P.setText("0");
//		P.setEditable(false);
//		P.setColumns(10);
//		P.setBorder(null);
//		P.setBackground(UIManager.getColor("Button.background"));
//		P.setBounds(100, 141, 38, 19);
//		Flags_panel.add(P);
//		
//		C_flag = new JTextField();
//		C_flag.setText("0");
//		C_flag.setEditable(false);
//		C_flag.setColumns(10);
//		C_flag.setBorder(null);
//		C_flag.setBackground(UIManager.getColor("Button.background"));
//		C_flag.setBounds(100, 171, 38, 19);
//		Flags_panel.add(C_flag);
		
	}
}
