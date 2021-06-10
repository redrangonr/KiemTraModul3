<%--
  Created by IntelliJ IDEA.
  User: cyber
  Date: 6/10/2021
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>Products list</title>
</head>
<body>

<div>
    <span style="text-align: left">
        <h1>Product list</h1>
    <button type="submit" style="background-color: blue  " ><a href="/products?action=create" style="color: white">+Add new Product</a></button>
    </span>
    <span style="text-align: right">
        <form >
        <input type="text" placeholder="Search" name="search">
        <button  style="background-color: aqua" type="submit">Search</button>
    </form>
    </span>

</div>
<div >
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#</th>
            <th style="text-align: left">Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Color</th>
            <th> Category</th>
            <th> Acition</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ProductList}" var="product">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.name}"/></td>
            <td><c:out value="${product.price}"/></td>
            <td><c:out value="${product.quantity}"/></td>
            <td><c:out value="${product.color}"/></td>
            <td><c:out value="${product.category}"/></td>
            <td>
                <button style="background: #0c63e4"><a href="/products?action=edit&id=${product.id}" style="color: white"> Edit</a></button>
                <button style="background: firebrick "><a href="/products?action=delete&id=${product.id}" style="color: white"> Delete</a></button>
            </td>
        </tr>
        </c:forEach>
        <tbody>
    </table>
</div>

</body>
</html>
