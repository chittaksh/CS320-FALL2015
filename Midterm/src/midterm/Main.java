package midterm;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class Main
 */
@WebServlet("/midterm/Main")
public class Main extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException
	{
		// TODO Auto-generated method stub
		super.init(config);

		// Create Basic arrays
		ArrayList<User> arrUser = new ArrayList<User>();
		ArrayList<Rebate> arrRebate = new ArrayList<Rebate>();

		// Create and Add users
		User U1 = new User(arrUser.size() + 1, "Me");
		arrUser.add(U1);
		arrUser.add(new User(arrUser.size() + 1, "Chittaksh"));

		// Create and Add couple of Rebates
		arrRebate.add(new Rebate(arrRebate.size() + 1, U1, "Amazon", 20));
		arrRebate.add(new Rebate(arrRebate.size() + 1, U1, "EBay", 100));

		// Save to details to application context.
		getServletContext().setAttribute("arrUser", arrUser);
		getServletContext().setAttribute("arrRebate", arrRebate);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();	
		session.setAttribute("SelectedUser", 1);
		response.sendRedirect("Main.jsp");
		//request.getRequestDispatcher("Main.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		HttpSession session = request.getSession();	
		int intUser = (int)session.getAttribute("SelectedUser");
		
		if (request.getParameter("Add") != null)
		{
			
			String strRebateName = request.getParameter("txtName");
			int intValue = Integer.parseInt(request.getParameter("txtValue"));

			ArrayList<Rebate> arrRebate = (ArrayList<Rebate>) getServletContext()
					.getAttribute("arrRebate");
			ArrayList<User> arrUser = (ArrayList<User>) getServletContext()
					.getAttribute("arrUser");
			for (User U1 : arrUser)
			{
				if (U1.getId() == intUser)
				{
					arrRebate.add(new Rebate(arrRebate.size() + 1, U1,
							strRebateName, intValue));
					break;
				}
			}
		}else{
			 intUser = Integer
					.parseInt(request.getParameter("currUser").trim());
			session.setAttribute("SelectedUser", intUser);
		}
		
		//response.sendRedirect("Main.jsp");
		request.getRequestDispatcher("Main.jsp").include(request, response);

	}

}
