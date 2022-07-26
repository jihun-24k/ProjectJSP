package com.ll.exam.article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;

public class ArticleController {
    private ArticleService articleService;

    public ArticleController(){
        articleService = new ArticleService();
    }

    public void showList(Rq rq) throws IOException {
        List<ArticleDto> listDtos = articleService.findAll();

        rq.setAtt("articles", listDtos);
        rq.view("usr/article/list");
    }

    public void showWrite(Rq rq) {
        rq.view("usr/article/write");
    }

    public void doWrite(Rq rq) throws IOException {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body","");

        long id = articleService.write(title, body);
        rq.appendBody("%d번 게시물이 생성 되었습니다.".formatted(id));
    }

    public void showDetail(Rq rq) {
        long id = 1;
        ArticleDto findDto = articleService.findById(id);

        rq.setAtt("article",findDto);
        rq.view("usr/article/detail");
    }
}
