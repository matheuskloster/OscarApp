package matheus.com.br.oscarapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import matheus.com.br.oscarapp.model.Usuario;

/**
 * Created by Matheus on 16/11/17.
 */

public class HTTPHandler {

    public HTTPHandler() {    }


    public Bitmap downloadImage(String url) throws IOException {
        URL address = new URL(url);
        InputStream inputStream = address.openStream();
        Bitmap image = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        return image;
    }

    public String makeServiceCall(String reqUrl){
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }
    private String convertStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null){
                sb.append(line).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return sb.toString();
        }
    }



}
