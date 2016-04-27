package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbhelpers.CustomerHelper;
import model.Customer;
import utilities.PasswordService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Controller to handle Customer login", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session; 
	private String url;
	private int loginAttempts;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// user clicks log out link
		
		session = request.getSession();
		
		//check to make sure we've clicked link
				if(request.getParameter("logout") !=null){

					//logout and redirect to frontpage
					logout();
					url="index.jsp";
				}
				//forward our request along
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * Process POST requests/responses (login)
	 */
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get our current session
				session = request.getSession();

				//get the number of logins
				if(session.getAttribute("loginAttempts") == null){
					loginAttempts = 0;
				}
				
				//exceeded logins
				if(loginAttempts > 3){
					String errorMessage = "Error: Number of Login Attempts Exceeded";
					request.setAttribute("errorMessage", errorMessage);
					url = "index.jsp";
				}else{	//proceed
					//pull the fields from the form
					String username = request.getParameter("username");
					String password = request.getParameter("password");

					//encrypt the password to check against what's stored in DB
					PasswordService pws = new PasswordService();
					String encryptedPass = pws.encrypt(password);
					
					//create a user helper class to make database calls, and call authenticate user method
					CustomerHelper uh = new CustomerHelper();
					Customer customer = uh.authenticateCustomer(username, encryptedPass);

					//we've found a user that matches the credentials
					if(customer != null){
						//invalidate current session, then get new session for our user (combats: session hijacking)
						session.invalidate();
						session=request.getSession(true);
						session.setAttribute("customer", customer);
						url="index.jsp";
					}
					// user doesn't exist, redirect to previous page and show error
					else{
						String errorMessage = "Error: Unrecognized Username or Password<br>Login attempts remaining: "+(3-(loginAttempts));
						request.setAttribute("errorMessage", errorMessage);

						//track login attempts (combats: brute force attacks)
						session.setAttribute("loginAttempts", loginAttempts++);
						url = "index.jsp";
					}
				}
				//forward our request along
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}

	

	
	private void logout() {
	session.invalidate();
		
	}
}
