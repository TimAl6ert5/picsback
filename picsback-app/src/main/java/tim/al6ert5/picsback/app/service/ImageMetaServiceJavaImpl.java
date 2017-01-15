package tim.al6ert5.picsback.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@Component
@Profile("javaMeta")
public class ImageMetaServiceJavaImpl implements ImageMetaService {

	@Override
	public String getMetaData(Path file) throws IOException {
		// reference: http://johnbokma.com/java/obtaining-image-metadata.html
		
		StringBuilder buf = new StringBuilder();

		ImageInputStream iis = ImageIO.createImageInputStream(new File(file.toString()));
		Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
		
		if (readers.hasNext()) {

            // pick the first available ImageReader
            ImageReader reader = readers.next();

            // attach source to the reader
            reader.setInput(iis, true);

            // read metadata of first image
            IIOMetadata metadata = reader.getImageMetadata(0);

            String[] names = metadata.getMetadataFormatNames();
            int length = names.length;
            for (int i = 0; i < length; i++) {
                buf.append( "Format name: " + names[ i ] );
                displayMetadata(metadata.getAsTree(names[i]), buf);
            }
        }
		return buf.toString();
	}

    void displayMetadata(Node root, StringBuilder buf) {
        displayMetadata(root, 0, buf);
    }

    void indent(int level, StringBuilder buf) {
        for (int i = 0; i < level; i++)
        	buf.append("    ");
    }

    void displayMetadata(Node node, int level, StringBuilder buf) {
        // print open tag of element
        indent(level, buf);
        buf.append("<" + node.getNodeName());
        NamedNodeMap map = node.getAttributes();
        if (map != null) {

            // print attribute values
            int length = map.getLength();
            for (int i = 0; i < length; i++) {
                Node attr = map.item(i);
                buf.append(" " + attr.getNodeName() +
                                 "=\"" + attr.getNodeValue() + "\"");
            }
        }

        Node child = node.getFirstChild();
        if (child == null) {
            // no children, so close element and return
        	buf.append("/>");
            return;
        }

        // children, so close current tag
        buf.append(">");
        while (child != null) {
            // print children recursively
            displayMetadata(child, level + 1, buf);
            child = child.getNextSibling();
        }

        // print close tag of element
        indent(level, buf);
        buf.append("</" + node.getNodeName() + ">");
    }
}
