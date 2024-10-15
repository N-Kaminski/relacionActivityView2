package com.murek.relacionactivityview2;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {

    private List<Usuario> listaUsuarios = new ArrayList<>();

    //metodos para agregar usuario
    public void addUser (Usuario usuario){
        listaUsuarios.add(usuario);
    }

    //metodo para obtener lista de usuarios
    public List<Usuario> getListaUsuarios(){
        return listaUsuarios;
    }
}
