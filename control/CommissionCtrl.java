package control;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import database.DBCommission;
import model.Client;
import model.Commission;
import model.CommissionLine;
import model.Discount;
import model.Material;
import model.SubCommission;
import model.Group;

public class CommissionCtrl {
	private DBCommission dbCommission;
	
	private GroupCtrl groupCtrl;
	private DiscountCtrl discountCtrl;
	private MaterialCtrl materialCtrl;
	private ClientCtrl clientCtrl;
	
	private Commission currentCommission;
	private SubCommission currentSubCommission;
	
	public CommissionCtrl() {
		dbCommission = new DBCommission();
		groupCtrl = new GroupCtrl();
		clientCtrl = new ClientCtrl();
		materialCtrl = new MaterialCtrl();
		discountCtrl = new DiscountCtrl();
	}
	
	public void createComission(String deadLine) 												//Create a commission and
	{																							//make it the current commission
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date currentDate = new Date();
		currentCommission = new Commission(formatter.format(currentDate), deadLine, null, -1);
		System.out.println(currentCommission.getDate() + "\nDeadline:\n\t" + currentCommission.getDeadLine());
	}
	
	public ArrayList<Group> getGroups() throws SQLException										//Gets all the groups in the database
	{
		return groupCtrl.findAll();
	}
	
	public void addGroup (int id) throws SQLException 											//Adds the selected group
	{																							//to the current commission
		currentCommission.addGroup(groupCtrl.findByNo(id));
	}
	
	public ArrayList<Client> getClients() throws SQLException									//Gets all the clients in the database
	{
		return clientCtrl.findAll();
	}
	
	public void addClient(int no) throws SQLException  											//Adds the selected client
	{																							//to the current commission
		currentCommission.setClient(clientCtrl.findByNo(no));
	}
	
	public ArrayList<Discount> getDiscount() throws SQLException								//Gets all the discount in the database
	{
		return discountCtrl.findAll();
	}
	
	public void addDiscount(int no) throws SQLException  										//Adds the selected discount
	{																							//to the current commission
		currentCommission.setDiscount(discountCtrl.findByNo(no));
	}
	
	public void createSubComission(String deadLine, String description) 						//Creates a sub commission and
	{																							//make it the current sub commission
		currentSubCommission = new SubCommission(description, deadLine);
	}
	
	public ArrayList<Material> getMaterials() throws SQLException								//Gets all the material in the database
	{
		return materialCtrl.findAll();
	}
	
	public void addMaterial(int no, int quantity) throws SQLException  							//Adds the selected material
	{																							//to the current sub commission
		currentSubCommission.addCl(new CommissionLine(materialCtrl.findByNo(no), quantity));
	}
	
	public void addSubCommission() 																//Adds the current sub commission to
	{																							//the current commission and
		currentCommission.addSc(currentSubCommission);											//updates the price
		currentCommission.calculatePrice();
		currentSubCommission = null;
	}
	
	public void saveToDataBase() throws Exception 												//Inserts the current commission 
	{																							//to the database
		dbCommission.insertIntoDataBase(currentCommission);
	}
	
	public Discount getSelectedDiscount() {
		return currentCommission.getDiscount();
	}
	
	public Client getSelectedClient() {
		return currentCommission.getClient();
	}
	
	public LinkedList<Group> getSelectedGroups() {
		return currentCommission.getGroups();
	}
	
	public LinkedList<CommissionLine> getCommissionLines() {
		return currentSubCommission.getCls();
	}
	
	public void reset() 
	{
		currentCommission = null;
		currentSubCommission = null;
	}
	
	public Commission getCommission() {
		return currentCommission;
	}
	
	public String toString() 
	{
		return currentCommission.toString();
	}
}
