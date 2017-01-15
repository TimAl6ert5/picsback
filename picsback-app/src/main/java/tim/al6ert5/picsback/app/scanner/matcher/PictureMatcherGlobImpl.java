package tim.al6ert5.picsback.app.scanner.matcher;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.attribute.BasicFileAttributes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tim.al6ert5.picsback.app.properties.ScannerProperties;

@Component
@Profile("globMatcher")
public class PictureMatcherGlobImpl implements PictureMatcher {

	@Autowired
	private ScannerProperties scannerProperties;

	private PathMatcher matcher;

	@PostConstruct
	public void init() {
		matcher = FileSystems.getDefault()
				.getPathMatcher("glob:*.{" + scannerProperties.getImageFileExtensions() + "}");
	}

	@Override
	public boolean isPicture(Path file, BasicFileAttributes attrs) {
		Path name = file.getFileName();
		return name != null && matcher.matches(name);
	}
}
