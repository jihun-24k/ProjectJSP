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
                switch (rq.getActionPath()) {
                    case "/usr/article/detail" -> articleController.showDetail(rq);
                    case "/usr/member/login" -> memberController.showLogin(rq);
                    case "/usr/article/list" -> articleController.showList(rq);
                    case "/usr/article/write" -> articleController.showWrite(rq);
                    case "/usr/article/delete" -> articleController.showDelete(rq);
                    case "/usr/article/modify" -> articleController.showModify(rq);
                }
            }
            case "POST" -> {
                switch (rq.getActionPath()){
                    case "/usr/article/write" -> articleController.doWrite(rq);
                    case "/usr/article/modify" -> articleController.doModify(rq);
                }
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
