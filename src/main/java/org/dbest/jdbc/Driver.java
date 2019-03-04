package org.dbest.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.dbest.commons.DBEstStrings;
import org.dbest.exception.DBEstException;

import com.google.common.base.Joiner;

/**
 * Importat: If the name of this class changes, the change must be reflected in the service file
 * located at: src/main/resources/META-INF/services/java.sql.Driver
 *
 * @author Yongjoo Park
 */
public class Driver implements java.sql.Driver {

    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            System.err.println("Error occurred while registering VerdictDB driver:");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        if (acceptsURL(url)) {
            String newUrl = url;
            try {
                String[] tokens = url.split(":");
                if (tokens.length >= 2
                        && (tokens[1].equalsIgnoreCase("verdict") || tokens[1].equalsIgnoreCase("verdictdb"))) {
                    List<String> newTokens = new ArrayList<>();
                    for (int i = 0; i < tokens.length; ++i) {
                        if (i != 1) newTokens.add(tokens[i]);
                    }
                    newUrl = Joiner.on(":").join(newTokens);
                }
                Connection verdictConnection = new org.dbest.jdbc.DBEstConnection(newUrl, info);
                // System.out.println("VerdictConnection has been created: " + verdictConnection);
                return verdictConnection;
            } catch (DBEstException e) {
                e.printStackTrace();
                throw new SQLException(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        String[] tokens = url.split(":");
        if (tokens.length >= 2
                && (tokens[1].equalsIgnoreCase(DBEstStrings.DRIVER_NAME) || tokens[1].equalsIgnoreCase(DBEstStrings.DRIVER_NAME_MORE))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 5;
    }

    @Override
    public boolean jdbcCompliant() {
        return true;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }
}
