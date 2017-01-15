package tim.al6ert5.picsback.app.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tim.al6ert5.picsback.app.properties.ScannerProperties;

@Component
public class DigestService {

	@Autowired
	private ScannerProperties scannerProperties;

	public String digest(Path file) throws IOException {
		InputStream fis = Files.newInputStream(file);
		return digest(fis);
	}

	public String digest(String data) throws IOException {
		InputStream is = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
		return digest(is);
	}

	public String digest(InputStream is) throws IOException {
		String digest;
		switch (scannerProperties.getDigestType()) {
		case "SHA-1":
			digest = org.apache.commons.codec.digest.DigestUtils.sha1Hex(is);
			break;
		case "MD5":
		default:
			digest = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
			break;
		}
		is.close();
		return digest;
	}
}
