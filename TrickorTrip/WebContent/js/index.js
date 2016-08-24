/**
 * Created by 박소윤 on 2016-08-24.
 */
$('.dropdown-toggle').dropdown();
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



