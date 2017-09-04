package uy.com.antel.capacitacion.pruebacursobasico201708;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private String[] users = {"root:secreto","super:gransecret"};
    private String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signIn = (Button)findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView errorMsg = (TextView)findViewById(R.id.errorMessage);
                errorMsg.setVisibility(TextView.INVISIBLE);

                EditText user= (EditText)findViewById(R.id.username);
                EditText pass= (EditText)findViewById(R.id.password);
                boolean logedIn = false;
                //Log.i(TAG,"*"+user.getText()+"*"+pass.getText()+"*");
                for (String combo : users) {
                    String[] partes = combo.split(":");
                    //Log.i(TAG,"*"+partes[0]+"*"+partes[1]+"*");
                    if (partes[0].trim().equals(user.getText().toString().trim()) &&
                        partes[1].trim().equals(pass.getText().toString().trim())) {
                        Intent it = new Intent(LoginActivity.this,SelectorActivity.class);
                        startActivity(it);
                        logedIn = true;
                        break;
                    }/* else {
                        Log.i(TAG,"*"+partes[0].trim().equals(user.getText().toString().trim())+"*"
                                +partes[1].trim().equals(pass.getText().toString().trim())+"*");
                    }*/
                }
                if (!logedIn) {
                    errorMsg.setText(getString(R.string.error_invalid));
                    errorMsg.setVisibility(TextView.VISIBLE);
                }
            }
        });
    }
}
