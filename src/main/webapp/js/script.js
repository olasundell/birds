$(function() {
    $("#answerbutton").click(function() {
        var currentName = $('input[name=choice]:checked').val();
        var dataString = 'choice='+currentName + '&id='+$("#id").val();

        $.ajax({
            type: "POST",
            url: "/random/",
            data: dataString,
            success: function(data, textStatus, jqXHR) {
                $('#'+data).addClass('correct');
                /*            if (currentName == data) {
                 alert ("correct")
                 $('input[name=choice]:checked').parent("li").addClass('correct');
                 } else {
                 alert("incorrect");
                 $('input[name=choice]').each(function(){
                 if (this.value == data) {
                 this.parent("li").addClass('correct');
                 }
                 })
                 }*/
            }
        });
        return false;
    })
});
