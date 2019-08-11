package net.moonlightflower.wc3libs.port.mac;

import net.moonlightflower.wc3libs.port.GameVersion;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.Nonnull;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class PListGameVersionParser {
    public static File PLIST_FILE = new File("/Applications/Warcraft III/x86_64/Warcraft III.app/Contents/Info.plist");

    public static GameVersion get(@Nonnull File file) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

        doc.normalize();

        Node dict = doc.getDocumentElement().getElementsByTagName("dict").item(0);

        NodeList keyList = ((Element) dict).getElementsByTagName("key");

        for (int i = 0; i < keyList.getLength(); i++) {
            Node key = keyList.item(i);

            if (key.getTextContent().equals("BlizzardFileVersion")) {
                Node val = key.getNextSibling().getNextSibling();

                return new GameVersion(val.getTextContent());
            }
        }

        throw new Exception("key BlizzardFileVersion not found");
    }

    public static GameVersion get() throws Exception {
        return get(PLIST_FILE);
    }
}
