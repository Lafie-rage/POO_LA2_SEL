package data.model.member;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PRO")
public class ProfessionalDbEntity extends MemberDbEntity {
    @Basic
    @Column(name = "SIRET")
    private String siret;
}
