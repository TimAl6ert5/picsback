package tim.al6ert5.picsback.app.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tim.al6ert5.picsback.app.persistence.PictureDetailsEntity;
import tim.al6ert5.picsback.app.persistence.PictureDetailsRepository;
import tim.al6ert5.picsback.app.scanner.PictureFoundHandler;

@Component
public class PictureStoreService implements PictureFoundHandler {

	private static final Logger logger = LoggerFactory.getLogger(PictureStoreService.class);

	@Autowired
	private DigestService digestService;

	@Autowired
	private PictureDetailsRepository pictureDetailsRepository;

	@Override
	public void processPicture(Path file, BasicFileAttributes attrs) {
		logger.info("Found Picture: {}", file);
		PictureDetailsEntity pd = new PictureDetailsEntity();
		pd.setFile(file.toString());
		try {
			pd.setFileHash(digestService.digest(file.toString()));
		} catch (IOException e) {
			logger.warn("File Hash Error: {}", file, e);
		}
		pd.setName(file.getFileName().toString());

		pd.setSize(attrs.size());
		pd.setCreationTime(attrs.creationTime().toString());
		pd.setLastModifiedTime(attrs.lastModifiedTime().toString());

		pictureDetailsRepository.save(pd);
	}

	public boolean hasPictures() {
		return pictureDetailsRepository.count() > 0;
	}
	
	public Iterable<PictureDetailsEntity> getAllPictures() {
		return pictureDetailsRepository.findAll();
	}

}
