package com.letterball.utils;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ColumnUtils {

    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://192.168.198.129:3306/tensquare_user?useSSL=false&serverTimezone=Asia/Shanghai";
            String user = "root";
            String pass = "123456";
            conn = DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 获取表列名称
     * @param columnCount
     * @return
     */
    public List getColumnName(int columnCount,String sql) {

        Connection conn=getConnection();
        PreparedStatement stmt;
        ArrayList<String> columnList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);
            ResultSetMetaData data=rs.getMetaData();

            columnList = new ArrayList<>();
            for (int i = 1; i < columnCount+1; i++) {
                String columnName = data.getColumnName(i);
                columnList.add(columnName);
            }
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
        }
        return columnList;
    }


    public static void main(String[] args){
        Connection conn=getConnection();
        String sql="select * from tb_user";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);
            ResultSetMetaData data=rs.getMetaData();

            while(rs.next()){
                for(int i = 1 ; i<= data.getColumnCount() ; i++){
                    //获得所有列的数目及实际列数
                    int columnCount=data.getColumnCount();

                    //获得指定列的列名
                    String columnName = data.getColumnName(i);

                    //获得指定列的列值
                    String columnValue = rs.getString(i);

                    //获得指定列的数据类型
                    int columnType=data.getColumnType(i);

                    //获得指定列的数据类型名
                    String columnTypeName=data.getColumnTypeName(i);

                    //所在的Catalog名字
                    String catalogName=data.getCatalogName(i);

                    //对应数据类型的类
                    String columnClassName=data.getColumnClassName(i);

                    //在数据库中类型的最大字符个数
                    int columnDisplaySize=data.getColumnDisplaySize(i);

                    //默认的列的标题
                    String columnLabel=data.getColumnLabel(i);

                    //获得列的模式
                    String schemaName=data.getSchemaName(i);

                    //某列类型的精确度(类型的长度)
                    int precision= data.getPrecision(i);

                    //小数点后的位数
                    int scale=data.getScale(i);

                    //获取某列对应的表名
                    String tableName=data.getTableName(i);

                    // 是否自动递增
                    boolean isAutoInctement=data.isAutoIncrement(i);

                    //在数据库中是否为货币型
                    boolean isCurrency=data.isCurrency(i);

                    //是否为空
                    int isNullable=data.isNullable(i);

                    //是否为只读
                    boolean isReadOnly=data.isReadOnly(i);

                    //能否出现在where中
                    boolean isSearchable=data.isSearchable(i);

                }
            }
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
        }
    }
}
