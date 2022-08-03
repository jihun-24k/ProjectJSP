package com.ll.exam.chat;

import com.ll.exam.Rq;
import com.ll.exam.chat.dto.ChatMsgDto;
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
        long id = rq.getLongPathValueByIndexForChat(1, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ChatRoomDto chatRoomDto = chatService.findRoomById(id);
        List<ChatMsgDto> chatMessageDtos = chatService.findMessagesByRoomId(chatRoomDto.getId());

        if (chatRoomDto == null) {
            rq.historyBack("해당 채팅방이 존재하지 않습니다.");
            return;
        }
        rq.setAtt("room",chatRoomDto);
        rq.setAtt("messages",chatMessageDtos);
        rq.view("usr/chat/room");
    }

    public void showCreateRoom(Rq rq) {
        rq.view("usr/chat/createRoom");
    }

    public void showModifyRoom(Rq rq) {
        long id = rq.getLongPathValueByIndexForChat(1, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ChatRoomDto modifyDto = chatService.findRoomById(id);

        if (modifyDto == null) {
            rq.historyBack("해당 채팅방이 존재하지 않습니다.");
            return;
        }
        rq.setAtt("room",modifyDto);
        rq.view("usr/chat/modifyRoom");
    }

    public void doCreateRoom(Rq rq) {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body","");

        long id = chatService.write(title, body);
        rq.replace("/usr/chat/room/%d".formatted(id), "%d번 채팅방이 생성 되었습니다.".formatted(id));
    }

    public void doModifyRoom(Rq rq) {
        long id = rq.getLongPathValueByIndexForChat(1, 0);
        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }
        ChatRoomDto modifyDto = chatService.findRoomById(id);

        if (modifyDto == null) {
            rq.historyBack("해당 채팅방이 존재하지 않습니다.");
            return;
        }
        String title = rq.getParam("title", "");
        String body = rq.getParam("body","");

        chatService.modify(id,title, body);
        rq.replace("/usr/chat/room/%d".formatted(id), "%d번 채팅방이 수정 되었습니다.".formatted(id));
    }

    public void deleteRoom(Rq rq) {
        long id = rq.getLongPathValueByIndexForChat(1, 0);
        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }
        ChatRoomDto deleteDto = chatService.findRoomById(id);
        if (deleteDto == null) {
            rq.historyBack("해당 채팅방이 존재하지 않습니다.");
            return;
        }

        chatService.delete(id);
        rq.replace("/usr/chat/roomList", "%d번 게시물이 삭제되었습니다.".formatted(id));
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

    public void doWriteMsg(Rq rq) {
        long roomId = rq.getLongPathValueByIndexForChat(1, 0);
        if (roomId == 0) {
            rq.failJson("채팅방 번호를 입력해주세요.");
            return;
        }

        ChatRoomDto chatRoom = chatService.findRoomById(roomId);

        if (chatRoom == null) {
            rq.failJson("존재하지 않는 채팅방 입니다.");
            return;
        }
        String body = rq.getParam("body","");

        if (body.trim().length() == 0) {
            rq.historyBack("내용을 입력해주세요.");
            return;
        }

        long newChatMsgId = chatService.writeMsg(roomId, body);
        rq.successJson(newChatMsgId);
    }
    public void getMessages(Rq rq) {
        long roomId = rq.getLongPathValueByIndex(0, -1);

        if (roomId == -1) {
            rq.failJson("채팅방 번호를 입력해주세요.");
            return;
        }

        ChatRoomDto chatRoom = chatService.findRoomById(roomId);

        if (chatRoom == null) {
            rq.failJson("존재하지 않는 채팅방 입니다.");
            return;
        }

        long fromId = rq.getLongParam("fromId", -1);

        List<ChatMsgDto> chatMessageDtos = null;

        if ( fromId == -1 ) {
            chatMessageDtos = chatService.findMessagesByRoomId(roomId);
        }
        else {
            chatMessageDtos = chatService.findMessagesByRoomIdGreaterThan(roomId, fromId);
        }

        rq.successJson(chatMessageDtos);
    }

    public void deleteMessages(Rq rq) {
        long id = rq.getLongPathValueByIndex(0, 0);

        if (id == 0) {
            rq.failJson("번호를 입력해주세요.");
            return;
        }

        ChatMsgDto chatMessageDto = chatService.findMessageById(id);

        if (chatMessageDto == null) {
            rq.failJson("해당 메세지가 존재하지 않습니다.");
            return;
        }

        chatService.deleteMessage(id);

        rq.json(id, "S-1", "%d번 메세지가 삭제되었습니다.".formatted(id));
    }
}
