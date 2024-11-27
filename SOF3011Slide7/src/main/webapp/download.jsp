<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<button onclick="download()">Download</button>
	<script>
		function download(){
			var url ="http://localhost:8080/SOF3011Slide7/ajax/fetch/download";
			fetch(url).then(resp => resp.blob()).then(blob => {
				var blobUrl = URL.createObjectURL(blob);//Tao URL tu blob
				saveAs(blobUrl,"data.txt");//Luu xuong file data.txt
				URL.revokeObjectURL(blobUrl);//Xoa URL da tao o tren
			})
		}
		function saveAs(url, filename) {
			var a = document.createElement("a");//Tao <a></a>
			a.href=url;// <a href=@url></a>
			a.download = filename;//<a href=@url download=@filename>
			document.body.appendChild(a);// bo sung <a> vao <body>
			a.click();//phat sinh su kien click vao <a>
			document.body.removeChild(a);//Xoa <a> khoi <body>
		}
	</script>
</body>
</html>