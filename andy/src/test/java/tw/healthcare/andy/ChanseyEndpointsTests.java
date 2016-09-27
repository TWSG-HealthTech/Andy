package tw.healthcare.andy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import tw.healthcare.andy.models.Token;
import tw.healthcare.andy.models.VisitSchedule;
import tw.healthcare.andy.models.VitalRecord;
import tw.healthcare.andy.services.ChanseyEndpoints;
import tw.healthcare.andy.services.EndpointFactory;

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
}