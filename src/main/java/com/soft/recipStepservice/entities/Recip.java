package com.soft.recipStepservice.entities;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Recip {

    private Long id;
    private String name;
    private String description;
    private String picture;
    private String cookingTime;
    private int subCategoryId;
    private boolean isEnable;
    private String needs;
}
