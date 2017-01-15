package tim.al6ert5.picsback.app.service;

import java.io.IOException;
import java.nio.file.Path;

public interface ImageMetaService {

	public String getMetaData(Path file) throws IOException;
}
