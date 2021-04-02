package com.soen390.team11.service;

import com.soen390.team11.constant.LogTypes;
import com.soen390.team11.entity.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogService {

    /**
     * Collection of all logs.
     *
     * Each log has 3 values: OffsetDateTime, Log Message, and LogType
     */
    private final Map<LogTypes, List<Log>> logs;

    public LogService()
    {
        this.logs = new HashMap<>();
        restartLogs();
    }

    /**
     * Resets all logs
     */
    private void restartLogs() {
        logs.put(LogTypes.SYSTEM, new ArrayList<>());
        logs.put(LogTypes.ORDERS, new ArrayList<>());
        logs.put(LogTypes.USERS, new ArrayList<>());
        logs.put(LogTypes.MACHINERY, new ArrayList<>());
        logs.put(LogTypes.PRODUCT, new ArrayList<>());
        logs.put(LogTypes.PART, new ArrayList<>());
        logs.put(LogTypes.MATERIAL, new ArrayList<>());
        logs.put(LogTypes.VENDOR, new ArrayList<>());
    }

    /**
     * Returns the SYSTEM logs
     *
     * @return List of System logs
     */
    public List<Log> getAllLogs()
    {
        return logs.get(LogTypes.SYSTEM);
    }

    /**
     * Gets a specified logs
     *
     * @param logTypes the type of log
     * @return List of all logs of that type
     */
    public List<Log> getLogs(LogTypes logTypes)
    {
        return logs.get(logTypes);
    }

    /**
     * Obtains the CSV file with the logs
     *
     * @param logTypes the types of log
     * @return A file with the logs
     * @throws IOException Thrown if it is unable to create a file
     */
    public ByteArrayResource getCSV(LogTypes logTypes) throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (Writer writer = new OutputStreamWriter(out)) {
            writer.write("type,time,message\n");
            logs.get(logTypes).forEach((log) -> {
                try {
                    writer.write(log.getType() + "," + log.getTime() + "," + log.getMessage() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return new ByteArrayResource(out.toByteArray());
    }

    /**
     * Write a new message onto the logs and onto system if needed
     *
     * @param logType the type of log
     * @param message the message
     */
    public void writeLog(LogTypes logType, String message)
    {
        List<Log> list = logs.get(logType);
        list.add(new Log(logType.toString(), OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS).toString(), message));
        logs.put(logType, list);

        if (!logType.equals(LogTypes.SYSTEM))
        {
            List<Log> listSystem = logs.get(LogTypes.SYSTEM);
            listSystem.add(new Log(LogTypes.SYSTEM.toString(), OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS).toString(), message));
            logs.put(LogTypes.SYSTEM, listSystem);
        }

    }
}
