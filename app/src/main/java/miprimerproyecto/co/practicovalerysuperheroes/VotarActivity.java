package miprimerproyecto.co.practicovalerysuperheroes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import miprimerproyecto.co.practicovalerysuperheroes.model.Usuario;

public class VotarActivity extends AppCompatActivity {


    private Button btn_votar;
    private RadioButton rb_spiderman;
    private RadioButton rb_ironman;
    private RadioButton rb_capitanamerica;
    private RadioButton rb_capitanamarvel;
    private RadioButton rb_hulk;
    private RadioButton rb_viudanegra;
    private RadioButton rb_thor;
    private RadioButton rb_doctorstrange;

    private UUID uuid;
    FirebaseDatabase rtdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar);

        rtdb = FirebaseDatabase.getInstance();

        uuid= UUID.randomUUID();

        btn_votar = findViewById(R.id.btn_votar);
        rb_spiderman = findViewById(R.id.rb_spiderman);
        rb_ironman = findViewById(R.id.rb_ironman);
        rb_capitanamerica = findViewById(R.id.rb_capitanamerica);
        rb_capitanamarvel = findViewById(R.id.rb_capitanamarvel);
        rb_hulk = findViewById(R.id.rb_hulk);
        rb_viudanegra = findViewById(R.id.rb_viudanegra);
        rb_thor = findViewById(R.id.rb_thor);
        rb_doctorstrange = findViewById(R.id.rb_doctorstrange);


        btn_votar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //voto por el que
                String ramaHeroe ="spiderman";

                if(rb_spiderman.isChecked()){
                    //voto por spiderman
                    ramaHeroe="spiderman";
                }
                if(rb_ironman.isChecked()){
                    ramaHeroe="ironman";

                }
                if(rb_capitanamerica.isChecked()){
                    ramaHeroe="capitanamerica";

                }
                if(rb_capitanamarvel.isChecked()){
                    ramaHeroe="capitanamarvel";
                }
                if(rb_hulk.isChecked()){
                    ramaHeroe="hulk";
                }
                if(rb_viudanegra.isChecked()){
                    ramaHeroe="viudanegra";
                }
                if(rb_thor.isChecked()){
                    ramaHeroe="thor";
                }
                if(rb_doctorstrange.isChecked()){
                    ramaHeroe="doctorstrange";

                }

                Intent datos = getIntent();
                String nombre = datos.getExtras().getString("nombre");
                String edad = datos.getExtras().getString("edad");
                String sexo = datos.getExtras().getString("sexo");


                Usuario usuario= new Usuario();
                String randomUUIDString = uuid.toString().substring(1,8);
                usuario.setId(randomUUIDString);
                usuario.setEdad(edad);
                usuario.setSexo(sexo);
                usuario.setNombre(nombre);
                //ya el usuario esta listo

                //Verifico a que grupo pertenece
                String grupo ="ninio";

                //primero la edad
                int ente_edad=Integer.parseInt(edad);
                if(ente_edad<12){
                    //Es ninio o ninia
                    //dont care el sexo
                    grupo="ninio";
                }else if(ente_edad >= 12 && ente_edad<18){
                    //Es un adolescente
                    if(sexo.equals("femenino")){
                        //es mujer adolescente
                        grupo="mujer_adolescente";
                    }else{
                        //es hombre adolescente
                        grupo="hombre_adolescente";
                    }

                }else if(ente_edad >= 18){
                    //es adulto

                    if(sexo.equals("femenino")){
                        //es mujer adulta
                        grupo="mujer_adulta";
                    }else{
                        //es hombre adulto
                        grupo="hombre_adulto";
                    }
                }
                //ahora guardo el resultado en la bd y lo paso a estadisticas

                rtdb.getReference().child(grupo).child(ramaHeroe).push().setValue(usuario);

                Intent i = new Intent(VotarActivity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });



    }
}
