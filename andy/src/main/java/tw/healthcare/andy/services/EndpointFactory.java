package tw.healthcare.andy.services;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class EndpointFactory {
    public static ChanseyEndpoints getChanseyEndpoints(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(ChanseyEndpoints.class);
    }
}
