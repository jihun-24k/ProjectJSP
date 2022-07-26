package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private static List<ArticleDto> articleDtos;
    private static long lastId;

    static {
        articleDtos = new ArrayList<>();
        lastId = 0;
    }

    public long write(String title, String body) {
        lastId++;
        ArticleDto newDto = new ArticleDto(lastId,title,body);
        articleDtos.add(newDto);
        return lastId;
    }

    public List<ArticleDto> findAll() {
        return articleDtos;
    }

    public ArticleDto findById(long id) {
        for (ArticleDto article : articleDtos){
            if (article.getId() == id){
                return article;
            }
        }
        return null;
    }
}