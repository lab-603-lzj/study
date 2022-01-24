package com.example.poi.pdf.itext.util;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.*;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


/**
 * @Description:
 * @author: lzj
 * @date: 2021年10月20日 16:10
 */
@Slf4j
public class PdfUtil {

    private static float _PT_CM_RATE = 0.352736F;

    // 定义字体
    private static PdfFont helvetica;
    private static PdfFont helveticaBold;

    static {
        try {
            helvetica = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            helveticaBold = PdfFontFactory.createFont(StandardFonts.COURIER_BOLD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //simpleWrite("test.pdf");
        //canvasWrite("canvas.pdf");
        //writeRender("render.pdf");
        //writeAnnotation("annotation.pdf");
        writePdfVery("resource.pdf","dest.pdf");
    }

    public static void simpleWrite(String fileName) throws IOException {
        File dest = new File(System.getProperty("user.dir")+File.separator+fileName);
        log.info(dest.getAbsolutePath());
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        PdfFont headFont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);


        document.add(new Paragraph("hello word"));

        // 修改字体
        document.setFont(font);
        // 添加图片
        document.add(new Paragraph("添加无序列表 add ol:"));
        // 默认字体StandardFonts.HELVETICA
        List list = new List().setSymbolIndent(12).setListSymbol("\u2022");
        list.add(new ListItem("Never gonna give you up"))
                .add(new ListItem("Never gonna let you down"))
                .add(new ListItem("Never gonna run around and desert you"))
                .add(new ListItem("Never gonna make you cry"))
                .add(new ListItem("Never gonna say goodbye"))
                .add(new ListItem("Never gonna tell a lie and hurt you"));
        // document 能够添加三种：块元素（IBlockElement）、页隔断（AreaBreak）、图片（Image）
        document.add(list);

        //添加图片
        Image image = new Image(ImageDataFactory.create("https://img2.baidu.com/it/u=411374890,3609130831&fm=253&fmt=auto&app=138&f=JPEG?w=499&h=281"));
        image.setHeight(120).setWidth(160);
        document.setFont(headFont);
        document.add(new Paragraph("\nthis is a picture \n      ").add(image).add("this is picture end"));

        // 添加表格
        Table table = new Table(new float[]{50,200,250});
        Iterator<String> iterator = new ArrayList<String>() {{
            add("data1,data2,data3");
            add("data1,data2,data3");
        }}.iterator();
        table.addCell(new Cell().add(new Paragraph("head1")));
        table.addCell(new Cell().add(new Paragraph("head2")));
        table.addCell(new Cell().add(new Paragraph("head3")));
        while (iterator.hasNext()) {
            Iterator<String> iterator1 = Arrays.asList(iterator.next().split(",")).iterator();
            while (iterator1.hasNext()){
                table.addCell(new Cell().add(new Paragraph(iterator1.next())));
            }
        }
        document.add(new Paragraph("\n\nthis is table\n\t                ").add(table).add("\n this is table end"));

        // 关闭文档,会隐式的关闭pdfDocument
        document.close();
    }

    public static void canvasWrite(String fileName) throws IOException {
        File dest = new File(System.getProperty("user.dir")+File.separator+fileName);
        log.info(dest.getAbsolutePath());
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        // 横置的A4纸大小
        PageSize pageSize = PageSize.A4.rotate();
        PdfPage page = pdf.addNewPage(pageSize);
        PdfCanvas canvas = new PdfCanvas(page);

        // 移动坐标系到中心,核心方法是变化矩阵
        canvas.concatMatrix(1, 0, 0, 1, pageSize.getWidth() / 2, pageSize.getHeight() / 2);

        // 画出坐标轴
        // 画出x轴
        canvas.moveTo(-(pageSize.getWidth() / 2 - 15), 0)
                .lineTo(pageSize.getWidth() / 2 - 15, 0)
                .stroke();
        // 画出x轴箭头:两条线相交
        canvas.setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.ROUND)
                .moveTo(pageSize.getWidth() / 2 - 25, -10)
                .lineTo(pageSize.getWidth() / 2 - 15, 0)
                .lineTo(pageSize.getWidth() / 2 - 25, 10).stroke()
                .setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.MITER);
        // 画出y轴
        canvas.moveTo(0,-(pageSize.getHeight())/2 -15)
                .lineTo(0,pageSize.getHeight()/2 -15)
                .stroke();
        // 画出y轴箭头
        canvas.saveState()
                .setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.ROUND)
                .moveTo(-10, pageSize.getHeight() / 2 - 25)
                .lineTo(0, pageSize.getHeight() / 2 - 15)
                .lineTo(10, pageSize.getHeight() / 2 - 25).stroke()
                .restoreState();
        // x轴刻度:40画一个刻度，从y轴-5画到5
        for (int i = -((int) pageSize.getWidth() / 2 - 61);
             i < ((int) pageSize.getWidth() / 2 - 60); i += 40) {
            canvas.moveTo(i, 5).lineTo(i, -5);
        }
        // y轴刻度:40画一个刻度，从x轴-5画到5
        for (int j = -((int) pageSize.getHeight() / 2 - 57);
             j < ((int) pageSize.getHeight() / 2 - 56); j += 40) {
            canvas.moveTo(5, j).lineTo(-5, j);
        }
        canvas.stroke();

        // 添加网线格
        Color grayColor = new DeviceCmyk(0.f, 0.f, 0.f, 0.875f);
        Color greenColor = new DeviceCmyk(1.f, 0.f, 1.f, 0.176f);
        Color blueColor = new DeviceCmyk(1.f, 0.156f, 0.f, 0.118f);

        canvas.setLineWidth(0.1f).setStrokeColor(blueColor);
        for (int i = -((int) pageSize.getHeight() / 2 - 57);
             i < ((int) pageSize.getHeight() / 2 - 56); i += 40) {
            canvas.moveTo(-(pageSize.getWidth() / 2 - 15), i)
                    .lineTo(pageSize.getWidth() / 2 - 15, i);
        }
        canvas.stroke();
        canvas.setLineWidth(0.1f).setStrokeColor(greenColor);
        for (int j = -((int) pageSize.getWidth() / 2 - 61);
             j < ((int) pageSize.getWidth() / 2 - 60); j += 40) {
            canvas.moveTo(j, -(pageSize.getHeight() / 2 - 15))
                    .lineTo(j, pageSize.getHeight() / 2 - 15);
        }
        canvas.stroke();

        // 画虚线
        canvas.setLineWidth(2).setStrokeColor(greenColor)
                .setLineDash(10, 10, 8)
                .moveTo(-(pageSize.getWidth() / 2 - 15), -(pageSize.getHeight() / 2 - 15))
                .lineTo(pageSize.getWidth() / 2 - 15, pageSize.getHeight() / 2 - 15).stroke();

        canvas.setLineWidth(2).setStrokeColor(greenColor)
                .setLineDash(10, 10, 8)
                .moveTo(-(pageSize.getWidth() / 2 - 15), (pageSize.getHeight() / 2 -15))
                .lineTo(pageSize.getWidth() / 2 -15, -(pageSize.getHeight() / 2 -15)).stroke();


        // 添加文字
        ArrayList<String> text = new ArrayList<String>() {{
            add("         Episode V         ");
            add("  THE EMPIRE STRIKES BACK  ");
            add("It is a dark time for the");
            add("Rebellion. Although the Death");
            add("Star has been destroyed,");
            add("Imperial troops have driven the");
            add("Rebel forces from their hidden");
            add("base and pursued them across");
            add("the galaxy.");
            add("Evading the dreaded Imperial");
            add("Starfleet, a group of freedom");
            add("fighters led by Luke Skywalker");
            add("has established a new secret");
            add("base on the remote ice world");
            add("of Hoth...");
        }};
        Iterator<String> textIterator = text.iterator();
        // 移动坐标系到左上角
        canvas.concatMatrix(1, 0, 0, 1, -pageSize.getWidth()/2, pageSize.getHeight()/2);
        canvas.beginText()
                .setFontAndSize(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD), 14)
                .setLeading(14 * 1.2f)
                .moveText(70, -40);
        while (textIterator.hasNext()) {
            canvas.newlineShowText(textIterator.next());
        }
        canvas.endText();

        // 炫酷的文字
        canvas.rectangle(0, 0, pageSize.getWidth(), pageSize.getHeight());

        canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD),1)
                .setColor(DeviceCmyk.YELLOW,true);
        float lineHeight = 5;
        float yOffset = -40;
        for (int j = 0; j < text.size(); j++) {
            String line = text.get(j);
            float xOffset = pageSize.getWidth() / 2 - 45 - 8 * j;
            float fontSizeCoeff = 6 + j;
            float lineSpacing = (lineHeight + j) * j / 1.5f;
            int stringWidth = line.length();
            for (int i = 0; i < stringWidth; i++) {
                float angle = (16 - i) / 2f;
                float charXOffset = (4 + (float) j / 2) * i;
                canvas.setTextMatrix(fontSizeCoeff, 0,
                        angle, fontSizeCoeff / 1.5f,
                        xOffset + charXOffset, yOffset - lineSpacing)
                        .showText(String.valueOf(line.charAt(i)));
            }
        }
        canvas.endText();


        pdf.close();
    }

    public static void writeRender(String fileName) throws IOException {
        File dest = new File(System.getProperty("user.dir")+File.separator+fileName);
        log.info(dest.getAbsolutePath());
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));


        // 设置页眉页脚并添加水印
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new EndPageEventHandler());


        PageSize pageSize = PageSize.A5;
        Document document = new Document(pdf, pageSize);

        // 使用文档渲染器渲染结果:将一页文档按照三列渲染
        // 设置一些列需要使用的参数
        float offSet = 36;
        float columnWidth = (pageSize.getWidth() - offSet * 2 + 10) / 3;
        float columnHeight = pageSize.getHeight() - offSet * 2;
        // 定义列的方框
        Rectangle[] columns = {
                new Rectangle(offSet - 5, offSet, columnWidth, columnHeight),
                new Rectangle(offSet + columnWidth, offSet, columnWidth, columnHeight),
                new Rectangle(
                        offSet + columnWidth * 2 + 5, offSet, columnWidth, columnHeight)};
        document.setRenderer(new ColumnDocumentRenderer(document, columns));
        // 添加内容
        Image image = new Image(
                ImageDataFactory.create("https://img2.baidu.com/it/u=411374890,3609130831&fm=253&fmt=auto&app=138&f=JPEG?w=499&h=281"))
                .setWidth(columnWidth);
        String articleInstagram = new String(
                Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"inword.txt")), StandardCharsets.UTF_8);
        // 写入内容
        PdfFont timesNewRomanBold = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        Paragraph title = new Paragraph("Instagram May Change Your Feed, Personalizing It With an Algorithm")
                .setFont(timesNewRomanBold)
                .setFontSize(14);
        document.add(title);
        document.add(image);
        Paragraph author = new Paragraph()
                .setFont(timesNewRomanBold)
                .setFontSize(7)
                .setFontColor(new DeviceRgb(128,128,128))
                .add("By MIKE ISAAC MARCH 15, 2016");
        document.add(author);
        Paragraph article = new Paragraph()
                .setFont(timesNewRomanBold)
                .setFontSize(10)
                .add(articleInstagram);
        document.add(article);


        // 创建新的页面，使用cell渲染器自定义table

        PageSize ps = new PageSize(842, 680);

        // 创建新页并关闭上一渲染器
        document = new Document(document.getPdfDocument(),ps);
        document.add(new AreaBreak(ps));


        // 设置列表属性
        Table table = new Table(new float[]{50,200,250});
        table.setWidth(600)
                .setTextAlignment(TextAlignment.CENTER)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);

        // 设置数据
        ArrayList<String> arrayList = new ArrayList<String>() {};
        for (int i = 0; i < 20; i++) {
            arrayList.add("data1,data2,data3");
        }
        Iterator<String> dataIterator = arrayList.iterator();

        int columnNumber = 0;
        for (String value : new ArrayList<String>() {{
            add("header1");
            add("header2");
            add("header3");
        }}) {
            columnNumber++;
            Cell cell = new Cell().add(new Paragraph(value));
            cell.setNextRenderer(new RoundedCornersCellRenderer(cell));
            cell.setPadding(5f).setBorder(null).setFont(helveticaBold).setHeight(40f);
            switch (columnNumber) {
                case 1:
                    cell.setBackgroundColor(DeviceRgb.GREEN);
                    break;
                case 2:
                    cell.setBackgroundColor(DeviceRgb.RED);
                    break;
                case 3:
                    cell.setBackgroundColor(DeviceRgb.BLUE);
                    break;
                default:
                    cell.setBackgroundColor(DeviceRgb.WHITE);
            }
            table.addHeaderCell(cell);
        }

        while (dataIterator.hasNext()) {
            for (String s : dataIterator.next().split(",")) {
                columnNumber++;
                Cell cell = new Cell().setHeight(40f).add(new Paragraph(s));
                cell.setFont(helvetica).setBorder(new SolidBorder(DeviceRgb.BLACK, 0.5f));
                table.addCell(cell);
            }
        }

        document.setHorizontalAlignment(HorizontalAlignment.RIGHT).add(table);
        document.setFontSize(30f).add(new Paragraph("tmp 999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999"));
        document.close();


    }

    // 自定义渲染器
    private static class RoundedCornersCellRenderer extends CellRenderer {
        public RoundedCornersCellRenderer(Cell modelElement) {
            super(modelElement);
        }

        @Override
        public void drawBorder(DrawContext drawContext) {
            Rectangle rectangle = getOccupiedAreaBBox();
            float llx = rectangle.getX() + 1;
            float lly = rectangle.getY() + 1;
            float urx = rectangle.getX() + getOccupiedAreaBBox().getWidth() - 1;
            float ury = rectangle.getY() + getOccupiedAreaBBox().getHeight() - 1;
            PdfCanvas canvas = drawContext.getCanvas();
            float r = 4;
            float b = 0.4477f;
            canvas.moveTo(llx, lly).lineTo(urx, lly).lineTo(urx, ury - r)
                    .curveTo(urx, ury - r * b, urx - r * b, ury, urx - r, ury)
                    .lineTo(llx + r, ury)
                    .curveTo(llx + r * b, ury, llx, ury - r * b, llx, ury - r)
                    .lineTo(llx, lly).stroke();
            super.drawBorder(drawContext);
        }
    }

    // 自定义页结束事件处理器
    private static class EndPageEventHandler implements IEventHandler {

        @SneakyThrows
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
            PdfDocument document = documentEvent.getDocument();
            PdfPage page = documentEvent.getPage();
            int pageNumber = document.getPageNumber(page);
            Rectangle pageSize = page.getPageSize();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), document);


            // 设置背景颜色
            DeviceCmyk limeColor = new DeviceCmyk(0.208f, 0, 0.584f, 0);
            DeviceCmyk blueColor = new DeviceCmyk(0.445f, 0.0546f, 0, 0.0667f);
            canvas.saveState()
                    .setFillColor(pageNumber%2 == 1?limeColor:blueColor)
                    .rectangle(pageSize.getLeft(),pageSize.getBottom(),pageSize.getWidth(),pageSize.getHeight())
                    .fill().restoreState();


            // 添加页眉跟页脚
            canvas.beginText()
                    .setFontAndSize(helvetica,9)
                    .moveText(pageSize.getWidth()/2 -60,pageSize.getTop() -20)
                    .showText("THE TRUTH IS OUT THERE")
                    .moveText(60,-pageSize.getTop() +30)
                    .showText(String.valueOf(pageNumber))
                    .endText();

            // 添加水印
            Canvas pdfCanvas = new Canvas(canvas, document, page.getPageSize());
            pdfCanvas.setProperty(Property.FONT_COLOR,new TransparentColor(DeviceRgb.WHITE));
            pdfCanvas.setProperty(Property.FONT_SIZE, UnitValue.createPointValue(60));
            pdfCanvas.setProperty(Property.FONT,helveticaBold);

            pdfCanvas.showTextAligned(new Paragraph("CONFIDENTIAL"),298,422,document.getPageNumber(page),TextAlignment.CENTER, VerticalAlignment.MIDDLE,45);
            pdfCanvas.flush();
        }

    }

    public static void writeAnnotation(String fileName) throws IOException {
        File dest = new File(System.getProperty("user.dir")+File.separator+fileName);
        log.info(dest.getAbsolutePath());
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        PageSize pageSize = PageSize.A4;
        PdfPage page = pdf.addNewPage(pageSize);
        Document document = new Document(pdf, pageSize);


        // 创建线注释
        PdfArray lineEndings = new PdfArray();
        lineEndings.add(new PdfName("diamond"));
        lineEndings.add(new PdfName("diamond"));
        PdfAnnotation lineAnonotation = new PdfLineAnnotation(new Rectangle(0, 0),
                new float[]{20, pageSize.getHeight()-30, pageSize.getWidth()-20, pageSize.getHeight()-30})
                .setLineEndingStyles((lineEndings))
                .setContentsAsCaption(true)
                .setTitle(new PdfString("iText"))
                .setContents("The example of line annotation")
                .setColor(DeviceRgb.BLUE);
        page.addAnnotation(lineAnonotation);


        // 创建链接注释
        PdfLinkAnnotation linkAnnotation = new PdfLinkAnnotation(new Rectangle(0,0))
                .setAction(PdfAction.createURI("http://itextpdf.com/"));
        Link link = new Link("itextpdf",linkAnnotation);
        document.add(new Paragraph("The example of link annotation. Click ")
                        .add(link.setUnderline())
                        .add("to learn more"));


        // 文本标记注释 open方法未找到，使用setflag替换不了
        PdfAnnotation textMarkupAnnotation = PdfTextMarkupAnnotation.createHighLight(new Rectangle(105,760,64,10),
                new float[]{169, 790, 105, 790, 169, 800, 105, 800})
                .setColor(DeviceCmyk.YELLOW)
                .setTitle(new PdfString("Hello !"))
                .setContents("I'm a popup.")
                .setTitle(new PdfString("iText"))
                .setFlag(PdfAnnotation.INVISIBLE)
                .setRectangle(new PdfArray(new float[]{100, 600, 200, 100}));
        page.addAnnotation(textMarkupAnnotation);

        // 交互式表单AcroForm


        // 关闭文件流
        document.close();
    }

    public static void writePdfVery(String src,String dest) throws IOException {
        File destFile = new File(System.getProperty("user.dir")+File.separator+dest);
        File srcFile = new File(System.getProperty("user.dir")+File.separator+src);
        log.info(destFile.getAbsolutePath());
        log.info(srcFile.getAbsolutePath());

        // 源文档
        PdfDocument srcDocument = new PdfDocument(new PdfReader(src));
        // 目标文档
        PdfDocument destDocument = new PdfDocument(new PdfWriter(dest));

        PdfPage srcPage = srcDocument.getPage(1);
        Rectangle srcPageSize = srcPage.getPageSizeWithRotation();

        // 将第一页缩放为A4大小
        PdfPage destPage = destDocument.addNewPage(PageSize.A4.rotate());
        PdfCanvas canvas = new PdfCanvas(destPage);
        AffineTransform transform = AffineTransform.getScaleInstance(
                destPage.getPageSize().getWidth()/ srcPageSize.getWidth(),
                destPage.getPageSize().getHeight()/ srcPageSize.getHeight());
        canvas.concatMatrix(transform);

        PdfFormXObject xObject = srcPage.copyAsFormXObject(destDocument);
        canvas.addXObject(xObject,0,0);

        // 将第二页分裂为4个A5大小
        PageSize splitSize = new PageSize(srcPageSize.getWidth()/2f,srcPageSize.getHeight()/2f);
        AffineTransform transform2 = AffineTransform.getScaleInstance(
                (splitSize.getWidth()* 2f)/ srcPageSize.getWidth(),
                (splitSize.getHeight() * 2f)/ srcPageSize.getHeight());
        PdfPage secondSrcPage = srcDocument.getPage(2);
        PdfFormXObject secondSrcForm = secondSrcPage.copyAsFormXObject(destDocument);
        // 第一块
        PdfPage firstPage = destDocument.addNewPage(splitSize);
        canvas = new PdfCanvas(firstPage);
        canvas.concatMatrix(transform2);
        canvas.addXObject(secondSrcForm,0,-secondSrcPage.getPageSize().getHeight()/ 2f);
        // 第二块
        PdfPage secondPage = destDocument.addNewPage(splitSize);
        canvas = new PdfCanvas(secondPage);
        canvas.concatMatrix(transform2);
        canvas.addXObject(secondSrcForm,-secondSrcPage.getPageSize().getWidth()/ 2f,-secondSrcPage.getPageSize().getHeight()/ 2f);
        // 第三块
        PdfPage thirdPage = destDocument.addNewPage(splitSize);
        canvas = new PdfCanvas(thirdPage);
        canvas.concatMatrix(transform2);
        canvas.addXObject(secondSrcForm,0,0);
        // 第四块
        PdfPage fourthPage = destDocument.addNewPage(splitSize);
        canvas = new PdfCanvas(fourthPage);
        canvas.concatMatrix(transform2);
        canvas.addXObject(secondSrcForm,-secondSrcPage.getPageSize().getWidth()/ 2f,0);

        // 聚集pdf
        PdfPage thirdSrcPage = srcDocument.getPage(3);
        PdfPage fourthSrcPage = srcDocument.getPage(4);
        PageSize focusSize = new PageSize(thirdSrcPage.getPageSize().getWidth()+ fourthSrcPage.getPageSize().getWidth(),
                Math.max(thirdSrcPage.getPageSize().getHeight(), fourthSrcPage.getPageSize().getHeight()));

        PdfFormXObject thirdSrcForm = thirdSrcPage.copyAsFormXObject(destDocument);
        PdfFormXObject fourthSrcForm = fourthSrcPage.copyAsFormXObject(destDocument);


        PdfPage focusPage = destDocument.addNewPage(focusSize);
        canvas = new PdfCanvas(focusPage);
        canvas.addXObject(thirdSrcForm,0,0);
        canvas.addXObject(fourthSrcForm,thirdSrcPage.getPageSize().getWidth(),0);


        // 关闭文档
        srcDocument.close();
        destDocument.close();
    }



}
