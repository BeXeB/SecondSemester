package ui;

import control.*;
import model.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDeadLineC;
	private static CommissionCtrl comctr;
	private InputValidationCtrl v;
	private JTextField textFieldName;
	private JTextField textFieldAddress;
	private JTextField textFieldZipcode;
	private JTextField textFieldEmail;
	private JTextField textFieldPhone;
	private JTextField textFieldNameE;
	private JTextField textFieldAddressE;
	private JTextField textFieldZipcodeE;
	private JTextField textFieldEmailE;
	private JTextField textFieldPhoneE;
	private JTextField textFieldPercentage;
	private JTextField textFieldNameM;
	private JTextField textFieldDescM;
	private JTextField textFieldPriceM;
	private JTextField textFieldDescriptionG;
	private ClientCtrl clctrl;
	private EmployeeCtrl empctrl;
	private MaterialCtrl mctrl;
	private DiscountCtrl dctrl;
	private GroupCtrl groupctrl;
	private Group group;
	private AddEmployeeFrame addEmployeeFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() throws SQLException {
		clctrl = new ClientCtrl();
		empctrl = new EmployeeCtrl();
		comctr = new CommissionCtrl();
		v = new InputValidationCtrl();
		mctrl = new MaterialCtrl();
		dctrl = new DiscountCtrl();
		groupctrl = new GroupCtrl();
		addEmployeeFrame = new AddEmployeeFrame(groupctrl);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1077, 672);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 1049, 620);
		contentPane.add(tabbedPane);

		JPanel panelCommission = new JPanel();
		tabbedPane.addTab("Commission", null, panelCommission, null);
		panelCommission.setLayout(null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 1044, 590);
		panelCommission.add(tabbedPane_1);

		JPanel panelCreateCommission = new JPanel();
		tabbedPane_1.addTab("Create", null, panelCreateCommission, null);
		panelCreateCommission.setLayout(null);

		JLabel label_1 = new JLabel("CREATE COMMISSION");
		label_1.setFont(label_1.getFont().deriveFont(label_1.getFont().getStyle() | Font.BOLD,
				label_1.getFont().getSize() + 17f));
		label_1.setBounds(312, 27, 424, 28);
		panelCreateCommission.add(label_1);

		JLabel label_2 = new JLabel("DeadLine:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(55, 97, 85, 28);
		panelCreateCommission.add(label_2);

		JLabel label_4 = new JLabel("Price:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_4.setBounds(55, 272, 73, 28);
		panelCreateCommission.add(label_4);

		textFieldDeadLineC = new JTextField();
		textFieldDeadLineC.setText("");
		textFieldDeadLineC.setBounds(152, 102, 205, 22);
		panelCreateCommission.add(textFieldDeadLineC);
		textFieldDeadLineC.setColumns(10);

		JLabel lblcalculatedPriceAfter = new JLabel("...calculated price after subcommissions are added");
		lblcalculatedPriceAfter.setForeground(Color.GRAY);
		lblcalculatedPriceAfter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblcalculatedPriceAfter.setBounds(108, 274, 902, 28);
		panelCreateCommission.add(lblcalculatedPriceAfter);
		
		JButton btnAddSubC = new JButton("ADD SUBCOMMISSION");
		btnAddSubC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSubcommissionFrame addSubcommissionFrame = null;
				addSubcommissionFrame = new AddSubcommissionFrame(comctr);
				addSubcommissionFrame.setVisible(true);
			}
		});
		btnAddSubC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddSubC.setBounds(718, 153, 253, 42);
		panelCreateCommission.add(btnAddSubC);

		JLabel lblchosenClient = new JLabel("...chosen client");
		lblchosenClient.setForeground(Color.GRAY);
		lblchosenClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblchosenClient.setBounds(108, 208, 902, 28);
		panelCreateCommission.add(lblchosenClient);

		JButton btnAddClient = new JButton("ADD CLIENT");
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddClientFrame addClientFrame = null;
				try {
					addClientFrame = new AddClientFrame(comctr);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				addClientFrame.setVisible(true);
				lblchosenClient.setText(comctr.getSelectedClient().toString());
			}
		});
		btnAddClient.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddClient.setBounds(51, 153, 177, 42);
		panelCreateCommission.add(btnAddClient);

		JLabel lblchosenGroup = new JLabel("...chosen group");
		lblchosenGroup.setVerticalAlignment(SwingConstants.TOP);
		lblchosenGroup.setForeground(Color.GRAY);
		lblchosenGroup.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblchosenGroup.setBounds(118, 311, 909, 167);
		panelCreateCommission.add(lblchosenGroup);
		
		JButton btnAddGroup = new JButton("ADD GROUP");
		btnAddGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGroupFrame addGroupFrame = null;
				try {
					addGroupFrame = new AddGroupFrame(comctr);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				addGroupFrame.setVisible(true);
				String s = "<html>";
				for(Group g : comctr.getSelectedGroups()) {
					s += "Description: " + g.getDescription() + ", Group No: " + g.getGroupNo() + "<br>";
				}
				s += "</html>";
				lblchosenGroup.setText(s);
			}
		});
		btnAddGroup.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddGroup.setBounds(524, 153, 164, 42);
		panelCreateCommission.add(btnAddGroup);

		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBounds(511, 105, 457, 16);
		panelCreateCommission.add(lblNewLabel);
		
		JLabel lblchosenDiscountif = new JLabel("...chosen discount");
		lblchosenDiscountif.setForeground(Color.GRAY);
		lblchosenDiscountif.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblchosenDiscountif.setBounds(138, 240, 872, 28);
		panelCreateCommission.add(lblchosenDiscountif);

		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDiscount.setBounds(55, 238, 97, 28);
		panelCreateCommission.add(lblDiscount);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshCommission(textFieldDeadLineC, lblNewLabel, lblchosenClient, lblchosenDiscountif, lblcalculatedPriceAfter, lblchosenGroup);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancel.setBounds(760, 482, 116, 42);
		panelCreateCommission.add(btnCancel);

		
		JButton btnAddDiscount = new JButton("ADD DISCOUNT");
		btnAddDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddDiscountFrame addDiscountFrame = null;
				try {
					addDiscountFrame = new AddDiscountFrame(comctr);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				addDiscountFrame.setVisible(true);
				lblchosenDiscountif.setText(comctr.getSelectedDiscount().toString());
			}
		});
		btnAddDiscount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddDiscount.setBounds(282, 153, 177, 42);
		panelCreateCommission.add(btnAddDiscount);

		JLabel lblGroup = new JLabel("Group:");
		lblGroup.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGroup.setBounds(55, 302, 85, 28);
		panelCreateCommission.add(lblGroup);

		JLabel lblClient = new JLabel("Client:");
		lblClient.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClient.setBounds(55, 208, 85, 28);
		panelCreateCommission.add(lblClient);

		JButton btnCreate = new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(v.dateValidation(textFieldDeadLineC.getText())) {
					comctr.createComission(textFieldDeadLineC.getText());
					lblNewLabel.setText("Commission created successfully");
				}
				else {
					JOptionPane.showMessageDialog(getParent(),"WRONG~! The correct format is yyyy-mm-dd  hh:mm:ss!", "Alert", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreate.setBounds(370, 89, 116, 42);
		panelCreateCommission.add(btnCreate);
		
		JPanel panel = new DBConnectionPanel();
		panel.setBounds(760, 529, 250, 22);
		panelCreateCommission.add(panel);
		
		JButton btnRefreshPrice = new JButton("REFRESH PRICE");
		btnRefreshPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblcalculatedPriceAfter.setText(""+comctr.getCommission().getPrice());
			}
		});
		btnRefreshPrice.setFont(new Font("Tahoma", Font.ITALIC, 16));
		btnRefreshPrice.setBounds(55, 483, 161, 42);
		panelCreateCommission.add(btnRefreshPrice);

		getContentPane().setLayout(new BorderLayout());
		
		JButton btnNewButton = new JButton("OK!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.err.println(comctr.toString());
					comctr.saveToDataBase();
					JOptionPane.showMessageDialog(getParent(),"Commission created!", "", JOptionPane.INFORMATION_MESSAGE);
					refreshCommission(textFieldDeadLineC, lblNewLabel, lblchosenClient, lblchosenDiscountif, lblcalculatedPriceAfter, lblchosenGroup);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(913, 482, 97, 42);
		panelCreateCommission.add(btnNewButton);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Client", null, tabbedPane_2, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane_2.addTab("Create", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblCreateClient = new JLabel("CREATE CLIENT");
		lblCreateClient.setFont(lblCreateClient.getFont().deriveFont(lblCreateClient.getFont().getStyle() | Font.BOLD, lblCreateClient.getFont().getSize() + 17f));
		lblCreateClient.setBounds(343, 40, 273, 28);
		panel_1.add(lblCreateClient);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(76, 119, 69, 20);
		panel_1.add(lblName);
		
		JLabel lblNewLabel_1 = new JLabel("Address:");
		lblNewLabel_1.setBounds(76, 164, 69, 20);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblZipcode = new JLabel("Zipcode:");
		lblZipcode.setBounds(76, 209, 69, 20);
		panel_1.add(lblZipcode);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(76, 255, 69, 20);
		panel_1.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(76, 298, 69, 20);
		panel_1.add(lblPhone);
		
		JLabel lblIsbusiness = new JLabel("IsBusiness:");
		lblIsbusiness.setBounds(76, 348, 79, 20);
		panel_1.add(lblIsbusiness);
		
		JCheckBox chckbxBusiness = new JCheckBox("");
		chckbxBusiness.setBounds(166, 344, 29, 29);
		panel_1.add(chckbxBusiness);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(147, 116, 760, 26);
		panel_1.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(147, 161, 760, 26);
		panel_1.add(textFieldAddress);
		
		textFieldZipcode = new JTextField();
		textFieldZipcode.setColumns(10);
		textFieldZipcode.setBounds(144, 206, 763, 26);
		panel_1.add(textFieldZipcode);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(125, 252, 782, 26);
		panel_1.add(textFieldEmail);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(135, 295, 772, 26);
		panel_1.add(textFieldPhone);
		
		JButton button = new JButton("OK!");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(v.numberValidateInput(textFieldZipcode.getText()) && v.numberValidateInput(textFieldPhone.getText())) {
				try {
				Client c = clctrl.createClient(textFieldName.getText(), textFieldAddress.getText(), Integer.parseInt(textFieldZipcode.getText()), textFieldEmail.getText(), textFieldPhone.getText(), chckbxBusiness.isSelected());
				JOptionPane.showMessageDialog(getParent(),c.toString(), "Client created!", JOptionPane.INFORMATION_MESSAGE);
				textFieldName.setText("");
				textFieldAddress.setText("");
				textFieldZipcode.setText("");
		        textFieldEmail.setText("");
		        textFieldPhone.setText("");
		        chckbxBusiness.setSelected(false);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
			else{JOptionPane.showMessageDialog(getParent(),"WRONG~! Try that again with a positive number!", "Alert", JOptionPane.WARNING_MESSAGE);}

			}});
		button.setFont(new Font("Tahoma", Font.BOLD, 20));
		button.setBounds(856, 463, 97, 42);
		panel_1.add(button);
		
		JButton button_1 = new JButton("CANCEL");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldName.setText("");
				textFieldAddress.setText("");
				textFieldZipcode.setText("");
		        textFieldEmail.setText("");
		        textFieldPhone.setText("");
		        chckbxBusiness.setSelected(false);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_1.setBounds(703, 463, 116, 42);
		panel_1.add(button_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_2.addTab("Display", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane_2.addTab("Update", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_2.addTab("Delete", null, panel_4, null);
		panel_4.setLayout(null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Employee", null, tabbedPane_3, null);
		
		JPanel Create = new JPanel();
		tabbedPane_3.addTab("Create", null, Create, null);
		Create.setLayout(null);
		
		textFieldNameE = new JTextField();
		textFieldNameE.setColumns(10);
		textFieldNameE.setBounds(139, 126, 760, 26);
		Create.add(textFieldNameE);
		
		JLabel label = new JLabel("Name:");
		label.setBounds(68, 129, 69, 20);
		Create.add(label);
		
		JLabel label_3 = new JLabel("Address:");
		label_3.setBounds(68, 174, 69, 20);
		Create.add(label_3);
		
		textFieldAddressE = new JTextField();
		textFieldAddressE.setColumns(10);
		textFieldAddressE.setBounds(139, 171, 760, 26);
		Create.add(textFieldAddressE);
		
		JLabel label_5 = new JLabel("Zipcode:");
		label_5.setBounds(68, 219, 69, 20);
		Create.add(label_5);
		
		textFieldZipcodeE = new JTextField();
		textFieldZipcodeE.setColumns(10);
		textFieldZipcodeE.setBounds(136, 216, 763, 26);
		Create.add(textFieldZipcodeE);
		
		JLabel label_6 = new JLabel("Email:");
		label_6.setBounds(68, 265, 69, 20);
		Create.add(label_6);
		
		textFieldEmailE = new JTextField();
		textFieldEmailE.setColumns(10);
		textFieldEmailE.setBounds(117, 262, 782, 26);
		Create.add(textFieldEmailE);
		
		JLabel label_7 = new JLabel("Phone:");
		label_7.setBounds(68, 308, 69, 20);
		Create.add(label_7);
		
		textFieldPhoneE = new JTextField();
		textFieldPhoneE.setColumns(10);
		textFieldPhoneE.setBounds(127, 305, 772, 26);
		Create.add(textFieldPhoneE);
		
		JLabel lblIsmanager = new JLabel("IsManager:");
		lblIsmanager.setBounds(68, 358, 79, 20);
		Create.add(lblIsmanager);
		
		JCheckBox checkBoxManager = new JCheckBox("");
		checkBoxManager.setBounds(158, 358, 139, 29);
		Create.add(checkBoxManager);
		
		JButton button_2 = new JButton("CANCEL");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNameE.setText("");
				textFieldAddressE.setText("");
				textFieldZipcodeE.setText("");
		        textFieldEmailE.setText("");
		        textFieldPhoneE.setText("");
		        checkBoxManager.setSelected(false);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_2.setBounds(754, 476, 116, 42);
		Create.add(button_2);
		
		JButton button_3 = new JButton("OK!");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(v.numberValidateInput(textFieldZipcodeE.getText()) && v.numberValidateInput(textFieldPhoneE.getText())) {
					try {
					Employee emp = empctrl.createEmployee(textFieldNameE.getText(), textFieldAddressE.getText(), Integer.parseInt(textFieldZipcodeE.getText()), textFieldEmailE.getText(), textFieldPhoneE.getText(), checkBoxManager.isSelected());
					JOptionPane.showMessageDialog(getParent(),emp.toString(), "Employee created!", JOptionPane.INFORMATION_MESSAGE);
					textFieldNameE.setText("");
					textFieldAddressE.setText("");
					textFieldZipcodeE.setText("");
			        textFieldEmailE.setText("");
			        textFieldPhoneE.setText("");
			        checkBoxManager.setSelected(false);
				} catch (NumberFormatException ee) {
					ee.printStackTrace();
				} catch (SQLException ee) {
					ee.printStackTrace();
				}}
				else{JOptionPane.showMessageDialog(getParent(),"WRONG~! Try that again with a positive number!", "Alert", JOptionPane.WARNING_MESSAGE);}
				}});
		button_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_3.setBounds(907, 476, 97, 42);
		Create.add(button_3);
		
		JLabel lblCreateEmployee = new JLabel("CREATE EMPLOYEE");
		lblCreateEmployee.setFont(lblCreateEmployee.getFont().deriveFont(lblCreateEmployee.getFont().getStyle() | Font.BOLD, lblCreateEmployee.getFont().getSize() + 17f));
		lblCreateEmployee.setBounds(324, 38, 351, 28);
		Create.add(lblCreateEmployee);
		
		JTabbedPane tabbedPane_4 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Material", null, tabbedPane_4, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane_4.addTab("Create", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel label_9 = new JLabel("CREATE MATERIAL");
		label_9.setFont(label_9.getFont().deriveFont(label_9.getFont().getStyle() | Font.BOLD, label_9.getFont().getSize() + 17f));
		label_9.setBounds(318, 39, 351, 28);
		panel_5.add(label_9);
		
		JLabel label_10 = new JLabel("Name:");
		label_10.setBounds(80, 152, 69, 20);
		panel_5.add(label_10);
		
		textFieldNameM = new JTextField();
		textFieldNameM.setColumns(10);
		textFieldNameM.setBounds(137, 149, 774, 26);
		panel_5.add(textFieldNameM);
		
		JLabel label_11 = new JLabel("Description:");
		label_11.setBounds(80, 197, 97, 20);
		panel_5.add(label_11);
		
		textFieldDescM = new JTextField();
		textFieldDescM.setColumns(10);
		textFieldDescM.setBounds(176, 194, 735, 26);
		panel_5.add(textFieldDescM);
		
		JLabel label_12 = new JLabel("Price:");
		label_12.setBounds(80, 242, 69, 20);
		panel_5.add(label_12);
		
		textFieldPriceM = new JTextField();
		textFieldPriceM.setColumns(10);
		textFieldPriceM.setBounds(137, 239, 774, 26);
		panel_5.add(textFieldPriceM);
		
		JButton button_6 = new JButton("CANCEL");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNameM.setText("");
				textFieldDescM.setText("");
				textFieldPriceM.setText("");
			}
		});
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_6.setBounds(745, 476, 116, 42);
		panel_5.add(button_6);
		
		JButton button_7 = new JButton("OK!");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(v.numberDoubleValidateInput(textFieldPriceM.getText())) {
					try {
					Material mat = mctrl.createMaterial(textFieldNameM.getText(), textFieldDescM.getText(), Double.parseDouble(textFieldPriceM.getText()));
					JOptionPane.showMessageDialog(getParent(), mat.toString(), "Material created!", JOptionPane.INFORMATION_MESSAGE);
					textFieldNameM.setText("");
					textFieldDescM.setText("");
					textFieldPriceM.setText("");
				} catch (NumberFormatException ee) {
					ee.printStackTrace();
				} catch (SQLException ee) {
					ee.printStackTrace();
				}}
				else{JOptionPane.showMessageDialog(getParent(),"WRONG~! Try that again with a positive number!", "Alert", JOptionPane.WARNING_MESSAGE);}
				}
		});
		button_7.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_7.setBounds(898, 476, 97, 42);
		panel_5.add(button_7);
		
		JTabbedPane tabbedPane_5 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Discount", null, tabbedPane_5, null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane_5.addTab("Create", null, panel_6, null);
		panel_6.setLayout(null);
		
		JLabel label_8 = new JLabel("Percentage:");
		label_8.setBounds(80, 177, 97, 20);
		panel_6.add(label_8);
		
		textFieldPercentage = new JTextField();
		textFieldPercentage.setColumns(10);
		textFieldPercentage.setBounds(179, 174, 732, 26);
		panel_6.add(textFieldPercentage);
		
		JLabel lblCreateDiscount = new JLabel("CREATE DISCOUNT");
		lblCreateDiscount.setFont(lblCreateDiscount.getFont().deriveFont(lblCreateDiscount.getFont().getStyle() | Font.BOLD, lblCreateDiscount.getFont().getSize() + 17f));
		lblCreateDiscount.setBounds(348, 29, 351, 28);
		panel_6.add(lblCreateDiscount);
		
		JButton button_4 = new JButton("CANCEL");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldPercentage.setText("");
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_4.setBounds(747, 478, 116, 42);
		panel_6.add(button_4);
		
		JButton button_5 = new JButton("OK!");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(v.numberValidateInput(textFieldPercentage.getText())) {
				try {
					Discount d = dctrl.createDiscount(Integer.parseInt(textFieldPercentage.getText()));
					JOptionPane.showMessageDialog(getParent(), d.toString(), "Discount created!", JOptionPane.INFORMATION_MESSAGE);
					textFieldPercentage.setText("");
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				}
			
			else {
			JOptionPane.showMessageDialog(getParent(),"WRONG~! Try that again with a positive number!", "Alert", JOptionPane.WARNING_MESSAGE);
			}}});
		button_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_5.setBounds(900, 478, 97, 42);
		panel_6.add(button_5);
		
		JTabbedPane tabbedPane_6 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Group", null, tabbedPane_6, null);
		
		JPanel panel_7 = new JPanel();
		tabbedPane_6.addTab("Create", null, panel_7, null);
		panel_7.setLayout(null);
		
		JLabel lblCreateGroup = new JLabel("CREATE GROUP");
		lblCreateGroup.setFont(lblCreateGroup.getFont().deriveFont(lblCreateGroup.getFont().getStyle() | Font.BOLD, lblCreateGroup.getFont().getSize() + 17f));
		lblCreateGroup.setBounds(363, 28, 271, 28);
		panel_7.add(lblCreateGroup);
		
		JLabel lblGroup_1 = new JLabel("Description:");
		lblGroup_1.setBounds(71, 176, 97, 20);
		panel_7.add(lblGroup_1);
		
		textFieldDescriptionG = new JTextField();
		textFieldDescriptionG.setColumns(10);
		textFieldDescriptionG.setBounds(170, 173, 732, 26);
		panel_7.add(textFieldDescriptionG);
		
		JButton button_8 = new JButton("CANCEL");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldDescriptionG.setText("");
				groupctrl.resetGroup();
			}
		});
		button_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_8.setBounds(738, 477, 116, 42);
		panel_7.add(button_8);
		
		JButton button_9 = new JButton("OK!");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.err.println(groupctrl.toString());
					groupctrl.saveToDataBase();
					JOptionPane.showMessageDialog(getParent(),"Group created!", "", JOptionPane.INFORMATION_MESSAGE);
					textFieldDescriptionG.setText("");
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		button_9.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_9.setBounds(891, 477, 97, 42);
		panel_7.add(button_9);
		
		JLabel lblchosenEmployees = new JLabel("...chosen employees");
		lblchosenEmployees.setVerticalAlignment(SwingConstants.TOP);
		lblchosenEmployees.setForeground(Color.GRAY);
		lblchosenEmployees.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblchosenEmployees.setBounds(115, 279, 909, 167);
		panel_7.add(lblchosenEmployees);
		
		JLabel lblEmployees = new JLabel("Employees:");
		lblEmployees.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmployees.setBounds(15, 270, 116, 28);
		panel_7.add(lblEmployees);
		
		JButton btnAddEmployee = new JButton("ADD EMPLOYEE");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEmployeeFrame.setVisible(true);
				String string="<html>";
				for(Employee employee : group.getHoursOfWork().keySet())
				{
					string+=employee.toString()+"<br>";
				
				}
				string+="</html>";
				lblchosenEmployees.setText(string);
			}
		});
		btnAddEmployee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddEmployee.setBounds(15, 212, 177, 42);
		panel_7.add(btnAddEmployee);
		
		JLabel labelGroupCreated = new JLabel(" ");
		labelGroupCreated.setForeground(Color.GRAY);
		labelGroupCreated.setBounds(567, 212, 457, 16);
		panel_7.add(labelGroupCreated);
		
		JButton button_11 = new JButton("CREATE");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			group =	groupctrl.createGroup(textFieldDescriptionG.getText());
			if(group!=null) {
			labelGroupCreated.setText("GROUP CREATED");
			}}
			
		});
		button_11.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_11.setBounds(908, 163, 116, 42);
		panel_7.add(button_11);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private static void refreshCommission(JTextField a, JLabel b, JLabel c, JLabel d, JLabel e, JLabel f ) {
		comctr.reset();
		a.setText("");
		b.setText("");
		c.setText("...chosen client");
		d.setText("...chosen discount");
		e.setText("...calculated price after subcommissions are added");
		f.setText("...chosen group");
	}
}
