package com.letterball.entity;

import java.util.Date;

public class Video {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.id
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.course_id
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private String courseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.chapter_id
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private String chapterId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.title
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.video_source_id
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private String videoSourceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.video_original_name
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private String videoOriginalName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.sort
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.play_count
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private Long playCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.is_free
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private Byte isFree;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.duration
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private Float duration;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.status
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.size
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private Long size;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.version
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private Long version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.gmt_create
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column edu_video.gmt_modified
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.id
     *
     * @return the value of edu_video.id
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.id
     *
     * @param id the value for edu_video.id
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.course_id
     *
     * @return the value of edu_video.course_id
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.course_id
     *
     * @param courseId the value for edu_video.course_id
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.chapter_id
     *
     * @return the value of edu_video.chapter_id
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public String getChapterId() {
        return chapterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.chapter_id
     *
     * @param chapterId the value for edu_video.chapter_id
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.title
     *
     * @return the value of edu_video.title
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.title
     *
     * @param title the value for edu_video.title
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.video_source_id
     *
     * @return the value of edu_video.video_source_id
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public String getVideoSourceId() {
        return videoSourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.video_source_id
     *
     * @param videoSourceId the value for edu_video.video_source_id
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setVideoSourceId(String videoSourceId) {
        this.videoSourceId = videoSourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.video_original_name
     *
     * @return the value of edu_video.video_original_name
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public String getVideoOriginalName() {
        return videoOriginalName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.video_original_name
     *
     * @param videoOriginalName the value for edu_video.video_original_name
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setVideoOriginalName(String videoOriginalName) {
        this.videoOriginalName = videoOriginalName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.sort
     *
     * @return the value of edu_video.sort
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.sort
     *
     * @param sort the value for edu_video.sort
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.play_count
     *
     * @return the value of edu_video.play_count
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public Long getPlayCount() {
        return playCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.play_count
     *
     * @param playCount the value for edu_video.play_count
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.is_free
     *
     * @return the value of edu_video.is_free
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public Byte getIsFree() {
        return isFree;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.is_free
     *
     * @param isFree the value for edu_video.is_free
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setIsFree(Byte isFree) {
        this.isFree = isFree;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.duration
     *
     * @return the value of edu_video.duration
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public Float getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.duration
     *
     * @param duration the value for edu_video.duration
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setDuration(Float duration) {
        this.duration = duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.status
     *
     * @return the value of edu_video.status
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.status
     *
     * @param status the value for edu_video.status
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.size
     *
     * @return the value of edu_video.size
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public Long getSize() {
        return size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.size
     *
     * @param size the value for edu_video.size
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.version
     *
     * @return the value of edu_video.version
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public Long getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.version
     *
     * @param version the value for edu_video.version
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.gmt_create
     *
     * @return the value of edu_video.gmt_create
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.gmt_create
     *
     * @param gmtCreate the value for edu_video.gmt_create
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column edu_video.gmt_modified
     *
     * @return the value of edu_video.gmt_modified
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column edu_video.gmt_modified
     *
     * @param gmtModified the value for edu_video.gmt_modified
     *
     * @mbggenerated Tue Oct 20 15:22:34 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}