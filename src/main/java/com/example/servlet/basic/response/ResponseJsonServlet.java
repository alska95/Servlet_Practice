package com.example.servlet.basic.response;

import com.example.servlet.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "responseJsonServlet" , urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-type : application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);
        String output = objectMapper.writeValueAsString(helloData);

        response.getWriter().write(output);

    }
}
