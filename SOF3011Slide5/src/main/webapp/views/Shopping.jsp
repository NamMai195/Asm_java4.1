<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Shopping card</h1>
        Please choose your favourish:
        <form action="/SOF3011Slide5/ShoppingServletSession">
            <select name="cboCard">
                <option>Caffe</option>
                <option>Duong</option>
                <option>Tieu</option>
                <option>Ca</option>
                <option>Sua</option>
                <option>Kem</option>
                <option>Banh Mi</option>
            </select><br>
            <input type="submit" value="Add To Cart" name="action" />
            <input type="submit" value="View Cart" name="action" />
        </form>
    </body>
</html>
