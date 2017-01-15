package tim.al6ert5.picsback.app.service;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PictureDetailsService {

	@Autowired
	private ImageMetaService imageMetaService;

	public String getContentType(Path file) throws IOException {
		// Java 7 doesn't work
		// String probe = Files.probeContentType(file);
		// return probe;

		// Apache Tika works great
		File f = new File(file.toString());
		Tika tika = new Tika();
		return tika.detect(f);
	}

	public Long getFileSize(Path file) {
		File f = new File(file.toString());
		return f.length();
	}

	public Dimension getImageSize(Path file) throws IOException {
		File f = new File(file.toString());
		BufferedImage bimg = ImageIO.read(f);
		int width = bimg.getWidth();
		int height = bimg.getHeight();
		return new Dimension(width, height);
	}

	public String getMetaData(Path file) throws IOException {
		return imageMetaService.getMetaData(file);
	}
}
