package com.nathanpool.updatepanel;

import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseExtraction {
    static final String APIKEY = "AIzaSyDWhU_k_pkFr8Bm9Gie71FHbQI-PCNmfhI";
    static final String BASESEARCHURL = "https://www.googleapis.com/youtube/v3/search?";
    static final String APIURL1 = BASESEARCHURL + "key=" + APIKEY + "&channelId=";
    static final String APIURL2 = "&part=snippet,id&order=date&maxResults=1";


    // Perform the http request not in the main thread. Get the full JSON response and save it
    // in result.
    public String getResponse(int i) throws ExecutionException, InterruptedException {
        String myUrl = APIURL1 + Controller.CHANNELS[i] + APIURL2;
        HttpGetRequest getRequest = new HttpGetRequest();
        // Perform the doInBackground method, passing in the url
        String result = getRequest.execute(myUrl).get();
        Log.i("Madeit", result);
        return(result);
    }

    // Extract only the information we need from the JSON responses.
    // That is, the newest video IDs from each channel given in the CHANNELS constant
    public String[] extractIDs(String[] responses) {
        String[] ids = new String[Controller.LEN];
        // Regex that extracts a verification that the object is a youtube video
        Pattern pattern = Pattern.compile("\"items\":.*\"id\":.*\"kind\": \"(.*?)\",");
        for (int i = 0; i < Controller.LEN; i++) {
            Matcher matcher = pattern.matcher(responses[i]);
            if (matcher.find()) {
                ids[i] = matcher.group(1);
            } else {
                Log.i("Madeit", "You did something wrong.");
            }

            // Verify that it is a youtube video
            Log.i("Links", ids[i]);
            if (ids[i].equals("youtube#video")) {
                pattern = Pattern.compile("\"items\":.*\"id\":.*\"kind\": \"(.*?)\",.*?\"video" +
                        "Id\": \"(.*?)\" ");
                matcher = pattern.matcher(responses[i]);
                if (matcher.find()) {
                    // if you want the full url, add BASEVIDEOURL to the beginning
                    // but we don't need it. We just need the ID.
                    ids[i] = matcher.group(2);  // put newest vid links in array
                    Log.i("Madeitt", matcher.group(2));
                } else {
                    Log.i("Madeitnahhh", "You're so close, dog");
                }
            }
            Log.i("Madeitman", i + "");
            Log.i("Madeitman", ids[i]);
        }
        // After much regex, these are the links of the newest videos for each given YouTube channel
        Log.i("Madeit", "Returning now...");
        return(ids);
    }
}
