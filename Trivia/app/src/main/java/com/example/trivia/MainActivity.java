package com.example.trivia;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.trivia.data.AnsListAsyncResponse;
import com.example.trivia.data.Repository;
import com.example.trivia.databinding.ActivityMainBinding;
import com.example.trivia.model.Question;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuesIndex = 0;
    private List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // This use of interface is when android will be getting data it will not wait for
        // further process instead run them in parallel thus we will get ArrayOutOfBound error
        // as nothing is in questionArrayList so we call processFinished and put data in Repo class only
        questions = new Repository().getQuestions(new AnsListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                binding.tvQuestion.setText(questionArrayList.get(currentQuesIndex)
                        .getStatement());
                updateCounter(questionArrayList);
            }
        });
        binding.btnNext.setOnClickListener(v -> {
            currentQuesIndex = (currentQuesIndex + 1) % questions.size();
            updateQuestion();
        });

        binding.btnTrue.setOnClickListener(v -> {
            checkAns(true);
            updateQuestion();
        });

        binding.btnFalse.setOnClickListener(v -> {
            checkAns(false) ;
            updateQuestion();
        });

    }

    private void checkAns(boolean b) {
        boolean ans = questions.get(currentQuesIndex).isAnsTrue();
        int snackMessageId;
        if(b == ans)
        {
            snackMessageId = R.string.correctAns;
//            Log.d("ans","true");
            Toast.makeText(this, R.string.correctAns, Toast.LENGTH_SHORT).show();
            fadeAnimation();
        }
        else
        {
            snackMessageId = R.string.incorrectAns;
//            Log.d("ans","incorrect");
            Toast.makeText(this, R.string.incorrectAns, Toast.LENGTH_SHORT).show();
            shakeAnimation();
        }
        Snackbar.make(binding.cvQuestion,snackMessageId,Snackbar.LENGTH_SHORT)
                .show();
    }

    private void updateCounter(ArrayList<Question> questionArrayList) {
        binding.tvOutOfQuestion.setText(String.format(getString(R.string.formattedTextViewOutOfQuestion),
                currentQuesIndex+1, questionArrayList.size()+1));
    }

    private void updateQuestion() {
        binding.tvQuestion.setText(questions.get(currentQuesIndex).getStatement());
        updateCounter((ArrayList<Question>) questions);
    }

    private void fadeAnimation(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        binding.cvQuestion.setAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.tvQuestion.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.tvQuestion.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void shakeAnimation(){
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.shake_animation);

        binding.cvQuestion.setAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.tvQuestion.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.tvQuestion.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}