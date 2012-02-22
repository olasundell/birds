(function() {
  var nextBirdHandler, submitAnswerHandler;

  nextBirdHandler = function() {
    window.location = "/random/";
    return false;
  };

  submitAnswerHandler = function() {
    var currentName, dataString;
    currentName = $('input[name=choice]:checked').val();
    dataString = 'choice=' + currentName + '&id=' + $("#id").val();
    $.ajax('/random/', {
      type: 'POST',
      data: dataString,
      success: function(data, textStatus, jqXHR) {
        $("#" + data).addClass("correct");
        $("#answerbutton").text('Nästa');
        return $("#answerbutton").click(nextBirdHandler);
      }
    });
    return false;
  };

  $(function() {
    return $("#answerbutton").click(submitAnswerHandler);
  });

}).call(this);
