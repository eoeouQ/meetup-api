package org.izouir.meetup_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "meets")
public class Meet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meet_id")
    private Long meetId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "keeper")
    private String keeper;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "place")
    @Enumerated(EnumType.STRING)
    private Place place;

    public Meet() {
    }

    public Meet(final Long meetId, final String title, final String description, final String keeper, final Timestamp date, final Place place) {
        this.meetId = meetId;
        this.title = title;
        this.description = description;
        this.keeper = keeper;
        this.date = date;
        this.place = place;
    }

    public Long getMeetId() {
        return meetId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getKeeper() {
        return keeper;
    }

    public Timestamp getDate() {
        return date;
    }

    public Place getPlace() {
        return place;
    }
}
