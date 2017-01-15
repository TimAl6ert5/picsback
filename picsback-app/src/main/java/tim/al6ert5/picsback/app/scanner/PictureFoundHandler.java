package tim.al6ert5.picsback.app.scanner;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public interface PictureFoundHandler {

	public void processPicture(Path file, BasicFileAttributes attrs);
}
