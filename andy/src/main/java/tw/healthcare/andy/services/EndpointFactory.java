package tw.healthcare.andy.services;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class EndpointFactory {

    private static final String CHANSEY_HOST = "http://young-journey-22645.herokuapp.com/";

    public static ChanseyEndpoints getChanseyEndpoints() {
        return getChanseyEndpoints(CHANSEY_HOST);
    }

    public static ChanseyEndpoints getChanseyEndpoints(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(ChanseyEndpoints.class);
    }
}
