package se.atrosys.birds.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;

import java.io.*;
import java.net.URI;
import java.util.Map;

@Component
public class FileFetcherImpl implements FileFetcher {
	private HttpClient httpClient;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public FileFetcherImpl() {
		httpClient = new DefaultHttpClient();
//		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost("proxy.svt.se", 8080, "http"));
	}

	public File fetchFile(Map<String, String> getParams, String fileUrl, String httpUrl) {
		UriTemplate uriTemplate = new UriTemplate(fileUrl);
		URI fileUri = uriTemplate.expand(getParams);
		File file = new File(fileUri);
		if (!file.exists()) {
			try {
				URI uri = new UriTemplate(httpUrl).expand(getParams);
				HttpGet httpget = new HttpGet(uri);
				HttpResponse response = httpClient.execute(httpget);
				HttpEntity entity = response.getEntity();

				FileWriter writer = new FileWriter(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					writer.write(line);
				}

				writer.close();
				reader.close();

				file = new File(fileUri);

			} catch (IOException e) {
				logger.error("", e);
			}
		}
		return file;
	}
}