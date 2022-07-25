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
        Rq rq = new Rq(req,res);

        ArticleController articleController = new ArticleController();
        MemberController memberController = new MemberController();

        String url = req.getRequestURI();
        System.out.println(url);

        switch (url) {
            case "/usr/member/login" -> memberController.showLogin(rq);
            case "/usr/article/list/free" -> articleController.showList(rq);
        }

    }
}
