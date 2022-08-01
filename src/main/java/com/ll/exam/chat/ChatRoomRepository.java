package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChatRoomRepository {
    private static List<ChatRoomDto> chatRoomDtos;
    private static long lastId;

    static {
        chatRoomDtos = new ArrayList<>();
        lastId = 0;
        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(id -> {
            String title = "채팅방%d".formatted(id);
            String body = "내용%d".formatted(id);
            write(title, body);
        });
    }

    public static ChatRoomDto findById(long id) {
        for (ChatRoomDto room : chatRoomDtos){
            if (room.getId() == id){
                return room;
            }
        }
        return null;
    }

    public List<ChatRoomDto> findAll() {
        return chatRoomDtos;
    }

    public List<ChatRoomDto> findIdGreaterThan(long fromId) {
        return chatRoomDtos
                .stream()
                .filter(chatRoomDto -> chatRoomDto.getId() > fromId)
                .collect(Collectors.toList());
    }

    public static long write(String title, String body) {
        long id  = ++lastId;
        ChatRoomDto newDto = new ChatRoomDto(id,title,body);
        chatRoomDtos.add(newDto);
        return id;
    }
}
