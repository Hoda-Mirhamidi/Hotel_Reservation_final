<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<h1><%= "WELCOME TO THE HOTEL ! PLEASE CHOOSE ONE OF THE OPTIONS BELOW : " %>
</h1>
<br/>
<form action="main" method="post">
    <input type="radio" id="reservation" name="options" value="reserve">
    <label for="reservation">Make a reservation</label><br>
    <input type="radio" id="modification" name="options" value="modify">
    <label for="modification">Modify your reservation info</label><br>
    <input type="radio" id="view" name="options" value="view">
    <label for="view">View your reservation info</label><br>
    <input type="radio" id="cancel" name="options" value="cancel">
    <label for="cancel">Cancel your reservation</label><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>