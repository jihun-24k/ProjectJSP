package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatService {
    private ChatRoomRepository chatRoomRepository;
    private ChatMsgRepository chatMsgRepository;
    
    public ChatService(){
        chatRoomRepository = new ChatRoomRepository();
    }

    public ChatRoomDto findRoomById(long id) {
        return chatRoomRepository.findById(id);
    }

    public List<ChatRoomDto> findAll() {
        return chatRoomRepository.findAll();
    }

    public List<ChatRoomDto> findIdGreaterThan(long fromId) {
        return chatRoomRepository.findIdGreaterThan(fromId);
    }

    public long write(String title, String body) {
        return chatRoomRepository.write(title, body);
    }

    public void modify(long id, String title, String body) {
        chatRoomRepository.modify(id,title,body);
    }

    public void delete(long id) {
        chatRoomRepository.delete(id);
    }

    public long writeMsg(long roomId, String body) {
        return chatMsgRepository.write(roomId, body);
    }
}
