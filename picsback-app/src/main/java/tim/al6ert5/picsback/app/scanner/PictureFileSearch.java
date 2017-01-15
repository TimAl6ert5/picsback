package tim.al6ert5.picsback.app.scanner;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tim.al6ert5.picsback.app.scanner.matcher.PictureMatcher;

@Component
public class PictureFileSearch implements FileVisitor<Path> {

	private static final Logger logger = LoggerFactory.getLogger(PictureFileSearch.class);

	@Autowired
	private PictureMatcher pictureMatcher;

	@Autowired
	private PictureFoundHandler pictureFoundHandler;

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		logger.debug("PreVisit-Directory: {}", dir);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		logger.debug("Visit File: {}", file);
		if (pictureMatcher.isPicture(file, attrs)) {
			pictureFoundHandler.processPicture(file, attrs);
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		logger.warn("Visit File Failed: {}", file, exc);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		logger.debug("PostVisit-Directory: {}", dir);
		return FileVisitResult.CONTINUE;
	}

}
