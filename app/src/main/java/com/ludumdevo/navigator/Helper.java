package com.ludumdevo.navigator;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
public class Helper {
    private static final String DIRECTION_API = "https://maps.googleapis.com/maps/api/directions/json?origin=";
    public static final String API_KEY = "AIzaSyDTah-xAk6pKm_hmV2yJ6zOasxY4bxecaM";
    public static final int MY_SOCKET_TIMEOUT_MS = 5000;

    public static String getUrl(String originLat, String originLon, String destinationLat, String destinationLon, int pathTopic) {
        String waypoints = "";
        if (pathTopic == 0)
            waypoints = "";
        if (pathTopic == 1)
            waypoints = "&waypoints=55.755994,37.6151227|55.7558247,37.6147286|55.7558247,37.6147286|55.7555059,37.6142342";
        if (pathTopic == 2)
            waypoints = "&waypoints=55.7560767,37.6172764|55.7559102,37.6171323|55.7556071,37.6179592|55.7555687,37.6177657|55.752714, 37.612037";
        if (pathTopic == 3)
            waypoints = "&waypoints=55.7541691,37.6178771|55.7544407,37.6175394|55.7536457,37.6150231|55.7522625,37.6124315|55.7523867,37.6115843|55.7525453,37.6105946|55.752723,37.611929";
        if (pathTopic == 4)
            waypoints = "&waypoints=55.7511731,37.6157043|55.7515857,37.6175279|55.75067,37.6162041|55.7524717,37.6187076";
        if (pathTopic == 5)
            waypoints = "&waypoints=55.7562845,37.6050369|55.7546181,37.6035778|55.7541472,37.5998227|55.7522031,37.5926344|55.7505003,37.5880639|55.747487,37.5836182|55.7470945,37.5839186";
        if (pathTopic == 6)
            waypoints = "&waypoints=55.7546636,37.6203838|55.749673,37.6297603|55.7513587,37.6139527";
        if (pathTopic == 7)
            waypoints = "&waypoints=55.7614369,37.6219955|55.7612725,37.6232298|55.7589544,37.6154192";
        return Helper.DIRECTION_API + originLat + "," + originLon + "&destination=" + destinationLat + "," + destinationLon + "&key=" + API_KEY + "&mode=walking" + waypoints;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}