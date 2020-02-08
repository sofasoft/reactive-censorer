package com.tt.reactive.source.domain;

import java.util.Objects;
import java.util.UUID;

public class Comment {

    /**
     * Timestamp of unix epoch millis
     */
    private long timestamp;

    /**
     * Java UUID field
     */
    private String id;

    /**
     * Content of commentary
     */
    private String message;

    public Comment(long timestamp, String id, String message) {
        this(message);

        this.timestamp = timestamp;
        this.id = id;
    }

    public Comment(String message) {
        this();

        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public Comment() {
        this.id = UUID.randomUUID().toString();
        message = "";
        this.timestamp = -1;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return getTimestamp() == comment.getTimestamp() &&
                getId().equals(comment.getId()) &&
                getMessage().equals(comment.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimestamp(), getId(), getMessage());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "timestamp=" + timestamp +
                ", id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
