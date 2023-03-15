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


/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/UpdateProduct")
@MultipartConfig
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = request.getParameter("name");
		int productID = Integer.parseInt(request.getParameter("productID"));
		String price =  request.getParameter("price");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		InputStream inputStream = null;
        Part filePart = request.getPart("image");
        if(!(productName == null || productName.trim().isEmpty())) {
        	ProductUpdate.updateName(productID, productName);
        }
        if(!(price == null || price.trim().isEmpty())) {
        	ProductUpdate.updatePrice(productID, new BigDecimal(price));
        }
        if(!(description == null || description.trim().isEmpty())) {
        	ProductUpdate.updateDescription(productID, description);
        }
        if(!(category == null || category.trim().isEmpty())) {
        	ProductUpdate.updateCategory(productID, category);
        }
        if (filePart != null) {
            inputStream = filePart.getInputStream();
            if(filePart.getSize() != 0) {
            	ProductUpdate.updateImage(productID,inputStream);
            }
        }
        response.sendRedirect("product.jsp");
	}

}
