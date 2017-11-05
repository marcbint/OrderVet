package br.com.bitre.ordervet;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.bitre.ordervet.database.DadosOpenHelper;
import br.com.bitre.ordervet.dominio.entidades.Garanhao;
import br.com.bitre.ordervet.dominio.repositorio.GaranhaoRepositorio;

public class actCadastroGaranhao extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtLocal;
    private EditText edtEndereco;
    private EditText edtNro;
    private EditText edtComplemento;
    private EditText edtBairro;
    private Spinner spnUf;
    private String strUf;
    private EditText edtCidade;

    //Cria o objeto cliente repositório para realização de atividades com base de dados.
    private GaranhaoRepositorio garanhaoRepositorio;
    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private ConstraintLayout layoutContentActCadastroGaranhao;
    private Garanhao garanhao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_cadastro_garanhao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //Para exibir o ConstraintLayout
        layoutContentActCadastroGaranhao = (ConstraintLayout)findViewById(R.id.layoutContentActCadGaranhao);

        //criarConexao();

    }

    private void criarConexao(){
        try{
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();
            Snackbar.make(layoutContentActCadastroGaranhao,R.string.message_conexao_criada_com_sucesso,Snackbar.LENGTH_LONG)
                    .setAction(R.string.action_ok,null).show();

            //Cria instância da conexão
            garanhaoRepositorio = new GaranhaoRepositorio(this);

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok,null);
            dlg.show();

        }
    }

    private void confirmar(){
        garanhao = new Garanhao();
        garanhaoRepositorio.salvar(garanhao);
        /*if(validaCampos()==false){
            try{
                garanhaoRepositorio.salvar(garanhao);

                //finaliza a activity
                finish();
            }catch (SQLException ex){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle(R.string.title_erro);
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton(R.string.action_ok, null);
                dlg.show();
            }
        }*/
    }

    private boolean validaCampos(){
        boolean res = false;

        String nome = edtNome.getText().toString();
        String local = edtLocal.getText().toString();
        String endereco = edtEndereco.getText().toString();
        String nro = edtNro.getText().toString();
        String complemento = edtComplemento.getText().toString();
        String bairro = edtBairro.getText().toString();
        //String uf = strUf;
        String cidade = edtCidade.getText().toString();

        //Associa os valores da tela ao objeto Private Garanhao
        garanhao.nome = nome;
        garanhao.local = local;
        garanhao.endereco = endereco;
        garanhao.nro = nro;
        garanhao.complemento = complemento;
        garanhao.bairro = bairro;
        //garanhao.uf = uf;
        garanhao.cidade = cidade;

        if(res = isCampoVazio(nome)){
            edtNome.requestFocus();
        }
        else
            if(res = isCampoVazio(local)){
            edtLocal.requestFocus();
            }
            else
                if (res = isCampoVazio(endereco)){
                edtEndereco.requestFocus();
                }
                else
                    if (res = isCampoVazio(nro)){
                    edtNro.requestFocus();
                    }
                    else
                        if (res = isCampoVazio(bairro)){
                        edtBairro.requestFocus();
                        }
                        /*else
                            if (res = isCampoVazio(uf)){
                            spnUf.requestFocus();
                            }*/
                            else
                                if (res = isCampoVazio(cidade)){
                                edtCidade.requestFocus();
                                }
         if (res){
             AlertDialog.Builder dlg = new AlertDialog.Builder(this);
             dlg.setTitle(R.string.title_aviso);
             dlg.setMessage(R.string.message_campos_invalidos_brancos);
             dlg.setNeutralButton(R.string.action_ok,null);
             dlg.show();
         }

         return res;

    }

    private boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor)|| valor.trim().isEmpty());
        return resultado;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        //Para configurar o menu da tela de cadastro de garanhão
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_garanhao, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Evento de click nos botões de seleção do menu superior
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_ok:
                //confirmar();
                validaCampos();
                // Para exibir mensagem
                Toast.makeText(this, "Botão OK Selecionado", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_cancelar:
                // Para exibir mensagem
                //Toast.makeText(this, "Botão Cancelar Selecionado", Toast.LENGTH_SHORT).show();

                //Fecha o activity
                finish();
                break;
        }


        return super.onOptionsItemSelected(item);
    }





}
