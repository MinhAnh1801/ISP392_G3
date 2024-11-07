package Controller.Student;

import DAO.ProfileDAO;
import DAO.TransactionsDAO;
import Model.Student_Profile;
import Model.Transactions;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddMoney", urlPatterns = {"/addMoney"})
public class AddMoney extends HttpServlet {

    private ProfileDAO profileDAO = new ProfileDAO();
    private TransactionsDAO transactionsDAO = new TransactionsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Student/addMoney.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get session and retrieve user ID
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("user");

        // Get the amount from the request
        int amountToAdd;
        try {
            amountToAdd = Integer.parseInt(request.getParameter("amount"));
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "Invalid amount entered.");
            response.sendRedirect("addMoney.jsp");
            return;
        }

        // Validate amount within limits
        if (amountToAdd < 5000 || amountToAdd > 500000000) {
            session.setAttribute("errorMessage", "Amount must be between 5,000 VND and 500,000,000 VND.");
            response.sendRedirect("addMoney.jsp");
            return;
        }

        try {
            // Get student profile
            Student_Profile studentProfile = profileDAO.getStudentProfileByUserId(userId);

            if (studentProfile != null) {
                // Update wallet balance
                int updatedBalance = studentProfile.getWallet() + amountToAdd;
                studentProfile.setWallet(updatedBalance);
                profileDAO.updateStudentProfile(studentProfile);

                // Log transaction
                Transactions transaction = new Transactions(
                        0, // Transaction ID auto-generated
                        userId,
                        amountToAdd,
                        "Add Money",
                        "Added " + amountToAdd + " VND to wallet",
                        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())
                );
                transactionsDAO.insertTransaction(transaction);

                // Success message
                session.setAttribute("successMessage", "Money added successfully to your wallet!");
            } else {
                session.setAttribute("errorMessage", "Student profile not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Database error occurred while adding money.");
        }

        // Redirect back to the add money page with messages
        response.sendRedirect("addMoney");
    }
}
