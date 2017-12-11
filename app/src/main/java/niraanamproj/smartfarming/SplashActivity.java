package niraanamproj.smartfarming;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by NiRaaNam on 11-Dec-17.
 */

public class SplashActivity extends Activity {

    Handler handler;
    ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashfile);

        progressBar=new ProgressDialog(this);
        progressBar.setMessage("Downloading...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
       // progressBar.setIndeterminate(true);
        //progressBar.setProgress(0);
        progressBar.show();
        //progressBar.cancel();



        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /*Intent intent=new Intent(SplashActivity.this,AA_MainActivity.class);
                startActivity(intent);
                finish();*/
                isInternetOn();

            }
        },2000);

    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet
            //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();

            Intent intent=new Intent(SplashActivity.this,AA_MainActivity.class);
            startActivity(intent);
            finish();

            progressBar.cancel();


            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            //Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            progressBar.cancel();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SplashActivity.this);
            alertDialogBuilder.setTitle("Warning!!!");
            alertDialogBuilder.setMessage("You have no Internet connection, Please check for retrieving data from Database Server");

            alertDialogBuilder.setPositiveButton("Retry",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {

                    Intent intent=new Intent(SplashActivity.this,SplashActivity.class);
                    startActivity(intent);
                    finish();

                }
            });
            // set negative button: No message
            alertDialogBuilder.setNegativeButton("Exit the app",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {

                    SplashActivity.this.finish();

                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            // show alert
            alertDialog.show();

            return false;
        }
        return false;
    }
}
