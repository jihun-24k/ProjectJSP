package com.ll.exam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public Rq(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        this.req = req;
        this.resp = resp;

        // 인코딩도 Rq 생성자에서 호출
        this.req.setCharacterEncoding("UTF-8");
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

    public void appendBody(String formatted) throws IOException {
        resp.getWriter().append(formatted);
    }
}
