package tim.al6ert5.picsback.app.processor;

import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tim.al6ert5.picsback.app.persistence.PictureDetailsEntity;
import tim.al6ert5.picsback.app.persistence.PictureDetailsRepository;
import tim.al6ert5.picsback.app.service.DigestService;
import tim.al6ert5.picsback.app.service.PictureDetailsService;

@Component
public class PictureProcessorImpl implements PictureProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(PictureProcessorImpl.class);

	@Autowired
	private DigestService digestService;

	@Autowired
	private PictureDetailsService pictureDetailsService;

	@Autowired
	private PictureDetailsRepository pictureDetailsRepository;

	@Override
	public PictureDetailsEntity process(PictureDetailsEntity pd) {
		
		Path path = Paths.get(pd.getFile());
		
		try {
			pd.setChecksum(digestService.digest(path));
		} catch (IOException e1) {
			logger.warn("Digest Error: {}", path, e1);
		}
		
		try {
			pd.setContentType(pictureDetailsService.getContentType(path));
		} catch (IOException e) {
			logger.warn("Content-Type Error: {}", path, e);
		}
		
		if(pd.getSize() == null) {
			pd.setSize(pictureDetailsService.getFileSize(path));
		}
		
		try {
			Dimension dim = pictureDetailsService.getImageSize(path);
			pd.setWidth(dim.width);
			pd.setHeight(dim.height);
		} catch (IOException | NullPointerException e) {
			logger.warn("Dimension Error: {}", path, e);
		}
		
		try {
			pd.setMetaBlob(pictureDetailsService.getMetaData(path));
		} catch (IOException e) {
			logger.warn("MetaData Error: {}", path, e);
		}
		
		pictureDetailsRepository.save(pd);
		
		return pd;
	}

}
