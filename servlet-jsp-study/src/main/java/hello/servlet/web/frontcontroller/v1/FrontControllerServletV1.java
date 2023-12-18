package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//*은 어떤게 들어와도 이 서블릿이 호출됨
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")

public class FrontControllerServletV1 extends HttpServlet {
    //어떤 컨트롤러가 호출되야하는지 조회
    private Map<String, ControllerV1> controllerMap = new HashMap<>();
    //앞에주소가되면 뒤에new가 실행
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");
        //주소부분(uri)를 받아옴.
        String requestURI = request.getRequestURI();
        //인터페이스로 꺼내기에 이것이 일관성이 좋음
        //ControllerV1 controller = MemberFormControllerV1();
        ControllerV1 controller = controllerMap.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//404
            return;
        }
        //인터페이스 호출
        controller.process(request, response);
    }
}
