package com.osolis.retrofitapp.rest;

import com.osolis.retrofitapp.entity.Categoria;
import com.osolis.retrofitapp.entity.request.GrabarCategoriaRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MethodWs {

    @GET("categoria")
    @Headers("Content-Type:application/json")
    Call<List<Categoria>> listarTodasLasCategorias();

    @GET("categoria/{idCategoria}")
    @Headers("Content-Type:application/json")
    Call<Categoria> obtenerUnaCategoria(@Path("idCategoria") String idCategoria);

    @POST("categoria")
    @Headers("Content-Type:application/json")
    Call<List<Categoria>> grabarCategoria(@Body GrabarCategoriaRequest grabarCategoriaRequest);

    @PUT("categoria")
    @Headers("Content-Type:application/json")
    Call<List<Categoria>> actualizarCategoria(@Body GrabarCategoriaRequest grabarCategoriaRequest);
}
