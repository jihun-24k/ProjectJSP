package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatService {
    private ChatRepository chatRepository;
    
    public ChatService(){
        chatRepository = new ChatRepository();
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

    public void delete(long id) {
    }
}
