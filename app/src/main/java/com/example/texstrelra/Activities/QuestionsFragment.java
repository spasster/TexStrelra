package com.example.texstrelra.Activities;


import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.texstrelra.R;
import com.example.texstrelra.databinding.FragmentFirstBinding;
import com.example.texstrelra.utils.server.GetAnswers;
import com.example.texstrelra.utils.server.GetQuestions;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class QuestionsFragment extends Fragment {

    private FragmentFirstBinding binding;

    private RadioGroup radioGroup;
    private RadioButton button;
    private Button button_next;
    private TextView question_text;
    public static List<String> answers_list = new ArrayList<>();
    private String right_answer, text, answer_text;
    private int quest_number = 1;
    public static int answer_count = 5;
    private int index_of_quest_text = 0;
    private int question_counter = GetQuestions.listQuestions.size();
    int j = 0;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        text = "Вопрос №";
        radioGroup = binding.radioGroup;
        question_text = binding.questionText;

        answer_count = answers_list.size();

        Log.d("quest_count", String.valueOf(question_counter));

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                RadioButton checkedRadioButton = (RadioButton) getActivity().findViewById(checkedId);
                String text = checkedRadioButton.getText().toString();
                Log.i("номер выбранного ответа - ", text);
                if (text == right_answer) {
                    Snackbar.make(view, "верно))))", Snackbar.LENGTH_LONG).show();
                    checkedRadioButton.setBackgroundColor(getResources().getColor(R.color.right));
                    for(int i = 0; i < radioGroup.getChildCount(); i++) {
                        ((RadioButton) radioGroup.getChildAt(i)).setEnabled(false);
                    }
                } else {
                    checkedRadioButton.setBackgroundColor(getResources().getColor(R.color.wrong));
                    for(int i = 0; i < radioGroup.getChildCount(); i++) {
                        checkedRadioButton.setBackgroundColor(getResources().getColor(R.color.wrong));
                        RadioButton ifBtn = (RadioButton) radioGroup.getChildAt(i);
                        if (ifBtn.getText() == right_answer){
                            ifBtn.setBackgroundColor(getResources().getColor(R.color.right));
                        }
                    }
                    for(int i = 0; i < radioGroup.getChildCount(); i++) {
                        ((RadioButton) radioGroup.getChildAt(i)).setEnabled(false);
                    }
                }
            }
        });
    }

    public void onStart () {

        super.onStart();

        answer_text = String.valueOf(GetAnswers.listAnswers);
        Log.d("LISTANSWERSFRAGMENT", answer_text);

        right_answer = GetAnswers.listRightAnswers.get(j);
        button = new RadioButton(getActivity());
        for(int i = 0; i < 4; i++) {
            button = new RadioButton(getActivity());
            button.setId(i);
//            button.setText(String.valueOf(answers_list.get(i)));
            //for (int j = 0; j < GetAnswers.partitions.size(); j++) {
                button.setText(GetAnswers.partitions.get(j).get(i));
            //}
            //button.setText(String.valueOf(GetAnswers.listAnswers.get(i)));
            binding.radioGroup.addView(button);
        }

        if (quest_number <= question_counter){
            binding.questionNumber.setText(text + quest_number);
            quest_number++;
        } else{
            getActivity().findViewById(R.id.button_next).setVisibility(View.INVISIBLE);
            getActivity().findViewById(R.id.button_end).setVisibility(View.VISIBLE);
        }

        question_text.setText(GetQuestions.listQuestions.get(index_of_quest_text));
        index_of_quest_text++;

        button_next = getActivity().findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j++;
                if (index_of_quest_text < GetQuestions.listQuestions.size()){
                    radioGroup.removeAllViews();

                    Snackbar.make(view, "следующий вопрос", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    getFragmentManager().beginTransaction().detach(QuestionsFragment.this).commit();
                    getFragmentManager().beginTransaction().attach(QuestionsFragment.this).commit();
                } else{
                    getActivity().findViewById(R.id.button_next).setVisibility(View.INVISIBLE);
                    getActivity().findViewById(R.id.button_end).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}