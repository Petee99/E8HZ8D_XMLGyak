package SaxE8HZ8D1019;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxE8HZ8D {

	public static void main(String[] args) {
		try {
			SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
			
			SAXParser saxParser= saxParserFactory.newSAXParser();
			
			SaxHandler saxHandler= new SaxHandler();
			
			saxParser.parse(new File("src/SaxE8HZ8D1019/szemelyekE8HZ8D.xml"), saxHandler);
			
		}catch(ParserConfigurationException | SAXException | IOException e)
		{
			e.printStackTrace();
		}
	}
}

class SaxHandler extends DefaultHandler{
	
	private int indent=0;
	
	boolean nev = false;
	boolean kor = false;
	boolean varos = false;
	
	 @Override
	 public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		 indent++;
		 indent();
		 System.out.println(qName +formatAttributes(attributes)+" start");
	 }
	 
	 @Override
	 public void endElement(String uri, String localName, String qName) throws SAXException {
		 indent();
		 indent--;
		 System.out.println(qName+" end");
	 }
	 
	 
	 @Override
	 public void characters(char ch[], int start, int length) throws SAXException {
		 String chars= new String(ch,start,length).trim();
		 if(!chars.isEmpty()) {
			 indent++;
			 indent();
			 indent--;
			 System.out.println(chars);
		 }
	 }
	
	private String formatAttributes(Attributes attributes) {
		int attrLength=attributes.getLength();
		if(attrLength==0) {
			return "";
		}
		StringBuilder sb= new StringBuilder(", {");
		for(int i=0; i<attrLength;i++) {
			sb.append(attributes.getLocalName(i)+":"+attributes.getValue(i));
			if(i<attrLength-1) {
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
		
	}
	private void indent() {
		for(int i=0;i<indent;i++) {
			System.out.println("   ");
		}
	}
	
}
