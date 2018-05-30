package com.joseangelmaneiro.movies.data.source.remote;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.StringContains.containsString;


public class MockWebServerTest {

    private static final String FILE_ENCODING = "UTF-8";

    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        this.server = new MockWebServer();
        this.server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    protected void enqueueMockResponse() throws IOException {
        enqueueMockResponse(200);
    }

    protected void enqueueMockResponse(int code) throws IOException {
        enqueueMockResponse(code, null);
    }

    protected void enqueueMockResponse(int code, String fileName) throws IOException {
        MockResponse mockResponse = new MockResponse();
        String fileContent = getContentFromFile(fileName);
        mockResponse.setResponseCode(code);
        mockResponse.setBody(fileContent);
        server.enqueue(mockResponse);
    }

    protected void assertRequestSentTo(String url) throws InterruptedException {
        RecordedRequest request = server.takeRequest();
        assertEquals(url, request.getPath());
    }

    protected void assertGetRequestSentTo(String url) throws InterruptedException {
        RecordedRequest request = server.takeRequest();
        assertEquals(url, request.getPath());
        assertEquals("GET", request.getMethod());
    }

    protected void assertPostRequestSentTo(String url) throws InterruptedException {
        RecordedRequest request = server.takeRequest();
        assertEquals(url, request.getPath());
        assertEquals("POST", request.getMethod());
    }

    protected void assertPutRequestSentTo(String url) throws InterruptedException {
        RecordedRequest request = server.takeRequest();
        assertEquals(url, request.getPath());
        assertEquals("PUT", request.getMethod());
    }

    protected void assertDeleteRequestSentTo(String url) throws InterruptedException {
        RecordedRequest request = server.takeRequest();
        assertEquals(url, request.getPath());
        assertEquals("DELETE", request.getMethod());
    }

    protected void assertRequestSentToContains(String... paths) throws InterruptedException {
        RecordedRequest request = server.takeRequest();

        for (String path : paths) {
            Assert.assertThat(request.getPath(), containsString(path));
        }
    }

    protected void assertRequestContainsHeader(String key, String expectedValue)
            throws InterruptedException {
        assertRequestContainsHeader(key, expectedValue, 0);
    }

    protected void assertRequestContainsHeader(String key, String expectedValue, int requestIndex)
            throws InterruptedException {
        RecordedRequest recordedRequest = getRecordedRequestAtIndex(requestIndex);
        String value = recordedRequest.getHeader(key);
        assertEquals(expectedValue, value);
    }

    protected String getBaseEndpoint() {
        return server.url("/").toString();
    }

    protected void assertRequestBodyEquals(String jsonFile) throws InterruptedException, IOException {
        RecordedRequest request = server.takeRequest();
        assertEquals(getContentFromFile(jsonFile), request.getBody().readUtf8());
    }

    protected String getContentFromFile(String fileName) throws IOException {
        if (fileName == null) {
            return "{}";
        }
        fileName = getClass().getResource("/" + fileName).getFile();
        File file = new File(fileName);
        List<String> lines = FileUtils.readLines(file, FILE_ENCODING);
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    private RecordedRequest getRecordedRequestAtIndex(int requestIndex) throws InterruptedException {
        RecordedRequest request = null;
        for (int i = 0; i <= requestIndex; i++) {
            request = server.takeRequest();
        }
        return request;
    }

}
