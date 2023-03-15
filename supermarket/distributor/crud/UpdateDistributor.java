package supermarket.distributor.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import supermarket.distributor.service.Service;

/**
 * Servlet implementation class UpdateDistributor
 */
@WebServlet("/UpdateDistributor")
public class UpdateDistributor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDistributor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String address =  request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		int id = Integer.parseInt(request.getParameter("distID"));
        Service.updateDistributor(name, address, phoneNumber, email, id);
        response.sendRedirect("distributor.jsp");
	}

}
