package se.atrosys.birds.factory;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

public class ScrapedTableBuilder {
	public Element getTable(Document doc) {
		return doc.getElementsByClass("AVBlist").get(3).child(0);
	}
}