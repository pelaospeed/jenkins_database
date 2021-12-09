package DataBaseTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Db_test {
    Connection con = null;
    ResultSet rs = null;
    ResultSet rs_02 = null;


    @BeforeClass
    void setup() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
    }

    @AfterClass
    void tearDown() throws SQLException {
        con.close();
    }

    @Test
    public void schema_compare_01() throws SQLException {
        SoftAssert softAssert = new SoftAssert();
        rs = con.createStatement().executeQuery("SELECT column_name,\n" +
                "       ordinal_position,\n" +
                "       is_nullable,\n" +
                "       data_type,\n" +
                "       character_maximum_length,\n" +
                "       numeric_precision,\n" +
                "       numeric_scale\n" +
                "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE TABLE_SCHEMA = 't_01' AND TABLE_NAME ='t_tbl'" + "ORDER BY ordinal_position;");

        rs_02 = con.createStatement().executeQuery("SELECT column_name,\n" +
                "       ordinal_position,\n" +
                "       is_nullable,\n" +
                "       data_type,\n" +
                "       character_maximum_length,\n" +
                "       numeric_precision,\n" +
                "       numeric_scale\n" +
                "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE TABLE_SCHEMA = 't_02' AND TABLE_NAME ='t_tbl'" +
                "ORDER BY ordinal_position;");
        softAssert.assertEquals(compareResultSets(rs, rs_02), true);
        softAssert.assertAll();
    }

    @Test
    public void schema_compare_02() throws SQLException {
        SoftAssert softAssert = new SoftAssert();
        rs = con.createStatement().executeQuery("SELECT column_name,\n" +
                "       ordinal_position,\n" +
                "       is_nullable,\n" +
                "       data_type,\n" +
                "       character_maximum_length,\n" +
                "       numeric_precision,\n" +
                "       numeric_scale\n" +
                "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE TABLE_SCHEMA = 't_01' AND TABLE_NAME ='t_tbl'" + "ORDER BY ordinal_position;");

        rs_02 = con.createStatement().executeQuery("SELECT column_name,\n" +
                "       ordinal_position,\n" +
                "       is_nullable,\n" +
                "       data_type,\n" +
                "       character_maximum_length,\n" +
                "       numeric_precision,\n" +
                "       numeric_scale\n" +
                "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE TABLE_SCHEMA = 't_03' AND TABLE_NAME ='t_tbl'" +
                "ORDER BY ordinal_position;");
        softAssert.assertEquals(compareResultSets(rs, rs_02), true);
        softAssert.assertAll();
    }

    @Test
    public void rows_compare_01() throws SQLException {
        SoftAssert softAssert = new SoftAssert();
        rs = con.createStatement().executeQuery("SELECT count(*) as Total FROM t_01.t_tbl");

        rs_02 = con.createStatement().executeQuery("SELECT count(*) as Total FROM t_03.t_tbl");
        softAssert.assertEquals(compareRowResult(rs,rs_02),true);
        softAssert.assertAll();
    }

    @Test
    public void rows_compare_02() throws SQLException {
        SoftAssert softAssert = new SoftAssert();
        rs = con.createStatement().executeQuery("SELECT count(*) as Total FROM t_01.t_tbl");

        rs_02 = con.createStatement().executeQuery("SELECT count(*) as Total  FROM t_04.t_tbl");
        softAssert.assertEquals(compareRowResult(rs,rs_02),true);
        softAssert.assertAll();
    }

    public boolean compareResultSets(ResultSet rs, ResultSet rs2) throws SQLException {
        boolean flag = true;
        while (rs.next()) {
            rs2.next();
            int count = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= count; i++) {
                if (!(String.valueOf(rs.getObject(i)).equals(String.valueOf(rs2.getObject(i))))) {
                    System.out.println("This column has a difference: " + rs.getMetaData().getColumnName(i) + " " + rs.getObject(i) + " " + rs2.getObject(i) + "\n");
                    flag = false;
                }
            }
        }
        return flag;
    }

    public boolean compareRowResult(ResultSet rs, ResultSet rs2) throws SQLException {
        int row_01=0,row_02 = 0;
        boolean flag=true;
        while (rs.next()){
            row_01 = rs.getInt("Total");
        }
        while (rs2.next()){
            row_02 = rs2.getInt(1);
        }
        if(row_01 != row_02){
            System.out.println("The number of files are: RS 1: " + row_01 + " RS 2: " + row_02  );
            flag = false;
        }
        return flag;
    }
}