package com.example.poi.pdf.itext.util;

import com.example.poi.pdf.itext.util.other.FileType;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: pdf转换
 * @author: lzj
 * @date: 2021年10月26日 9:16
 */
public class PdfReplace {

    private static final String exePath = "D:\\pdf2htmlEX\\pdf2htmlEX-win32-0.14.6-with-poppler-data\\pdf2htmlEX.exe";
    // main方法，用来测试
    public static void main(String[] args) throws IOException {

        //pdf2Html(System.getProperty("user.dir")+File.separator+"resource.pdf");
        //html2Word(pdf2Html(System.getProperty("user.dir")+File.separator+"navicat.pdf"),"navicat");
        //
        String htmlPath = System.getProperty("user.dir")+File.separator+"resource"+FileType.HTML;
        //
        File file = new File(htmlPath);
        FileInputStream inputStream = new FileInputStream(file);
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        for (int n = 0; (n=inputStream.read(bytes))!=-1; ) {
            stringBuffer.append(new String(bytes, 0, n));
        }



        //String context = new StringBuffer()
        //        .append("<!DOCTYPE html>\n" + "<!-- Created by pdf2htmlEX (https://github.com/coolwanglu/pdf2htmlex) -->\n" + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + "<head>\n" + "<meta charset=\"utf-8\"/>\n" + "<meta name=\"generator\" content=\"pdf2htmlEX\"/>\n" + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"/>")
        //        .append("<style type=\"text/css\">\n" + "/*! \n" + " * Base CSS for pdf2htmlEX\n" + " * Copyright 2012,2013 Lu Wang <coolwanglu@gmail.com> \n" + " * https://github.com/coolwanglu/pdf2htmlEX/blob/master/share/LICENSE\n" + " */#sidebar{position:absolute;top:0;left:0;bottom:0;width:250px;padding:0;margin:0;overflow:auto}#page-container{position:absolute;top:0;left:0;margin:0;padding:0;border:0}@media screen{#sidebar.opened+#page-container{left:250px}#page-container{bottom:0;right:0;overflow:auto}.loading-indicator{display:none}.loading-indicator.active{display:block;position:absolute;width:64px;height:64px;top:50%;left:50%;margin-top:-32px;margin-left:-32px}.loading-indicator img{position:absolute;top:0;left:0;bottom:0;right:0}}@media print{@page{margin:0}html{margin:0}body{margin:0;-webkit-print-color-adjust:exact}#sidebar{display:none}#page-container{width:auto;height:auto;overflow:visible;background-color:transparent}.d{display:none}}.pf{position:relative;background-color:white;overflow:hidden;margin:0;border:0}.pc{position:absolute;border:0;padding:0;margin:0;top:0;left:0;width:100%;height:100%;overflow:hidden;display:block;transform-origin:0 0;-ms-transform-origin:0 0;-webkit-transform-origin:0 0}.pc.opened{display:block}.bf{position:absolute;border:0;margin:0;top:0;bottom:0;width:100%;height:100%;-ms-user-select:none;-moz-user-select:none;-webkit-user-select:none;user-select:none}.bi{position:absolute;border:0;margin:0;-ms-user-select:none;-moz-user-select:none;-webkit-user-select:none;user-select:none}@media print{.pf{margin:0;box-shadow:none;page-break-after:always;page-break-inside:avoid}@-moz-document url-prefix(){.pf{overflow:visible;border:1px solid #fff}.pc{overflow:visible}}}.c{position:absolute;border:0;padding:0;margin:0;overflow:hidden;display:block}.t{position:absolute;white-space:pre;font-size:1px;transform-origin:0 100%;-ms-transform-origin:0 100%;-webkit-transform-origin:0 100%;unicode-bidi:bidi-override;-moz-font-feature-settings:\"liga\" 0}.t:after{content:''}.t:before{content:'';display:inline-block}.t span{position:relative;unicode-bidi:bidi-override}._{display:inline-block;color:transparent;z-index:-1}::selection{background:rgba(127,255,255,0.4)}::-moz-selection{background:rgba(127,255,255,0.4)}.pi{display:none}.d{position:absolute;transform-origin:0 100%;-ms-transform-origin:0 100%;-webkit-transform-origin:0 100%}.it{border:0;background-color:rgba(255,255,255,0.0)}.ir:hover{cursor:pointer}</style>")
        //        .append("<style type=\"text/css\">\n" + "/*! \n" + " * Fancy styles for pdf2htmlEX\n" + " * Copyright 2012,2013 Lu Wang <coolwanglu@gmail.com> \n" + " * https://github.com/coolwanglu/pdf2htmlEX/blob/master/share/LICENSE\n" + " */@keyframes fadein{from{opacity:0}to{opacity:1}}@-webkit-keyframes fadein{from{opacity:0}to{opacity:1}}@keyframes swing{0{transform:rotate(0)}10%{transform:rotate(0)}90%{transform:rotate(720deg)}100%{transform:rotate(720deg)}}@-webkit-keyframes swing{0{-webkit-transform:rotate(0)}10%{-webkit-transform:rotate(0)}90%{-webkit-transform:rotate(720deg)}100%{-webkit-transform:rotate(720deg)}}@media screen{#sidebar{background-color:#2f3236;background-image:url(\"data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI0IiBoZWlnaHQ9IjQiPgo8cmVjdCB3aWR0aD0iNCIgaGVpZ2h0PSI0IiBmaWxsPSIjNDAzYzNmIj48L3JlY3Q+CjxwYXRoIGQ9Ik0wIDBMNCA0Wk00IDBMMCA0WiIgc3Ryb2tlLXdpZHRoPSIxIiBzdHJva2U9IiMxZTI5MmQiPjwvcGF0aD4KPC9zdmc+\")}#outline{font-family:Georgia,Times,\"Times New Roman\",serif;font-size:13px;margin:2em 1em}#outline ul{padding:0}#outline li{list-style-type:none;margin:1em 0}#outline li>ul{margin-left:1em}#outline a,#outline a:visited,#outline a:hover,#outline a:active{line-height:1.2;color:#e8e8e8;text-overflow:ellipsis;white-space:nowrap;text-decoration:none;display:block;overflow:hidden;outline:0}#outline a:hover{color:#0cf}#page-container{background-color:#9e9e9e;background-image:url(\"data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI1IiBoZWlnaHQ9IjUiPgo8cmVjdCB3aWR0aD0iNSIgaGVpZ2h0PSI1IiBmaWxsPSIjOWU5ZTllIj48L3JlY3Q+CjxwYXRoIGQ9Ik0wIDVMNSAwWk02IDRMNCA2Wk0tMSAxTDEgLTFaIiBzdHJva2U9IiM4ODgiIHN0cm9rZS13aWR0aD0iMSI+PC9wYXRoPgo8L3N2Zz4=\");-webkit-transition:left 500ms;transition:left 500ms}.pf{margin:13px auto;box-shadow:1px 1px 3px 1px #333;border-collapse:separate}.pc.opened{-webkit-animation:fadein 100ms;animation:fadein 100ms}.loading-indicator.active{-webkit-animation:swing 1.5s ease-in-out .01s infinite alternate none;animation:swing 1.5s ease-in-out .01s infinite alternate none}.checked{background:no-repeat url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABYAAAAWCAYAAADEtGw7AAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH3goQDSYgDiGofgAAAslJREFUOMvtlM9LFGEYx7/vvOPM6ywuuyPFihWFBUsdNnA6KLIh+QPx4KWExULdHQ/9A9EfUodYmATDYg/iRewQzklFWxcEBcGgEplDkDtI6sw4PzrIbrOuedBb9MALD7zv+3m+z4/3Bf7bZS2bzQIAcrmcMDExcTeXy10DAFVVAQDksgFUVZ1ljD3yfd+0LOuFpmnvVVW9GHhkZAQcxwkNDQ2FSCQyRMgJxnVdy7KstKZpn7nwha6urqqfTqfPBAJAuVymlNLXoigOhfd5nmeiKL5TVTV+lmIKwAOA7u5u6Lped2BsbOwjY6yf4zgQQkAIAcedaPR9H67r3uYBQFEUFItFtLe332lpaVkUBOHK3t5eRtf1DwAwODiIubk5DA8PM8bYW1EU+wEgCIJqsCAIQAiB7/u253k2BQDDMJBKpa4mEon5eDx+UxAESJL0uK2t7XosFlvSdf0QAEmlUnlRFJ9Waho2Qghc1/U9z3uWz+eX+Wr+lL6SZfleEAQIggA8z6OpqSknimIvYyybSCReMsZ6TislhCAIAti2Dc/zejVNWwCAavN8339j27YbTg0AGGM3WltbP4WhlRWq6Q/btrs1TVsYHx+vNgqKoqBUKn2NRqPFxsbGJzzP05puUlpt0ukyOI6z7zjOwNTU1OLo6CgmJyf/gA3DgKIoWF1d/cIY24/FYgOU0pp0z/Ityzo8Pj5OTk9PbwHA+vp6zWghDC+VSiuRSOQgGo32UErJ38CO42wdHR09LBQK3zKZDDY2NupmFmF4R0cHVlZWlmRZ/iVJUn9FeWWcCCE4ODjYtG27Z2Zm5juAOmgdGAB2d3cBADs7O8uSJN2SZfl+WKlpmpumaT6Yn58vn/fs6XmbhmHMNjc3tzDGFI7jYJrm5vb29sDa2trPC/9aiqJUy5pOp4f6+vqeJ5PJBAB0dnZe/t8NBajx/z37Df5OGX8d13xzAAAAAElFTkSuQmCC)}}</style>")
        //        .append("</head><body>ccccccccccccccccccccccccccccccccccccccccccccccccc</body></html>")
        //        .toString();
        String context = stringBuffer.toString();
        //System.out.println(context.substring(0,1000));
        //System.out.println(context);
        // 处理头部
        Document document = Jsoup.parse(context);
        //document.select("!DOCTYPE").remove();
        //document.before("html").append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> \r\n");
        document.select("html")
                .attr("xmlns","http://www.w3.org/TR/REC-html40")
                .attr("xmlns:v","urn:schemas-microsoft-com:vml")
                .attr("xmlns:o","urn:schemas-microsoft-com:office:office")
                .attr("xmlns:w","urn:schemas-microsoft-com:office:word")
                .attr("xmlns:m","http://schemas.microsoft.com/office/2004/12/omml");


        document.select("meta").remove();
        Document tmp = Jsoup.parse("<meta charset=\"utf-8\"/>" +
                "<meta name=\"ProgId\" content=\"Word.Document\" />" +
                "<meta name=\"Generator\" content=\"Microsoft Word 12\" />" +
                "<meta name=\"Originator\" content=\"Microsoft Word 12\" /> " +
                "<!--[if gte mso 9]><xml><w:WordDocument><w:View>Print</w:View></w:WordDocument></xml><[endif]-->");

        tmp.getElementsByTag("meta").forEach(meta -> {
            System.out.println(meta.toString());
            System.out.println("Tag-----------------------");
            document.head().appendChild(meta);
        });


        String ruleContext = document.normalise().outerHtml();
        //System.out.println(ruleContext.substring(0,2000));
        System.out.println(ruleContext);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ruleContext.getBytes(StandardCharsets.UTF_8));

