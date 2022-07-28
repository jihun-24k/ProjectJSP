package com.ll.exam;

import com.ll.exam.util.Ut;
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

    public void print(String str) throws IOException {
        try {
            resp.getWriter().append(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void println(String str) throws IOException {
        print(str+"\n");
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
        return value;
    }

    public String getActionPath() {
        String[] urlBits = getPath().split("/");

        return "/%s/%s/%s".formatted(urlBits[1],urlBits[2],urlBits[3]);
    }

    public long getLongPathValueByIndex(int idx, int defaultValue) {
        String value = getPathValueByIndex(idx,null);

        if (value == null){
            return defaultValue;
        }

        try{
            return Long.parseLong(value);
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public String getPathValueByIndex(int idx, String defaultValue){
        String[] urlBits = getPath().split("/");
        try{
            return urlBits[4+idx];
        }catch (ArrayIndexOutOfBoundsException e){
            return defaultValue;
        }
    }

    public void replace(String uri, String msg) throws IOException {
        if (msg != null && msg.trim().length()>0){
            println("""
                    <script>
                    alert("%s");
                    </script>
                    """.formatted(msg));
        }
        println("""
                <script>
                location.replace("%s");
                </script>
                """.formatted(uri));
    }

    public void json(Object data) throws IOException {
        resp.setContentType("application/json; charset=utf-8");

        String jsonStr = Ut.json.toStr(data, "");
        println(jsonStr);
    }
}
