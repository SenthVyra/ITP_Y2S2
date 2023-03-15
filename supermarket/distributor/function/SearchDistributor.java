package supermarket.distributor.function;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import supermarket.distributor.model.Distributor;
import supermarket.distributor.service.Service;

/**
 * Servlet implementation class SearchDistributor
 */
@WebServlet("/SearchDistributor")
public class SearchDistributor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDistributor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		ArrayList<Distributor> searchResult = Service.getDistributorByName(search);
		request.setAttribute("searchDistributor", searchResult);
		RequestDispatcher rd = request.getRequestDispatcher("distributor.jsp");
		rd.forward(request, response);
	}

}
