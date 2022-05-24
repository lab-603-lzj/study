import sun.awt.datatransfer.DataTransferer;

import java.util.*;

/**
 * @Description:
 * @author: lzj
 * @date: 2022年02月14日 15:32
 */
public class test {
    public static void main(String[] args) {
        //TreeMap<String, Object> map = new TreeMap<>(getCachedAliasComparator());
        //map.put("rrr","32312");
        //map.put("ppp","32312");
        //map.put("www","32312");
        //map.put("vvv","32312");
        //Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        //while (iterator.hasNext()){
        //    Map.Entry<String, Object> next = iterator.next();
        //    System.out.println(next.getKey()+":  "+next.getValue());
        //}
        test();
    }


    private static Comparator<String> getCachedAliasComparator(){
        Set<String> keySet = new HashSet<String>(){{
            add("www");
            add("ppp");
            add("ddd");
            add("aaa");
            add("vvv");
        }};
        IndexedComparator<String> stringIndexedComparator = new IndexedComparator<>(keySet.toArray(new String[0]));
        return stringIndexedComparator;
    }


    private static void test(){
        Integer a = Integer.valueOf(0);
        Integer b = Integer.valueOf(1);
        Integer c = null;

        System.out.println(a & b);
        System.out.println(a & c);
    }
}
