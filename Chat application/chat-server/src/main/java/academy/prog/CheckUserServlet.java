package academy.prog;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkUser")
public class CheckUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");

        if (login == null || login.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        boolean exists = MessageList.getInstance().isUserRegistered(login);

        if (exists) {
            resp.setContentType("text/plain");
            PrintWriter pw = resp.getWriter();
            pw.println("true");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}