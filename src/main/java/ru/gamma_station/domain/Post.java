package ru.gamma_station.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TEXT")
    @NotEmpty
    private String author;

    @Column(columnDefinition = "TEXT")
    @NotEmpty
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP")
    private Date publishedDate;

    public Post(String content) {
        this.content = content;
    }

    public Post(String author, String content, Date publishedDate) {
        this.author = author;
        this.content = content;
        this.publishedDate = publishedDate;
    }
}
