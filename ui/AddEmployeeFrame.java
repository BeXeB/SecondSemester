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

public class AddEmployeeFrame extends JDialog{

	private JList list;
	private GroupCtrl gct;
	private ArrayList<Employee> em;
	private int index;
	private MainFrame mainFrame;
	private InputValidationCtrl v;
	
	public AddEmployeeFrame(GroupCtrl gct) throws SQLException {
		v = new InputValidationCtrl();
		
		setModal(true);
		setMinimumSize(new Dimension(450, 270));
		DefaultListModel model = new DefaultListModel();
		//mainFrame = new MainFrame(gct);
		em = new ArrayList<Employee>();
		this.gct = gct;
		em = this.gct.getEmployees();
		for (Employee employee : em) {
			model.addElement(employee.getName() + " - " + employee.getEmpNo());
		}
		
		list = new JList(model);
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JScrollPane pane = new JScrollPane(list);
		pane.setBounds(0, 0, 430, 150);
		JButton addButton = new JButton("Add");
		addButton.setBounds(12, 161, 130, 50);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = list.getSelectedIndex();
				try {
					gct.addEmployee(em.get(index).getEmpNo(), 0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				dispose();
			}
		});
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		getContentPane().setLayout(null);
		getContentPane().add(pane);
		getContentPane().add(addButton);
		
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

