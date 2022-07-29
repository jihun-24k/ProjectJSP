package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArticleRepository {
    private static List<ArticleDto> articleDtos;
    private static long lastId;

    static {
        articleDtos = new ArrayList<>();
        lastId = 0;
        makeTestData();
    }
    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(id -> {
            String title = "제목%d".formatted(id);
            String body = "내용%d".formatted(id);
            write(title, body);
        });
    }

    public static long write(String title, String body) {
        long id  = ++lastId;
        ArticleDto newDto = new ArticleDto(id,title,body);
        articleDtos.add(newDto);
        return id;
    }

    public static List<ArticleDto> findAll() {
        return articleDtos;
    }

    public static ArticleDto findById(long id) {
        for (ArticleDto article : articleDtos){
            if (article.getId() == id){
                return article;
            }
        }
        return null;
    }

    public static void delete(long id) {
        ArticleDto removeDto = findById(id);
        articleDtos.remove(removeDto);
    }

    public static void modify(long id, String title, String body) {
        ArticleDto modifyDto = findById(id);
        modifyDto.setTitle(title);
        modifyDto.setBody(body);
    }

    public List<ArticleDto> findAllIdGreaterThan(long fromId) {
        return articleDtos
                .stream()
                .filter(articleDto -> articleDto.getId() > fromId)
                .collect(Collectors.toList());
    }
}