package supermarket.product.service;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.imageio.ImageIO;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import supermarket.product.model.Product;
import supermarket.util.*;

public class Service {
	private static final String NEW_PRODUCT = "INSERT INTO products(productName,price,description,image,category,barcode) VALUES(?,?,?,?,?,?)";
	private static final String SELECT_PRODUCTS = "SELECT * FROM products";
	private static final String SEARCH_PRODUCTS = "SELECT * FROM products WHERE productName = ?";
	private static final String DELETE_PRODUCT = "DELETE FROM products WHERE productID = ?";
	
	
	public static void newProduct(String productName,BigDecimal price,String description,InputStream image,String category) {
		Connection connection = DbUtil.getConnection();
		InputStream barcode = null;
		try {
			barcode = generateEAN13BarcodeImage(productName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(NEW_PRODUCT);
			preparedStatement.setString(1,productName);
			preparedStatement.setBigDecimal(2, price);
			preparedStatement.setString(3, description);
			if(image!=null) {
				preparedStatement.setBlob(4, image);
			}
			preparedStatement.setString(5, category);
			preparedStatement.setBlob(6, barcode);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static InputStream generateEAN13BarcodeImage(String barcodeText) throws Exception {
	    Barcode barcode = BarcodeFactory.createCode128(barcodeText);
	    BufferedImage bcImage = BarcodeImageHandler.getImage(barcode);
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(bcImage, "png", baos);
	    InputStream is = new ByteArrayInputStream(baos.toByteArray());
	    return is;
	}
	public static ArrayList<Product> selectProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				Product product = new Product();
				String name = result.getString("productName");
				BigDecimal price = result.getBigDecimal("price");
				String description = result.getString("description");
				String category = result.getString("category");
				int productID = result.getInt("productID");
				Blob image = result.getBlob("image");
				Blob barcode = result.getBlob("barcode");
                product.setCategory(category);
                product.setDescription(description);
                product.setImage(blobToBase64(image));
                product.setName(name);
                product.setPrice(price);
                product.setProductID(productID);
                product.setBarcode(blobToBase64(barcode));
                products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
		
	}
	private static String blobToBase64(Blob image) {
		InputStream inputStream = null;
		try {
			inputStream = image.getBinaryStream();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        try {
			while ((bytesRead = inputStream.read(buffer)) != -1) {
			    outputStream.write(buffer, 0, bytesRead);                  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        byte[] imageBytes = outputStream.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        try {
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return base64Image;
	}
	public static ArrayList<Product> searchProducts(String search){
		ArrayList<Product> products = new ArrayList<Product>();
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCTS);
			preparedStatement.setString(1, search);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Product product = new Product();
				String name = result.getString("productName");
				BigDecimal price = result.getBigDecimal("price");
				String description = result.getString("description");
				String category = result.getString("category");
				int productID = result.getInt("productID");
				Blob image = result.getBlob("image");
				InputStream inputStream = image.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                try {
					while ((bytesRead = inputStream.read(buffer)) != -1) {
					    outputStream.write(buffer, 0, bytesRead);                  
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
                 
                try {
					inputStream.close();
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                product.setCategory(category);
                product.setDescription(description);
                product.setImage(base64Image);
                product.setName(name);
                product.setPrice(price);
                product.setProductID(productID);
                products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	public static void deleteProduct(int productID) {
		Connection connection = DbUtil.getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
			preparedStatement.setInt(1, productID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
