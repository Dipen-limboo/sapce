<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "Controller.User" %>
    <%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Image Display</title>
</head>
<body>
	
    <table border= 1>
    <tr>
    <th>User Id</th>
    <th>User Name</th>
    <th>Image</th>
    </tr>
    <% 
    ArrayList <User> userlist = (ArrayList <User>) request.getAttribute("userList");
    %>
    <%
    if(userlist != null ){
    	for (User user: userlist){
    %>
    <tr>
    <td><%= user.getId() %></td>
    <td> <%= user.getUsername() %></td>
    <td> <img src="<%= user.getImage() %>"></td> 
    </tr>
    <% } 
    } else {%>
	<p>No image</p>
	<% } %>
    </table>

</body>
</html>
