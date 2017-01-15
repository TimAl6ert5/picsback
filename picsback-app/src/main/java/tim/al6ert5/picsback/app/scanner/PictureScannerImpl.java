package tim.al6ert5.picsback.app.scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tim.al6ert5.picsback.app.persistence.PictureDetailsEntity;
import tim.al6ert5.picsback.app.properties.ScannerProperties;
import tim.al6ert5.picsback.app.service.PictureStoreService;

@Component
public class PictureScannerImpl implements PictureScanner {
	
	private static final Logger logger = LoggerFactory.getLogger(PictureScannerImpl.class);

	@Autowired
	private ScannerProperties scannerProperties;

	@Autowired
	private PictureFileSearch pictureFileSearch;
	
	@Autowired
	private PictureStoreService pictureStoreService;
	
	@Override
	public void scan() {
		Path root = Paths.get(scannerProperties.getImagesRoot());
		try {
			Files.walkFileTree(root, pictureFileSearch);
		} catch (IOException e) {
			logger.warn("Error walking file tree!", e);
		} catch(NullPointerException npe) {
			logger.warn("Error walking file tree!", npe);
		}
	}

	@Override
	public boolean hasResults() {
		return pictureStoreService.hasPictures();
	}

	@Override
	public Iterable<PictureDetailsEntity> getResults() {
		return pictureStoreService.getAllPictures();
	}
}
