package hackathon028.team1.bustice;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button server_check_button = (Button) findViewById(R.id.server_check);
        if (server_check_button != null) {
            server_check_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                GetExample example = new GetExample();
                try{
                String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
                System.out.println(response);
                }
                catch (Exception e){
                    System.out.println("Caught exception:\n");
                    e.printStackTrace();
                }
            }
            });
        }

        //Button to_nfc = (Button) findViewById(R.id.to_nfc);


    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, NFCActivity.class);
        startActivity(intent);
    }

}

class GetExample {
    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}