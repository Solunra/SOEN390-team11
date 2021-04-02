package com.soen390.team11.controller;

import com.soen390.team11.constant.LogTypes;
import com.soen390.team11.entity.Log;
import com.soen390.team11.service.LogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.OffsetDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class LogControllerTest {

    LogController logController;
    @Mock
    LogService logService;

    @BeforeEach
    void setup()
    {
        openMocks(this);
        logController = new LogController(logService);
    }

    @Test
    void getAllLogs()
    {
        when(logService.getAllLogs()).thenReturn(List.of(new Log(LogTypes.SYSTEM.toString(), OffsetDateTime.now().toString(), "testMessage")));
        List<Log> logs = logController.getAllLogs();
        assertEquals("testMessage", logs.get(0).getMessage());
        assertEquals(LogTypes.SYSTEM.toString(), logs.get(0).getType());
    }

    @Test
    void getCorrectLogType()
    {
        when(logService.getLogs(LogTypes.ORDERS)).thenReturn(List.of(new Log(LogTypes.ORDERS.toString(), OffsetDateTime.now().toString(), "testMessage")));
        List<Log> logs = logController.getLogs(LogTypes.ORDERS.toString());
        assertEquals("testMessage", logs.get(0).getMessage());
        assertEquals(LogTypes.ORDERS.toString(), logs.get(0).getType());
    }

    @Test
    void getIncorrectLogType()
    {
        List<Log> logs = logController.getLogs("invalid");
        assertNull(logs);
    }

    @Test
    void downloadCorrectLogs() throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (Writer writer = new OutputStreamWriter(out)) {
            writer.write("type,time,message");
            writer.write("SYSTEM,2007-12-03T10:15:30+01:00,message1");
            writer.write("SYSTEM,2007-12-04T10:15:30+01:00,message2");
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(out.toByteArray());
        when(logService.getCSV(LogTypes.SYSTEM)).thenReturn(byteArrayResource);

        ResponseEntity<Resource> responseEntity = logController.downloadLogs(LogTypes.SYSTEM.toString());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(byteArrayResource.contentLength(), responseEntity.getBody().contentLength());
    }

    @Test
    void downloadIncorrectLogs()
    {
        ResponseEntity<Resource> responseEntity = logController.downloadLogs("invalid");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}
