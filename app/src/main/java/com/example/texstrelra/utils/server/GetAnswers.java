package com.example.texstrelra.utils.server;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.texstrelra.Activities.AuthActivity;
import com.example.texstrelra.Activities.MainActivity;
import com.example.texstrelra.Activities.QuestionsFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class GetAnswers extends AsyncTask<Void, Void, Void> {

    public static List<List<String>> partitions;
    private int partitionSize = 4;
    private int question_id;
    private Context mCtx;
    public GetAnswers(Context ctx, int qId){
        mCtx = ctx;
        question_id = qId;
    }
    public static List<String> listAnswers = new ArrayList();
    public static List<String> listRightAnswers = new ArrayList();
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            JSONObject[] resultJson = HttpUtils.getAllAnswers(question_id);
            for (int i = 0; i < resultJson.length; i++) {
                listAnswers.add(String.valueOf(resultJson[i].get("answer")));
                if (Boolean.parseBoolean(String.valueOf(resultJson[i].get("is_right")))) listRightAnswers.add(String.valueOf(resultJson[i].get("answer")));
            }

            partitions = new LinkedList<List<String>>();
            Log.e("ХУЙХУХЙУХЙХУХЙХУ", listAnswers.toString());
            for (int i = 0; i < listAnswers.size(); i += partitionSize) {
                partitions.add(listAnswers.subList(i,
                        Math.min(i + partitionSize, listAnswers.size())));
            }
            Log.e("ЗФКЕШЩТЫ", partitions.toString());

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
        mCtx.startActivity(new Intent(mCtx, MainActivity.class));
    }
}

