package tim.al6ert5.picsback.app.processor;

import tim.al6ert5.picsback.app.persistence.PictureDetailsEntity;

public interface PictureProcessor {

	public PictureDetailsEntity process(PictureDetailsEntity pd);
}
