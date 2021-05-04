package edu.lewisu.cs.corriganry.anirate;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EntryActivity extends AppCompatActivity {
    private Anime anime;
    private EditText animeNameTextField;
    private EditText commentTextField;
    private EditText favoriteCharacterTextField;
    private Spinner ratingSpinner;
    private Spinner recommendationSpinner;
    private Button addEditButton;
    private String userId;
    private String ref;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        animeNameTextField = findViewById(R.id.animeNameEditText);
        animeNameTextField.addTextChangedListener(new AnimeNameListener());

        commentTextField = findViewById(R.id.commentEditText);
        commentTextField.addTextChangedListener(new CommentListener());

        favoriteCharacterTextField = findViewById(R.id.favoriteCharacterEditText);
        favoriteCharacterTextField.addTextChangedListener(new FavoriteCharacterListener());

        ratingSpinner = findViewById(R.id.ratingSpinner);
        ratingSpinner.setOnItemSelectedListener(new RatingSelect());

        recommendationSpinner = findViewById(R.id.recommendationSpinner);
        recommendationSpinner.setOnItemSelectedListener(new RecommendationSelect());

        addEditButton = findViewById(R.id.add_edit_button);

        userId = getIntent().getStringExtra("uid");

        anime = new Anime(userId);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        ref = getIntent().getStringExtra("ref");

        if (ref != null) {
            mDatabaseReference = mFirebaseDatabase.getReference().child("anime").child(ref);

            ValueEventListener animeListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    anime = snapshot.getValue(Anime.class);
                    setUi();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };

            mDatabaseReference.addValueEventListener(animeListener);
        } else {
            addEditButton.setOnClickListener(new OnAddButtonClick());
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabaseReference = mFirebaseDatabase.getReference("anime");
    }

}

    private void setUi(){
        if(anime != null) {
            animeNameTextField.setText(anime.getAnimeName());
            commentTextField.setText(anime.getComment());
            favoriteCharacterTextField.setText(anime.getFavoriteCharacter());
            ratingSpinner.setSelection(anime.getRating());
            recommendationSpinner.setSelection(anime.getRecommendation());
            addEditButton.setText(R.string.update);
            addEditButton.setOnClickListener(new OnUpdateButtonClick());


        }
    }

    private class CommentListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            anime.setComment(s.toString());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class FavoriteCharacterListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            anime.setFavoriteCharacter(s.toString());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class AnimeNameListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            anime.setAnimeName(s.toString());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class RatingSelect implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(anime != null)
                anime.setRating(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class RecommendationSelect implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(anime != null)
                anime.setRecommendation(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnAddButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            mDatabaseReference.push().setValue(anime);
            finish();
        }
    }

    private class OnUpdateButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            mDatabaseReference.setValue(anime);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        if (ref != null) {
            getMenuInflater().inflate(R.menu.delete_menu, menu);
            return true;
        } else {
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.delete) {
            mDatabaseReference.removeValue();
            finish();
        }else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
