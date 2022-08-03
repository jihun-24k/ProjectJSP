package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatMsgDto;
import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;
import java.util.stream.Collectors;

public class ChatService {
    private ChatRoomRepository chatRoomRepository;
    private ChatMsgRepository chatMsgRepository;
    
    public ChatService(){
        chatRoomRepository = new ChatRoomRepository();
        chatMsgRepository = new ChatMsgRepository();
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

    public List<ChatMsgDto> findMessagesByRoomId(long id) {
        return chatMsgRepository.findByRoomId(id);
    }

    public List<ChatMsgDto> findMessagesByRoomIdGreaterThan(long roomId, long fromId) {
        return chatMsgRepository.findByRoomIdGreaterThan(roomId, fromId);
    }
}
