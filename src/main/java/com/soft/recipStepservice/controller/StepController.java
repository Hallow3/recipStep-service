package com.soft.recipStepservice.controller;

import com.soft.recipStepservice.Exceptions.CustomizeException;
import com.soft.recipStepservice.entities.Step;
import com.soft.recipStepservice.service.RecipFetcherService;
import com.soft.recipStepservice.service.StepService;
import com.soft.recipStepservice.service.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/step")
public class StepController {

    @Autowired
    private StepService stepService;

    @Autowired
    private StreamingService streamingService;

    @Autowired
    private RecipFetcherService recipFetcherService;

    @GetMapping("/all")
    public List<Step> getAllSteps(){
        return stepService.getAllSteps();
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Step> createStep(@RequestPart("step") Step step, @RequestPart("video") MultipartFile file) throws Exception {
            return new ResponseEntity<>(stepService.addStep(step, file), HttpStatus.OK);
    }

    @PostMapping(value = "/steps")
    public List<Step> getStepsByRecipId(@RequestParam(name = "id") int recipId){
        return stepService.getStepByRecipId(recipId);
    }

    @GetMapping(value = "/recip/{position}")
    public Map<String, Object> getStepsByPosition(@PathVariable int position, @RequestParam(name = "recip") int recip) throws CustomizeException {
        return stepService.getStepByPosition(recip, position);
    }


    @GetMapping("/exist/{id}")
    public boolean stepExist(@PathVariable(name = "id") int id){
        if(stepService.getStepById(id) != null)
            return true;
        else
            return false;
    }

    @PostMapping("/update")
    public ResponseEntity<Step> updateStep(@RequestBody Step step){
        return new ResponseEntity<>(stepService.updateStep(step), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteStep(@RequestParam(name = "id") int stepId){
        stepService.deleteStep(stepId);
        return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
    }

    @GetMapping(value = "/video/{title}", produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String title){
        return streamingService.getVideo(title);
    }
}
