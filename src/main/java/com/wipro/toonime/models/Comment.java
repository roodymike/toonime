package com.wipro.toonime.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    private String id;
    private String text;
    private String author;

    public int likeCount() {
        return likeCount.get();
    }

    public int dislikeCount() {
        return dislikeCount.get();
    }

    private AtomicInteger likeCount = new AtomicInteger(0);
    private AtomicInteger dislikeCount = new AtomicInteger(0);


}
