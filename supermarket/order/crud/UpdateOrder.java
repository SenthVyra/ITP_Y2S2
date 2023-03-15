package supermarket.order.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supermarket.order.service.*;
/**
 * Servlet implementation class UpdateOrder
 */
@WebServlet("/UpdateOrder")
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		int qunatity = Integer.parseInt(request.getParameter("quantity"));
		String status = request.getParameter("status");
		Service.updateOrder(orderID, qunatity, status);
		response.sendRedirect("order.jsp");
	}

}
