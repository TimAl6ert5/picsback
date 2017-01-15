package tim.al6ert5.picsback.app.scanner.matcher;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public interface PictureMatcher {

	public boolean isPicture(Path file, BasicFileAttributes attrs);
}
