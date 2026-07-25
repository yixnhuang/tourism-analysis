package Util;

import java.io.*;
import java.util.*;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.*;

public class HBaseUtil {

    private static Connection conn;


    private static void startConn() {
        try {
            conn = Connected.getHbase();  //Get the HBase connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeConn() {
        try {
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//DDL

    /**
     * Create a table
     *
     * @param tableName   Table name
     * @param columnNames Variable-length array of column families
     * @throws Exception
     */
    public static void createTable(String tableName, String... columnNames) throws IOException {
        startConn();
        //Get the table administration object
        Admin admin = conn.getAdmin();
        TableName tableNameObj = TableName.valueOf(Bytes.toBytes(tableName));
        //Check whether the table exists
        if (tableName != null && !tableName.isEmpty()) {

            if (!admin.tableExists(tableNameObj)) {
                HTableDescriptor hdr = new HTableDescriptor(tableNameObj);
                for (String columnName : columnNames) {
                    hdr.addFamily(new HColumnDescriptor(columnName));
                }
                admin.createTable(hdr);
            }
        }
        closeConn();
    }

    /**
     * Delete a table
     *
     * @param tableName Table name
     * @throws Exception
     */
    public static void deleteTable(String tableName) throws Exception {
        startConn();
        if (tableName != null && !tableName.isEmpty()) {
            Admin admin = conn.getAdmin();
            TableName tableNameObj = TableName.valueOf(Bytes.toBytes(tableName));
            admin.disableTable(tableNameObj);
            admin.deleteTable(tableNameObj);
        }
        closeConn();
    }

    /**
     * List all tables
     *
     * @return
     * @throws IOException
     */
    public static List<String> listTables() throws IOException {
        startConn();
        ArrayList<String> strings = new ArrayList<>();
        Admin admin = conn.getAdmin();

        TableName[] tableNames = admin.listTableNames();
        for (TableName tableName : tableNames) {
            strings.add(tableName.toString());
        }

        closeConn();
        return strings;

    }

//DML

    /**
     * Delete data for the specified row key
     *
     * @param tablename
     * @param rowkey
     * @throws Exception
     */
    public static void delDataByRowkey(String tablename, String rowkey) throws Exception {
        startConn();
        Table table = conn.getTable(TableName.valueOf(tablename));
        Delete delete = new Delete(Bytes.toBytes(rowkey));
        table.delete(delete);
        table.close();
        closeConn();
    }

    /**
     *  Store source results in a map
     * @param sourceResults Source results
     * @param familyk   Column family for the map key
     * @param columnk   Column name for the map key
     * @param familyv   Column family for the map value
     * @param columnv   Column name for the map value
     * @return
     * @throws IOException
     */
    public static Map<String,String> getDataByColumn(List<Result> sourceResults, String familyk, String columnk,String familyv, String columnv) throws IOException {
        startConn();
        Scan scan = new Scan();


        byte[] prek = null;
        String k = null;
        byte[] prev = null;
        String v = null;
        HashMap<String, String> soult = new HashMap<>();

        for (Result result : sourceResults) {
            prek = result.getValue(Bytes.toBytes(familyk), Bytes.toBytes(columnk));
            k = Bytes.toString(prek);

            prev = result.getValue(Bytes.toBytes(familyv), Bytes.toBytes(columnv));
            v = Bytes.toString(prev);
            soult.put(k,v);
        }
        closeConn();
        return soult;
    }

    /**
     *  Query results for a city with a result limit
     * @param cityName
     * @param tablename
     * @param dataLength
     * @return
     * @throws IOException
     */
    public static List<Result> getDatabyCName(String cityName, String tablename, int dataLength) throws IOException {
        startConn();
        Table table = conn.getTable(TableName.valueOf(tablename));
        Scan scan = new Scan();

        ResultScanner scanner = table.getScanner(scan);

        ArrayList<Result> value = new ArrayList<Result>();
        byte[] precityName=null;
        for (Result result : scanner) {
            precityName=result.getValue(Bytes.toBytes("cityInfo"), Bytes.toBytes("cityName"));
//            System.out.println(Bytes.toString(precityName));
            if ((dataLength>0)&&Bytes.toString(precityName).equals(cityName)) {
                dataLength--;
                value.add(result);
            }
        }
        closeConn();
    return value;
    }

    /**
     * Delete the specified column
     *
     * @param tablename
     * @param rowkey
     * @param family
     * @param columns
     * @throws Exception
     */
    public static void delDataByColumuns(String tablename, String rowkey, String family, String... columns) throws Exception {
        startConn();
        Table table = conn.getTable(TableName.valueOf(tablename));
        Delete delete = new Delete(Bytes.toBytes(rowkey));

        for (String column : columns) {
            delete.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));
        }

        table.delete(delete);
        table.close();
        closeConn();
    }

    /**
     * Delete data by row key
     *
     * @param tablename
     * @param rowkey
     */
    public static void delDataByRowKey(String tablename, String rowkey) throws Exception {
        startConn();

        Table table = conn.getTable(TableName.valueOf(tablename));

        Delete delete = new Delete(Bytes.toBytes(rowkey));

        table.delete(delete);
        closeConn();
    }

    /**
     * Add data to the specified table
     *
     * @param tablename Table name
     * @param puts      Put objects to add
     * @return long Return the execution time
     * @throws IOException
     */
    public static long putDataByTable(String tablename, List<Put> puts) throws Exception {
        startConn();
        long currentTime = System.currentTimeMillis();

        Table table = conn.getTable(TableName.valueOf(Bytes.toBytes(tablename)));
        try {
            table.put(puts);
        } finally {
            table.close();
            closeConn();
        }

        return System.currentTimeMillis() - currentTime;  //Return the insertion time in milliseconds
    }


    /**
     * Scan a table
     *
     * @param tName
     * @throws Exception
     */
    public static List<String> scanTable(String tName) throws Exception {
        startConn();
        Table table = conn.getTable(TableName.valueOf(tName));
        ArrayList<String> value = new ArrayList<>();

        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);

        for (Result result : scanner) {
            List<Cell> cells = result.listCells();
            List<String> strings = printFormat(cells);
            value.addAll(strings);
        }
        table.close();
        closeConn();

        return value;
    }

