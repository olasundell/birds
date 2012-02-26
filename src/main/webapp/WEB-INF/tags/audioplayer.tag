<%@ attribute name="currentSound" type="se.atrosys.birds.model.SoundModel" required="true" %>

		<audio id="audioplayer" controls="controls" src="${currentSound.URL}" type="${currentSound.type}">
			<%--Your browser does not support the audio element.--%>
		</audio>
		<script type="text/javascript">
			var audioTag = document.createElement('audio');
			if (!(!!(audioTag.canPlayType) && ("no" != audioTag.canPlayType("audio/mpeg")) && ("" != audioTag.canPlayType("audio/mpeg")))) {
				AudioPlayer.embed("audioplayer", {soundFile: "${currentSound.URL}"});
			}
		</script>