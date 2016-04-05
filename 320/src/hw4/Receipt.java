package hw4;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DataAccess;

/**
 * Servlet implementation class Receipt
 */
@WebServlet("/hw4/Receipt")
public class Receipt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Receipt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try
		{
			String ID = request.getParameter("ID");
			request.setAttribute("rentLog", DataAccess.getRentLog(Integer.parseInt(ID)));
			
			request.getRequestDispatcher("Receipt.jsp").include(request, response);
		}
		catch (Exception ex)
		{
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher("Receipt.jsp").include(request,
					response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}
