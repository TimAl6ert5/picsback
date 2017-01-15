package tim.al6ert5.picsback.app.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PictureDetailsRepository extends CrudRepository<PictureDetailsEntity, Long>{

	public List<PictureDetailsEntity> findByFile(String file);
	public List<PictureDetailsEntity> findByFileHash(String fileHash);
	public List<PictureDetailsEntity> findByName(String name);
	public List<PictureDetailsEntity> findByChecksum(String checksum);
}
