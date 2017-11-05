package br.com.bitre.ordervet.dominio.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.bitre.ordervet.database.DbGateway;
import br.com.bitre.ordervet.dominio.entidades.Garanhao;

/**
 * Created by b1204126 on 29/10/2017.
 */

public class GaranhaoRepositorio {

    private final String TABLE_GARANHAO = "Garanhao";
    private DbGateway gw;

    public GaranhaoRepositorio(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }


    public boolean salvar(Garanhao garanhao){

        //Recebe os parametros da activity
        ContentValues cv = new ContentValues();
        //Associa ao atributo da tabela o valor do atributo da entidade cliente
        cv.put("NOME",garanhao.nome);
        cv.put("LOCAL",garanhao.local);
        cv.put("ENDERECO",garanhao.endereco);
        cv.put("NUMERO",garanhao.nro);
        cv.put("COMPLEMENTO",garanhao.complemento);
        cv.put("BAIRRO",garanhao.bairro);
        cv.put("UF",garanhao.uf);
        cv.put("CIDADE",garanhao.cidade);

        //Associa a parametros o valor da chave da entidade cliente
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(garanhao.codigo);

        if (garanhao.codigo>0)
            return gw.getDatabase().update(TABLE_GARANHAO,cv,"CODIGO=?",new String[]{garanhao.codigo + ""})>0;
            //return gw.getDatabase().update(TABLE_GARANHAO,cv,"CODIGO = ?", parametros)>0;
        else
            return gw.getDatabase().insert(TABLE_GARANHAO,null, cv)>0;
            //return gw.getDatabase().insertOrThrow(TABLE_GARANHAO,null, cv)>0;

    }


}
