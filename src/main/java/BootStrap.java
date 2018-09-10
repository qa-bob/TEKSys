import com.google.gson.Gson;
import java.io.*;
import java.util.Scanner;
import json.TEKAPI;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BootStrap {
    private static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        System.out.println("Please enter the state name or state abbreviation: ");
        Scanner inputData = new Scanner(System.in);
        String value = inputData.next();
        //TODO:If null, if multiple, if buffer overflow
        getStatesData("all", value);
    }

    public static String getJSON (String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
    }

    public static void getStatesData(String path, String value) {
        String json = null;
        try {
            json = getJSON("http://services.groupkt.com/state/get/USA/" + path); //Here is where you set the json item to pull
        } catch (Exception e){
            e.printStackTrace();
        }

        Gson gson = new Gson();
        TEKAPI.MyPojo response = gson.fromJson(json, TEKAPI.MyPojo.class);

        response.getRestResponse().getResult().forEach(r -> {
            if (r.getAbbr().equalsIgnoreCase(value)) {
                System.out.println("The largest City for that state is: "+ gson.toJson(r.getLargest_city()));
                System.out.println("The Capital for that state is: "+gson.toJson(r.getCapital()));
                return;
            } else if (r.getName().equalsIgnoreCase(value)){
                System.out.println("The largest City for that state is: "+gson.toJson(r.getLargest_city()));
                System.out.println("The Capital for that state is: "+gson.toJson(r.getCapital()));
                return;
            } else if (r.getName().isEmpty()) {
                System.out.println("I did not get anything to check, please try again");
                return;
            }
            else if (r.getName().matches("^[a-zA-Z]*$")) {
                System.out.println("Sorry i only accept Characters A-Z, please try again");
                return;
            }
        });
    }
}