    public static void clearTable(String tablename) throws IOException {
        startConn();
        Table table = conn.getTable(TableName.valueOf(tablename));

        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);

        Delete delete = null;
        for (Result result : scanner) {
            delete = new Delete(result.getRow());
            table.delete(delete);
        }

        closeConn();
    }

    /**
     * Get data by row key
     *
     * @param tablename
     * @param rowkey
     * @return
     * @throws IOException
     */
    public static List<Cell> getDataByRowkey(String tablename, String rowkey) throws Exception {
        startConn();
        Table table = conn.getTable(TableName.valueOf(tablename));
        Get get = new Get(Bytes.toBytes(rowkey));
        Result result = table.get(get);

        List<Cell> cells = result.listCells();
        table.close();
        closeConn();
        return cells;
    }

    /**
     * Print cells
     *
     * @param cells
     * @return
     * @throws Exception
     */
    public static List<String> printFormat(List<Cell> cells) throws Exception {
        startConn();
        ArrayList<String> strings = new ArrayList<>();

        byte[] row = null;
        byte[] family = null;
        byte[] qualifier = null;
        byte[] value = null;

        String format = null;
        for (Cell cell : cells) {
            row = CellUtil.cloneRow(cell); //rowkey
            family = CellUtil.cloneFamily(cell);
            qualifier = CellUtil.cloneQualifier(cell);
            value = CellUtil.cloneValue(cell);

            format = String.format("rowkey:%s\tcolumnFamily:%s\tcolumn:%s\tvalue:%s"
                    , Bytes.toString(row), Bytes.toString(family), Bytes.toString(qualifier), Bytes.toString(value));

            strings.add(format);
        }
        closeConn();
        return strings;
    }


    /**
     * Row count
     *
     * @param tName
     * @return
     */
    public static long getRowCount(String tName) throws Exception {
        startConn();
        long rowCount = 0;
        try {
            TableName tableName = TableName.valueOf(Bytes.toBytes(tName));
            Table table = conn.getTable(tableName);
            Scan scan = new Scan();
            ResultScanner resultScanner = table.getScanner(scan);
            for (Result result : resultScanner) {
                rowCount += result.size();
            }
            System.out.println("rowCount-->" + rowCount);
        } catch (IOException e) {
        } finally {
            closeConn();
        }
        return rowCount;
    }

}