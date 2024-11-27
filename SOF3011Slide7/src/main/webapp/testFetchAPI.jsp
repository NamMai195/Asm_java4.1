<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<button onclick="uploadText()">Upload Text</button>
	<button onclick="uploadJson()">Upload Json</button>
	<script>
		function uploadText() {
			var url="http://localhost:8080/SOF3011Slide7/ajax/fetch/text";
			var options = {method:'GET'};
			fetch(url,options).then(resp => {
				console.log("------- TEXT ---------");
				console.log("ok",resp.ok);
				console.log("status",resp.status);
				resp.text().then(text => {
					console.log("data",text)
				})
			}) 
			
		}
		
		function uploadJson() {
			var url="http://localhost:8080/SOF3011Slide7/ajax/fetch/json";
			var options = {method:'GET'};
			fetch(url,options).then(resp => {
				console.log("------- JSON ---------");
				console.log("ok",resp.ok);
				console.log("status",resp.status);
				resp.json().then(text => {
					console.log("data",text)
				})
			}) 
			
		}
	</script>
</body>
</html>