package tw.healthcare.andy.services;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tw.healthcare.andy.services.dtos.NurseDto;
import tw.healthcare.andy.services.dtos.PatientDto;
import tw.healthcare.andy.services.dtos.ScheduleDto;
import tw.healthcare.andy.services.dtos.VitalsDto;

public interface ChanseyEndpoints {
    @GET("simplestring")
    Call<ResponseBody> getSimpleString();

    @GET("empty")
    Call<ResponseBody> getEmptyResponse();

    @Headers({"Accept: application/json"})
    @GET("nurses")
    Call<List<NurseDto>> getAllNurses();

    @Headers({"Accept: application/json"})
    @GET("nurses/{nurseId}")
    Call<NurseDto> getNurse(@Path("nurseId") Long nurseId);

    @Headers({"Accept: application/json"})
    @GET("patients")
    Call<List<PatientDto>> getAllPatients();

    @Headers({"Accept: application/json"})
    @GET("visiting_schedules")
    Call<List<ScheduleDto>> getAllSchedules();

    @Headers({"Accept: application/json"})
    @GET("vital_records")
    Call<List<VitalsDto>> getAllVitalRecords();

    @Headers({"Accept: application/json"})
    @POST("vital_records")
    Call<ResponseBody> postVitalRecord(@Body VitalsDto record);
}
