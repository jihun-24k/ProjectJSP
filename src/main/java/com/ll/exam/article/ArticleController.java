package com.ll.exam.article;

import java.io.IOException;
import com.ll.exam.Rq;

public class ArticleController {
    public void showList(Rq rq) throws IOException {
        rq.appendBody("게시물 리스트");
    }
}
