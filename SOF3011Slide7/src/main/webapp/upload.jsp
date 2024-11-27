<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<input id="photo" type="file">
	<button onclick="upload()">Upload</button>
	<script>
		function upload() {
			var input = document.getElementById("photo");
			var formData = new FormData();
			formData.append('photo',input.files[0]);
			var url="http://localhost:8080/SOF3011Slide7/ajax/fetch/upload";
			var options = {method:'POST', body:formData}
			fetch(url,options).then(resp => resp.json()).then(json =>{
				console.log("success",json);
			});
		}
	</script>
</body>
</html>