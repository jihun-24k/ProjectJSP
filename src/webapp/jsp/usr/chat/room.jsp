<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jspf"%>

<script>
 function MsgSave__submitForm(form) {
     form.body.value = form.body.value.trim();
     if ( form.body.value.length == 0 ) {
         alert('메시지를 입력해주세요.');
         form.body.focus();
         return;
     }
     form.submit();
 }
</script>

<section>
    <div class="container px-3 mx-auto mt-5">
        <h1 class="font-bold text-lg">채팅방</h1>

        <div>
                <div>
                    ID: ${room.id}
                </div>
                <div>
                    TITLE: ${room.title}
                </div>
                <div>
                    <input name="body" type="text" maxlength="300" placeholder="메시지를 입력해주세요." />
                    <button class="hover:bg-black bg-[#F8F8F8] p-[8px_15px_5px_15px] rounded-[25px] hover:text-white"><input type="submit" value="보내기" /></button>
                </div>
        </div>
    </div>
</section>
    <div class="container px-3 mx-auto mt-5">
        <h1 class="font-bold text-lg">채팅 메시지</h1>
        <div class="place-1 border-2 border-[red] min-h-[500px]">



        </div>
    </div>
<section>

</section>

<%@ include file="../common/foot.jspf"%>