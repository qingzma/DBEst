package org.dbest.core.sqlobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dbest.commons.Config;
import org.dbest.commons.DbestStrings;
import org.dbest.commons.io.DbestFileSystem;
import org.dbest.parser.DbestSQLParser;
import org.dbest.parser.DbestSQLParser.Show_models_statementContext;
import org.dbest.sqlparser.SqlParser;

public class ShowModelsQuery extends DbestQuery {
    private String schema;
    private static final long serialVersionUID= -6139981086530749812L;
    private String sql;
    private Logger log = LogManager.getLogger(getClass());
//    private static Config config;
    private static DbestFileSystem fileSystem;

    public ShowModelsQuery(String sql) {
        this.sql = sql;
        this.parse();
//        config = new Config();
        execute(false);
    }

    public void visit(Show_models_statementContext show_models_statementContext){
        try{
            this.schema = show_models_statementContext.schema.getText();
        }catch (NullPointerException e){
            this.schema=fileSystem.getWorkingSchema();
//            log.debug(DbestStrings.EXCEPTION_MODEL_SCHEMA_NOT_PROVIDED);
        }
    }

    @Override
    public String getSql() {
        return sql;
    }

    @Override
    public void parse() {
        DbestSQLParser p = SqlParser.parse(this.sql);
        visit(p.show_models_statement());
    }



    @Override
    public synchronized void execute(boolean getResult) {
        fileSystem= new DbestFileSystem();
        fileSystem.showModels(schema);
        fileSystem.close();
    }
}
