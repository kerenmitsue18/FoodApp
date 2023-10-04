package com.example.foodapp.users;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

public class FormularyActivity extends AppCompatActivity {

    StepView stepView;
    TextView stepTextView;
    TextView stepQuestionView;
    private int currentStep = 0;
    private int currentStep2 = 0;
    private int numberSteps = 4;

    int stepIndex = 0;
    String[] stepsTexts = {"PASO 1", "Paso 2", "Paso 3", "Paso 4"};
    String[] questions = {"biub", "hbuh", "guih", "giug"};

    @Override
    protected void onCreate(Bundle saveInstaceState){
        super.onCreate(saveInstaceState);
        setContentView(R.layout.formulary_activity);

        stepTextView = findViewById(R.id.stepTextView);
        stepQuestionView = findViewById(R.id.stepQuestionView);
        stepView = findViewById(R.id.step_view);

        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                Toast.makeText(FormularyActivity.this, "Step " + step, Toast.LENGTH_SHORT).show();
            }
        });

        stepView.getState().animationDuration(StepView.ANIMATION_ALL)
                .stepsNumber(4)
                .steps(new ArrayList<String>() {{
                    add("First step");
                    add("Second step");
                    add("Third step");
                }})
                .animationDuration(getResources().getInteger((android.R.integer.config_shortAnimTime)))
                .commit();

        nextStep();

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep < stepView.getStepCount() - 1) {
                    currentStep++;
                    stepView.go(currentStep, true);
                } else {
                    stepView.done(true);
                }
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep > 0) {
                    currentStep--;
                }
                stepView.done(false);
                stepView.go(currentStep, true);
            }
        });

        List<String> steps = new ArrayList<>();
        for (int i = 0; i < numberSteps; i++) {
            steps.add("Step " + (i + 1));
        }
        steps.set(steps.size() - 1, steps.get(steps.size() - 1) + " last one");
        stepView.setSteps(steps);





        /*
        final StepView stepView2 = findViewById(R.id.step_view_2);
        stepView2.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                Toast.makeText(FormularyActivity.this, "Step " + step, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.next_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep2 < stepView2.getStepCount() - 1) {
                    currentStep2++;
                    stepView2.go(currentStep2, true);
                } else {
                    stepView2.done(true);
                }
            }
        });
        findViewById(R.id.back_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep2 > 0) {
                    currentStep2--;
                }
                stepView2.done(false);
                stepView2.go(currentStep2, true);
            }
        });
        */


    }

    private void nextStep() {
        stepIndex++;
        if(stepIndex <stepsTexts.length){
            stepTextView.setText(stepsTexts[stepIndex]);
            stepQuestionView.setText(questions[stepIndex]);
            stepView.go(stepIndex, true);
        }
    }


}
