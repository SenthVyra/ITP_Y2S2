package supermarket.discount.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import supermarket.discount.service.Service;

/**
 * Servlet implementation class UpdateDiscount
 */
@WebServlet("/UpdateDiscount")
public class UpdateDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDiscount() {
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
		Service.updateDiscount(productID, quantity, discount);
		response.sendRedirect("discount.jsp");
	}

}
