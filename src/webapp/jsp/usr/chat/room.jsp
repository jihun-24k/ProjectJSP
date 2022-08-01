<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jspf"%>

<script>
let Msg__lastId = 0;
function Msgs__loadMore() {
    fetch(`/usr/chat/getRooms?fromId=\${Rooms__lastId}`)
        .then(data => data.json())
        .then(responseData => {
            const rooms = responseData.datum;
            for ( const key in rooms ) {
                const room = rooms[key];
                const html = `
                    <li class = "flex">
                    <a class="w-[40px] hover:underline hover:text-[red]" href="/usr/chat/room/\${room.id}">\${room.id}</a>
                    <a class="flex-grow hover:underline hover:text-[red]" href="/usr/chat/room/\${room.id}">\${room.title}</a>
                    </li>
                `;

                $('.rooms').append(html);
            }
            if (rooms.length > 0){
                Msg__lastId = rooms[rooms.length - 1].id;
            }
            setTimeout(Msgs__loadMore, 3000); // Msgs__loadMore(); 를 3초 뒤에 수행
        });
}
</script>

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

        </div>
    </div>
</section>
    <div class="container px-3 mx-auto mt-5">
        <h1 class="font-bold text-lg">채팅 메시지</h1>

        <script>
            function ChatMessageSave__submitForm(form) {
                form.body.value = form.body.value.trim();
                if ( form.body.value.length == 0 ) {
                    form.body.focus();
                    return false;
                }
               form.submit();
           }
        </script>

                <form onsubmit="ChatMessageSave__submitForm(this); return false;" method="POST" action="/usr/chat/writeMessage/${room.id}">
                    <input autofocus name="body" type="text" placeholder="메세지를 입력해주세요." class="input input-bordered" />
                    <button type="submit" value="" class="btn btn-outline btn-primary">
                        작성
                    </button>
                </form>
                ${messages}
        </div>
    </div>
<section>

</section>

<%@ include file="../common/foot.jspf"%>