package Controller.Student;

import DAO.TransactionsDAO;
import Model.Transactions;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to handle transaction history requests
 */
public class TransactionHistory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // Lấy user ID từ session
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("user");

        // Lấy các tham số lọc và phân trang từ request
        String action = request.getParameter("action");
        String transactionType = request.getParameter("transactionType");

        // Sử dụng TransactionsDAO để lấy dữ liệu
        TransactionsDAO transactionsDAO = new TransactionsDAO();
        List<Transactions> transactionList;

        // Thực hiện filter nếu action là "filter"
        if ("filter".equals(action)) {
            // Lọc theo transactionType
            transactionList = transactionsDAO.getFilteredTransactionsByUserId(
                    userId,
                    transactionType != null && !transactionType.trim().isEmpty() ? transactionType : null
            );
        } else {
            // Nếu không có action hoặc không phải filter, lấy toàn bộ dữ liệu
            transactionList = transactionsDAO.getTransactionsByUserId(userId);
        }

        // Pagination logic
        String pageRaw = request.getParameter("page");
        int page;
        try {
            page = Integer.parseInt(pageRaw);
            if (page <= 0) {
                page = 1;
            }
        } catch (NumberFormatException e) {
            page = 1; 
        }

        int recordsPerPage = Constant.RECORD_PER_PAGE; 
        int totalRecord = transactionList.size(); 
        int totalPage = (totalRecord % recordsPerPage == 0) ? (totalRecord / recordsPerPage) : (totalRecord / recordsPerPage) + 1;

        int start = (page - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalRecord);

        // Lấy danh sách phân trang
        List<Transactions> paginatedList = transactionList.subList(start, end);

        // Tính toán các trang để hiển thị
        int beginPage = Math.max(1, page - 2);
        int endPage = Math.min(totalPage, page + 2);

        // Đưa dữ liệu vào request để hiển thị trên JSP
        request.setAttribute("transactionList", paginatedList);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("beginPage", beginPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("transactionType", transactionType);

        // Chuyển hướng đến trang JSP
        request.getRequestDispatcher("/Student/transactionHistory.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles requests for displaying the transaction history.";
    }
}
