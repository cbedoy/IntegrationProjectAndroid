package cbedoy.gymap.interfaces;

import java.util.HashMap;

public interface IRestService {

    public void request(String url, IRestCallback callback);

    public interface IRestCallback {

        public void run(HashMap<String, Object> response);

    }

}
