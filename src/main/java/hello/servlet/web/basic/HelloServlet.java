package hello.servlet.web.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//서블릿이름,매핑 곂치면안됨
@WebServlet(name="helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override//컨트롤+O
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");//soutm 단축키
        System.out.println("request = " + request);//soutv 단축키
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain");//단순문자 보내기
        response.setCharacterEncoding("utf-8");//문자세트 설정
        response.getWriter().write("hello " + username);//http바디에 메세지들어감
    }
}
