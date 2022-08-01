package com.ll.exam;

import com.ll.exam.article.ArticleController;
import com.ll.exam.chat.ChatController;
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
        ChatController chatController = new ChatController();

        switch (rq.getMethod()) {
            case "GET" -> {
                switch (rq.getActionPath()) {
                    // article uri
                    case "/usr/article/detail" -> articleController.showDetail(rq);
                    case "/usr/article/list" -> articleController.showList(rq);
                    case "/usr/article/write" -> articleController.showWrite(rq);
                    case "/usr/article/delete" -> articleController.showDelete(rq);
                    case "/usr/article/modify" -> articleController.showModify(rq);
                    case "/usr/article/getArticles" -> articleController.getArticles(rq);
                    // chat uri
                    case "/usr/chat/roomList" -> chatController.showChatRoom(rq);
                    case "/usr/chat/room" -> chatController.showRoom(rq);
                    case "/usr/chat/createRoom" -> chatController.showCreateRoom(rq);
                    case "/usr/chat/modifyRoom"-> chatController.showModifyRoom(rq);
                    // member uri
                    case "/usr/member/login" -> memberController.showLogin(rq);

                }
            }
            case "POST" -> {
                switch (rq.getActionPath()){
                    // article uri
                    case "/usr/article/write" -> articleController.doWrite(rq);
                    case "/usr/article/modify" -> articleController.doModify(rq);
                    // chat uri
                    case "/usr/chat/writeMessage" -> chatController.doWriteMsg(rq);
                    case "/usr/chat/createRoom" -> chatController.doCreateRoom(rq);
                    case "/usr/chat/modifyRoom" -> chatController.doModifyRoom(rq);
                }
            }
            case "DELETE" ->{
                switch (rq.getActionPath()){
                    case "usr/chat/deleteRoom" -> chatController.deleteRoom(rq);
                }
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
