package controller;

import dao.ProductDAO;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createNewProduct(request, response);
                break;
            case "edit":
                showEditProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                showListProduct(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
        }
    }
    private void createNewProduct(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        RequestDispatcher dispatcher = request .getRequestDispatcher("/Create.jsp");
        dispatcher.forward(request,response);
    }
    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int Quantity = Integer.parseInt(request.getParameter("quantity"));
        String Color = request.getParameter("color");
        String Category  = request.getParameter("category");
        double Price =Double.parseDouble(request.getParameter("price"));

        Product product = new Product(name,Price,Quantity,Color,Category);
        boolean isInserted = productDAO.add(product);
        if (!isInserted) {
            request.setAttribute("message","Error!");
        }else {
            request.setAttribute("message", "Successful!");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Create.jsp");
        dispatcher.forward(request, response);
    }
    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int Quantity = Integer.parseInt(request.getParameter("quantity"));
        String Color = request.getParameter("color");
        String Category  = request.getParameter("category");
        double Price =Double.parseDouble(request.getParameter("price"));

        Product product = new Product(id, name,Price,Quantity,Color,Category);
        boolean isInserted= productDAO.update(id, product);
        if(!isInserted){
            request.setAttribute("message","Error!");
        }else {
            request.setAttribute("message","Successful");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Edit.jsp");
        dispatcher.forward(request, response);
    }
    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.findById(id);
        if (product == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error-404.jsp");
            dispatcher.forward(request, response);
        }
        productDAO.remove(id);
        response.sendRedirect("/products");
    }
    private void showEditProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Edit.jsp");
        if (product == null) {
            dispatcher = request.getRequestDispatcher("/error-404.jsp");
        }
        request.setAttribute("product", product);
        dispatcher.forward(request, response);
    }

    private void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("Search");
        List<Product> products;
        if (query == null || query.equals("")) {
            products = productDAO.findAll();
        } else {
            products = productDAO.findName("query");
        }
            request.setAttribute("ProductList", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/List.jsp");
            dispatcher.forward(request, response);
        }

}
