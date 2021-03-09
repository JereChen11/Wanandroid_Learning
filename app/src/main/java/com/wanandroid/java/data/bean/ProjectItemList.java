package com.wanandroid.java.data.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jere
 */
public class ProjectItemList {
    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","audit":1,"author":"gs666","canEdit":false,"chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"一个模仿企鹅 FM 界面的Android 应用&mdash;喜马拉雅Kotlin。完全使用 Kotlin 开发。有声资源和播放器由喜马拉雅 SDK 提供。 主要功能：\r\n\r\n1,在线播放专辑点播\r\n2,在线播放国家/省/市广播电台\r\n3,最近收听\r\n4,搜索节目/专辑/广播（包括热搜词）","descMd":"","envelopePic":"https://wanandroid.com/blogimgs/2baa4b4b-acfe-473c-b534-9d672423e564.png","fresh":false,"id":10135,"link":"https://www.wanandroid.com/blog/show/2703","niceDate":"2019-11-07 10:43","niceShareDate":"2019-11-07 10:43","origin":"","prefix":"","projectLink":"https://github.com/gs666/XimalayaKotlin","publishTime":1573094591000,"selfVisible":0,"shareDate":1573094591000,"shareUser":"","superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"一个模仿企鹅 FM 界面的 Android 应用&mdash;喜马拉雅Kotlin。完全使用 Kotlin 开发。","type":0,"userId":-1,"visible":1,"zan":0}],"offset":0,"over":false,"pageCount":12,"size":15,"total":166}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","audit":1,"author":"gs666","canEdit":false,"chapterId":294,"chapterName":"完整项目","collect":false,"courseId":13,"desc":"一个模仿企鹅 FM 界面的Android 应用&mdash;喜马拉雅Kotlin。完全使用 Kotlin 开发。有声资源和播放器由喜马拉雅 SDK 提供。 主要功能：\r\n\r\n1,在线播放专辑点播\r\n2,在线播放国家/省/市广播电台\r\n3,最近收听\r\n4,搜索节目/专辑/广播（包括热搜词）","descMd":"","envelopePic":"https://wanandroid.com/blogimgs/2baa4b4b-acfe-473c-b534-9d672423e564.png","fresh":false,"id":10135,"link":"https://www.wanandroid.com/blog/show/2703","niceDate":"2019-11-07 10:43","niceShareDate":"2019-11-07 10:43","origin":"","prefix":"","projectLink":"https://github.com/gs666/XimalayaKotlin","publishTime":1573094591000,"selfVisible":0,"shareDate":1573094591000,"shareUser":"","superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=294"}],"title":"一个模仿企鹅 FM 界面的 Android 应用&mdash;喜马拉雅Kotlin。完全使用 Kotlin 开发。","type":0,"userId":-1,"visible":1,"zan":0}]
         * offset : 0
         * over : false
         * pageCount : 12
         * size : 15
         * total : 166
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private ArrayList<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ArrayList<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(ArrayList<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * audit : 1
             * author : gs666
             * canEdit : false
             * chapterId : 294
             * chapterName : 完整项目
             * collect : false
             * courseId : 13
             * desc : 一个模仿企鹅 FM 界面的Android 应用&mdash;喜马拉雅Kotlin。完全使用 Kotlin 开发。有声资源和播放器由喜马拉雅 SDK 提供。 主要功能：

             1,在线播放专辑点播
             2,在线播放国家/省/市广播电台
             3,最近收听
             4,搜索节目/专辑/广播（包括热搜词）
             * descMd :
             * envelopePic : https://wanandroid.com/blogimgs/2baa4b4b-acfe-473c-b534-9d672423e564.png
             * fresh : false
             * id : 10135
             * link : https://www.wanandroid.com/blog/show/2703
             * niceDate : 2019-11-07 10:43
             * niceShareDate : 2019-11-07 10:43
             * origin :
             * prefix :
             * projectLink : https://github.com/gs666/XimalayaKotlin
             * publishTime : 1573094591000
             * selfVisible : 0
             * shareDate : 1573094591000
             * shareUser :
             * superChapterId : 294
             * superChapterName : 开源项目主Tab
             * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
             * title : 一个模仿企鹅 FM 界面的 Android 应用&mdash;喜马拉雅Kotlin。完全使用 Kotlin 开发。
             * type : 0
             * userId : -1
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private int audit;
            private String author;
            private boolean canEdit;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String descMd;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String niceShareDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
            private int selfVisible;
            private long shareDate;
            private String shareUser;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<TagsBean> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public boolean isCanEdit() {
                return canEdit;
            }

            public void setCanEdit(boolean canEdit) {
                this.canEdit = canEdit;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getDescMd() {
                return descMd;
            }

            public void setDescMd(String descMd) {
                this.descMd = descMd;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSelfVisible() {
                return selfVisible;
            }

            public void setSelfVisible(int selfVisible) {
                this.selfVisible = selfVisible;
            }

            public long getShareDate() {
                return shareDate;
            }

            public void setShareDate(long shareDate) {
                this.shareDate = shareDate;
            }

            public String getShareUser() {
                return shareUser;
            }

            public void setShareUser(String shareUser) {
                this.shareUser = shareUser;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public static class TagsBean {
                /**
                 * name : 项目
                 * url : /project/list/1?cid=294
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
