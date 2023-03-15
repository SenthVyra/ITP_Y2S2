package supermarket.discount.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supermarket.discount.service.*;

/**
 * Servlet implementation class NewDiscount
 */
@WebServlet("/NewDiscount")
public class NewDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewDiscount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productID = Integer.parseInt(request.getParameter("prodID"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int discount = Integer.parseInt(request.getParameter("discount"));
		String productName = request.getParameter("prodName");
		Service.newDiscount(productID, quantity, discount, productName);
		response.sendRedirect("selectproductdiscount.jsp");
	}

}
