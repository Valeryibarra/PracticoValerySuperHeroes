package miprimerproyecto.co.practicovalerysuperheroes;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import miprimerproyecto.co.practicovalerysuperheroes.model.Usuario;

//se va a encargar de las estadisticas
public class MainActivity extends AppCompatActivity {

    private Button btn_estadisticas;
    private RadioButton rb_todopublico;
    private RadioButton rb_mujeresadultas;
    private RadioButton rb_hombresadultos;
    private RadioButton rb_mujeresadolescentes;
    private RadioButton rb_hombresadolescentes;
    private RadioButton rb_ninios;
    private TextView txt_estad;


    FirebaseDatabase rtdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rtdb = FirebaseDatabase.getInstance();


        rb_todopublico = findViewById(R.id.rb_todopublico);
        rb_mujeresadultas = findViewById(R.id.rb_mujeresadultas);
        rb_hombresadultos = findViewById(R.id.rb_hombresadultos);
        rb_mujeresadolescentes = findViewById(R.id.rb_mujeresadolescentes);
        rb_hombresadolescentes = findViewById(R.id.rb_hombresadolescentes);
        rb_ninios = findViewById(R.id.rb_ninios);
        txt_estad=findViewById(R.id.txt_estad);
        btn_estadisticas =findViewById(R.id.btn_estadisticas);
        txt_estad.setMovementMethod(new ScrollingMovementMethod());

        //para que por defecto de las estadisticas globales
        rb_todopublico.isChecked();



        //se cambia el txt dependiendo la info


        btn_estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String grupo ="mujer_adulta";

                if(rb_todopublico.isChecked()){
                    grupo="practicodosvalery";


                }else if(rb_mujeresadultas.isChecked()){
                    grupo="mujer_adulta";
                }else if(rb_hombresadultos.isChecked()){
                    grupo="hombre_adulto";
                }else if(rb_mujeresadolescentes.isChecked()){
                    grupo="mujer_adolescente";
                }else if(rb_hombresadolescentes.isChecked()){
                    grupo="hombre_adolescente";
                }else if(rb_ninios.isChecked()){
                    grupo="ninio";

                }

                //if(grupo.equals(""))

                DatabaseReference resultado;
                if(grupo.equals("practicodosvalery")){
                    resultado =rtdb.getReference();
                }else{
                    resultado =rtdb.getReference().child(grupo);
                }
                resultado.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Long superheroes=dataSnapshot.getChildrenCount();

