package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// v1/{?} v1 하위에 어떤 게 들어와도 이 해당 servlet이 호출됨.
@WebServlet(name ="frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    // key는 url. 어떤 url이 들어오면 controllerv1을 꺼내서 호출하도록 구현
    private Map<String , ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    /***
     * 1. localhost:8080/front-controller/v1/members 로 요청 들어옴
     * 2. service() 호출
     * 3. 해당 RequestURI 인 /front-controller/v1/members를 키 값으로 갖는 객체인 MemberListControllerV1()를 뺀다.
     */

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        /***
         * 1. request.getRequestURI() = 프로젝트 + 파일경로
         * ex) localhost:8080/project/list.jsp -> return : /project/list.jsp
         * 2. request.getRequestURL() = 전체 경로
         * ex) localhost:8080/project/list.jsp -> return : localhost:8080/project/list.jsp
         */

        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);

        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap을 넘겨야함.
        //request.getParameter 다 꺼내야함
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);
        // 여기까지는 현재 mv에서 얻는게 논리 이름 뿐임 (new-form)
        MyView view = viewResolver(viewName);

        view.render(model, request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}

/***
 * 프론트 컨트롤러 도입 - v1
 * 1. 클라이언트가 http 요청을 하면 front controller가 받고
 * 2. 매핑 정보에서 나에게 맞는 매핑 정보 찾음
 * 3. 찾아서 그 컨트롤러 호출 후 거기에서 view에 뿌림
 */
