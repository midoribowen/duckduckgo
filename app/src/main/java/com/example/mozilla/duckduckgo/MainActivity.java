package com.example.mozilla.duckduckgo;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        TextView.OnEditorActionListener {
    EditText mEditText;
    Button mSearchButton;
    CharSequence mClipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.search_edit_text);
        mEditText.setOnEditorActionListener(this);
        mSearchButton = findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
            ClipData clip = clipboardManager.getPrimaryClip();
            if (clip != null &&
                clipboardManager.getPrimaryClipDescription()
                        .hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                ClipData.Item clipItemAt0 = clip.getItemAt(0);
                mClipboard = clipItemAt0.getText();
                mEditText.setText(mClipboard);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.search_button:
                performSearch();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            performSearch();
            return true;
        }
        return false;
    }

    private void performSearch() {
        String term = mEditText.getText().toString();
        Toast.makeText(this, term, Toast.LENGTH_SHORT).show();
    }
}
