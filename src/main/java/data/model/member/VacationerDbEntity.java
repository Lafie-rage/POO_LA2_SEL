package data.model.member;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PRT")
public class VacationerDbEntity extends MemberDbEntity {
    @Basic
    @Column(name="MODEL_LOGEMENT")
    private String housingMode;
}
