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

// v1/{?} v1 하위에 어떤 게 들어와도 이 해당 servlet이 호출됨.
@WebServlet(name ="frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // key는 url. 어떤 url이 들어오면 controllerv1을 꺼내서 호출하도록 구현
    private Map<String , ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    /***
     * 1. localhost:8080/front-controller/v1/members 로 요청 들어옴
     * 2. service() 호출
     * 3. 해당 RequestURI 인 /front-controller/v1/members를 키 값으로 갖는 객체인 MemberListControllerV1()를 뺀다.
     */

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        /***
         * 1. request.getRequestURI() = 프로젝트 + 파일경로
         * ex) localhost:8080/project/list.jsp -> return : /project/list.jsp
         * 2. request.getRequestURL() = 전체 경로
         * ex) localhost:8080/project/list.jsp -> return : localhost:8080/project/list.jsp
         */

        String requestURI = request.getRequestURI();
//        System.out.println("경로 : " + requestURI);

        ControllerV1 controller = controllerMap.get(requestURI);

        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);
    }
}

/***
 * 프론트 컨트롤러 도입 - v1
 * 1. 클라이언트가 http 요청을 하면 front controller가 받고
 * 2. 매핑 정보에서 나에게 맞는 매핑 정보 찾음
 * 3. 찾아서 그 컨트롤러 호출 후 거기에서 view에 뿌림
 */
