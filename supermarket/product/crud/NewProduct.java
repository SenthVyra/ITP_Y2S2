package supermarket.product.crud;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import supermarket.product.service.ProductUpdate;
import supermarket.product.service.Service;

/**
 * Servlet implementation class NewProduct
 */
@WebServlet("/NewProduct")
@MultipartConfig
public class NewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = request.getParameter("name");
		BigDecimal price =  new BigDecimal(request.getParameter("price"));
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		InputStream inputStream = null;
        Part filePart = request.getPart("image");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }
        Service.newProduct(productName, price, description, inputStream, category);
        response.sendRedirect("product.jsp");
}
}
