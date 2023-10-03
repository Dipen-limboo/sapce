<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="serv" enctype="multipart/form-data">
     <div>
      <label>User Name:</label>
      <input type="text" name="username" size="50" />
     </div>
     <div>
      <label>Profile Photo: </label>
      <input type="file" name="image" size="50" />
     </div>
     <input type="submit" value="Save">
    </form>
</body>
</html>