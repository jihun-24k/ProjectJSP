package com.ll.exam.article;

public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService(){
        this.articleRepository = new ArticleRepository();
    }

    public long write(String title, String body) {
        return articleRepository.write(title, body);
    }
}
