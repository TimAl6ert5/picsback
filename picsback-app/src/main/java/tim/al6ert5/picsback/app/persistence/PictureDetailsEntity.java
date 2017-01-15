package tim.al6ert5.picsback.app.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

import com.google.common.base.MoreObjects;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"file"})})
public class PictureDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String file;

	private String fileHash;

	@Column(nullable = false)
	private String name;

	private Long size;

	private String creationTime;

	private String lastModifiedTime;

	private String checksum;

	private String contentType;

	@Min(0)
	private int width;

	@Min(0)
	private int height;

	@Column(length=4096)
	private String metaBlob;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getMetaBlob() {
		return metaBlob;
	}

	public void setMetaBlob(String metaBlob) {
		this.metaBlob = metaBlob;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("file", file)
				.add("fileHash", fileHash)
				.add("name", name)
				.add("size", size)
				.add("creationTime", creationTime)
				.add("lastModifiedTime", lastModifiedTime)
				.add("checksum", checksum)
				.add("contentType", contentType)
				.add("width", width)
				.add("height", height)
				.add("metaBlob", metaBlob)
				.toString();
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (obj == null || getClass() != obj.getClass()) {
//			return false;
//		}
//		final PictureDetailsEntity other = (PictureDetailsEntity) obj;
//		return Objects.equals(file, other.file) &&
//				Objects.equals(fileHash, other.fileHash) &&
//				Objects.equals(name, other.name) &&
//				Objects.equals(size, other.size) &&
//				Objects.equals(creationTime, other.creationTime) &&
//				Objects.equals(lastModifiedTime, other.lastModifiedTime) &&
//				Objects.equals(checksum, other.checksum) &&
//				Objects.equals(contentType, other.contentType) &&
//				Objects.equals(width, other.width) &&
//				Objects.equals(height, other.height) &&
//				Objects.equals(metaBlob, other.metaBlob);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(file, fileHash, name, size, creationTime, lastModifiedTime, checksum, contentType, width, height, metaBlob);
//	}
}
