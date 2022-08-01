<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jspf"%>

<script>
 function ArticleSave__submitForm(form) {
     form.title.value = form.title.value.trim();
     if ( form.title.value.length == 0 ) {
         alert('제목을 입력해주세요.');
         form.title.focus();
         return;
     }
     form.body.value = form.body.value.trim();
     if ( form.body.value.length == 0 ) {
         alert('내용을 입력해주세요.');
         form.body.focus();
         return;
     }
     form.submit();
 }
</script>

<section>
    <div class="container px-3 mx-auto mt-5">
        <h1 class="font-bold text-lg">채팅방 수정</h1>
         <form method="POST"  onsubmit="ArticleSave__submitForm(this); return false;">
             <div class="flex gap-3 mt-3">
                 <span>제목</span>
                 <div>
                    <input name="title" type="text" maxlength="50" placeholder="제목을 입력해주세요." />
                </div>
            </div>
            <div class="flex gap-3 mt-3">
                <span>내용</span>
                <div>
                    <input name="body" type="text" maxlength="300" placeholder="내용을 입력해주세요." />
                </div>
            </div>
            <div class="flex gap-3 mt-3">
                <div>
                    <button class="hover:bg-black bg-[#F8F8F8] p-[8px_15px_5px_15px] rounded-[25px] hover:text-white"><input type="submit" value="작성" /></button>
                </div>
            </div>
        </form>
    </div>
</section>

<%@ include file="../common/foot.jspf"%>