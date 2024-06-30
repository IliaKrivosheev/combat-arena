package com.combat.arena.core.event;

import com.combat.arena.core.categories.Category;
import com.combat.arena.core.common.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "event")
@EqualsAndHashCode(callSuper = true)
public class Event extends AbstractAuditingEntity implements Serializable {
    @Column
    private String uuid = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String name;

    @Column
    private String shortName;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;

    @Column
    private String country;

    @Column
    private String city;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "EventCategories",
            joinColumns = {@JoinColumn(name = "eventId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="categoryId", referencedColumnName = "id")}
    )
    private Set<Category> categories = new HashSet<>();

    @Column
    private String posterPath;

    @Column
    private String dateToDisplay;

    @Column
    private String eventDetails;

    @Column
    private String venueAddress;

    @Column
    private String venueLink;

    @Column
    private String contactPhone;

    @Column
    private String contactEmail;

    @Column
    private String facebookLink;

    @Column
    private String instagramLink;

    @Column
    @Enumerated(EnumType.STRING)
    private ComparisonKeys comparisonKey;

    @Column
    private String comparisonKeyDisplayedName;

    @Column
    @Enumerated(EnumType.STRING)
    private FilterKeys filterKey;

    @Column
    private String filterKeyDisplayedName;

    @Column
    private Boolean hideDrawsOnClientPage;

    @Column
    private Boolean showCountry;

    @Column
    private Boolean showOrganization;

    @Column
    private Boolean showDojo;

    @Column
    private Boolean whiteParticipantFirst;

    @Column
    private String drawSignatures;

    @Column
    private String liveStreamLink;

    public Boolean getHideDrawsOnClientPage() {
        return hideDrawsOnClientPage != null && hideDrawsOnClientPage;
    }

    public Boolean getShowCountry() {
        return showCountry != null && showCountry;
    }

    public Boolean getShowOrganization() {
        return showOrganization != null && showOrganization;
    }

    public Boolean getShowDojo() {
        return showDojo != null && showDojo;
    }

    public Boolean getWhiteParticipantFirst() {
        return whiteParticipantFirst != null && whiteParticipantFirst;
    }
}
