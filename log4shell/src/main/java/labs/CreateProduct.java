package labs;

import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*; 	 

public class CreateProduct extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		request.getRequestDispatcher("create.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
		String name = request.getParameter("name");  
		String description = request.getParameter("description");  
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));  
		String dbDriver = "com.mysql.jdbc.Driver"; 
        String dbURL = System.getenv("JDBC_URL");

        String dbUsername = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");
  
        try {
        	Class.forName(dbDriver); 
        	Connection con = DriverManager.getConnection(dbURL, dbUsername,  dbPassword);
			String query = "insert into products(name,description,quantity) values(?,?,?)";
        	PreparedStatement ps = con.prepareStatement(query);    
			ps.setString(1,name);  
			ps.setString(2,description);  
			ps.setInt(3,quantity);    
						
			int i = ps.executeUpdate();  
			if(i > 0) {
				System.out.println("Data has been saved successfully");
				response.sendRedirect("/log4shell/products");
			}
			else {
				response.sendRedirect("/log4shell/products");
			}		
        }
        catch (Exception e2) {
        	System.out.println(e2);
        	response.sendRedirect("/log4shell/products");
        }
	}
}
