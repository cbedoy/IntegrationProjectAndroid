package cbedoy.gymap.interfaces;

import java.util.HashMap;

public interface IRestService {

    public void setPort(int port);

    public void setURL(String url);

    public void setEnvironmentMaps(HashMap<String, Object> EnvironmentMaps);


    public void request(String url, HashMap<String, Object> parameters, IRestCallback callback);

    public interface IRestCallback {

        public void run(HashMap<String, Object> response);

    }

}
