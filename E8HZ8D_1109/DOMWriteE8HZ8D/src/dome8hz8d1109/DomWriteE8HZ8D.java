package dome8hz8d1109;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomWriteE8HZ8D {
	public static void main(String[] args) {

		try {
	         DocumentBuilderFactory dbFactory =
	         DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         
	         // root element
	         Element rootElement = doc.createElement("users");
	         doc.appendChild(rootElement);
	         Attr attr = doc.createAttribute("xmlns");
	         attr.setValue("dome8hz8d");
	         rootElement.setAttributeNode(attr);
	         
	         // user1 element
	         Element user1 = doc.createElement("user");
	         Attr uid1 = doc.createAttribute("id");
	         uid1.setValue("1");
	         user1.setAttributeNode(uid1);
	         
	         Element firstname1 = doc.createElement("firstname");
	         Element lastname1 = doc.createElement("lastname");
	         Element profession1 = doc.createElement("profession");
	         firstname1.appendChild(doc.createTextNode("Peter"));
	         lastname1.appendChild(doc.createTextNode("Mako"));
	         profession1.appendChild(doc.createTextNode("SW Engineer"));
	         	         	         
	         user1.appendChild(firstname1);
	         user1.appendChild(lastname1);
	         user1.appendChild(profession1);
	         
	         // user2 element
	         Element user2 = doc.createElement("user");
	         Attr uid2 = doc.createAttribute("id");
	         uid2.setValue("2");
	         user2.setAttributeNode(uid2);
	         
	         Element firstname2 = doc.createElement("firstname");
	         Element lastname2 = doc.createElement("lastname");
	         Element profession2 = doc.createElement("profession");
	         
	         firstname2.appendChild(doc.createTextNode("Chandler"));
	         lastname2.appendChild(doc.createTextNode("Bing"));
	         profession2.appendChild(doc.createTextNode("Transponster"));
	         
	         user2.appendChild(firstname2);
	         user2.appendChild(lastname2);
	         user2.appendChild(profession2);
	         
	         // user3 element
	         Element user3 = doc.createElement("user");
	         Attr uid3 = doc.createAttribute("id");
	         uid3.setValue("3");
	         user3.setAttributeNode(uid3);
	         
	         Element firstname3 = doc.createElement("firstname");
	         Element lastname3 = doc.createElement("lastname");
	         Element profession3 = doc.createElement("profession");
	         
	         firstname3.appendChild(doc.createTextNode("Joey"));
	         lastname3.appendChild(doc.createTextNode("Tribbiani"));
	         profession3.appendChild(doc.createTextNode("Actor"));
	         
	         user3.appendChild(firstname3);
	         user3.appendChild(lastname3);
	         user3.appendChild(profession3);
	         
	         // append elements
	         rootElement.appendChild(user1);
	         rootElement.appendChild(user2);
	         rootElement.appendChild(user3);
   
	         // write to file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	         transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File("src/user1E8HZ8D.xml"));
	         transformer.transform(source, result);
	         
	         // Output to console for testing
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
}
