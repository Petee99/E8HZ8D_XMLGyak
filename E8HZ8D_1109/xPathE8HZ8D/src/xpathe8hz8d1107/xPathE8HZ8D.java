package xpathe8hz8d1107;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class xPathE8HZ8D {
	public static void main(String[] args) {
	      
	      try {
	         File inputFile = new File("src/hallgatoKE8HZ8D.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder;

	         dBuilder = dbFactory.newDocumentBuilder();

	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();

	         XPath xPath =  XPathFactory.newInstance().newXPath();
	         
	         //1
	         String expression = "/class/student";
	         //2 String expression = "/class/student[@id = '01']";
	         //3 String expression = "//student";
	         //4 String expression = "/class/student[2]";
	         //5 String expression = "/class/student[last()]";
	         //6 String expression = "/class/student[last()-1]";
	         //7 String expression = "/class/student[position()<=2]";
	         //8 String expression = "/class/*";
	         //9 String expression = "//student[@*]";
	         //10 String expression = "//*";
	         //11 String expression = "/class/student[kor>20]"; 
	         //12 String expression = "//student/vezeteknev| //student/keresztnev";
	         	         
	         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
	            doc, XPathConstants.NODESET);
	         
	         for (int i = 0; i < nodeList.getLength(); i++) {
	            Node nNode = nodeList.item(i);
	            System.out.println("\nCurrent Element :" + nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               System.out.println("Student id : " 
	                  + eElement.getAttribute("id"));
	               System.out.println("Keresztnev : " 
	                  + eElement
	                  .getElementsByTagName("keresztnev")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Vezeteknev : " 
	                  + eElement
	                  .getElementsByTagName("vezeteknev")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Becenev : " 
	                  + eElement
	                  .getElementsByTagName("becenev")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Kor : " 
	                  + eElement
	                  .getElementsByTagName("kor")
	                  .item(0)
	                  .getTextContent());
	            }
	         }
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e) {
	         e.printStackTrace();
	      }
	   }
}
