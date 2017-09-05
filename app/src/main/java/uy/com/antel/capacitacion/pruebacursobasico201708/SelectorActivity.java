package uy.com.antel.capacitacion.pruebacursobasico201708;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectorActivity extends AppCompatActivity {

    private String[] array = {"Selector","Audio","Images","Video"};
    private String TAG = "SELECTOR";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item ,array);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent it;
                switch (position) {
                    case 0:
                        Log.i(TAG,"0");
                        break;
                    case 1:
                        //Log.i(TAG,"1");//Audio
                        it = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.INTERNAL_CONTENT_URI);
                        startActivityForResult(it,1);
                        break;
                    case 2:
                        //Log.i(TAG,"2");//Images
                        it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        startActivityForResult(it,2);
                        break;
                    case 3:
                        //Log.i(TAG,"3");//Video
                        /*
                        it = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
                        startActivityForResult(it,3);
                        */
                        it = new Intent(Intent.ACTION_PICK, null);
                        it.setType("video/*");
                        startActivityForResult(it, 3);
                        break;
                    default:
                        Log.i(TAG,"When");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG,requestCode+"");
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                case 2:
                case 3:
                Log.i(TAG,data.toString());
                Uri song = data.getData();
                Log.i(TAG,song.toString());

                //String[] projection = {MediaStore.Audio.Media.TITLE};
/*
                Cursor cursor =  this.getContentResolver()
                        .query(song, null, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    int column = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    String title = cursor.getString(column);
                    Toast.makeText(this,title + " seleccionada ",Toast.LENGTH_LONG ).show();
                } else {
                    Toast.makeText(this, "No se encontro la cancion seleccionada ",Toast.LENGTH_LONG ).show();
                }
*/
            }
        }
    }
}
