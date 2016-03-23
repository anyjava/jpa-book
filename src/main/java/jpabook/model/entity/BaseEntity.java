package jpabook.model.entity;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by anyjava on 2016. 3. 23..
 */
@MappedSuperclass
public class BaseEntity {

    private Date createdDate;
    private Date lastModifiedDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
