package com.ace.models.entity;

import com.ace.models.entity.base.BaseEntity;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 * @Classname: RRWebEvent
 * @Date: 12/5/24 PM6:23
 * @Author: garlam
 * @Description:
 */

@EntityListeners(AuditingEntityListener.class)
@Table(name = "rrweb_events")
@Entity
public class RRWebEvents extends BaseEntity {
    private static final Logger log = LogManager.getLogger(RRWebEvents.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
    @Column
    private Long eventId;
    @Column
    private String url;
    @Column
    private Long userId;
    @Column
    private String userAccount;
    @Column
    private StringBuilder eventData;


}

