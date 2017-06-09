package codigohernancho.app.prueba.com.inventariodecompras;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import baseDeDatos.EntradasSqliteHelper;
import objetos.Entrada;

public class ENTRADAS extends AppCompatActivity {

    EditText codigoABuscar;
    EditText nombreProductoABuscar;
    EditText cantidadARegistrar;
    EditText stockMax;
    EditText nombreProducto;
    EditText marca;
    EditText unidad;
    EditText descripcion;
    EditText stockMin;
    Button adicionar;
    EntradasSqliteHelper u;


    public ENTRADAS()
    {
//        createProducto();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);
        u = new EntradasSqliteHelper(this);

        codigoABuscar = (EditText) findViewById(R.id.txtcodigoABuscar);
        nombreProductoABuscar = (EditText) findViewById(R.id.txtnombreProductoABuscar);
        cantidadARegistrar = (EditText) findViewById(R.id.txtcantidad);


        stockMax = (EditText) findViewById(R.id.txtstockActualProductoEncontrado);
        stockMax.setEnabled(false);

        nombreProducto = (EditText) findViewById(R.id.txtnombreProductoEncontrado);
        nombreProducto.setEnabled(false);

        marca = (EditText) findViewById(R.id.txtmarcaProductoEncontrado);
        marca.setEnabled(false);

        unidad = (EditText) findViewById(R.id.txtunidadProductoEncontrado);
        unidad.setEnabled(false);

        descripcion = (EditText) findViewById(R.id.txtdescripcionProductoEncontrado);
        descripcion.setEnabled(false);

        stockMin = (EditText) findViewById(R.id.txtstockMinProductoEncontrado);
        stockMin.setEnabled(false);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_entradas, menu);
        return  true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                this.finish();
                return true;
            }

            case R.id.action_add:
            {
                String stockMa= stockMax.getText().toString();
                String cant= cantidadARegistrar.getText().toString();
                String cod= codigoABuscar.getText().toString();
                String nomprod = nombreProducto.getText().toString();
                String marc= marca.getText().toString();
                String unid= unidad.getText().toString();
                String descr= descripcion.getText().toString();
                String sockMi= stockMin.getText().toString();

                Toast.makeText(this, "El usuario es: "+nomprod+" "+marc+" "+unid, Toast.LENGTH_LONG ).show();

                return  true;
            }
            default:
            {
                return  super.onOptionsItemSelected(item);
            }
        }


    }

    public void guardar_clicked(View view){
        Cursor cursor=null;
        u.createProducto(new Entrada(nombreProductoABuscar.getText().toString(), Integer.parseInt(codigoABuscar.getText().toString())));

    }

    public void modificar_clicked(View view){
        int id = Integer.parseInt( codigoABuscar.getText().toString() );
        Cursor c = u.entradabyid(id, nombreProductoABuscar.getText().toString());

        codigoABuscar.setText(c.getString(c.getColumnIndexOrThrow("id")));
        nombreProducto.setText(c.getString(c.getColumnIndexOrThrow("nombreProducto")));
        cantidadARegistrar.setText(c.getString(c.getColumnIndexOrThrow("cantidad")));
    }

    public void limpiar_clicked(View view){
        codigoABuscar.setText("");
        nombreProductoABuscar.setText("");
    }

    public void limpiarCajas()
    {
        stockMax.setText("");
        cantidadARegistrar.setText("");
        codigoABuscar.setText("");
        nombreProducto.setText("");
        marca.setText("");
        unidad.setText("");
        descripcion.setText("");
        stockMin.setText("");
    }


}
