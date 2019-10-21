package ui;

import control.*;
import model.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddMaterialFrame extends JDialog{

	private JList list;
	private CommissionCtrl ct;
	private ArrayList<Material> mt;
	private int index;
	private AddSubcommissionFrame sbcFrame;
	private JTextField textField;
	private InputValidationCtrl v;
	
	public AddMaterialFrame(CommissionCtrl ct) throws SQLException {
		setModal(true);
		setMinimumSize(new Dimension(450, 270));
		DefaultListModel model = new DefaultListModel();
		sbcFrame = new AddSubcommissionFrame(ct);
		mt = new ArrayList<Material>();
		this.ct = ct;
		mt = this.ct.getMaterials();
		for (Material material : mt) {
			model.addElement(material.getName() + " - " + material.getMaterialNo());
		}
		
		v = new InputValidationCtrl();
		
		list = new JList(model);
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JScrollPane pane = new JScrollPane(list);
		pane.setBounds(0, 0, 430, 150);
		JButton addButton = new JButton("Add");
		addButton.setBounds(12, 161, 130, 50);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(v.numberValidateInput(textField.getText())) {
				index = list.getSelectedIndex();
				try {
					ct.addMaterial(mt.get(index).getMaterialNo(), Integer.parseInt(textField.getText()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				dispose();
				}
					else {
					JOptionPane.showMessageDialog(getParent(),"WRONG~! Try that again with a positive number!", "Alert", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		getContentPane().setLayout(null);
		getContentPane().add(pane);
		getContentPane().add(addButton);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(177, 163, 56, 16);
		getContentPane().add(lblQuantity);
		
		textField = new JTextField();
		textField.setBounds(164, 189, 83, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCancel.setBounds(272, 160, 130, 50);
		getContentPane().add(btnCancel);
	}

}
