package com.ll.exam;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public Rq(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;

        // 인코딩도 Rq 생성자에서 호출
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        this.resp.setCharacterEncoding("UTF-8");
        this.resp.setContentType("text/html; charset=utf-8");
    }

    public int getIntParam(String dan, int defaultValue) {
        String paramValue = req.getParameter(dan);
        if (paramValue == null)
            return defaultValue;
        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public void appendBody(String str) throws IOException {
        try {
            resp.getWriter().append(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAtt(String name, Object value){
        req.setAttribute(name, value);
    }

    public void view (String path){
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/" + path + ".jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getPath() {
        return req.getRequestURI();
    }

    public String getMethod() {
        return req.getMethod();
    }

    public String getParam(String paramName, String defaultValue) {
        String value = req.getParameter(paramName);
        if (paramName == null || value.trim().length() == 0)
            return defaultValue;
        return paramName;
    }
}
