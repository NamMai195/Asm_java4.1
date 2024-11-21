<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách video yêu thích</title>
</head>
<body>
    <h1>Tìm kiếm video yêu thích</h1>
    
    <!-- Form tìm kiếm -->
    <form action="searchFavorites" method="post">
        <label for="searchQuery">Tìm kiếm theo tên, email hoặc ID:</label>
        <input type="text" name="searchQuery" id="searchQuery" placeholder="Nhập tên, email, hoặc ID">
        <button type="submit">Tìm kiếm</button>
    </form>
    
    <h2>Kết quả tìm kiếm:</h2>
    
    <table border="1">
        <thead>
            <tr>
                <th>Video Title</th>
                <th>Người thích</th>
                <th>Ngày thích</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="favorite" items="${favorites}">
                <tr>
                    <td>${favorite.video.title}</td>
                    <td>${favorite.user.fullname}</td>
                    <td>${favorite.dateLiked}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
