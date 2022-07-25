package com.ll.exam.member;

import com.ll.exam.Rq;
import java.io.IOException;

public class MemberController {
    public void showLogin(Rq rq) throws IOException {
        rq.appendBody("로그인 화면");
    }
}
