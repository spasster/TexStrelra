package com.example.texstrelra.utils.server;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.texstrelra.Activities.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetQuestions extends AsyncTask<Void, Void, Void> {

    private GetAnswers getAnswers;
    private Context mCtx;
    private int quiz_id1;

    public GetQuestions(Context ctx, int quiz_id){
        mCtx = ctx;
        quiz_id1 = quiz_id;
    }
    public static List<String> listQuestions = new ArrayList();
    @SuppressLint("WrongThread")
    @Override
    protected Void doInBackground(Void... voids) {
        Log.d("RUNNING", "running back");
        try {
            JSONObject[] resultJson = HttpUtils.getAllQuestions(quiz_id1);
            for (int i = 0; i < resultJson.length; i++) {
                listQuestions.add(String.valueOf(resultJson[i].get("question")));
                getAnswers = new GetAnswers(mCtx, Integer.parseInt(String.valueOf(resultJson[i].get("question_id"))));
                getAnswers.execute();
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
    }
}

