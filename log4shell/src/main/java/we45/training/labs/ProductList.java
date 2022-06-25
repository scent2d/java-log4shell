package we45.training.labs;

import java.io.*;  
import java.sql.*;  
import java.util.*;
import javax.servlet.ServletException;  
import javax.servlet.http.*; 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ProductList extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public Connection getConnection() {
		String dbDriver = "com.mysql.jdbc.Driver"; 
        String dbURL = System.getenv("JDBC_URL");

        String dbUsername = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");
        
        try {
	        Class.forName(dbDriver); 
	        Connection con = DriverManager.getConnection(dbURL, dbUsername,  dbPassword);
	        return con;
        }
        catch (Exception e) {
        	System.out.println(e);
        	e.printStackTrace();
        	return null;
        }	
	}
	
	public List<Product> processProducts(ResultSet rs) {
		List<Product> products = new ArrayList<Product>();
		try {
			while (rs.next ()){
	        	Product u = new Product();
	        	u.setName(rs.getString("name"));
	        	u.setDescription(rs.getString("description"));
	        	u.setQuantity(rs.getInt("quantity"));
	        	products.add(u);
	        }
		}
		catch (Exception e) {        	
        	e.printStackTrace();        	
        }
		return products;
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		List<Product> products = new ArrayList<Product>();
		
		try {
			Connection con = getConnection();
			if (con != null) {
				Statement stmt = con.createStatement();
		        String sql;
		        sql = "SELECT * FROM products";
		        ResultSet rs = stmt.executeQuery(sql);
		        products = processProducts(rs);
		        rs.close ();
		        stmt.close ();
			}
		}
		catch (Exception e) {        	
        	e.printStackTrace();        	
        }	
        
        request.setAttribute("products", products);
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		 
      	List<Product> products = new ArrayList<Product>();
      	try {
			Connection con = getConnection();
			if (con != null) {
				Statement stmt = con.createStatement();
      
				String name = request.getParameter("name"); 

				Logger logger = LogManager.getLogger(we45.training.labs.log4j.class);
		        logger.error(request.getParameter("name"));        


				String sql = "select * from products where name = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,name.toString()); 
				ResultSet rs = ps.executeQuery();
       
				products = processProducts(rs);
			    rs.close ();
			    stmt.close ();
			}
      	}
      	catch (Exception e) {
    	  	e.printStackTrace();
      	}
      	request.setAttribute("products", products);
      	request.getRequestDispatcher("list.jsp").forward(request, response);
	}
}
