package tim.al6ert5.picsback.app.scanner.matcher;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tim.al6ert5.picsback.app.properties.ScannerProperties;
import tim.al6ert5.picsback.app.scanner.matcher.PictureMatcherGlobImpl;

@RunWith(Parameterized.class)
public class PictureMatcherImplTest {

	private static final Logger logger = LoggerFactory.getLogger(PictureMatcherImplTest.class);

	private String testImage;
	private Boolean isImage;
	private String fileExtensions;
	private Path file;
	private BasicFileAttributes attrs;

	@Mock
	private ScannerProperties scannerProperties;

	@InjectMocks
	private PictureMatcherGlobImpl pictureMatcher;

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		// list of image files to test against
		return Arrays.asList(new Object[][] { { "some_image.png", true, "png,gif,jpg,jpeg" },
				{ "not_an_image.txt", false, "png,gif,jpg,jpeg" } });
	}

	public PictureMatcherImplTest(String testImage, Boolean isImage, String fileExtensions) {
		this.testImage = testImage;
		this.isImage = isImage;
		this.fileExtensions = fileExtensions;
	}

	@Before
	public void setup() throws IOException {
		MockitoAnnotations.initMocks(this);

		file = Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + "samples"
				+ System.getProperty("file.separator") + testImage);
		attrs = Files.readAttributes(file, BasicFileAttributes.class);

		when(scannerProperties.getImageFileExtensions()).thenReturn(fileExtensions);
		pictureMatcher.init();
	}

	@Test
	public void test() {
		logger.info("Testing File: {}", file);
		// assertThat(Files.isRegularFile(file) & Files.isReadable(file),
		// equalTo(true));
		boolean actual = pictureMatcher.isPicture(file, attrs);
		assertThat(actual, equalTo(isImage));
	}
}
