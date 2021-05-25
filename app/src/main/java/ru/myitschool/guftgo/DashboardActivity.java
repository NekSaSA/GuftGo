package ru.myitschool.guftgo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashboardActivity extends AppCompatActivity {

    EditText secretCodeBox;
    Button joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        secretCodeBox = findViewById(R.id.codeBox);
        joinBtn = findViewById(R.id.joinBtn);

        URL serverURL;

        try { //нужен для отлова непредвиденных ошибок
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        joinBtn.setOnClickListener(v -> { //когда нажимаешь на кнопку JOIN тебя переносит в конференцию
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setRoom(secretCodeBox.getText().toString())
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeetActivity.launch(DashboardActivity.this, options);

        });
    }
}