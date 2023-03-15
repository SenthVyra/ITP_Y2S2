package supermarket.purchase.crud;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supermarket.purchase.service.*;

/**
 * Servlet implementation class NewPurchase
 */
@WebServlet("/NewPurchase")
public class NewPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPurchase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		int distID = Integer.parseInt(request.getParameter("distID"));
		String distName = request.getParameter("distName");
		BigDecimal amount = new BigDecimal(request.getParameter("amount"));
		Service.newPurchase(distID,orderID,amount,distName);
		response.sendRedirect("recievedorders.jsp");
	}

}
