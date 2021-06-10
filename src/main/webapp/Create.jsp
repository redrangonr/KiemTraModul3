<%--
  Created by IntelliJ IDEA.
  User: cyber
  Date: 6/10/2021
  Time: 12:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProductList</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<h1 align="left">Add New Product</h1>

<c:if test="${message!= null}">
    <p style="color: red"><c:out value="${message}"/></p>
</c:if>
<form method="post">

    <div class="mb-3">
        <label for="formGroupExampleInput" class="form-label">Name</label>
        <input type="text" class="form-control" name="name" id="formGroupExampleInput" placeholder="name">
    </div>
    <div class="mb-3">
        <label for="formGroupExampleInput2" class="form-label">Price</label>
        <input type="text" class="form-control" name="price" id="formGroupExampleInput2" placeholder="Price">
    </div>
    <div class="mb-3">
        <label for="formGroupExampleInput" class="form-label">Quantity</label>
        <input type="text" class="form-control" name="quantity" id="formGroupExampleInput3" placeholder="Quantity">
    </div>
    <div class="mb-3">
        <label for="formGroupExampleInput" class="form-label">Color</label>
        <input type="text" class="form-control" name="color" id="formGroupExampleInput4" placeholder="Color">
    </div>
    <div class="mb-3">
        <label for="formGroupExampleInput" class="form-label">Category</label>
        <input type="text" class="form-control" name="category" id="formGroupExampleInput5" placeholder="Category">
    </div>
    <div align="left">
        <button>Create</button>
        <button href="/product"> Back</button>
    </div>

</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>