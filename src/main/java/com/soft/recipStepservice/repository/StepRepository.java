package com.soft.recipStepservice.repository;

import com.soft.recipStepservice.entities.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepRepository extends JpaRepository<Step, Integer> {

    public List<Step> findByRecipId(int id);
}
