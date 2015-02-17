package cbedoy.gymap.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utils {

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


}
