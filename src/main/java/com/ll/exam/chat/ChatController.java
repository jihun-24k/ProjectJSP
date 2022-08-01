package com.ll.exam.chat;

import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatController {
    private ChatService chatService;

    public ChatController(){
        chatService = new ChatService();
    }

    public void showChatRoom(Rq rq) {
        rq.view("usr/chat/roomList");
    }

    public void showRoom(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ChatRoomDto findDto = chatService.findById(id);

        if (findDto == null) {
            rq.historyBack("해당 채팅방이 존재하지 않습니다.");
            return;
        }
        rq.setAtt("chat",findDto);
        rq.view("usr/chat/room");
    }

    public void showCreateRoom(Rq rq) {
        rq.view("usr/chat/createRoom");
    }

    public void showModifyRoom(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ChatRoomDto modifyDto = chatService.findById(id);

        if (modifyDto == null) {
            rq.historyBack("해당 채팅방이 존재하지 않습니다.");
            return;
        }
        rq.setAtt("chat",modifyDto);
        rq.view("usr/chat/modifyRoom");
    }

    public void doWriteMsg(Rq rq) {
    }

    public void doCreateRoom(Rq rq) {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body","");

        long id = chatService.write(title, body);
        rq.replace("/usr/chat/room/%d".formatted(id), "%d번 채팅방이 생성 되었습니다.".formatted(id));
    }

    public void doModifyRoom(Rq rq) {
    }

    public void deleteRoom(Rq rq) {
    }

    public void getRooms(Rq rq){
        long fromId = rq.getLongParam("fromId", -1);

        List<ChatRoomDto> listDtos = null;

        if ( fromId == -1 ) {
            listDtos = chatService.findAll();
        }
        else {
            listDtos = chatService.findIdGreaterThan(fromId);
        }
        rq.successJson(listDtos);
    }
}
