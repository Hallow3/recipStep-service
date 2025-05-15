package com.soft.recipStepservice.serviceImplement;

import com.soft.recipStepservice.Exceptions.CustomizeException;
import com.soft.recipStepservice.entities.Recip;
import com.soft.recipStepservice.entities.Step;
import com.soft.recipStepservice.repository.StepRepository;
import com.soft.recipStepservice.service.RecipFetcherService;
import com.soft.recipStepservice.service.StepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StepServiceImplement implements StepService {

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private RecipFetcherService recipFetcherService;

    @Override
    public Step addStep(Step step, MultipartFile video) {
        if(video.getSize() > 10000000 || video.getSize() < 0 || video.isEmpty()){
            log.info("the video size is not correct");
            return null;
        }
        try {
            String videoName = StringUtils.cleanPath(video.getOriginalFilename());
            if(!videoName.isEmpty() && !videoName.trim().equals(" ")){
                if(videoName.endsWith(".mp4") || videoName.endsWith(".avi") || videoName.endsWith(".mkv")){
                    File fichier = new File("C:\\Users\\Halloween\\Documents\\workspace-spring-tool-suite-4-4.15.3.RELEASE\\Digest\\digest-front\\src\\assets\\videos\\steps\\"+videoName);
                    fichier.createNewFile();
                    FileOutputStream fout = new FileOutputStream(fichier);
                    fout.write(video.getBytes());
                    fout.close();
                    step.setVideo(videoName);
                    Step stepToSave = stepRepository.save(step);
                    recipFetcherService.upgradeRecip(step.getRecipId(), step.getId());
                    return stepToSave;
                }else {
                    log.info("your file type isn't allowed");
                    return null;
                }

            }else {
                log.info("failed to extract your file name");
                return null;
            }

        }catch (Exception e){
            log.info("error while trying to add step: "+e);
            return null;
        }
    }

    @Override
    public List<Step> getAllSteps() {
        return stepRepository.findAll();
    }

    @Override
    public Step updateStep(Step step) {
        Step stepToSave = stepRepository.findById(step.getId()).get();
        if(stepToSave != null){
            stepToSave.setDuration(step.getDuration());
            stepToSave.setStepText(step.getStepText());
            stepToSave.setVideo(step.getVideo());
            return stepRepository.save(stepToSave);
        }
        throw new IllegalArgumentException("step with this that id haven't be found");
    }

    @Override
    public void deleteStep(int id) {
        stepRepository.deleteById(id);
    }

    @Override
    public Step getStepById(int id) {
        return stepRepository.findById(id).get();
    }

    @Override
    public List<Step> getStepByRecipId(int id) {
        return stepRepository.findByRecipId(id);
    }

    @Override
    public Map<String, Object> getStepByPosition(int recipId, int stepPosition) throws CustomizeException {
        Step step = new Step();
        List<Step> steps = stepRepository.findByRecipId(recipId);
        if(steps != null){
            step = steps.get(stepPosition);
        }

        if(step != null){
            Map<String, Object> result = new HashMap<>();
            result.put("step",step);
            result.put("limit",steps.size());
            return  result;
        }else {
            throw new CustomizeException("impossible de trouvé cette étape de cuisson");
        }
    }
}
