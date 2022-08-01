package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatMsgDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChatMsgRepository {
    private static List<ChatMsgDto> chatMsgDtos;
    private static long lastId;

    static {
        chatMsgDtos = new ArrayList<>();
        lastId = 0;
        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(roomId -> {
            IntStream.rangeClosed(1, 2).forEach(id -> {
                String body = "메시지 %d".formatted(id);
                write(roomId, body);
            });
        });
    }

    public static long write(long roomId, String body) {
        long id = ++lastId;
        ChatMsgDto newChatMessageDto = new ChatMsgDto(id, roomId, body);

        chatMsgDtos.add(newChatMessageDto);

        return id;
    }

    public List<ChatMsgDto> findByRoomId(long roomId) {
        return chatMsgDtos
                .stream()
                .filter(chatMessageDto -> chatMessageDto.getRoomId() == roomId)
                .collect(Collectors.toList());
    }

    public List<ChatMsgDto> findByRoomIdGreaterThan(long roomId, long fromId) {
        return chatMsgDtos
                .stream()
                .filter(chatMessageDto -> chatMessageDto.getRoomId() == roomId)
                .filter(chatMessageDto -> chatMessageDto.getId() > fromId)
                .collect(Collectors.toList());
    }
}
