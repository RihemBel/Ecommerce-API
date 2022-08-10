package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {
/*    @Column(name = "deleted")
    private Boolean deleted= false;
    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "UTC")
    private LocalDateTime dataChangeCreatedTime;
    @Column(name = "updated_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "UTC")
    private LocalDateTime dataChangeLastModifiedTime;

//    @PrePersist
//    protected void prePersist() {
//        System.out.println("sssssssssssssssssssssssssssssssssssss");
//
//        if (this.dataChangeCreatedTime == null) dataChangeCreatedTime = new DateTime();
//        if (this.dataChangeLastModifiedTime == null) dataChangeLastModifiedTime = new Date();
//    }
//
//    @PreUpdate
//    protected void preUpdate() {
//        System.out.println("teeeeeeeeeeeeeeeeeeeeeeeeeeee");
//        this.dataChangeLastModifiedTime = new Date();
//    }
//
//    @PreRemove
//    protected void preRemove() {
//        this.dataChangeLastModifiedTime = new Date();
//    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }*/
}
