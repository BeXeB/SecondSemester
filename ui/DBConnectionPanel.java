package ui;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import database.DBTest;

import javax.swing.JLabel;
import java.awt.Color;

public class DBConnectionPanel extends JPanel{
	private DBTest dbTest;
	
	public DBConnectionPanel() {
		
		JLabel lblDBCon = new JLabel("");
		add(lblDBCon);
		dbTest = new DBTest();
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				while (true) {
					lblDBCon.setText("Testing");
					lblDBCon.setForeground(Color.BLACK);
					Thread.sleep(500);
					lblDBCon.setText(dbTest.doTest());
					lblDBCon.setForeground(Color.RED);
					Thread.sleep(3000);
				}
			}
		};	 
		worker.execute();
	}
}
