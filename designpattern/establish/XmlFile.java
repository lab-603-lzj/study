

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * xml文件解析
 */
public class XmlFile {

    private DocumentBuilder builder;

    private List<Map<String,String>> mapList = new ArrayList<>();

    public XmlFile() {
        init();
    }

    /**
     * 初始化documentBuilder对象
     */
    private void init() {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取document对象
     *
     * @param file
     * @return
     */
    private Document getDocument(File file) {
        try {
            if (builder == null) {
                return null;
            }
            Document document = builder.parse(file);
            return document;
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void xmlParse(Document document) throws TransformerException {
        Element element = document.getDocumentElement();
        NodeList img = element.getElementsByTagName("p");
        for (int i = 0; i < img.getLength(); i++) {
            Node item = img.item(i);
            NamedNodeMap attributes = item.getAttributes();
            for (int j = 0; j < attributes.getLength(); j++) {
                Node item1 = attributes.item(j);
                System.out.println(item1.getNodeName()+"="+item1.getNodeValue());
                //这里重新设置值
                item1.setNodeValue("text-indent: 0em; text-align: left;");
                System.out.println("==================重新设置的值===================");
                System.out.println(item1.getNodeName()+"="+item1.getNodeValue());
            }

        }



        TransformerFactory formerFactory=TransformerFactory.newInstance();
        Transformer transformer=formerFactory.newTransformer();
        // 换行
        transformer.setOutputProperty(OutputKeys.INDENT, "YES");
        // 文档字符编码
        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");

        // 可随意指定文件的后缀,效果一样,但xml比较好解析,比如: E:\\person.txt等
        transformer.transform(new DOMSource(document),new StreamResult(new File("file/test2.html")));



    }

    public static void main(String[] args) throws TransformerException {
        XmlFile xmlFile = new XmlFile();
        File file = new File("C:\\Users\\Liuzhengjin\\Desktop\\test.html");
        Document document = xmlFile.getDocument(file);
        xmlFile.xmlParse(document);


    }
}
