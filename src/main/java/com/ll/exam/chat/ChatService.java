package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatService {
    private ChatRoomRepository chatRepository;
    
    public ChatService(){
        chatRepository = new ChatRoomRepository();
    }

    public ChatRoomDto findById(long id) {
        return chatRepository.findById(id);
    }

    public List<ChatRoomDto> findAll() {
        return chatRepository.findAll();
    }

    public List<ChatRoomDto> findIdGreaterThan(long fromId) {
        return chatRepository.findIdGreaterThan(fromId);
    }

    public long write(String title, String body) {
        return chatRepository.write(title, body);
    }

    public void modify(long id, String title, String body) {
        chatRepository.modify(id,title,body);
    }

    public void delete(long id) {
        chatRepository.delete(id);
    }
}
