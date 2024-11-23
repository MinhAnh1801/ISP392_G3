package Filter;

import DAO.ScheduleDAO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

public class StatusUpdateFilter implements Filter {

    private final ScheduleDAO scheduleDAO = new ScheduleDAO();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Update expired schedules before continuing the request
        scheduleDAO.updateExpiredSchedules();
        scheduleDAO.updateFullSchedules();
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
