package cbedoy.gymap.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.UrlQuerySanitizer;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Utils {

    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;

    public static HashMap<String, Object> mergeHashMaps(HashMap<String, Object> map1, HashMap<String, Object> map2) {
        HashMap<String, Object> merge = new HashMap<String, Object>();

        for (Map.Entry<String, Object> entry : map2.entrySet()) {
            merge.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Object> entry : map1.entrySet()) {
            merge.put(entry.getKey(), entry.getValue());
        }

        return merge;
    }

    public static Object toJSON(Object object) throws JSONException {
        if (object instanceof Map) {
            JSONObject json = new JSONObject();
            Map map = (Map) object;
            for (Object key : map.keySet()) {
                json.put(key.toString(), toJSON(map.get(key)));
            }
            return json;
        } else if (object instanceof Iterable) {
            JSONArray json = new JSONArray();
            for (Object value : ((Iterable) object)) {
                json.put(toJSON(value));
            }
            return json;
        } else
            return object;
    }

    public static boolean isEmptyObject(JSONObject object) {
        return object.names() == null;
    }

    public static Map<String, Object> getMap(JSONObject object, String key) throws JSONException {
        return toMap(object.getJSONObject(key));
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap();
        Iterator keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(object.get(key)));
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public static List toList(JSONArray array) throws JSONException {
        List list = new ArrayList();
        for (int i = 0; i < array.length(); i++) {
            list.add(fromJson(array.get(i)));
        }
        return list;
    }

    private static Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        } else {
            if (json instanceof JSONObject) {
                return toMap((JSONObject) json);
            } else {
                if (json instanceof JSONArray) {
                    return toList((JSONArray) json);
                } else {
                    return json;
                }
            }
        }
    }

    public static String mapToUrlString(Map<String, Object> map) {
        String result = "";
        Map<String, Object> sortedMap = new TreeMap<>(map);
        try {
            for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                String to_append = URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value.toString(), "UTF-8");
                result = result + (result.length() > 0 ? "&" : "") + to_append;
            }
        } catch (Exception ex) {
            result = "";
        }
        return result;
    }

    public static boolean isValidEmail(String eMail) {
        String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(eMail);
        return matcher.matches();
    }

    public static String createHMAC(String input, String key) {
        String hmac;
        try {
            Formatter formatter = new Formatter();
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKeySpec);
            for (byte b : mac.doFinal(input.getBytes())) {
                formatter.format("%02x", b);
            }
            hmac = formatter.toString();
            formatter.close();
        } catch (Exception ex) {
            hmac = "";
        }
        return hmac;
    }

    public static String createSHA1(String raw) {
        String sha1;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            digest.update(raw.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            sha1 = hexString.toString();
        } catch (Exception ex) {
            sha1 = raw;
        }
        while (sha1.length() < 32) {
            sha1 = "0" + sha1;
        }
        return sha1;
    }

}
