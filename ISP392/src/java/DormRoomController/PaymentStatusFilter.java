package DormRoomController;

import DAL.DormResidentDAO;
import Model.DormResident;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;

public class PaymentStatusFilter implements Filter {

    private DormResidentDAO dormResidentDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        dormResidentDAO = new DormResidentDAO();
    }

@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    int userId = 2; // Lấy userId từ session hoặc request
    String paymentType = "Fee";

    // Kiểm tra trạng thái thanh toán
    if (dormResidentDAO.checkPaymentStatus(userId, paymentType)) {
        // Nếu thanh toán hoàn tất, di chuyển dữ liệu từ dorm_temp sang dorm_residents
        dormResidentDAO.transferDormTempToDormResident(userId);
        System.out.println("Data moved from dorm_temp to dorm_residents for user: " + userId);
    }

    chain.doFilter(request, response);
}





    @Override
    public void destroy() {
    }
}
