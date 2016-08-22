/**
 * Created by 박소윤 on 2016-08-22.
 */
var websocket;

$(document).ready(function() {
    websocket=new WebSocket("ws://localhost:8080/chat/wsServer");

    websocket.onmessage=function(result){
        var txt=$("#myTextArea").val();
        $("#myTextArea").val(txt+"\n"+result.data);
    }

})

function closeServer(){
    websocket.close();

}
function sendMSG(){
    //enter key를 입력했을 때 입력된 내용을 서버에 보내요!
    if(event.keyCode==13){
        //enter key가 입력되면 서버에 데이터를 보내요
        websocket.send($("#myText").val());
    }
}