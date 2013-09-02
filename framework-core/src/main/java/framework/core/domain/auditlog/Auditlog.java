package framework.core.domain.auditlog;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import framework.core.constants.EventType;
import framework.core.domain.BaseEntity;

/**
 * Represents events necessary for auditing.
 * 
 * @author Frederick Yap
 */
@Entity
@Table(name = "AUDITLOG")
@NamedQueries(value = { @NamedQuery(name = "findLastAuditlogByDetail", query = "from Auditlog where detail LIKE :detail ORDER BY logdate DESC") })
public class Auditlog extends BaseEntity {

    private static final long serialVersionUID = -3935171119789690953L;

    @Column(columnDefinition = "TEXT")
    private String detail;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private final Date logdate;

    @Column
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column
    private String userid;

    public Auditlog() {
        this.logdate = Calendar.getInstance().getTime();
    }

    /**
     * Returns the event log entry.
     * 
     * @return the event log entry.
     */
    public String getDetail() {
        return this.detail;
    }

    /**
     * Returns the event type.
     * 
     * @return the event type.
     */
    public EventType getType() {
        return this.type;
    }

    /**
     * Returns the unique id of the authenticated user when the event has occured. Otherwise, returns SYSTEM if it is an
     * application event.
     * 
     * @return the unique id of the authenticated user.
     */
    public String getUserid() {
        return this.userid;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getLogdate() {
        return this.logdate;
    }

}
