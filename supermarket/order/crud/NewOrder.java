package supermarket.order.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supermarket.order.service.*;

/**
 * Servlet implementation class NewOrder
 */
@WebServlet("/NewOrder")
public class NewOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product = request.getParameter("product");
		System.out.println(product);
		String distributor = request.getParameter("distributor");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String productName = product.split("_")[1];
		int productID = Integer.parseInt(product.split("_")[0]);
		String distributorName = distributor.split("_")[0];
		int distributorID = Integer.parseInt(distributor.split("_")[1]);
		Service.newOrder(productID, productName, quantity, distributorID, "Pending", distributorName);
		response.sendRedirect("order.jsp");
	}

}
