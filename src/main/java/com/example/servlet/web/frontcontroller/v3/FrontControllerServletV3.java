package com.example.servlet.web.frontcontroller.v3;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControlControllerV3" , urlPatterns = "/front-controllerV3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String , ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controllerV3/members/new-form" , new MemberFormControllerV3());
        controllerMap.put("/front-controllerV3/members/save" , new MemberSaveControllerV3());
        controllerMap.put("/front-controllerV3/members/" , new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControlControllerV3.service");

        //front-cControllerV3/members
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paramMap = createParamMap(request);

        ModelView modelView = controller.process(paramMap);
        String viewName = modelView.getViewName();

        MyView view = new MyView(viewResolver(viewName));
        view.render(modelView.getModel() , request, response);

    }

    private String viewResolver(String viewName) {
        return "/WEB-INF/views/" + viewName + ".jsp";
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String ,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName->paramMap.put(paramName , request.getParameter(paramName)));
        return paramMap;
    }
}
