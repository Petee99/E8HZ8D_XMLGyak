/*
 * Ez a DOM v�ltoztat� program az adott XMLE8HZ8D.xml f�jlon fog tudni v�gigmenni 
 * �s megv�ltoztatni bizonyos adatokat, amennyiben ezek a v�ltoztat�sok sz�ks�gesek. 
 * 2 adat v�ltoztat�sa ker�lt meg�r�sra:
 * 1) SnowboardStuff viszontelad� c�mv�ltoz�sa 
 * 2) Snowboard Shop "aaa111" rendel�s�nek v�ltoztat�sa, 2 db Ride Rodeo k�t�sre 
 */

package hu.domparse.e8hz8d;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLObjectElement;

public class DOMModifyE8HZ8D {
	public static void main(String argv[]) {

		try {
			File inputFile = new File("../XMLE8HZ8D.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList equipments = doc.getElementsByTagName("snowboard_felszereles");
			NodeList suppliers = doc.getElementsByTagName("beszallito");
			NodeList deliveries = doc.getElementsByTagName("szallitja"); 
			NodeList orders = doc.getElementsByTagName("rendeles");
			NodeList resellers = doc.getElementsByTagName("viszontelado");

			//Modification no.1: SnowboardStuff viszontelad� c�mv�ltoz�sa 
			for (int rIndex = 0; rIndex < resellers.getLength(); rIndex++) {
				Node reseller = resellers.item(rIndex);

				if (reseller.getNodeType() == Node.ELEMENT_NODE) {
					Element rElement = (Element) reseller;

					if("SnowboardStuff".equals(rElement.getElementsByTagName("cegnev").item(0).getTextContent())) {
						NodeList address = rElement.getElementsByTagName("cim").item(0).getChildNodes();

						for (int aIndex = 0; aIndex < address.getLength(); aIndex++) {
							Node addressPart = address.item(aIndex);

							switch(addressPart.getNodeName()) {
							case "zip":
								addressPart.setTextContent("3522");
								break;
							case "utca":
								addressPart.setTextContent("Karolyi utca");
								break;
							case "hazszam":
								addressPart.setTextContent("35");
								break;
							default:
							}
						}
					}
				}
			}

			//Modification no.2: Snowboard Shop "aaa111" rendel�s�nek v�ltoztat�sa, 2 db Ride Rodeo k�t�sre 
			for (int oIndex = 0; oIndex < orders.getLength(); oIndex++) {
				Node order = orders.item(oIndex);

				if (order.getNodeType() == Node.ELEMENT_NODE) {
					Element oElement = (Element) order;

					if ("aaa111".equals(oElement.getAttribute("rendeles_id"))) {
						for (int rIndex = 0; rIndex < resellers.getLength(); rIndex++) {
							Node reseller = resellers.item(rIndex);

							if (reseller.getNodeType() == Node.ELEMENT_NODE) {
								Element rElement = (Element) reseller;

								if(rElement.getAttribute("adoszam").equals(oElement.getAttribute("viszontelado"))) {
									for (int eIndex = 0; eIndex < equipments.getLength(); eIndex++) {
										Node equipment = equipments.item(eIndex);

										if (equipment.getNodeType() == Node.ELEMENT_NODE) {
											Element eElement = (Element) equipment;

											if(eElement.getElementsByTagName("marka").item(0).getTextContent().equals("Ride") && 
													eElement.getElementsByTagName("nev").item(0).getTextContent().equals("Rodeo")){
												
												oElement.setAttribute("termek", eElement.getAttribute("azonosito"));
												oElement.getElementsByTagName("mennyiseg").item(0).setTextContent("2");
											}
										}
									}
								}
							}
						}
					}
				}
			}

			// write to file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("../XMLE8HZ8D_Modified.xml"));
			transformer.transform(source, result);

			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
