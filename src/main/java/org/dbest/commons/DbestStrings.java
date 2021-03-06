package org.dbest.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbestStrings {
    public static final String METHOD_UNIFORM="uniform";
    public static final String METHOD_HASH="hash";

    public static final String EXCEPTION_SAMPLING_METHOD_NOT_PROVIDED="The sampling method is not provided, the default uniform method is used.";
    public static final String EXCEPTION_SAMPLING_RATIO_NOT_PROVIDED="Sampling ratio is not provided, default 1.0 is used.";
    public static final String EXCEPTION_MODEL_SCHEMA_NOT_PROVIDED="The schema is not provided in the query!";
    public static final String EXCEPTION_ORIGINAL_SCHEMA_NOT_PROVIDED="The original schema is not provided in the query!";
    public static final String EXCEPTION_ORIGINAL_TABLE_NOT_PROVIDED="The original table is not provided in the query!";
    public static final String EXCEPTION_INDEPENDENT_NOT_PROVIDED="The independent attribute is not provided in the query!";
    public static final String EXCEPTION_DEPENDENTS_NOT_PROVIDED="The dependent attribute(s) are not provided in the query!";
    public static final String EXCEPTION_NULL_VALUE="null value passed.";
    public static final String EXCEPTION_OBJECT_TYPE="unexpected object type passed: ";
    public static final String EXCEPTION_GROUPBY_NOT_EXISTS="This query does not contain a GROUP BY attribute.";
    public static final String EXCEPTION_SQL_NOT_SUPPORTED="Can not issue data manipulation statements using ExecutionContext().";


    public static final String SQL_CREATE_SCHEMA="CREATE SCHEMA dbestschema";
    public static final String SQL_CREATE_MODEL_COMPLETE_="CREATE model  ss.modl from ha.tablex INDEPENDENT y DEPENDENT x METHOD uniform RATIO 0.5 group by groups";
    public static final String SQL_IF_NOT_EXISTS=" IF NOT EXISTS ";
    public static final String SQL_SHOW_MODELS="show models";
    public static final String SQL_DROP_MODEL="drop model model1 on modeltable";
    public static final String SQL_DROP_MODELs="drop all models modeltable";
    public static final String SQL_CREATE_DATABASE="create database dbtocreate";
    public static final String SQL_SHOW_DATABASES="show databases";
    public static final String SQL_DROP_DATABASE="drop database dbtodrop";
    public static final String SQL_SHOW_TABLES="SHOW TABLES in db";
    public static final String SQL_SELECT="select count(*) from modeli";


    public static final String LOGGER_NAME="org.dbest";
    public static final String LOGGER_CONVERT_SQL_TO_STRING="Converting the following sql object to string: ";
    public static final String LOGGER_DEFAULT_CONSOLE_LOG_LEVEL="info";
    public static final String LOGGER_DEFAULT_FILE_LOG_LEVEL="debug";


    public static final String DRIVER_NAME="dbest";
    public static final String DRIVER_NAME_MORE="dbestdb";
    public static final String METASTORE_NAME="dbestmetastore";
    public static final String DRIVER_DEFAULT_META_SCHEMA_NAME="metaschema";
    public static final String DRIVER_DEFAULT_TMP_SCHEMA_NAME="tmpschema";
    public static final String DRIVER_DEFAULT_ORIGINAL_SCHEMA="original_schema";
    public static final String DRIVER_DEFAULT_ORIGINAL_TABLE="original_table";
    public static final String DRIVER_DEFAULT_MODEL_SCHEMA="model_schema";
    public static final String DRIVER_DEFAULT_MODEL_TABLE="model_table";
    public static final String DRIVER_DEFAULT_INDEPENDENT="independent";
    public static final List<String> DRIVER_DEFAULT_DEPENDENTS= Arrays.asList("dependents");
    public static final String DRIVER_DEFAULT_SAMPLING_METHOD="model_sampling_method";

    public static final String DEFAULT_MODEL_SCHEMA="dbestdefaultschema";

    public static final int EXIT_NORMAL=0;
    public static final int EXIT_ABNORMAL=1;


}
