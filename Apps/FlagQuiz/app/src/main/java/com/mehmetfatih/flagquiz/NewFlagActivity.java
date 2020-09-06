package com.mehmetfatih.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewFlagActivity extends AppCompatActivity {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PICTURE = "picture";
    private EditText editFlagId, editFlagName, editFlagPicture;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flag);
        editFlagId = findViewById(R.id.edit_flag_id);
        editFlagName = findViewById(R.id.edit_flag_name);
        editFlagPicture = findViewById(R.id.edit_flag_picture);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editFlagId.getText()) || TextUtils.isEmpty(editFlagName.getText()) || TextUtils.isEmpty(editFlagPicture.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    int id = Integer.valueOf(editFlagId.getText().toString());
                    replyIntent.putExtra(ID, id);
                    String name = editFlagName.getText().toString();
                    replyIntent.putExtra(NAME, name);
                    String picture = editFlagPicture.getText().toString();
                    replyIntent.putExtra(PICTURE, picture);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}