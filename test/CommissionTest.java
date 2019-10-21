package test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import org.junit.runners.MethodSorters;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import model.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommissionTest {

	public static Commission c;
	@BeforeClass
	public static void setUp(){
		c = new Commission("2020-01-01 11:11:11", "2020-10-01 01:01:01", "2020-01-01 15:11:11", 10);
	}
	
	@Test
	public void acreateCommission() {
	assertTrue(c!=null);
	}
	
	@Test
	public void baddSubCommission() {
		Material cement = new Material("cement bag", "25 kg of quality cement", 47.95, 1);
		Material paint = new Material("wall paint", "5 liters of pink wall paint, quick drying", 34.95, 2);
		CommissionLine cl1 = new CommissionLine(cement, 1);
		CommissionLine cl2 = new CommissionLine(paint, 2);
		SubCommission sb1 = new SubCommission("repair a 10x8 meters wall by filling the holes in it with cement", "05/01/2001 01:01:01");
		SubCommission sb2 = new SubCommission("paint a 10x8 meters wall in pink quick drying wall paint", "10/01/2001 01:01:01");
		sb1.addCl(cl1);
		sb2.addCl(cl2);
		c.addSc(sb1);
		c.addSc(sb2);
		LinkedList<SubCommission> lsb = new LinkedList<SubCommission>();
		lsb=c.getScs();
		assertTrue(lsb.indexOf(sb1)==0 && lsb.indexOf(sb2)==1);
	}
	
	@Test
	public void caddGroup() {
		Employee e1 = new Employee("Rares Constantinescu", "Str Unirii", "Bucharest", 054376, "raresr@gmail.com", "+4072365387", true, 1);
		Employee e2 = new Employee("Bogdan  Georgescu", "Str Berceni", "Bucharest", 051344, "bogdanel@gmail.com", "+4078748723", false, 2);
		Group g = new Group("Construction team", 1);
		g.addEmployee(e1, 25);
		g.addEmployee(e2, 23);
		c.addGroup(g);
		LinkedList<Group> lg = new LinkedList<>();
		lg=c.getGroups();
		assertTrue(lg.indexOf(g)==0);	
	}
	
	@Test
	public void dsetClient() {
		Client client = new Client("Ana Grigorescu", "Str. Panselelor", "Bucharest", 042061, "anag@gmail.com", "+40723595536", true, 1); 
		assertTrue(c.setClient(client));
	}
	
	@Test 
	public void esetDiscount(){
		Discount d = new Discount(10, 1);
		assertTrue(c.setDiscount(d));
	}
	
	@Test
	public void fcheckPrice() {
		c.calculatePrice(); 
		System.out.println(c.toString());
		//System.out.println(c.getPrice());
		double price = 2106.065;
		assertTrue(price==c.getPrice());
	}

}
