<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="we45.training.labs.Product"%>
<%@page import="java.util.Iterator"%> 
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Products</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body class="container">
        <br>
        <div class="card">
            <h5 class="card-header text-white bg-info">
                Products
                <a class="btn btn-info float-right" href="/log4shell/create">Create</a>
            </h5>
            <div class="card-body">
                <h5 class="card-title"></h5>
                <table class="table">
                    <tr>
                        <form action="products" method="post">
                            <td colspan='2'>
                                <input type="text" class="form-control" name="name" placeHolder="Search by product name">	    
                            </td>
                            <td>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </td>
                        </form>
                    </tr>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Quantity</th>
                    </tr>
                    <%  
                      List<Product> list = (List) request.getAttribute("products");
                      for(Product product : list){
                    %>
                    <tr>
                        <td><%=product.getName()%></td>
                        <td><%=product.getDescription()%></td>
                        <td><%=product.getQuantity()%></td>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" ></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>