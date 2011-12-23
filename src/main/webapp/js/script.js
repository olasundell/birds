.hover(
	function(){
		$(this).addClass("ui-state-hover");
	},
	function(){
		$(this).removeClass("ui-state-hover");
	}
)
var nextBirdHandler = function() {
    window.location = "/random/";

    return false;
};

var submitAnswerHandler = function() {
    var currentName = $('input[name=choice]:checked').val();
    var dataString = 'choice='+currentName + '&id='+$("#id").val();

    $.ajax({
        type: "POST",
        url: "/random/",
        data: dataString,
        success: function(data, textStatus, jqXHR) {
            $('#'+data).addClass('correct');
            $('#answerbutton').text('Nästa');
            $('#answerbutton').click(nextBirdHandler);
        }
    });
    return false;
};
$(document).ready(function() {
    $("#answerbutton").click(submitAnswerHandler);
});













//            $('#answerbutton').unbind('click');
//            $('#answerbutton').bind('click', nextBirdHandler);
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

