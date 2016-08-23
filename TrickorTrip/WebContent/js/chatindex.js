/**
 * Created by 박소윤 on 2016-08-22.
 */
var websocket;


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

    websocket.send($("#myText").val());
    $("#myText").val("");
}



function sendMSG(){
    //enter key를 입력했을 때 입력된 내용을 서버에 보내요!
    if(event.keyCode==13){
        //enter key가 입력되면 서버에 데이터를 보내요
        websocket.send($("#myText").val());
        $("#myText").val("");
    }

}


function openChat(){


    $('#dialog').dialog({
            autoOpen: false,
            position:'center'

        });

    $('#dialog').dialog('open');


        websocket=new WebSocket("ws://localhost:8080/tot/wsServer");

        websocket.onmessage=function(result){
            var txt=$("#myTextArea").val();
            $("#myTextArea").val(txt+"\n"+result.data);
        }

}