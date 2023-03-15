package supermarket.purchase.function;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import supermarket.purchase.model.Purchase;
import supermarket.purchase.service.Service;

/**
 * Servlet implementation class SearchPurchase
 */
@WebServlet("/SearchPurchase")
public class SearchPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPurchase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		Purchase searchResult = Service.getPurchaseByID(orderID);
		request.setAttribute("searchPurchase", searchResult);
		RequestDispatcher rd = request.getRequestDispatcher("purchase.jsp");
		rd.forward(request, response);
	}

}
