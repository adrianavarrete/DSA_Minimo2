package upc.dsa.dsa_minimo2;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.ProgressDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    List<Element> data;
    RecyclerView recyclerView;
    Recycler recycler;

    MuseoAPI API;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recycler = new Recycler(this);
        recyclerView.setAdapter(recycler);

        API = MuseoAPI.retrofit.create(MuseoAPI.class);
        getData();


    }

    private void getData() {
        Call<Museums> call = API.getData(1, 10);

        call.enqueue(new Callback<Museums>() {
            @Override
            public void onResponse(Call<Museums> call, Response<Museums> response) {
                if (response.isSuccessful()) {
                    Museums museums = response.body();
                    data = museums.getElements();
                    recycler.addElements(data);
                } else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                    alertDialogBuilder
                            .setTitle("Error")
                            .setMessage(response.message())
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, which) -> closeContextMenu());

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            }

            @Override
            public void onFailure(Call<Museums> call, Throwable t) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder
                        .setTitle("Error")
                        .setMessage(t.getMessage())
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> finish());

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }


        });




    }
}
