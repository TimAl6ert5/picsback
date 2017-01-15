package tim.al6ert5.picsback.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tim.al6ert5.picsback.app.persistence.PictureDetailsEntity;
import tim.al6ert5.picsback.app.processor.PictureProcessor;
import tim.al6ert5.picsback.app.scanner.PictureScanner;

@Component
public class Runner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Runner.class);

	@Autowired
	private PictureScanner pictureScanner;

	@Autowired
	private PictureProcessor pictureProcessor;

	@Override
	public void run(String... args) throws Exception {
		logger.debug("Hello World!");

		pictureScanner.scan();

		if (pictureScanner.hasResults()) {
			logger.info("Found Pictures!");

			for (PictureDetailsEntity pd : pictureScanner.getResults()) {
				logger.debug("Processing: {}", pd);

				logger.info("{}", pictureProcessor.process(pd));
			}
		} else {
			logger.info("No Pictures!");

		}
	}

}
