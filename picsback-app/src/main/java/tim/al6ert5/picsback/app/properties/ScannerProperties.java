package tim.al6ert5.picsback.app.properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;

@Component
@ConfigurationProperties(prefix = "picsback")
public class ScannerProperties {

	private static final Logger logger = LoggerFactory.getLogger(ScannerProperties.class);

	// the root director to scan for images
	private String imagesRoot;

	// comma separated list of image file extensions (example: png,gif,jpg,jpeg)
	private String imageFileExtensions;

	// The message digest to use for the files (i.e. MD5, SHA-1)
	private String digestType;

	public String getImagesRoot() {
		return imagesRoot;
	}

	public void setImagesRoot(String imagesRoot) {
		this.imagesRoot = imagesRoot;
	}

	public String getImageFileExtensions() {
		return imageFileExtensions;
	}

	public void setImageFileExtensions(String imageFileExtensions) {
		this.imageFileExtensions = imageFileExtensions;
	}

	public String getDigestType() {
		return digestType;
	}

	public void setDigestType(String digestType) {
		this.digestType = digestType;
	}

	@PostConstruct
	public void log() {
		logger.debug("Config: {}", this);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("imagesRoot", imagesRoot)
				.add("imageFileExtensions", imageFileExtensions).add("digestType", digestType).toString();
	}
}
