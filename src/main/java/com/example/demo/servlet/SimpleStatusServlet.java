package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/status")
public class SimpleStatusServlet extends HttpServlet {

    @Override
    public void doGet(jakarta.servlet.http.HttpServletRequest req,
                    jakarta.servlet.http.HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        java.io.PrintWriter writer = resp.getWriter();
        writer.write("Servlet Alive");
    }

}
