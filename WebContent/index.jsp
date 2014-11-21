
<%@page import="Service.ServiceImages"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Upload</title>
<script src="js/inicioFb.js"></script>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/main.css">
</head>

<body>
	<script type="text/javascript">
		cargarFbSDK();
		iniciarFb();
	</script>
	<div class="container">
		<div class="row">
			<h3 class="col-xs-6" >Choose File to Upload in Server</h3>
			<input class="col-xs-4" type="button" value="Iniciar con FB" onclick="loginFB()">
		</div>
		<form action="upload" method="post" name="uploadForm"
			enctype="multipart/form-data">
			<input type="file" name="uploadfile" /> <input name="submit"
				type="submit" value="upload" size="100"/>
		</form>
		<ul class="imagesList"></ul>
	</div>
	<ul class="imagenes">
		<%
			String li ="<li>";
			String endLi ="</li>";
			ServiceImages si = new ServiceImages();
			List <String> imag=si.retriveImages();
			for(String img: imag){
				img = "<img src=\""+img+"\">";
				out.write(li+img+endLi+"\n");
			}
		 %>
	</ul>
</body>
</html>