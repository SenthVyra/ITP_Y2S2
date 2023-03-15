package supermarket.payment.function;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import supermarket.payment.model.Payment;
import supermarket.payment.service.Service;

/**
 * Servlet implementation class SearchPayment
 */
@WebServlet("/SearchPayment")
public class SearchPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		ArrayList<Payment> searchResult = Service.getPaymentByEmployeeName(search);
		request.setAttribute("searchPayment", searchResult);
		RequestDispatcher rd = request.getRequestDispatcher("payments.jsp");
		rd.forward(request, response);
	}

}
