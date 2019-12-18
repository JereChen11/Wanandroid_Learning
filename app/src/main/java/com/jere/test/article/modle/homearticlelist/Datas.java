package com.jere.test.article.modle.homearticlelist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datas {
    @SerializedName("apkLink")
    @Expose
    private String apkLink;
    @SerializedName("audit")
    @Expose
    private Integer audit;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("chapterId")
    @Expose
    private Integer chapterId;
    @SerializedName("chapterName")
    @Expose
    private String chapterName;
    @SerializedName("collect")
    @Expose
    private Boolean collect;
    @SerializedName("courseId")
    @Expose
    private Integer courseId;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("envelopePic")
    @Expose
    private String envelopePic;
    @SerializedName("fresh")
    @Expose
    private Boolean fresh;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("niceDate")
    @Expose
    private String niceDate;
    @SerializedName("niceShareDate")
    @Expose
    private String niceShareDate;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("projectLink")
    @Expose
    private String projectLink;
    @SerializedName("publishTime")
    @Expose
    private Integer publishTime;
    @SerializedName("selfVisible")
    @Expose
    private Integer selfVisible;
    @SerializedName("shareDate")
    @Expose
    private Integer shareDate;
    @SerializedName("shareUser")
    @Expose
    private String shareUser;
    @SerializedName("superChapterId")
    @Expose
    private Integer superChapterId;
    @SerializedName("superChapterName")
    @Expose
    private String superChapterName;
    @SerializedName("tags")
    @Expose
    private List<Tag> tags = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("visible")
    @Expose
    private Integer visible;
    @SerializedName("zan")
    @Expose
    private Integer zan;

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public Integer getAudit() {
        return audit;
    }

    public void setAudit(Integer audit) {
        this.audit = audit;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Boolean getCollect() {
        return collect;
    }

    public void setCollect(Boolean collect) {
        this.collect = collect;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public Boolean getFresh() {
        return fresh;
    }

    public void setFresh(Boolean fresh) {
        this.fresh = fresh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getNiceShareDate() {
        return niceShareDate;
    }

    public void setNiceShareDate(String niceShareDate) {
        this.niceShareDate = niceShareDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public Integer getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Integer publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getSelfVisible() {
        return selfVisible;
    }

    public void setSelfVisible(Integer selfVisible) {
        this.selfVisible = selfVisible;
    }

    public Integer getShareDate() {
        return shareDate;
    }

    public void setShareDate(Integer shareDate) {
        this.shareDate = shareDate;
    }

    public String getShareUser() {
        return shareUser;
    }

    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }

    public Integer getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(Integer superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getZan() {
        return zan;
    }

    public void setZan(Integer zan) {
        this.zan = zan;
    }
}
