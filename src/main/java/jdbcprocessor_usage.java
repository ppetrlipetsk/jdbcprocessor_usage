import com.ppsdevelopment.jdbcprocessor.DataBaseConnector;
import com.ppsdevelopment.jdbcprocessor.DataBaseProcessor;

import javax.swing.plaf.metal.MetalFileChooserUI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbcprocessor_usage {
    public static Connection connectDataBaseIntSecurity() {

        String instanceName = "localhost\\MSSQLSERVER";
        String databaseName = "dogc";
        String connectionUrl = "jdbc:sqlserver://%1$s;databaseName=%2$s;integratedSecurity=true";
        Connection c = null;
        try {
            c = DataBaseConnector.connectDataBaseIntSecurity(connectionUrl, instanceName, databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static boolean query() {
        boolean result = false;
        DataBaseProcessor h = new DataBaseProcessor(DataBaseConnector.getConnection());
        String q = "SELECT * FROM [dbo].[extable]";
        try {
            ResultSet r = h.query(q);
            if (r != null) result = true;
            else
                System.out.println("The query result have not ResultSet");
            while(r.next()){
                System.out.println(r.getString("fname"));

            }
            h.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        connectDataBaseIntSecurity();
        query();
        System.out.println("success");

    }

}
