package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatRoomDto;

public class ChatService {
    private ChatRepository chatRepository;
    
    public ChatService(){
        chatRepository = new ChatRepository();
    }

    public static ChatRoomDto findById(long id) {
    }
}
