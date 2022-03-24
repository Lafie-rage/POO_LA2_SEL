package data.model.member;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("INH")
public class InhabitantDbEntity extends MemberDbEntity {
    @Basic
    @Column(name = "ELU")
    private boolean isElected;
}
