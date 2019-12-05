package com.osolis.retrofitapp.entity.request;

public class GrabarCategoriaRequest {

    private String txt_name_categoria;
    private String text_desc_categoria;
    private String idCategoria;

    public String getTxt_name_categoria() {
        return txt_name_categoria;
    }

    public void setTxt_name_categoria(String txt_name_categoria) {
        this.txt_name_categoria = txt_name_categoria;
    }

    public String getText_desc_categoria() {
        return text_desc_categoria;
    }

    public void setText_desc_categoria(String text_desc_categoria) {
        this.text_desc_categoria = text_desc_categoria;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }
}
