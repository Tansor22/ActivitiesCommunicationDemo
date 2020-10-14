package activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Optional;

import static shared.Constants.MESSAGE_KEY;
import static shared.Constants.MESSAGE_REQUEST_CODE;

public class MasterActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        textView = findViewById(R.id.textViewMaster);
        Button sendButton = findViewById(R.id.sendButtonMaster);
        EditText editText = findViewById(R.id.editTextMaster);
        sendButton.setOnClickListener(self -> {
            Intent intent = new Intent(this, SlaveActivity.class);
            intent.putExtra(MESSAGE_KEY, editText.getText().toString());
            startActivityForResult(intent, MESSAGE_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MESSAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                data = null;
                String message = Optional.ofNullable(data)
                        .map(self -> self.getStringExtra(MESSAGE_KEY))
                        .orElse(getString(R.string.default_message_from, "slave"));
                textView.setText(message);
            }
        }
    }
}