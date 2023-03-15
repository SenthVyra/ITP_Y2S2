package supermarket.order.function;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import supermarket.order.model.Order;
import supermarket.order.service.Service;

/**
 * Servlet implementation class SearchOrder
 */
@WebServlet("/SearchOrder")
public class SearchOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("orderID"));
		Order searchResult = Service.getOrderByID(id);
		request.setAttribute("searchOrder", searchResult);
		RequestDispatcher rd = request.getRequestDispatcher("order.jsp");
		rd.forward(request, response);
	}

}
