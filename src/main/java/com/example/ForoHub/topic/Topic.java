package com.example.ForoHub.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Table(name="topics")
@Entity(name="Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String title;
    @Column(unique = true)
    private String message;
    private LocalDateTime dateCreation;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String author;
    private  String course;

    public Topic(DataRegisterTopic dataRegisterTopic) {
        this.title = dataRegisterTopic.title();
        this.message = dataRegisterTopic.message();
        this.dateCreation = LocalDateTime.now();
        this.status = Status.CREATED;
        this.author = dataRegisterTopic.author();
        this.course = dataRegisterTopic.course();
    }


}
