(function() {
  var ineligibleHandler, nextBirdHandler, submitAnswerHandler;

  nextBirdHandler = function() {
    window.location = "";
    return false;
  };

  ineligibleHandler = function() {
    var dataString, mediaId, mediaType;
    mediaId = $('input[name=mediaId]').val();
    mediaType = $('input[name=mediaType]').val();
    dataString = 'mediaId=' + mediaId + "&mediaType=" + mediaType;
    $.ajax("../ineligible/", {
      type: 'POST',
      data: dataString,
      success: function(data, textStatus, jqXHR) {
        return window.location = "";
      }
    });
    return false;
  };

  submitAnswerHandler = function() {
    var currentName, dataString;
    currentName = $('input[name=choice]:checked').val();
    dataString = 'choice=' + currentName + '&id=' + $("#id").val();
    $.ajax($('document.location').href, {
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
    $("#answerbutton").click(submitAnswerHandler);
    return $("#ineligiblebutton").click(ineligibleHandler);
  });

}).call(this);
