package com.ll.exam.chat;

import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatService {
    private ChatRepository chatRepository;
    
    public ChatService(){
        chatRepository = new ChatRepository();
    }

    public static ChatRoomDto findById(long id) {
    }

    public List<ArticleDto> findAll() {
    }

    public List<ArticleDto> findIdGreaterThan(long fromId) {
    }
}
