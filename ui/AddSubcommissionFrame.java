package ui;

import control.*;
import model.CommissionLine;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollBar;

public class AddSubcommissionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDesc;
	private JTextField textFieldDeadline;
	private static CommissionCtrl ct;
	private InputValidationCtrl v = new InputValidationCtrl();

	/**
	 * Create the frame.
	 */
	public AddSubcommissionFrame(CommissionCtrl ct) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1057, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.ct = ct;
		
		JLabel lblCreateSubcommission = new JLabel("CREATE SUBCOMMISSION");
		lblCreateSubcommission.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblCreateSubcommission.setBounds(346, 13, 363, 34);
		contentPane.add(lblCreateSubcommission);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(40, 89, 68, 16);
		contentPane.add(lblDescription);
		
		JLabel lblDeadline = new JLabel("Deadline:");
		lblDeadline.setBounds(40, 126, 56, 16);
		contentPane.add(lblDeadline);
		
		textFieldDesc = new JTextField();
		textFieldDesc.setBounds(122, 86, 887, 22);
		contentPane.add(textFieldDesc);
		textFieldDesc.setColumns(10);
		
		textFieldDeadline = new JTextField();
		textFieldDeadline.setColumns(10);
		textFieldDeadline.setBounds(122, 123, 887, 22);
		contentPane.add(textFieldDeadline);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBounds(40, 226, 969, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnCreateSubcommission = new JButton("Create Subcommission");
		btnCreateSubcommission.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCreateSubcommission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(v.dateValidation(textFieldDeadline.getText())) {
					ct.createSubComission(textFieldDeadline.getText(), textFieldDesc.getText());
					lblNewLabel.setText("Subcommission created successfully!");
					}
					else {
						JOptionPane.showMessageDialog(getParent(),"WRONG~! The correct format is yyyy-mm-dd  hh:mm:ss!", "Alert", JOptionPane.WARNING_MESSAGE);
					}
			}
		});
		btnCreateSubcommission.setBounds(40, 170, 224, 43);
		contentPane.add(btnCreateSubcommission);
		
		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.setBounds(40, 348, 969, 86);
		contentPane.add(lblNewLabel_1);
		
		JButton btnAddMaterial = new JButton("ADD MATERIAL");
		btnAddMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMaterialFrame addMaterialFrame=null;
				try {
					addMaterialFrame = new AddMaterialFrame(ct);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				addMaterialFrame.setVisible(true);
				String s = "<html>";
				for(CommissionLine cl : ct.getCommissionLines()) {
					s += cl.toString() + "<br>";
				}
				s += "</html>";
				lblNewLabel_1.setText(s);
			}
		});
		btnAddMaterial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddMaterial.setBounds(40, 278, 176, 43);
		contentPane.add(btnAddMaterial);
		
		JButton btnOk = new JButton("OK!");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ct.addSubCommission();
				dispose();
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnOk.setBounds(912, 447, 97, 34);
		contentPane.add(btnOk);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(988, 357, 21, 77);
		contentPane.add(scrollBar);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancel.setBounds(757, 447, 117, 34);
		contentPane.add(btnCancel);
		
		
}
}
