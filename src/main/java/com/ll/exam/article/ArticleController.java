package com.ll.exam.article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;

public class ArticleController {
    public void showList(Rq rq) throws IOException {
        List<ArticleDto> articleDtos = new ArrayList<>();

        articleDtos.add(new ArticleDto(4, "제목4","내용4"));
        articleDtos.add(new ArticleDto(3, "제목3","내용3"));
        articleDtos.add(new ArticleDto(2, "제목2","내용2"));
        articleDtos.add(new ArticleDto(1, "제목1","내용1"));

        rq.setAtt("articles", articleDtos);
        rq.view("usr/article/list");
    }

    public void showWrite(Rq rq) {
        rq.view("usr/article/write");
    }

    public void doWrite(Rq rq) throws IOException {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body","");

        rq.appendBody("<div>title : %s</div>".formatted(title));
        rq.appendBody("<div>body : %s</div>".formatted(body));
    }
}
