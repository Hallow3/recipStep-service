package com.soft.recipStepservice.service;

import com.soft.recipStepservice.entities.Recip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "RECIP-SERVICE")
public interface RecipFetcherService {

    @GetMapping("/recip/{id}")
    Recip findRecipById(@PathVariable(name = "id") int id);

    //augmenter le nombre d'étapes de la recette pour afficher à l'utilisateur
    @PostMapping("/recip/upgrade")
    Recip upgradeRecip(@RequestParam(name = "idrecip") int recipId, @RequestParam(name = "idstep") int stepId);
}
