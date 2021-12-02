/*
 * Ez a DOM lek�rdezo program az adott XMLE8HZ8D.xml f�jlon fog tudni v�gigmenni, �s k�l�nb�zo lek�rdez�seket v�gezni rajta, nevezetesen 3mat:
 * 1) Term�kekhez tartoz� besz�ll�t�k
 * 2) 60 ezer forintn�l olcs�bb term�kek
 * 3) 60 ezer forintn�l dr�g�bb rendel�sekhez tartoz� viszontelad� �s term�k(ek)
 */

package hu.domparse.e8hz8d;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class DOMQueryE8HZ8D {

	public static void main(String argv[]) {

		try {
			File inputFile = new File("../XMLE8HZ8D.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("-----------------------------------------------------------------------------------");

			NodeList equipments = doc.getElementsByTagName("snowboard_felszereles");
			NodeList suppliers = doc.getElementsByTagName("beszallito");
			NodeList deliveries = doc.getElementsByTagName("szallitja"); 
			NodeList orders = doc.getElementsByTagName("rendeles");
			NodeList resellers = doc.getElementsByTagName("viszontelado");

			//Query no.1: Term�kekhez tartoz� besz�ll�t�k
			System.out.println("Term�kekhez tartoz� besz�ll�t�k:\n");

			for (int eIndex = 0; eIndex < equipments.getLength(); eIndex++) {
				Node equipment = equipments.item(eIndex);
				
				if (equipment.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) equipment;
					System.out.println("Felszerel�s:\n " 
							+ eElement
							.getElementsByTagName("marka")
							.item(0)
							.getTextContent() + " "
							+ eElement
							.getElementsByTagName("nev")
							.item(0)
							.getTextContent() + ": ");

					for (int dIndex = 0; dIndex < deliveries.getLength(); dIndex++) {
						Node delivery = deliveries.item(dIndex);

						if (delivery.getNodeType() == Node.ELEMENT_NODE) {
							Element dElement = (Element) delivery;

							for (int sIndex = 0; sIndex < suppliers.getLength(); sIndex++) {
								Node supplier = suppliers.item(sIndex);

								if (supplier.getNodeType() == Node.ELEMENT_NODE) {
									Element sElement = (Element) supplier; 

									if (eElement.getAttribute("azonosito").equals(dElement.getAttribute("termek")) &&
											sElement.getAttribute("adoszam").equals(dElement.getAttribute("beszallito"))) {

										System.out.println(" -> Besz�ll�t�: " 
												+ sElement
												.getElementsByTagName("cegnev")
												.item(0)
												.getTextContent()
												+ "\n");
									}
								}
							}
						}
					}
				}
			}

			//Query no.2: 60 ezer forintn�l olcs�bb term�kek
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("60 ezer forintn�l olcs�bb term�kek:\n");

			for (int eIndex = 0; eIndex < equipments.getLength(); eIndex++) {
				Node equipment = equipments.item(eIndex);

				if (equipment.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) equipment;

					if (Double.valueOf(eElement.getElementsByTagName("ar").item(0).getTextContent())<60000) {
						System.out.println("Felszerel�s:\n " 
								+ eElement
								.getElementsByTagName("marka")
								.item(0)
								.getTextContent() + " "
								+ eElement
								.getElementsByTagName("nev")
								.item(0)
								.getTextContent() + ": ");
						System.out.println("�r:\n " 
								+ eElement
								.getElementsByTagName("ar")
								.item(0)
								.getTextContent()
								+ "\n");
					}
				}
			}	         

			//Query no.3: 60 ezer forintn�l dr�g�bb rendel�sekhez tartoz� viszontelad� �s term�k(ek)
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("60 ezer forintn�l dr�g�bb rendel�sekhez tartoz� viszontelad� �s term�k(ek):\n");

			for (int oIndex = 0; oIndex < orders.getLength(); oIndex++) {
				Node order = orders.item(oIndex);

				if (order.getNodeType() == Node.ELEMENT_NODE) {
					Element oElement = (Element) order;

					for (int eIndex = 0; eIndex < equipments.getLength(); eIndex++) {
						Node equipment = equipments.item(eIndex);

						if (equipment.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) equipment;

							for (int rIndex = 0; rIndex < resellers.getLength(); rIndex++) {
								Node reseller = resellers.item(rIndex);

								if (reseller.getNodeType() == Node.ELEMENT_NODE) {
									Element rElement = (Element) reseller;

									if (oElement.getAttribute("termek").equals(eElement.getAttribute("azonosito")) &&
											oElement.getAttribute("viszontelado").equals(rElement.getAttribute("adoszam"))) {

										double  oSize = Double.valueOf(oElement.getElementsByTagName("mennyiseg").item(0).getTextContent());
										double ePrice = Double.valueOf(eElement.getElementsByTagName("ar").item(0).getTextContent());

										if (ePrice * oSize > 60000) {
											System.out.println("Rendel�s (no. "
													+ oElement
													.getAttribute("rendeles_id")
													+ ") ("
													+ ePrice*oSize 
													+" Ft)");
											System.out.println(" -> Viszontelad�: " 
													+ rElement
													.getElementsByTagName("cegnev")
													.item(0)
													.getTextContent());
											System.out.println(" -> Felszerel�s: " 
													+ oElement
													.getElementsByTagName("mennyiseg")
													.item(0)
													.getTextContent() + "x "
													+ eElement
													.getElementsByTagName("marka")
													.item(0)
													.getTextContent() + " "
													+ eElement
													.getElementsByTagName("nev")
													.item(0)
													.getTextContent()
													+ "\n");
										}
									}
								}	                  
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
