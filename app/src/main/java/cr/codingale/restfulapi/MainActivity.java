package cr.codingale.restfulapi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RESTfulService.startServer(this);
    }

    @Override
    protected void onDestroy() {
        RESTfulService.stopServer(this);
        super.onDestroy();
    }
}
