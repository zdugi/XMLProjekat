package com.organ.project_organ.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.organ.project_organ.repository.Repository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.Charset;

public abstract class AbsService {
    private DocumentBuilderFactory documentFactory;

    private TransformerFactory transformerFactory;

    private String xslPath;

    private String fontPath;


    public AbsService(String xslPath, String fontPath) {
        this.xslPath = xslPath;
        this.fontPath = fontPath;

        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();
    }

    protected ByteArrayOutputStream generatePDF(String requestId, Repository repository) throws IOException, DocumentException {
        // Step 1
        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Step 2
        PdfWriter writer = PdfWriter.getInstance(document, out);

        // Step 3
        document.open();

        ByteArrayInputStream stream = new ByteArrayInputStream(generateHTML(requestId, repository).toString().getBytes());

        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontImp.register(fontPath);
        FontFactory.setFontImp(fontImp);

        // Step 4
        XMLWorkerHelper.getInstance()
                .parseXHtml(writer, document, stream, null, Charset.forName("UTF-8"), fontImp);

        // Step 5
        document.close();

        return out;
    }

    private org.w3c.dom.Document buildDocument(InputStream is) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(is);

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;

        }

        return document;
    }

    protected StringWriter generateHTML(String requestId, Repository repository) throws FileNotFoundException {

        try {
            StringWriter os = repository.getOneXMLStream(requestId);

            StringWriter htmlOutput = new StringWriter();

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(new ByteArrayInputStream(os.toString().getBytes())));
            StreamResult result = new StreamResult(htmlOutput);
            transformer.transform(source, result);

            return htmlOutput;
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public abstract ByteArrayOutputStream getOneRDF(String id) throws Exception;

    public abstract ByteArrayOutputStream getOneJSON(String id) throws Exception;
}
