package ru.hogwarts.school.model;

import javax.persistence.*;

@Entity
public class Avatar {
    @Id
    @GeneratedValue
    Long id;
    String filePath;
    long fileSize;
    String mediaType;
    @Lob
    byte[] data;
    @OneToOne
    Student student;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public byte[] getData() {
        return data;
    }
}
