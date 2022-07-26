package com.ll.exam;

import com.ll.exam.article.ArticleController;
import com.ll.exam.member.MemberController;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/usr/*")
public class DispatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Rq rq = new Rq(req, res);

        ArticleController articleController = new ArticleController();
        MemberController memberController = new MemberController();

        switch (rq.getMethod()) {
            case "GET" -> {
                switch (rq.getPath()) {
                    case "/usr/member/login" -> memberController.showLogin(rq);
                    case "/usr/article/list/free" -> articleController.showList(rq);
                    case "/usr/article/write/free" -> articleController.showWrite(rq);
                }
            }
            case "POST" -> {
                switch (rq.getPath()){
                    case "/usr/article/write/free" -> articleController.doWrite(rq);
                }
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
