import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest {

    public static void simpleWrite(){
        String name = "E:\\ideaItem\\study\\src\\easyexcel\\EasyExcelTest\\src\\main\\resources\\"
                +"测试excel.xls";
        EasyExcel.write(name,Cols.class).sheet().doWrite(data());
    }

    public static void simpleRead(){
        String path = "E:\\ideaItem\\study\\src\\easyexcel\\EasyExcelTest\\src\\main\\resources\\"
                +"读取用excel.xls";
        EasyExcelFactory.read(path,Cols.class,new DemoListener()).sheet("读取用excel").doRead();
    }

    public static void multiRead(){
        String path = "E:\\ideaItem\\study\\src\\easyexcel\\EasyExcelTest\\src\\main\\resources\\"
                +"读取用excel.xls";
        ExcelReader reader = null;
        try {
            reader = EasyExcel.read(path).build();
            ReadSheet readSheet = EasyExcelFactory.readSheet(0).head(Cols.class).registerReadListener(new DemoListener()).headRowNumber(0).build();
            reader.read(readSheet);
        }finally {
            if (reader!=null){
                reader.finish();
            }
        }
    }

    public static List<Cols> data(){
        List<Cols> cols = new ArrayList<Cols>();
        for (int i = 0; i < 100; i++) {
            Cols cols1 = new Cols();
            cols1.setCol1(i+"");
            cols1.setCol2(i+""+i);
            cols1.setCol3(i+""+i+i);
            cols.add(cols1);
        }
        return cols;
    }

    public static void main(String[] args) {
        //simpleWrite();
        //simpleRead();
        multiRead();
    }
}

class DemoListener extends AnalysisEventListener<Cols>{
    private static final int BATCH_COUNT = 5;
    List<Cols> colsList = new ArrayList<Cols>();

    public void invoke(Cols cols, AnalysisContext analysisContext) {
        colsList.add(cols);
        if (colsList.size()>=BATCH_COUNT){
            for (Cols cols1:colsList){
                System.out.println(cols1.toString());
            }
            colsList.clear();
        }
    }

    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        for (Cols cols1:colsList){
            System.out.println(cols1.toString());
        }
    }

}

