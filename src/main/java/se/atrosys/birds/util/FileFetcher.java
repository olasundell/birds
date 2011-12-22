package se.atrosys.birds.util;

import java.io.File;
import java.util.Map;

/**
 * TODO write comment
 */
public interface FileFetcher {
	File fetchFile(Map<String, String> getParams, String fileUrl, String httpUrl);
}
