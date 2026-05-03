package bank;
import java.util.*;
import java.sql.*;
class DBConnection{
	Connection getConnection() {
	Connection con=null;      
    try {
    	Class.forName("org.sqlite.JDBC"); 
        con=DriverManager.getConnection("jdbc:sqlite:C:\\sql\\cus_record.db");
        System.out.println("Opened database successfully"); 
    }catch(Exception e) {
    	System.out.println(e.getMessage());
    }
    return con;
}}
class create extends DBConnection {
    public void createaccount(int customer_id, String name,String address,String city,int pincode,String phoneno,double initial_deposite,double balance) {
        Connection con = null;
        Statement smt = null;           
        try {
            con.setAutoCommit(true);
            System.out.println("Opened database successfully for creating account");
            smt=con.createStatement(); 
            String query="insert into cus_details values(?,?,?,?,?,?,?,?)";
            PreparedStatement presmt=con.prepareStatement(query);
            presmt.setInt(1,customer_id);
            presmt.setString(2,name);
            presmt.setString(3,address);
            presmt.setString(4,city);
            presmt.setInt(5,pincode);
            presmt.setString(6,phoneno);
            presmt.setDouble(7,initial_deposite);
            presmt.setDouble(8,balance);
            presmt.executeUpdate();
            presmt.close();
            smt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("successfully created account with initial amount");
    }
    class deposite extends DBConnection{
    	public void depositeAmount(int customer_id,double amount) {
    		Connection con=null; 
    		Statement smt=null;
    		try {
    			con=getConnection();
                con.setAutoCommit(true);
                System.out.println("Opened database successfully for deposite");
                smt = con.createStatement(); 
                String query = "update cus_details set balance=balance+? where customer_id=?";
                PreparedStatement presmt = con.prepareStatement(query);
                Scanner sc=new Scanner(System.in);
                System.out.println("enter customer id no: ");
                int customer_id1=sc.nextInt();
                System.out.println("enter amount: ");
                double amount1=sc.nextDouble();
                presmt.setDouble(1,amount1);
                presmt.setInt(2, customer_id1);
                presmt.executeUpdate();
                presmt.close();
                smt.close();
                con.close();
                
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    		System.out.println("successfully deposited amount to account.");
    		}
    	}
    class withdraw extends DBConnection{
    	public void withdrawAmount(int customer_id,double amount) {
    		Connection con=null;
            Statement smt=null; 
    		try {
    			con=getConnection();
                con.setAutoCommit(true);
                System.out.println("Opened database successfully for withdrawing amount");
                smt=con.createStatement(); 
                String query="SELECT balance FROM cus_details WHERE customer_id=?";
                PreparedStatement presmt=con.prepareStatement(query);
                Scanner sc=new Scanner(System.in);
                System.out.println("enter customer id no: ");
                int customer_id2=sc.nextInt();
                presmt.setInt(1,customer_id2);
                ResultSet rs=presmt.executeQuery();
                if(rs.next()) {
                	double balance=rs.getDouble("balance");
                if(balance>=amount) {
                	String query1="UPDATE cus_details SET balance=balance-? WHERE customer_id=?";
                	PreparedStatement presmt1=con.prepareStatement(query1);
                	Scanner s=new Scanner(System.in);
                    System.out.println("enter customer id no: ");
                    int customer_id3=sc.nextInt();
                    System.out.println("enter amount: ");
                    double amount2=sc.nextDouble();
                    presmt1.setDouble(1,amount2);
                    presmt1.setDouble(2,customer_id3);
                    presmt1.executeUpdate();
                    presmt1.close();
                    smt.close();
                    
                    System.out.println("Withdrawal successful!");
                } else {
                    System.out.println("Insufficient balance!");
                }

                } else {
                System.out.println("Account not found!");
                }
                rs.close();
                presmt.close();
                con.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }        
    	}
    	}
    class CheckBalance extends DBConnection {
        public void checkBalance(int customer_id) {
            Connection con=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            try {
                con=getConnection();
                if (con!=null) {
                    String query="SELECT name,balance FROM cus_details WHERE customer_id=?";
                    ps=con.prepareStatement(query);
                    Scanner s=new Scanner(System.in);
                    System.out.println("enter customer id no: ");
                    int customer_id4=s.nextInt();
                    ps.setInt(1,customer_id4);
                    rs=ps.executeQuery();
                    if (rs.next()) {
                        System.out.println("Customer Name: " + rs.getString("name"));
                        System.out.println("Balance: " + rs.getDouble("balance"));
                    } else {
                        System.out.println("Account not found!");
                    }
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                try {
                    if(rs!=null) rs.close();
                    if(ps!=null) ps.close();
                    if(con!=null) con.close();
                } catch (Exception e) {
                    System.out.println("Closing error: " + e.getMessage());
                }
            }
}}}
public class bankins{
    public void insert(int customer_id,String name,String address,String city,int pincode,String phoneno,double initial_deposite,double balance) {
        create x = new create();
        x.createaccount(customer_id, name,address,city,pincode,phoneno,initial_deposite,balance);
    }
    public void deposit(int customer_id,double amount) {
        create c = new create();
        create.deposite d = c.new deposite();
        d.depositeAmount(customer_id,amount);
    }
 
    public void withdraw(int customer_id,double amount) {
        create c = new create();
        create.withdraw w = c.new withdraw();
        w.withdrawAmount(customer_id,amount);
    }
 
    public void checkBalance(int customer_id) {
        create c = new create();
        create.CheckBalance cb = c.new CheckBalance();
        cb.checkBalance(customer_id);
    }
    
}