                        //Recorro a los superheroes que tenga
                        if(superheroes>0L){
                            //Recorro a los superheroes que tenga

                            Double cont_spiderman=0.0;
                            Double cont_ironman=0.0;
                            Double cont_capitanamerica=0.0;
                            Double cont_capitanamarvel=0.0;
                            Double cont_hulk=0.0;
                            Double cont_viudanegra=0.0;
                            Double cont_thor=0.0;
                            Double cont_doctorstrange=0.0;

                            boolean todoPublico=false;
                            for(DataSnapshot hijo : dataSnapshot.getChildren()){
                                String heroeActual= hijo.getKey();
                                //txt_estad.setText(heroeActual);

                               if(heroeActual.equals("spiderman")){
                                    cont_spiderman+= hijo.getChildrenCount();
                                }else if(heroeActual.equals("ironman")){
                                    cont_ironman+=hijo.getChildrenCount();
                                }else if(heroeActual.equals("capitanamerica")){
                                    cont_capitanamerica+=hijo.getChildrenCount();
                                }else if(heroeActual.equals("capitanamarvel")){
                                    cont_capitanamarvel+=hijo.getChildrenCount();
                                }else if(heroeActual.equals("hulk")){
                                    cont_hulk+=hijo.getChildrenCount();
                                }else if(heroeActual.equals("viudanegra")){
                                    cont_viudanegra+=hijo.getChildrenCount();
                                }else if(heroeActual.equals("thor")){
                                    cont_thor+=hijo.getChildrenCount();
                                }else if(heroeActual.equals("doctorstrange")){
                                    cont_doctorstrange+=hijo.getChildrenCount();
                                }else if(heroeActual.equals("ninio") || heroeActual.equals("mujer_adolescente")||heroeActual.equals("hombre_adolescente") ||heroeActual.equals("mujer_adulta")||heroeActual.equals("hombre_adulto") ){
                                       todoPublico=true;
                                       break;

                               }
                            }
                            //me salgo del ciclo

                            //si es para todo el publico me toca hacer doble for
                            if(todoPublico){


                                DatabaseReference resultadot;
                                resultadot =rtdb.getReference();
                                //por cada grupo
                                for(DataSnapshot grup : dataSnapshot.getChildren()) {
                                    String grupoActuali = grup.getKey();

                                    //por cada superheroe
                                    for (DataSnapshot hijo : grup.getChildren()) {
                                        String heroeActual = hijo.getKey();
                                        //txt_estad.setText(heroeActual);

                                        if (heroeActual.equals("spiderman")) {
                                            cont_spiderman += hijo.getChildrenCount();
                                        } else if (heroeActual.equals("ironman")) {
                                            cont_ironman += hijo.getChildrenCount();
                                        } else if (heroeActual.equals("capitanamerica")) {
                                            cont_capitanamerica += hijo.getChildrenCount();
                                        } else if (heroeActual.equals("capitanamarvel")) {
                                            cont_capitanamarvel += hijo.getChildrenCount();
                                        } else if (heroeActual.equals("hulk")) {
                                            cont_hulk += hijo.getChildrenCount();
                                        } else if (heroeActual.equals("viudanegra")) {
                                            cont_viudanegra += hijo.getChildrenCount();
                                        } else if (heroeActual.equals("thor")) {
                                            cont_thor += hijo.getChildrenCount();
                                        } else if (heroeActual.equals("doctorstrange")) {
                                            cont_doctorstrange += hijo.getChildrenCount();
                                        }
                                    }
                                }

                            }





                            Double votosTodosHeroes=cont_spiderman+
                                    cont_ironman+cont_capitanamerica+cont_capitanamarvel+cont_hulk+cont_viudanegra+cont_thor+cont_doctorstrange;
                            if(votosTodosHeroes==0.0){
                                //No hay votos de nada weird

                            }else{
                                cont_spiderman=(cont_spiderman.doubleValue()*100)/votosTodosHeroes;
                                cont_ironman= (cont_ironman*100)/votosTodosHeroes;
                                cont_capitanamerica= (cont_capitanamerica*100)/votosTodosHeroes;
                                cont_capitanamarvel= (cont_capitanamarvel*100)/votosTodosHeroes;
                                cont_hulk= (cont_hulk*100)/votosTodosHeroes;
                                cont_viudanegra= (cont_viudanegra*100)/votosTodosHeroes;
                                cont_thor= (cont_thor*100)/votosTodosHeroes;
                                cont_doctorstrange= (cont_doctorstrange*100)/votosTodosHeroes;

                            }


                            String respuestaP="";
                            //NO LO RECORTO PARA QUE TENDA UNA CONFIABILIDAD MUY ALTA JEJE
                            respuestaP=" Votos por Spiderman son: "+cont_spiderman +"%";
                            respuestaP+="\n Votos por Iron Man son "+cont_ironman+"%";
                            respuestaP+="\n Votos por Capitan América son :"+cont_capitanamerica+"%";
                            respuestaP+="\n Votos por Capitana Marvel son :"+cont_capitanamarvel+"%";
                            respuestaP+="\n Votos por Hulk son "+cont_hulk+"%";
                            respuestaP+="\n Votos por La Viuda Negra son :"+cont_viudanegra+"%";
                            respuestaP+="\n Votos por Thor son :"+cont_thor+"%";
                            respuestaP+="\n Votos por Doctor Strange son :"+cont_doctorstrange+"%";
                            txt_estad.setText(respuestaP+"\n Votos totales en este campo:"+votosTodosHeroes+"");

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });




    }

    public void operarGrupo(String grupo){

    }
}
