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
import javax.swing.JScrollPane;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Rectangle;

public class AddGroupFrame extends JDialog{

	private JList list;
	private ArrayList<Group> gr;
	private int index;
	
	public AddGroupFrame(CommissionCtrl ct) throws SQLException {
		setModal(true);
		setMinimumSize(new Dimension(450, 270));
		DefaultListModel model = new DefaultListModel();
		gr = new ArrayList<Group>();
		gr = ct.getGroups();
		for (Group group : gr) {
			model.addElement(group.getDescription() + " - " + group.getGroupNo());
		}

		list = new JList(model);
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JScrollPane pane = new JScrollPane(list);
		pane.setBounds(new Rectangle(0, 0, 432, 130));
		JButton addButton = new JButton("Add");
		addButton.setBounds(new Rectangle(12, 161, 130, 50));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = list.getSelectedIndex();
				try {
					ct.addGroup(gr.get(index).getGroupNo());
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
		
		JButton button_1 = new JButton("CANCEL");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button_1.setBounds(272, 160, 130, 50);
		getContentPane().add(button_1);
	}
}
