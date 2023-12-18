package hello.servlet.web.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RequestParamServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start" );//밑에 복붙:ctrl + d
        request.getParameterNames().asIterator()
                    //paramName:username , request.getParameter:키값
                        .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end " );
        System.out.println();

        System.out.println("[단위 파라미터 조회]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();
        //request.getparameter:단일값 보내기용 , request.getParametervalues:복수용
        System.out.println("[이름이 같은 복수 파리미터 조회]");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {//단축키 iter
            System.out.println("username = " + name);
        }

        response.getWriter().write("ok");


    }
}
