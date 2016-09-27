package tw.healthcare.andy.services;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tw.healthcare.andy.models.Token;
import tw.healthcare.andy.models.VisitSchedule;
import tw.healthcare.andy.models.VitalRecord;

public interface ChanseyEndpoints {
    @GET("login")
    Call<Token> loginAgain(@Query("user") String username, @Query("pass") String password);

    @GET("schedule/{nid}")
    Call<VisitSchedule> getScheduleOfToday(@Path("nid") String nurseId);

    @POST("vitalrecords")
    Call<ResponseBody> postVitalRecords(@Body List<VitalRecord> records);

    @GET("simplestring")
    Call<ResponseBody> getSimpleString();

    @GET("empty")
    Call<ResponseBody> getEmptyResponse();
}
