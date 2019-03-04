package org.dbest.commons;


import org.dbest.DBEstSingleResult;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.opencsv.CSVWriter;

public class DBEstResultPrinter {
    public static String SingleResultToCSV(DBEstSingleResult singleResult) {
        try {
            Writer writer = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            int colCount = singleResult.getColumnCount();
            String[] row = new String[colCount];

            // append header
            for (int i = 0; i < colCount; i++) {
                row[i] = singleResult.getColumnName(i);
            }
            csvWriter.writeNext(row);

            // append rows
            while (singleResult.next()) {
                for (int i = 0; i < colCount; i++) {
                    row[i] = singleResult.getString(i);
                }
                csvWriter.writeNext(row);
            }

            csvWriter.close();
            return writer.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
