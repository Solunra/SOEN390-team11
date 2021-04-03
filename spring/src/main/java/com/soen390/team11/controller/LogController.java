package com.soen390.team11.controller;

import com.soen390.team11.constant.LogTypes;
import com.soen390.team11.entity.Log;
import com.soen390.team11.service.LogService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    private final LogService logService;

    /**
     * Constructor with log service
     * @param logService The service side of the logs
     */
    public LogController(LogService logService)
    {
        this.logService = logService;
    }

    /**
     * Obtains all the system's logs
     * @return List of the system logs
     */
    @GetMapping
    public List<Log> getAllLogs()
    {
        return logService.getAllLogs();
    }

    /**
     * Obtains a specific log type
     * @param logType log type to get
     * @return List of the type of logs
     */
    @GetMapping("/{logType}")
    public List<Log> getLogs(@PathVariable String logType)
    {
        try
        {
            return logService.getLogs(LogTypes.valueOf(logType));
        }
        catch (IllegalArgumentException | NullPointerException e)
        {
            return null;
        }
    }

    /**
     * Allows a CSV of the logs to be downloaded
     *
     * @param logType The type of logs to download
     * @return The download file
     */
    @GetMapping("/{logType}/download")
    public ResponseEntity<Resource> downloadLogs(@PathVariable String logType)
    {
        try
        {
            ByteArrayResource resource = logService.getCSV(LogTypes.valueOf(logType));
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + "log-" + logType + ".csv" )
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
        catch (IllegalArgumentException | NullPointerException | IOException e)
        {
            return ResponseEntity.badRequest().build();
        }
    }
}
