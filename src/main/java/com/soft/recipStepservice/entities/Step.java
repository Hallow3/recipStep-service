package com.soft.recipStepservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Table(name = "step")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DURATION", nullable = false)
    private String duration;

    @Column(name = "VIDEO", nullable = false)
    private String video;

    @Column(name = "STEPTEXT", nullable = false)
    private String stepText;

    @Column(name = "RECIPID", nullable = false)
    private int recipId;

    @Transient
    private Recip recip;
}
