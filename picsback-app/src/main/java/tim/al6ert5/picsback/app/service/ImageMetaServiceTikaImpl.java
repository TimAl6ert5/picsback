package tim.al6ert5.picsback.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

@Component
@Profile("tikaMeta")
public class ImageMetaServiceTikaImpl implements ImageMetaService {

	private static final Logger logger = LoggerFactory.getLogger(ImageMetaServiceTikaImpl.class);

	@Override
	public String getMetaData(Path file) throws IOException {
		StringBuilder buf = new StringBuilder();

		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(new File(file.toString()));
		ParseContext pcontext = new ParseContext();

		// JpegParser parser = new JpegParser();
		// TiffParser parser = new TiffParser();
		// ImageParser parser = new ImageParser();
		AutoDetectParser parser = new AutoDetectParser();
		try {
			parser.parse(inputstream, handler, metadata, pcontext);
		} catch (SAXException | TikaException e) {
			logger.info("Tika Meta Parse Exception: {}", file, e);
			return buf.toString();
		}

		String handlerTxt = handler.toString();
		if (handlerTxt.length() > 0) {
			buf.append("Contents of the document:").append(handlerTxt).append("\n");
		}
		buf.append("Metadata of the document:").append("\n");
		String[] metadataNames = metadata.names();
		Map<String, String> metaStuff = new TreeMap<>();
		for (String name : metadataNames) {
			metaStuff.put(name, metadata.get(name));
		}
		buf.append(metaStuff);

		return buf.toString();
	}

}
