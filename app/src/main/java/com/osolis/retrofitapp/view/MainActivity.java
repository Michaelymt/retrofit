package com.osolis.retrofitapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.osolis.retrofitapp.R;
import com.osolis.retrofitapp.entity.Categoria;
import com.osolis.retrofitapp.entity.request.GrabarCategoriaRequest;
import com.osolis.retrofitapp.rest.HelperWs;
import com.osolis.retrofitapp.rest.MethodWs;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    SweetAlertDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_registrar)
    public void registrar() {

        pd = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pd.getProgressHelper().setBarColor(Color.parseColor("#3F51B5"));
        pd.setContentText("Por favor, espere!!");
        pd.setCancelable(false);
        pd.show();

        GrabarCategoriaRequest grabarCategoriaRequest = new GrabarCategoriaRequest();
        grabarCategoriaRequest.setTxt_name_categoria("Nombre de Yuri");
        grabarCategoriaRequest.setText_desc_categoria("Descripcion de Yuri");

        MethodWs methodWs = HelperWs.getConfiguration().create(MethodWs.class);
       Call<List<Categoria>> response = methodWs.grabarCategoria(grabarCategoriaRequest);
       response.enqueue(new Callback<List<Categoria>>() {
           @Override
           public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
               if (response.isSuccessful()) {
                   List<Categoria> list_categoria = response.body();
                   for (int i = 0; i < list_categoria.size(); i++) {
                       Log.i("michcode", list_categoria.get(i).get_id());
                       Log.i("michcode", list_categoria.get(i).getNombre());
                       Log.i("michcode", list_categoria.get(i).getDescripcion());
                       Log.i("michcode", "" + list_categoria.get(i).get__v());
                   }
                   pd.dismiss();
                   pd = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                   pd.getProgressHelper().setBarColor(Color.parseColor("#3F51B5"));
                   pd.setContentText("Registrado correctamente");
                   pd.setCancelable(false);
                   pd.show();
               } else {
                   pd.dismiss();
                   pd = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE);
                   pd.getProgressHelper().setBarColor(Color.parseColor("#3F51B5"));
                   pd.setContentText("Hubo un problema: " + response.code());
                   pd.setCancelable(false);
                   pd.show();
                   // Log.e("michcode", "Hubo un problema: " + response.code());
               }
           }

           @Override
           public void onFailure(Call<List<Categoria>> call, Throwable t) {
               // Log.e("michcode", t.getMessage());
               pd.dismiss();
               pd = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE);
               pd.getProgressHelper().setBarColor(Color.parseColor("#3F51B5"));
               pd.setContentText("Hubo un problema: " + t.getMessage());
               pd.setCancelable(false);
               pd.show();
           }
       });
    }

    @OnClick(R.id.btn_obtener_una_categoria)
    public void obtenerUnaCategoria() {
        MethodWs methodWs = HelperWs.getConfiguration().create(MethodWs.class);
        Call<Categoria> response = methodWs.obtenerUnaCategoria("5d8bdd33bb99b20400d44c6c");
        response.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                if (response.isSuccessful()) {
                    Categoria categoria = response.body();
                    Log.i("michcode", categoria.get_id());
                    Log.i("michcode", categoria.getNombre());
                    Log.i("michcode", categoria.getDescripcion());
                    Log.i("michcode", "" + categoria.get__v());
                } else {
                    Log.e("michcode", "Hubo un problema: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                Log.e("michcode", t.getMessage());
            }
        });
    }

    @OnClick(R.id.btnListarTodos)
    public void listarTodos() {
        MethodWs methodWs = HelperWs.getConfiguration().create(MethodWs.class);
        Call<List<Categoria>> response = methodWs.listarTodasLasCategorias();
        response.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful()) {
                    List<Categoria> list_categoria = response.body();
                    for (int i = 0; i < list_categoria.size(); i++) {
                        Log.i("michcode", list_categoria.get(i).get_id());
                        Log.i("michcode", list_categoria.get(i).getNombre());
                        Log.i("michcode", list_categoria.get(i).getDescripcion());
                        Log.i("michcode", "" + list_categoria.get(i).get__v());
                    }
                } else {
                    Log.e("michcode", "Hubo un problema: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Log.e("michcode", t.getMessage());
            }
        });
    }
}

