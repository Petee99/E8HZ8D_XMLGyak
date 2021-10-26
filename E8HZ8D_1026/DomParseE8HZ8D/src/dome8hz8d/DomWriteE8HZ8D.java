package dome8hz8d;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class DomWriteE8HZ8D {

	public static void main(String argv[]) {

		try {
	         File inputFile = new File("src/dome8hz8d/usersE8HZ8D.xml");
	         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	         Document doc = docBuilder.parse(inputFile);

	         doc.getDocumentElement().setAttribute("xmlns", "E8HZ8D");

	         // write the content on console
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         System.out.println("-----------Modified File-----------");
	         StreamResult consoleResult = new StreamResult(System.out);
	         StreamResult result = new StreamResult(new File("src/dome8hz8d/domusers1E8HZ8D.xml"));
	         transformer.transform(source, result);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
}
