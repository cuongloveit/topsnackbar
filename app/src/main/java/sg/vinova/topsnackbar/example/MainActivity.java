package sg.vinova.topsnackbar.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import sg.vinova.topsnackbar.TopSnackbar;
import vn.eazy.core.helper.snackbar.ClickActionListener;
import vn.eazy.core.helper.snackbar.SnackbarColor;
import vn.eazy.core.helper.snackbar.SnackbarIcon;
import vn.eazy.core.helper.snackbar.SnackbarInterface;

public class MainActivity extends AppCompatActivity {
    private SnackbarInterface snackbar = new TopSnackbar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.show(findViewById(android.R.id.content),"Test top snackbar!");
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.show(findViewById(android.R.id.content),"Test top snackbar!","Action");
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SnackbarColor snackbarColor = new SnackbarColor(MainActivity.this);
                snackbarColor.setMessageColor(Color.RED);
                snackbarColor.setBackgroundColor(Color.BLUE);
                snackbarColor.setActionColor(Color.WHITE);
                //TopSnackbarInterface snackbarInterface = new TopSnackbar();
                snackbar.show(findViewById(android.R.id.content), "Test top snackbar!", "Action", snackbarColor, null, new ClickActionListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(MainActivity.this, "hihi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SnackbarColor snackbarColor = new SnackbarColor(MainActivity.this);
                snackbarColor.setMessageColor(Color.RED);
                snackbarColor.setBackgroundColor(Color.BLUE);
                snackbarColor.setActionColor(Color.WHITE);

                SnackbarIcon snackbarIcon = new SnackbarIcon();
                snackbarIcon.setLeftIconRes(R.mipmap.ic_launcher);
                snackbarIcon.setRightIconRes(android.R.drawable.ic_dialog_alert);

                snackbar.show(findViewById(android.R.id.content), "Test top snackbar!", "", snackbarColor, snackbarIcon, new ClickActionListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
