package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// mvc는 항상 controller -> view
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {



    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // controller 에서 view로 이동 시 사용하는 것
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); // servlet에서 jsp호출하는 함수
        // 고객 요청이 오면 위 코드 호출
        // 그럼 viewPath를 다시 호출함 --> 서버끼리 내부에서 호출..
        // 클라이언트에서 서버로 "/servlet-mvc/members/new-form" 호출
        // 서버 안에서 자기들끼리 jsp 호출하고 jsp 에서 응답 만들어서 고객에게 보냄.
    }
}











