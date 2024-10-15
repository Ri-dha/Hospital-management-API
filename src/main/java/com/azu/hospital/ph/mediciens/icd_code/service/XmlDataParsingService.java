package com.azu.hospital.ph.mediciens.icd_code.service;
import com.azu.hospital.ph.mediciens.icd_code.entity.IcdCodClass;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlDataParsingService {

    public Page<IcdCodClass> parseXmlData(Pageable pageable) throws Exception {
        List<IcdCodClass> parsedData = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource("icdCode/icd-drug.xml");
        InputStream inputStream = resource.getInputStream();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);

        Element rootElement = document.getDocumentElement();
        NodeList mainTermNodes = rootElement.getElementsByTagName("mainTerm");
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), mainTermNodes.getLength());

        for (int i = start; i < end; i++) {
            Element mainTermElement = (Element) mainTermNodes.item(i);
            IcdCodClass dataObject = new IcdCodClass();
            dataObject.setTitle(getElementTextContent(mainTermElement));
            extractedAllCells(mainTermElement, dataObject);

            parsedData.add(dataObject);
        }
        return new PageImpl<>(parsedData, pageable, mainTermNodes.getLength());
    }

    private void extractedAllCells(Element mainTermElement, IcdCodClass dataObject) {
        dataObject.setCell2(getElementTextContent(mainTermElement, 2));
        dataObject.setCell3(getElementTextContent(mainTermElement, 3));
        dataObject.setCell4(getElementTextContent(mainTermElement, 4));
        dataObject.setCell5(getElementTextContent(mainTermElement, 5));
        dataObject.setCell6(getElementTextContent(mainTermElement, 6));
        dataObject.setCell7(getElementTextContent(mainTermElement, 7));
    }

    public Page<IcdCodClass> searchByTitle(String searchTerm, Pageable pageable) throws Exception {
        List<IcdCodClass> parsedData = new ArrayList<>();

        // Load and parse the XML file from the classpath
        ClassPathResource resource = new ClassPathResource("icdCode/icd-drug.xml");
        InputStream inputStream = resource.getInputStream();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);

        Element rootElement = document.getDocumentElement();

        NodeList mainTermNodes = rootElement.getElementsByTagName("mainTerm");

        for (int i = 0; i < mainTermNodes.getLength(); i++) {
            Element mainTermElement = (Element) mainTermNodes.item(i);
            String title = getElementTextContent(mainTermElement);


            if (title.toLowerCase().contains(searchTerm.toLowerCase())) {
                IcdCodClass dataObject = new IcdCodClass();
                dataObject.setTitle(title);
                extractedAllCells(mainTermElement, dataObject);

                parsedData.add(dataObject);
            }
        }


        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), parsedData.size());
        List<IcdCodClass> pageData = parsedData.subList(start, end);

        return new PageImpl<>(pageData, pageable, parsedData.size());
    }

    private String getElementTextContent(Element parentElement) {
        Node node = parentElement.getElementsByTagName("title").item(0);
        return (node != null) ? node.getTextContent() : "";
    }

    private String getElementTextContent(Element parentElement, int index) {
        Node node = parentElement.getElementsByTagName("cell").item(index);
        return (node != null) ? node.getTextContent() : "";
    }




}

