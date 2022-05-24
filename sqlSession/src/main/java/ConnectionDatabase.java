import java.sql.*;

/**
 * @Description:
 * @author: lzj
 * @date: 2022年01月25日 9:06
 */
public class ConnectionDatabase {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        String driver = "com.mysql.jdbc.Driver";

        String url = "jdbc:mysql://localhost:3306/sqltestdb";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "123456";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            if (!connection.isClosed()){
                System.out.println("连接数据库成功");
            }
            // 创建执行器
            Statement statement = connection.createStatement();
            // 执行并得到返回值
            ResultSet resultSet = statement.executeQuery("select * from tmp");
            String id = null;
            while (resultSet.next()){
                // 得到id
                id = resultSet.getString("id");
                System.out.println("id is"+id);
            }
            resultSet.close();
            connection.close();

        }catch (ClassNotFoundException | SQLException cnfe){
            cnfe.printStackTrace();
        }finally {
            if (!connection.isClosed()){
                connection.close();
            }
        }
    }
}
