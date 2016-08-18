/**
 * Created by 박소윤 on 2016-08-18.
 */
function checkID(){
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/tot/login",
        dataType:"json",
        data:{
            inputID:$("#inputID").val(),
            inputPassword:$("#inputPassword").val()
        },
        success:function(data){
            if(data.result){
                location.replace("index.html");
            }else{
                alert("로그인에 실패하셨습니다. 다시 한번 해주세요.");
            }

        },
        error:function(){
            alert("로그인에 실패하셨습니다. 다시 한번 해주세요.");
        }
    });
}