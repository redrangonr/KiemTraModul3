package dao;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static final String SELECT_ALL_PRODUCT = "select * from productlist;";
    public static final String SELECT_PRODUCT_BY_ID = "select * from productlist where id = ?;";
    public static final String INSERT_PRODUCT = "insert into productlist( ProductName, Price, Quantity, Color, Category) values (?,?,?,?,?);";
    public static final String UPDATE_PRODUCT_BY_ID = "update productlist set ProductName =? ,Price = ?,Quantity = ?,Color = ?, Category =? where id =?;";
    public static final String DELETE_PRODUCT = "delete from productlist where id =?;";
    public static final String FINL_PRODUCT_BY_NAME ="SELECT * from productlist where ProductName like ? ";


public List<Product> findAll(){
    List<Product> list = new ArrayList<>();
    Connection connection = SQLConnection.getConnection();
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String productName = resultSet.getString("ProductName");
            float price = resultSet.getFloat("Price");
            int quantity = resultSet.getInt("Quantity");
            String color = resultSet.getString("Color");
            String category = resultSet.getString("Category");
            list.add(new Product( id,  productName,  price,  quantity, color,  category));

        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return list;
}
public Product findById(int id){
    Product product =new Product();
    Connection connection = SQLConnection.getConnection();
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString("ProductName");
            double price = resultSet.getDouble("Price");
            int quantity = resultSet.getInt("Quantity");
            String color = resultSet.getString("Color");
            String category = resultSet.getString("Category");
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setColor(color);
            product.setCategory(category);

        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return product;
}
public boolean add(Product product){
    Connection connection = SQLConnection.getConnection();
    int rowInserted =0;
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
        preparedStatement.setString(1,product.getName());
        preparedStatement.setDouble(2,product.getPrice());
        preparedStatement.setInt(3,product.getQuantity());
        preparedStatement.setString(4, product.getColor());
        preparedStatement.setString(5, product.getCategory());
        rowInserted = preparedStatement.executeUpdate();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return rowInserted !=0;
}
public boolean update(int id , Product product){
    Connection connection = SQLConnection.getConnection();
    int rowUpdate =0;
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_BY_ID);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, product.getName());
        preparedStatement.setDouble(3, product.getPrice());
        preparedStatement.setInt(4, product.getQuantity());
        preparedStatement.setString(5, product.getColor());
        preparedStatement.setString(6, product.getCategory());
        rowUpdate = preparedStatement.executeUpdate();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return rowUpdate !=0;
}
    public boolean remove(int id) {
        Connection connection = SQLConnection.getConnection();
        int rowDeleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDeleted !=0;
    }
    public List<Product> findName(String string){
    List<Product> products =new ArrayList<>();
    Connection connection =SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(FINL_PRODUCT_BY_NAME);
            preparedStatement.setString(1,"%"+string+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("ProductName");
                double price = resultSet.getDouble("Price");
                int quantity = resultSet.getInt("Quantity");
                String color = resultSet.getString("Color");
                String category = resultSet.getString("Category");
                products.add(new Product(id,name,price,quantity,color,category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }
}
