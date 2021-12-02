/*
 * Ez a DOM olvasó program egy adott XML fájlon fog tudni végigmenni, és kiírni az xml dokumentum fa szerkezetu tartalmát.
 * A programot egy rekurzív függvény megírásával kiviteleztem, amely minden gyerekelemet és attribútumot megkeres, majd továbblép a következo elemre.
 */

package hu.domparse.e8hz8d;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class DOMReadE8HZ8D {

	public static void main (String[] args) {

		try {
			File inputFile = new File("../XMLE8HZ8D.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); 
			System.out.println("--------------------------------------------------------");
			printNodes(doc.getDocumentElement().getChildNodes(), 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printNodes (NodeList nodes, int depth) {
		for (int index = 0; index < nodes.getLength(); index++) {
			if (nodes.item(index).getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nodes.item(index);
				String eName = eElement.getTagName().substring(0, 1).toUpperCase() + eElement.getTagName().substring(1);
				String indent = "";

				for (int depthIndex = 0; depthIndex<depth; depthIndex++) {
					indent += "\t";
				}

				if (eElement.getChildNodes().getLength() > 1) {
					System.out.println(indent + eName + ": ");
				}
				else {
					System.out.println(indent + eName + ": " + eElement.getTextContent());
				}

				checkForAttributes(eElement.getAttributes(), indent+"\t");
				printNodes(nodes.item(index).getChildNodes(), depth+1);
			}
		}
	}

	private static void checkForAttributes (NamedNodeMap attributes, String indent) {
		for (int index = 0; index<attributes.getLength(); index++) {
			Attr attribute = (Attr) attributes.item(index);
			String aName = attribute.getNodeName().substring(0, 1).toUpperCase() + attribute.getNodeName().substring(1);
			System.out.println(indent + aName + ": " + attribute.getNodeValue());
		}
	}
}