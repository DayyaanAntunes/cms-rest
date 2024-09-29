package com.challenge.cms.country.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "UPDATE countries SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String capital;
    private String region;
    private String subRegion;
    private double area;
    private boolean isDeleted;
}
