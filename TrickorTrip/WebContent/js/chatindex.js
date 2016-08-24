/**
 * Created by 박소윤 on 2016-08-22.
 */
var websocket;

var uid;
$(document).ready(function(){
    $.ajax({
        type:"GET",
        url:"http://10.16.11.205:8080/tot/user",
        dataType:"json",
        success:function(data){
            if(data.flag){
                uid=data.id;
                $("#li_user").prepend(data.id+"님");
                console.log(data.id);

            }else{
                alert("로그인이 필요합니다.");
                location.replace("login.html");
            }
        },
        error:function () {
            alert("로그인이 필요합니다.");
            location.replace("login.html");
        }

    })
});

$( function() {
    $( "#dialog" ).dialog({
        autoOpen: false,
        close:function (event, ui) {
            websocket.close();
        }
    });

    $( "#us_submit" ).on( "click", function() {
        $( "#dialog" ).dialog( "open" );

    });
} );

function sendMSG1(){
    var msg=$("#li_user").text()+$("#myText").val();

    websocket.send(msg);
    $("#myText").val("");
}



function sendMSG(){
    //enter key를 입력했을 때 입력된 내용을 서버에 보내요!
    if(event.keyCode==13){
        //enter key가 입력되면 서버에 데이터를 보내요
        var msg=uid+":"+($("#myText").val());
        websocket.send(msg);
        $("#myText").val("");
    }

}



function openChat(){


    $('#dialog').dialog({
            autoOpen: false,
            position:'center'

        });

    $('#dialog').dialog('open');


        websocket=new WebSocket("ws://10.16.11.205:8080/tot/wsServer/"+uid);
        
        websocket.onmessage=function(result){
            var txt=$("#myTextArea").val();
            $("#myTextArea").val(txt+"\n"+result.data);

            $("#myTextArea").change(function() {
            	  scrollToBottom();
            	});

            function scrollToBottom() {
            	$("#myTextArea").scrollTop($("#myTextArea")[0].scrollHeight);
            	}
            
            
        }

}