package com.murek.relacionactivityview2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.murek.relacionactivityview2.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;  //para acceder a la ui
    private UserViewModel userVM; //almacena datos de usuario

    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding para acceder a los componentes
        binding=ActivityMainBinding.inflate(getLayoutInflater()); //crea vistas
        setContentView(binding.getRoot());

        //userVM para que se mantengan los datos
        userVM=new ViewModelProvider(this).get(UserViewModel.class); //crea instancia de userviewmodel y la asocia con este activity
        tarea();
    }

    //metodo para los botones
    public void tarea(){

        //boton guardar
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtengo valores declarados por el usuario
                String nombre = binding.etNombre.getText().toString();
                int edad;

                //valido valores
                if (!nombre.isEmpty() && !binding.etEdad.getText().toString().isEmpty()) {
                    try {
                        edad = Integer.parseInt(binding.etEdad.getText().toString());
                        Usuario usuario = new Usuario(nombre, edad);
                        userVM.addUser(usuario);
                        binding.etNombre.setText("");
                        binding.etEdad.setText("");
                    } catch (NumberFormatException e) {
                        binding.tvLista.setText("Error de formato en la edad");
                    }
                } else {
                    binding.tvLista.setText("ERROR :(");
                }
                binding.etNombre.setText("");
                binding.etEdad.setText("");
            }
        });

        //boton ver
        binding.btVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtengo la lista
                List<Usuario> lista=userVM.getListaUsuarios();
                String texto= "";

                //concateno la lista con nombre y edad
                for (Usuario u: lista){
                    texto+="Nombre: "+u.getNombre()+" Edad: "+u.getEdad()+"\n";
                }
                binding.tvLista.setText(texto);
            }
        });
    }
}