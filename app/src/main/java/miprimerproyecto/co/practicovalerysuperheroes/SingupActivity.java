package miprimerproyecto.co.practicovalerysuperheroes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

import java.util.UUID;

import miprimerproyecto.co.practicovalerysuperheroes.model.Usuario;

public class SingupActivity extends AppCompatActivity {

    private Button btn_singup_singup;
    private RadioButton rb_femenino;
    private RadioButton rb_masculino;
    private EditText et_nombre;
    private EditText et_edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        btn_singup_singup = findViewById(R.id.btn_signup_singup);
        et_edad = findViewById(R.id.et_edad);
        et_nombre = findViewById(R.id.et_nombre);
        rb_femenino = findViewById(R.id.rb_femenino);
        rb_masculino = findViewById(R.id.rb_masculino);

        btn_singup_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sexo = "masculino";
                if(rb_femenino.isChecked()){
                    sexo="femenino";
                }
                Intent i = new Intent(SingupActivity.this, VotarActivity.class);
                i.putExtra("nombre", et_nombre.getText().toString());
                i.putExtra("edad",et_edad.getText().toString() );
                i.putExtra("sexo",sexo );

                startActivity(i);
                finish();
            }
        });


    }
}
