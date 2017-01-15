package tim.al6ert5.picsback.app.scanner;

import tim.al6ert5.picsback.app.persistence.PictureDetailsEntity;

public interface PictureScanner {

	public void scan();
	public boolean hasResults();
	public Iterable<PictureDetailsEntity> getResults();
}
