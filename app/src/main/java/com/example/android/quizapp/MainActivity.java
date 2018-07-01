package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    int position = 0;
    int finalScore = 0;
    String selectedAnswer;
    private RadioGroup answersListGroup;
    String quizQuestionAnswersArray[][] = new String[7][7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find radio button list
        answersListGroup = (RadioGroup) findViewById(R.id.answers_list);
        //always clear checked items
        answersListGroup.clearCheck();
        //initialise sOnCheckedChange listener
        answersListGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);

                if (null != radioButton) {

                    CharSequence selectedItem = radioButton.getText();
                    if(selectedItem.toString().contains(selectedAnswer)) {

                        ImageView imageView = (ImageView) findViewById(R.id.correct);
                        imageView.setImageResource(R.drawable.tick);

                        //manage score
                        finalScore = finalScore + 5;

                    }
                    else
                    {
                        ImageView imageView = (ImageView) findViewById(R.id.correct);
                        imageView.setImageResource(R.drawable.cancel);

                    }
               }
            }
        });

        quizQuestionAnswersData();

        setupQuizData(quizQuestionAnswersArray);
    }


    /**
     * Set up sample data
     */
    public void quizQuestionAnswersData() {

        quizQuestionAnswersArray[0] = new String[]{
                "1. How many root elements can we have in XML", //Quiz Question
                "1,3,2,4", //Quiz Options
                "1" //Correct Answer
        };

        quizQuestionAnswersArray[1] = new String[]{
                "2. Why XLink is used in XML file", //Quiz Question
                "Remove link, Create link, Fetch link, Modify link", //Quiz Options
                "Create link" //Correct Answer
        };

        quizQuestionAnswersArray[2] = new String[]{
                "3. What is Xquery used for in XML file", //Quiz Question
                "Data store, Data display, Data create, Data fetch", //Quiz Options
                "Data fetch" //Correct Answer
        };

        quizQuestionAnswersArray[3] = new String[]{
                "4. Which special character is NOT used in XML", //Quiz Question
                "<, >, $, &", //Quiz Options
                "&" //Correct Answer
        };

        quizQuestionAnswersArray[4] = new String[]{
                "5. What is CDATA", //Quiz Question
                "Code data, Create data, Character data, Unparsed character data", //Quiz Options
                "Unparsed character data"  //Correct Answer

        };

        quizQuestionAnswersArray[5] = new String[]{
                "6. XML Can be Used to", //Quiz Question
                "Replace old language,Create new language, All of the above, None of these", //Quiz Options
                "All of the above"  //Correct Answer
        };

        quizQuestionAnswersArray[6] = new String[]{
                "7. Which is not a correct name for an XML element?", //Quiz Question
                "<age>, <NAME>, <first name>, All three names are incorrect",  //Quiz Options
                "<first name>"  //Correct Answer
        };

    }


    /**
     * Set up the questions on the quiz one by one
     */
    public void setupQuizData(String questionAnswerList[][]) {

        String selectedQuestions = questionAnswerList[position][1];
        String[] strArrys = selectedQuestions.split(",");

        if (strArrys.length > 0) {

            RadioButton answerA = (RadioButton) findViewById(R.id.answer_a);
            RadioButton answerB = (RadioButton) findViewById(R.id.answer_b);
            RadioButton answerC = (RadioButton) findViewById(R.id.answer_c);
            RadioButton answerD = (RadioButton) findViewById(R.id.answer_d);


            answerA.setText(strArrys[0]);
            answerB.setText(strArrys[1]);
            answerC.setText(strArrys[2]);
            answerD.setText(strArrys[3]);
        }


        String mainQuestion = questionAnswerList[position][0];

        TextView textView = (TextView) findViewById(R.id.showQuestion);
        textView.setText(mainQuestion);

        selectedAnswer = questionAnswerList[position][2];

    }


    /**
     * Submit answer, then load the next question
     */
    public void onSubmit(View v) {
        //always increment
        position = position + 1;

        if(position <= 6) {
            RadioButton rb = (RadioButton) answersListGroup.findViewById(answersListGroup.getCheckedRadioButtonId());
            if (rb == null) {
                Toast.makeText(MainActivity.this, "Please select an answer!!", Toast.LENGTH_LONG).show();
            } else {

                //always clear checked items
                answersListGroup.clearCheck();

                //always clear image
                ImageView imageView = (ImageView) findViewById(R.id.correct);
                imageView.setImageResource(android.R.color.transparent);

                //set up next question
                setupQuizData(quizQuestionAnswersArray);
            }
        }

        if(position > 6)
        {
            //show show on app
            TextView textView = (TextView) findViewById(R.id.final_score);
            textView.setText("You are done and have scored!! " + finalScore);

            //button must be invisible
            Button button = (Button) findViewById(R.id.next_question_button);
            button.setVisibility(View.GONE);

            //button must be visible
            Button startButton = (Button) findViewById(R.id.start_again_button);
            startButton.setVisibility(View.VISIBLE);

            //always clear image
            ImageView imageView = (ImageView) findViewById(R.id.correct);
            imageView.setImageResource(android.R.color.transparent);

            return;
        }
    }

    /**
     * Start the quiz again
     */
    public void onStarAgain(View v) {
        //reset values
        position = 0;
        finalScore = 0;
        answersListGroup.clearCheck();

        //button must be invisible
        Button submitButton = (Button) findViewById(R.id.next_question_button);
        submitButton.setVisibility(View.VISIBLE);

        //button must be invisible
        Button startButton = (Button) findViewById(R.id.start_again_button);
        startButton.setVisibility(View.GONE);

        TextView textView = (TextView) findViewById(R.id.final_score);
        textView.setText("");

        //always clear image
        ImageView imageView = (ImageView) findViewById(R.id.correct);
        imageView.setImageResource(android.R.color.transparent);

        //set up next question
        setupQuizData(quizQuestionAnswersArray);
    }

}
