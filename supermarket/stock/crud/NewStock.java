package supermarket.stock.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supermarket.stock.service.*;

/**
 * Servlet implementation class NewStock
 */
@WebServlet("/NewStock")
public class NewStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewStock() {
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
		// TODO Auto-generated method stub
		String productName = request.getParameter("name");
		int productID = Integer.parseInt(request.getParameter("productID"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Service.insertStock(productName, productID, quantity);
		response.sendRedirect("selectproduct.jsp");
	}

}
