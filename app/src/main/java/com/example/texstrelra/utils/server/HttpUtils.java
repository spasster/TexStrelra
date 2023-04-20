package com.example.texstrelra.utils.server;
import android.os.AsyncTask;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import com.example.texstrelra.models.UserInfo;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    private static final String QUERY = "http://10.45.18.33:8080/";
    private static final String QUERYHotel = "http://100.65.6.5:8080/";

    public static UserInfo auth(String login, String password) throws JSONException, IOException {
        final JSONObject json = new JSONObject();
        json.put("login", CryptUtils.encryptString(login));
        json.put("password", CryptUtils.encryptString(password));

        final URL url = new URL(QUERY + "auth");
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        final OutputStream os = conn.getOutputStream();
        os.write(json.toString().getBytes("UTF-8"));
        os.close();

        // read the response
        final InputStream in = new BufferedInputStream(conn.getInputStream());
        final String result = CryptUtils.decryptString(IOUtils.toString(in, "UTF-8"));
        final String[] resultModel = result.split(":");
        final UserInfo userInfo = new UserInfo(Integer.parseInt(resultModel[0]), resultModel[1], resultModel[2], Integer.parseInt(resultModel[3]), Integer.parseInt(resultModel[4]), resultModel[5], Integer.parseInt(resultModel[6]));

        in.close();
        conn.disconnect();

        return userInfo;
    }

    public static JSONObject[] getAllQuiz() throws JSONException, IOException {
        final URL url = new URL(QUERY + "quiz/getAll");
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        final OutputStream os = conn.getOutputStream();
        os.write("".getBytes("UTF-8"));
        os.close();

        // read the response
        final InputStream in = new BufferedInputStream(conn.getInputStream());
        final String result = CryptUtils.decryptString(IOUtils.toString(in, "UTF-8"));
        final String[] resultJson = result.split(":");
        final JSONObject[] json = new JSONObject[resultJson.length];
        for (int i = 0; i < resultJson.length; i++) {
            json[i] = new JSONObject(resultJson[i]);
        }

        in.close();
        conn.disconnect();

        return json;
    }

    public static JSONObject[] getAllQuestions(int quiz_id) throws JSONException, IOException {
        final JSONObject jsonInp = new JSONObject();
        jsonInp.put("hash", CryptUtils.encryptString(String.valueOf(quiz_id)));
        final URL url = new URL(QUERYHotel + "question/getAll");
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        final OutputStream os = conn.getOutputStream();
        os.write(jsonInp.toString().getBytes("UTF-8"));
        os.close();


        // read the response
        final InputStream in = new BufferedInputStream(conn.getInputStream());
        final String result = CryptUtils.decryptString(IOUtils.toString(in, "UTF-8"));
        final String[] resultJson = result.split(":");
        final JSONObject[] json = new JSONObject[resultJson.length];

        for (int i = 0; i < resultJson.length; i++) {
            json[i] = new JSONObject(resultJson[i]);
        }

        in.close();
        conn.disconnect();

        return json;
    }

    public static JSONObject[] getAllAnswers(int question_id) throws JSONException, IOException {
        final JSONObject jsonInp = new JSONObject();
        jsonInp.put("hash", CryptUtils.encryptString(String.valueOf(question_id)));
        final URL url = new URL(QUERYHotel + "answer/getAll");
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        final OutputStream os = conn.getOutputStream();
        os.write(jsonInp.toString().getBytes("UTF-8"));
        os.close();


        // read the response
        final InputStream in = new BufferedInputStream(conn.getInputStream());
        final String result = CryptUtils.decryptString(IOUtils.toString(in, "UTF-8"));
        final String[] resultJson = result.split(":");
        final JSONObject[] json = new JSONObject[resultJson.length];

        for (int i = 0; i < resultJson.length; i++) {
            json[i] = new JSONObject(resultJson[i]);
        }

        in.close();
        conn.disconnect();

        return json;
    }
}