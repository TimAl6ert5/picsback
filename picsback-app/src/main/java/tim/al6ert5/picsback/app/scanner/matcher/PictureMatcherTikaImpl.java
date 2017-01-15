package tim.al6ert5.picsback.app.scanner.matcher;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tim.al6ert5.picsback.app.service.PictureDetailsService;

@Component
@Profile("tikaMatcher")
public class PictureMatcherTikaImpl implements PictureMatcher {

	private static final Logger logger = LoggerFactory.getLogger(PictureMatcherTikaImpl.class);
	
	private static final String CONTENT_TYPE_IMAGE_REGEX = "image.";

	@Autowired
	private PictureDetailsService pictureDetailsService;
	
	private Pattern pattern = Pattern.compile(CONTENT_TYPE_IMAGE_REGEX);

	@Override
	public boolean isPicture(Path file, BasicFileAttributes attrs) {
		String contentType = "";
		try {
			contentType = pictureDetailsService.getContentType(file);
		} catch (IOException e) {
			logger.debug("Content type detection error", e);
		}
		Matcher matcher = pattern.matcher(contentType);
		return matcher.find();
	}

}
