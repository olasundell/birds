nextBirdHandler = ->
	window.location = "/random/"
	false

submitAnswerHandler = ->
	currentName = $('input[name=choice]:checked').val()
	dataString = 'choice='+currentName + '&id='+$("#id").val()

	$.ajax '/random/',
			type: 'POST'
			data: dataString
			success: (data, textStatus, jqXHR) ->
				$('#'+data).addClass('correct')
				$('#answerbutton').text('Nästa')
				$('#answerbutton').click(nextBirdHandler)

	false

$ ->
	$("#answerbutton").click(submitAnswerHandler)

