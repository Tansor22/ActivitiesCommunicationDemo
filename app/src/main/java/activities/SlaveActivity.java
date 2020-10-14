package activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import static shared.Constants.MESSAGE_KEY;

public class SlaveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slave);
        // getting message
        String messageFromMaster = getIntent().getStringExtra(MESSAGE_KEY);
        TextView textView = findViewById(R.id.textViewSlave);
        textView.setText(messageFromMaster);
        // setup
        Button sendButton = findViewById(R.id.sendButtonSlave);
        EditText editText = findViewById(R.id.editTextSlave);
        sendButton.setOnClickListener(self -> {
            Intent intent = new Intent(this, MasterActivity.class);
            intent.putExtra(MESSAGE_KEY, editText.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
