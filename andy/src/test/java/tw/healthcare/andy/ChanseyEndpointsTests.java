package tw.healthcare.andy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import tw.healthcare.andy.models.Token;
import tw.healthcare.andy.models.VisitSchedule;
import tw.healthcare.andy.models.VitalRecord;
import tw.healthcare.andy.services.ChanseyEndpoints;
import tw.healthcare.andy.services.EndpointFactory;
import tw.healthcare.andy.services.dtos.NurseDto;
import tw.healthcare.andy.services.dtos.PatientDto;
import tw.healthcare.andy.services.dtos.ScheduleDto;
import tw.healthcare.andy.services.dtos.VitalsDto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChanseyEndpointsTests {

    private MockWebServer server;
    private ChanseyEndpoints endpoints;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        endpoints = EndpointFactory.getChanseyEndpoints(server.url("").toString());
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void should_login_with_correct_credentials() throws Exception {
        String token = "{\"name\":\"some token\"}";
        server.enqueue(new MockResponse().setBody(token));

        Token response = endpoints.loginAgain("user", "pass").execute().body();

        assertEquals("some token", response.getName());
    }

    @Test
    public void should_get_visiting_schedule_of_today() throws Exception {
        String scheduleJson = "{\n" +
                "    \"nurse\": {\n" +
                "        \"name\": \"Chansey\"\n" +
                "    },\n" +
                "    \"scheduledDate\": \"2016-09-27T14:30:00.000Z\",\n" +
                "    \"patients\": [\n" +
                "        {\n" +
                "            \"name\": \"Andy\",\n" +
                "            \"age\": 58,\n" +
                "            \"gender\": \"male\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        server.enqueue(new MockResponse().setBody(scheduleJson));

        VisitSchedule response = endpoints.getScheduleOfToday("123").execute().body();

        assertEquals("Chansey", response.getNurse().getName());
        assertEquals(1, response.getPatients().size());
    }

    @Test
    public void should_update_patient_vital_records() throws Exception {
        server.enqueue(new MockResponse());

        List<VitalRecord> records = Arrays.asList(new VitalRecord(), new VitalRecord());
        boolean successful = endpoints.postVitalRecords(records).execute().isSuccessful();

        String requestBody = server.takeRequest().getBody().readUtf8();
        assertTrue(successful);
        assertTrue(requestBody.startsWith("["));
        assertTrue(requestBody.endsWith("]"));
        assertTrue(requestBody.contains("patientId"));
        assertTrue(requestBody.contains("height"));
    }

    @Test
    public void should_get_simple_string() throws Exception {
        server.enqueue(new MockResponse().setBody("simple string"));
        String response = endpoints.getSimpleString().execute().body().string();

        assertEquals("simple string", response);
    }

    @Test
    public void should_get_empty_response_body() throws Exception {
        server.enqueue(new MockResponse());
        String response = endpoints.getEmptyResponse().execute().body().string();

        assertTrue(response.isEmpty());
    }

    @Test
    public void should_get_all_nurses() throws Exception {
        String sampleResponses = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Dorothy Cook\",\n" +
                "    \"email\": \"dcook0@dell.com\",\n" +
                "    \"password\": \"ztId78ylicXi\",\n" +
                "    \"created_at\": \"2016-09-28T17:03:09.559+08:00\",\n" +
                "    \"updated_at\": \"2016-09-28T17:03:09.559+08:00\",\n" +
                "    \"url\": \"http://young-journey-22645.herokuapp.com/nurses/1.json\"\n" +
                "  }\n" +
                "]";
        server.enqueue(new MockResponse().setBody(sampleResponses));
        List<NurseDto> nurses = endpoints.getAllNurses().execute().body();

        assertEquals(1, nurses.size());
        assertEquals(1, nurses.get(0).getId().longValue());
        assertEquals("Dorothy Cook", nurses.get(0).getName());
    }

    @Test
    public void should_get_nurse_by_id() throws Exception {
        String sampleResponses = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Dorothy Cook\",\n" +
                "    \"email\": \"dcook0@dell.com\",\n" +
                "    \"password\": \"ztId78ylicXi\",\n" +
                "    \"created_at\": \"2016-09-27T06:15:31.818Z\",\n" +
                "    \"updated_at\": \"2016-09-27T06:15:31.818Z\",\n" +
                "    \"url\": \"https://young-journey-22645.herokuapp.com/nurses/1.json\"\n" +
                "  }";
        server.enqueue(new MockResponse().setBody(sampleResponses));
        NurseDto nurse = endpoints.getNurse(1L).execute().body();

        assertEquals(1, nurse.getId().longValue());
        assertEquals("Dorothy Cook", nurse.getName());
    }

    @Test
    public void should_get_all_patients() throws Exception {
        String sampleResponses = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Andrea Coleman\",\n" +
                "    \"dob\": \"1992-02-15\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"created_at\": \"2016-09-28T17:03:09.740+08:00\",\n" +
                "    \"updated_at\": \"2016-09-28T17:03:09.740+08:00\",\n" +
                "    \"url\": \"http://young-journey-22645.herokuapp.com/patients/1.json\"\n" +
                "  }\n" +
                "]";
        server.enqueue(new MockResponse().setBody(sampleResponses));
        List<PatientDto> patients = endpoints.getAllPatients().execute().body();

        assertEquals(1, patients.size());
        assertEquals(1, patients.get(0).getId().longValue());
        assertEquals("Andrea Coleman", patients.get(0).getName());
    }

    @Test
    public void should_get_all_schedules() throws Exception {
        String sampleResponses = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"appointment_time\": \"2016-09-14T00:00:00.000+08:00\",\n" +
                "    \"nurse_id\": 4,\n" +
                "    \"patient_id\": 10,\n" +
                "    \"created_at\": \"2016-09-28T17:03:10.219+08:00\",\n" +
                "    \"updated_at\": \"2016-09-28T17:03:10.219+08:00\",\n" +
                "    \"url\": \"http://young-journey-22645.herokuapp.com/visiting_schedules/1.json\"\n" +
                "  }\n" +
                "]";
        server.enqueue(new MockResponse().setBody(sampleResponses));
        List<ScheduleDto> schedules = endpoints.getAllSchedules().execute().body();

        assertEquals(1, schedules.size());
        assertEquals(1, schedules.get(0).getId().longValue());
        assertEquals(4, schedules.get(0).getNurseId().longValue());
        assertEquals(10, schedules.get(0).getPatientId().longValue());
        assertEquals(new Date(2016-1900, 8, 14, 0, 0, 0), schedules.get(0).getAppointmentTime());
    }

    @Test
    public void should_get_all_vital_records() throws Exception {
        String sampleResponses = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"patient_id\": 10,\n" +
                "    \"height\": \"178.66\",\n" +
                "    \"weight\": \"83.93\",\n" +
                "    \"pulse\": 60,\n" +
                "    \"temperature\": \"35.27\",\n" +
                "    \"bp_high\": 135,\n" +
                "    \"bp_low\": 85,\n" +
                "    \"created_at\": \"2016-09-28T17:03:09.950+08:00\",\n" +
                "    \"updated_at\": \"2016-09-28T17:03:09.950+08:00\",\n" +
                "    \"url\": \"http://young-journey-22645.herokuapp.com/vital_records/1.json\"\n" +
                "  }\n" +
                "]";
        server.enqueue(new MockResponse().setBody(sampleResponses));
        List<VitalsDto> vitals = endpoints.getAllVitalRecords().execute().body();

        assertEquals(1, vitals.size());
        assertEquals(1, vitals.get(0).getId().longValue());
        assertEquals(10, vitals.get(0).getPatientId().longValue());
        assertEquals("178.66", vitals.get(0).getHeight());
        assertEquals("83.93", vitals.get(0).getWeight());
        assertEquals("35.27", vitals.get(0).getTemperature());
        assertEquals(60, vitals.get(0).getPulse().intValue());
        assertEquals(85, vitals.get(0).getBloodpressureLow().intValue());
        assertEquals(135, vitals.get(0).getBloodpressureHigh().intValue());
    }

    @Test
    public void should_post_new_vital_record() throws Exception {
        server.enqueue(new MockResponse());

        VitalsDto vitals = new VitalsDto();
        vitals.setPatientId(1L);
        vitals.setHeight("175");
        vitals.setWeight("75");
        vitals.setTemperature("35");
        vitals.setPulse(90);

        boolean successful = endpoints.postVitalRecord(vitals).execute().isSuccessful();

        String requestBody = server.takeRequest().getBody().readUtf8();
        assertTrue(successful);
        assertTrue(requestBody.startsWith("{"));
        assertTrue(requestBody.endsWith("}"));
        assertTrue(requestBody.contains("patient_id"));
        assertTrue(requestBody.contains("height"));
        assertTrue(requestBody.contains("weight"));
        assertTrue(requestBody.contains("pulse"));
        assertTrue(requestBody.contains("temperature"));
    }
}