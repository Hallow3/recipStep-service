package com.soft.recipStepservice.service;

import com.soft.recipStepservice.Exceptions.CustomizeException;
import com.soft.recipStepservice.entities.Step;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface StepService {

     Step addStep(Step step, MultipartFile file);

     List<Step> getAllSteps();

     Step updateStep(Step step);

     void deleteStep(int id);

     Step getStepById(int id);

     List<Step> getStepByRecipId(int id);

     Map<String, Object> getStepByPosition(int recipId, int stepPosition) throws CustomizeException;
}
