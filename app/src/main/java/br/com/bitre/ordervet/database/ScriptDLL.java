package br.com.bitre.ordervet.database;

/**
 * Created by b1204126 on 29/10/2017.
 */

public class ScriptDLL {

    public static String getCreateTableGaranhao(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS GARANHAO (");
        sql.append("        CODIGO          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("        NOME            VARCHAR (255)   NOT NULL    DEFAULT (''),   ");
        sql.append("        LOCAL           VARCHAR (255)   NOT NULL    DEFAULT (''),   ");
        sql.append("        ENDERECO        VARCHAR (255)   NOT NULL    DEFAULT (''),   ");
        sql.append("        NUMERO          VARCHAR (255)   NOT NULL    DEFAULT (''),   ");
        sql.append("        COMPLEMENTO     VARCHAR (255)   ,   ");
        sql.append("        BAIRRO          VARCHAR (200)   NOT NULL    DEFAULT (''),   ");
        sql.append("        UF              VARCHAR (2)   NOT NULL    DEFAULT (''),   ");
        sql.append("        CIDADE          VARCHAR (200)    NOT NULL    DEFAULT (''))   ");

        return sql.toString();
    }
}
