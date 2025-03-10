package com.sea.reporter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sea.reporter.model.dto.ESBContradiction;
import com.sea.reporter.service.Services;
import com.sea.reporter.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Set;

/**
 * REST controller for managing scheduled tasks in the application.
 * Provides endpoints to start, stop, and list scheduled tasks, as well as
 * generate and download settlement reports.
 */
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    private static final String SCHEDULED_TASKS = "scheduledTasks";
    private ApplicationContext applicationContext;
    private String beanName;

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;

    @Qualifier("servicesImp")
    @Autowired
    private Services schedulerConfiguration;
    @Qualifier("servicesImpWithCutOff")
    @Autowired
    private Services withCutOff;

    /**
     * Stops a specific scheduled task.
     *
     * @param task The identifier of the task to stop
     * @return A message confirming the task was cancelled
     */
    @GetMapping(value = "/stop")
    public String stopSchedule(@RequestParam("task") String task) {
        Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();

        if (!setTasks.isEmpty()) {
            setTasks.forEach(s -> {
                if (s.getTask().toString().equals(task)) {
                    s.cancel();
                }
            });
        }
        //postProcessor.postProcessBeforeDestruction(schedulerConfiguration, SCHEDULED_TASKS);
        return "Task " + task + "CANCELED";
    }

    /**
     * Starts the scheduler configuration.
     *
     * @return A message confirming the scheduler was started
     */
    @GetMapping(value = "/start")
    public String startSchedule() {
        //postProcessor.postProcessAfterInitialization(schedulerConfiguration, SCHEDULED_TASKS);
        //schedulerConfiguration.test();
        return "OK";
    }

    /**
     * Generates and downloads a settlement report for a specified date range.
     * Uses the standard scheduler configuration.
     *
     * @param from Start date for the report
     * @param to End date for the report
     * @return ResponseEntity containing the generated report as a ZIP file
     */
    @GetMapping(value = "/start2")
    public ResponseEntity<Resource> startSchedule(@RequestParam("from") String from, @RequestParam("to") String to) {
        ESBContradiction esbContradiction = schedulerConfiguration.conflict(from, to, new File(System.getProperty("user.dir")));
        return getResourceResponseEntity(esbContradiction, schedulerConfiguration);
    }

    /**
     * Generates and downloads a settlement report for a specified date range.
     * Uses the scheduler configuration with cutoff time.
     *
     * @param from Start date and time for the report
     * @param to End date and time for the report
     * @return ResponseEntity containing the generated report as a ZIP file
     */
    @GetMapping(value = "/start1", produces = "application/zip")
    public ResponseEntity<Resource> startSchedule2(@RequestParam("from") String from, @RequestParam("to") String to) {
        ESBContradiction esbContradiction = withCutOff.conflict(from.split(" ")[0], to.split(" ")[0], new File(System.getProperty("user.dir")));
        return getResourceResponseEntity(esbContradiction, withCutOff);
    }

    /**
     * Helper method to create a ResponseEntity containing the generated report.
     * Compresses the report into a ZIP file and sets appropriate headers for download.
     *
     * @param esbContradiction The ESB contradiction data
     * @param services The service instance to use for settlement processing
     * @return ResponseEntity containing the compressed report file
     */
    private ResponseEntity<Resource> getResourceResponseEntity(ESBContradiction esbContradiction, Services services) {
        String outPutDir = services.makeSettlement(new File(System.getProperty("user.dir") +File.separator+ "vasout.txt"), new File(services.getDefaultDirectory()), Tools.EncryptStatus.ZIP_WITH_PASSWORD_AND_RAW, esbContradiction.getUdForms());
        boolean result = false;
        try {
            result = Tools.compress(new File(outPutDir), new File(outPutDir + ".zip"), Tools.EncryptStatus.ZIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result) {
            System.out.println(outPutDir + ".zip");
            File file = new File(outPutDir + ".zip");
            InputStreamResource resource = null;
            try {
                resource = new InputStreamResource(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Disposition", "attachment; filename=\"" + file.getName().trim() + "\"");
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");
            header.add(HttpHeaders.CONTENT_TYPE, "application/zip");
            System.out.println(String.valueOf(header));
            return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"" + file.getName().trim() + "\"").
                    header("Cache-Control", "no-cache, no-store, must-revalidate").
                    header("Pragma", "no-cache").
                    header("Expires", "0").
                    header(HttpHeaders.CONTENT_TYPE, "application/zip").contentLength(file.length()).body(resource);
        }

        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }

    /**
     * Lists all currently running scheduled tasks.
     *
     * @return A string representation of all running tasks, or a message if no tasks are running
     * @throws JsonProcessingException if there's an error processing the task list
     */
    @GetMapping(value = "/list")
    public String listSchedules() throws JsonProcessingException {
        Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();

        if (!setTasks.isEmpty()) {
            return setTasks.toString();
        } else {
            return "Currently no scheduler tasks are running";
        }
    }

}
