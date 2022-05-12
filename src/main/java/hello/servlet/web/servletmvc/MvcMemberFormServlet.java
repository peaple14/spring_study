package hello.servlet.web.servletmvc;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//컨트롤러(mvc의 c)
@WebServlet(name = "MvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")

public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //WEB-INF는 직접 호출하면 실행안됨.컨트롤러를통해 실행해야함.
        String viewpath = "/WEB-INF/views/new-form.jsp";
        //컨트롤러에서 뷰로 이동할때 쓰는것
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewpath);
        dispatcher.forward(request,response);//서블릿에서 jsp호출가능,리다이렉트 아님
    }
}