        //
        html2Word(byteArrayInputStream,"resource");
    }

    /** pdf转html **/
    public static InputStream pdf2Html(String pdfPath) throws IOException {
        File file = new File(pdfPath);
        String dirName = file.getParent();
        System.out.println(dirName);
        String fileName = null;
        if (file.exists()){
            fileName = file.getName().substring(0,file.getName().lastIndexOf("."));
            System.out.println(fileName);
        }
        // 需要使用三方程序pdf2htmlEX.exe,其中参数有
        // --dest-dir 源文件绝对路径 --hdpi 72 --vdpi 72 --embed-image 0 --embed-css 0 --embed-javascript 0 --embed-font 0 目标文件名称
        // 生成的文件在当前目录下
        StringBuffer sb = new StringBuffer();
        sb.append(exePath).append(" ").append(pdfPath)
                .append(" --hdpi 72 --vdpi 72 --embed-image 1 --embed-css 1 --embed-javascript 1 --embed-font 1 ")
                .append(fileName).append(FileType.HTML);
        String cmdStr = sb.toString();
        System.out.println(cmdStr);
        Process process = null;
        BufferedInputStream in = null;
        // 先跳转到想要保存的目录
        try {
            // 运行程序
            System.out.println(cmdStr);
            // 使用ProcessBuilder构建
            ProcessBuilder processBuilder = new ProcessBuilder(cmdStr.split(" "));
            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();


            in = (BufferedInputStream) process.getInputStream();
            StringBuffer out = new StringBuffer();

            byte[] b = new byte[8192];

            int n = 0;
            for (; (n = in.read(b)) != -1;) {
                System.out.println(new String(b, 0, n));
                out.append(new String(b, 0, n));
            }
            System.out.println("----------------------------------------");
            process.waitFor();
            // 将对应文件打开
            String htmlPath = System.getProperty("user.dir")+File.separator + fileName + FileType.HTML;
            System.out.println("htmlPath: "+htmlPath);
            return new FileInputStream(new File(htmlPath));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("执行异常！");
        }finally {
            if (Objects.nonNull(in)){
                in.close();
            }
            if (Objects.nonNull(process)){
                process.destroy();
            }
        }
        return null;
    }

    /** html转word **/
    public static void html2Word(InputStream input,String wordName) throws IOException {
        POIFSFileSystem poifsFileSystem = new POIFSFileSystem();
        String wordPath = System.getProperty("user.dir") + File.separator + wordName + FileType.WORD;
        FileOutputStream outputStream = new FileOutputStream(new File(wordPath));
        //
        //poifsFileSystem.createDocument(input,wordName);
        //poifsFileSystem.writeFilesystem(outputStream);
        //outputStream.close();
        //input.close();

        //POIFSFileSystem poifsFileSystem = new POIFSFileSystem();
        DirectoryEntry directory = poifsFileSystem.getRoot();
        directory.createDocument("resource", input);
        poifsFileSystem.writeFilesystem(outputStream);

        outputStream.close();
        input.close();


    }
}
