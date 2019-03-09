package org.dbest.connection;

import static java.sql.ResultSetMetaData.columnNoNulls;
import static java.sql.ResultSetMetaData.columnNullable;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructField;
import org.dbest.commons.AttributeValueRetrievalHelper;

public class SparkQueryResult extends AttributeValueRetrievalHelper implements DbmsQueryResult {

    private static final long serialVersionUID = -5414810504095360947L;

    List<String> columnNames = new ArrayList<>();

    List<Integer> columnTypes = new ArrayList<>();

    List<String> columnTypeNames = new ArrayList<>();

    List<Row> result = new ArrayList<>();

    DbmsQueryResultMetaData dbmsQueryResultMetaData = new DbmsQueryResultMetaData();

    int cursor = -1;

    public SparkQueryResult(Dataset<Row> dataset) {

        //    Tuple2<String, String>[] colNameAndColType = dataset.dtypes();
        List<Integer> nullable = new ArrayList<>();
        List<String> columnClassName = new ArrayList<>();
        for (StructField structField : dataset.schema().fields()) {
            if (structField.nullable()) {
                nullable.add(columnNullable);
            } else {
                nullable.add(columnNoNulls);
            }
            columnNames.add(structField.name());
            int type = SparkDataTypeConverter.typeInt(structField.dataType());
            columnTypes.add(type);
            columnClassName.add(SparkDataTypeConverter.typeClassName(type));
            columnTypeNames.add(structField.dataType().typeName());
        }
        dbmsQueryResultMetaData.isNullable = nullable;
        dbmsQueryResultMetaData.columnClassName = columnClassName;
//        System.out.println(dataset);
//        if(dataset.rdd().isEmpty()){
//            result= null;
//        }
//        else {
        result = dataset.collectAsList();
//        }
    }

    @Override
    public DbmsQueryResultMetaData getMetaData() {
        return dbmsQueryResultMetaData;
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int index) {
        return columnNames.get(index);
    }

    @Override
    public int getColumnType(int index) {
        return columnTypes.get(index);
    }

    @Override
    public String getColumnTypeName(int index) {
        return columnTypeNames.get(index);
    }

    @Override
    public boolean next() {
        if (cursor < result.size() - 1) {
            cursor++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object getValue(int index) {
        Object value = null;
        try {
            value = (Object) result.get(cursor).get(index);
            // value = resultSet.getObject(index + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public void printContent() {
        StringBuilder row;
        boolean isFirstCol = true;

        // print column names
        row = new StringBuilder();
        for (String col : columnNames) {
            if (isFirstCol) {
                row.append(col);
                isFirstCol = false;
            } else {
                row.append("\t" + col);
            }
        }
        System.out.println(row.toString());

        // print contents
        int colCount = getColumnCount();
        while (this.next()) {
            row = new StringBuilder();
            for (int i = 0; i < colCount; i++) {
                if (i == 0) {
                    row.append(getValue(i).toString());
                } else {
                    row.append("\t");
                    row.append(getValue(i).toString());
                }
            }
            System.out.println(row.toString());
        }
    }

    @Override
    public void rewind() {
        cursor = -1;
    }

    @Override
    public long getRowCount() {
        return result.size();
    }
}
