package com.jere.test.home.model.beanfiles;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jere
 */
public class HomeArticleListBean {
    /**
     * data : {"curPage":2,"datas":[{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12187,"link":"https://juejin.im/post/5e5f090de51d4526e4190980","niceDate":"2020-03-04 11:10","niceShareDate":"2020-03-04 11:10","origin":"","prefix":"","projectLink":"","publishTime":1583291437000,"selfVisible":0,"shareDate":1583291437000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android性能优化之绘制优化","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":487,"chapterName":"ViewModel","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12167,"link":"https://juejin.im/post/5e5bd0d2e51d4526e807f55f","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 08:44","origin":"","prefix":"","projectLink":"","publishTime":1583249268000,"selfVisible":0,"shareDate":1583196259000,"shareUser":"AprilEyon","superChapterId":423,"superChapterName":"Jetpack","tags":[],"title":"ViewModel 这些知识点你都知道吗？","type":0,"userId":3559,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":76,"chapterName":"项目架构","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12177,"link":"https://juejin.im/post/5e520db1e51d45270c277ca8","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 19:42","origin":"","prefix":"","projectLink":"","publishTime":1583249253000,"selfVisible":0,"shareDate":1583235761000,"shareUser":"许朋友爱玩","superChapterId":196,"superChapterName":"热门专题","tags":[],"title":"带你封装自己的MVP+Retrofit+RxJava2框架（二）","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":76,"chapterName":"项目架构","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12178,"link":"https://juejin.im/post/5e520d60f265da57127e43af","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 19:43","origin":"","prefix":"","projectLink":"","publishTime":1583249244000,"selfVisible":0,"shareDate":1583235821000,"shareUser":"许朋友爱玩","superChapterId":196,"superChapterName":"热门专题","tags":[],"title":"带你封装自己的MVP+Retrofit+RxJava2框架（一）","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12181,"link":"https://juejin.im/post/5e5e3cb7f265da571563183e","niceDate":"2020-03-03 23:25","niceShareDate":"2020-03-03 21:40","origin":"","prefix":"","projectLink":"","publishTime":1583249154000,"selfVisible":0,"shareDate":1583242837000,"shareUser":"darryrzhong","superChapterId":196,"superChapterName":"热门专题","tags":[],"title":"Android 内存优化篇 - 使用profile 和 MAT 工具进行内存泄漏检测","type":0,"userId":47092,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>早上看到事件分发的一个讨论：<\/p>\r\n<p><img src=\"https://wanandroid.com/blogimgs/9bfb6411-6262-4d59-884f-3e868e5493cd.jpg\" alt><\/p>\r\n<p>那么事件到底是先到DecorView还是先到Window(Activity，Dialog)的。<\/p>\r\n<ol>\r\n<li>touch相关事件在DecorView，PhoneWindow，Activity/Dialog之间传递的顺序是什么样子的？<\/li>\r\n<li>为什么要按照1这么设计？<\/li>\r\n<\/ol>","descMd":"","envelopePic":"","fresh":false,"id":12119,"link":"https://wanandroid.com/wenda/show/12119","niceDate":"2020-03-03 23:25","niceShareDate":"2020-02-27 19:07","origin":"","prefix":"","projectLink":"","publishTime":1583249132000,"selfVisible":0,"shareDate":1582801649000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 | 事件到底是先到DecorView还是先到Window的？","type":0,"userId":2,"visible":1,"zan":15},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12180,"link":"https://juejin.im/post/5e5bbcdcf265da5738464ad3","niceDate":"2020-03-03 21:38","niceShareDate":"2020-03-03 21:38","origin":"","prefix":"","projectLink":"","publishTime":1583242729000,"selfVisible":0,"shareDate":1583242729000,"shareUser":"darryrzhong","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android 组件化开源app -开眼短视频(OpenEyes)","type":0,"userId":47092,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":90,"chapterName":"数据库","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12165,"link":"https://juejin.im/post/5e5bdce6e51d452705318c59","niceDate":"2020-03-03 00:17","niceShareDate":"2020-03-03 00:16","origin":"","prefix":"","projectLink":"","publishTime":1583165838000,"selfVisible":0,"shareDate":1583165776000,"shareUser":"鸿洋","superChapterId":90,"superChapterName":"数据存储","tags":[],"title":"[译] 如何用 Room 处理一对一，一对多，多对多关系？","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>这个题目起源于一位同事问我：\u201c怎么优雅的监听双击\u201d这个行为？<\/p>\r\n<p>想到好像一直没看过GestureDetector的源码实现？<\/p>\r\n<p>那么问题就是：<\/p>\r\n<p>GestureDetector关于支持的手势是如何检测的？分析源码哈<\/p>","descMd":"","envelopePic":"","fresh":false,"id":12018,"link":"https://www.wanandroid.com/wenda/show/12018","niceDate":"2020-03-02 23:56","niceShareDate":"2020-02-20 23:50","origin":"","prefix":"","projectLink":"","publishTime":1583164598000,"selfVisible":0,"shareDate":1582213826000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 Android有个GestureDetector很好用？那么你知道它内部是如何实现的吗？","type":0,"userId":2,"visible":1,"zan":9},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":444,"chapterName":"androidx","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12164,"link":"https://juejin.im/post/5e5cd8686fb9a07cbc269d10","niceDate":"2020-03-02 23:55","niceShareDate":"2020-03-02 23:55","origin":"","prefix":"","projectLink":"","publishTime":1583164559000,"selfVisible":0,"shareDate":1583164523000,"shareUser":"鸿洋","superChapterId":55,"superChapterName":"5.+高新技术","tags":[],"title":"【背上Jetpack之Fragment】你真的会用Fragment吗？Fragment常见问题以及androidx下Fragment的使用新姿势","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":510,"chapterName":"大厂分享","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12163,"link":"https://juejin.im/post/5e5b9466518825494b3cd5aa","niceDate":"2020-03-02 23:54","niceShareDate":"2020-03-02 23:51","origin":"","prefix":"","projectLink":"","publishTime":1583164496000,"selfVisible":0,"shareDate":1583164305000,"shareUser":"鸿洋","superChapterId":510,"superChapterName":"大厂对外分享","tags":[],"title":"抖音BoostMultiDex优化实践：Android低版本上APP首次启动时间减少80%（一）","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12154,"link":"https://juejin.im/post/5e5b50eb6fb9a07cae136773","niceDate":"2020-03-02 09:32","niceShareDate":"2020-03-02 09:32","origin":"","prefix":"","projectLink":"","publishTime":1583112725000,"selfVisible":0,"shareDate":1583112725000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"【建议收藏】2020年中高级Android大厂面试秘籍，为你保驾护航金三银四，直通大厂","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12137,"link":"https://blog.csdn.net/willway_wang/article/details/104525652","niceDate":"2020-02-29 11:31","niceShareDate":"2020-02-29 11:19","origin":"","prefix":"","projectLink":"","publishTime":1582947117000,"selfVisible":0,"shareDate":1582946392000,"shareUser":"willwaywang6","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"代理模式学习笔记","type":0,"userId":833,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":375,"chapterName":"Flutter","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12132,"link":"https://juejin.im/post/5e58ceadf265da575918dd14","niceDate":"2020-02-29 00:06","niceShareDate":"2020-02-28 23:34","origin":"","prefix":"","projectLink":"","publishTime":1582905960000,"selfVisible":0,"shareDate":1582904067000,"shareUser":"鸿洋","superChapterId":375,"superChapterName":"跨平台","tags":[],"title":"Flutter 开发踩坑记录（干货总结）","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":412,"chapterName":"io","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12133,"link":"https://www.jianshu.com/p/2595445bc7e7","niceDate":"2020-02-29 00:05","niceShareDate":"2020-02-28 23:43","origin":"","prefix":"","projectLink":"","publishTime":1582905949000,"selfVisible":0,"shareDate":1582904619000,"shareUser":"鸿洋","superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"Stack Overflow上200万阅读量的一个提问：Java中怎么快速把InputStream转化为String","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12131,"link":"https://juejin.im/post/5e520c925188254903693f61","niceDate":"2020-02-28 19:35","niceShareDate":"2020-02-28 19:35","origin":"","prefix":"","projectLink":"","publishTime":1582889715000,"selfVisible":0,"shareDate":1582889715000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"进阶之路 | 奇妙的Drawable之旅","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12124,"link":"https://juejin.im/post/5e5898346fb9a07cd248d179","niceDate":"2020-02-28 13:41","niceShareDate":"2020-02-28 13:41","origin":"","prefix":"","projectLink":"","publishTime":1582868495000,"selfVisible":0,"shareDate":1582868495000,"shareUser":"DylanCai","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"优雅地管理 Loading 界面和标题栏","type":0,"userId":27680,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12122,"link":"https://juejin.im/post/5e58779f518825493f6ce7eb","niceDate":"2020-02-28 10:26","niceShareDate":"2020-02-28 10:26","origin":"","prefix":"","projectLink":"","publishTime":1582856797000,"selfVisible":0,"shareDate":1582856797000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android主流三方库源码分析（八、深入理解Dagger2源码）","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":433,"chapterName":"Window","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12118,"link":"https://juejin.im/post/5e574459518825494d4fd440","niceDate":"2020-02-27 18:59","niceShareDate":"2020-02-27 18:58","origin":"","prefix":"","projectLink":"","publishTime":1582801143000,"selfVisible":0,"shareDate":1582801116000,"shareUser":"鸿洋","superChapterId":173,"superChapterName":"framework","tags":[],"title":"进阶之路 | 奇妙的Window之旅","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12112,"link":"https://www.jianshu.com/p/df4f4467e5f1","niceDate":"2020-02-27 09:59","niceShareDate":"2020-02-27 09:59","origin":"","prefix":"","projectLink":"","publishTime":1582768766000,"selfVisible":0,"shareDate":1582768766000,"shareUser":"彭旭锐","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android | 这是一份详细的 EventBus 使用教程","type":0,"userId":30587,"visible":1,"zan":0}],"offset":20,"over":false,"pageCount":404,"size":20,"total":8065}
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
         * curPage : 2
         * datas : [{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12187,"link":"https://juejin.im/post/5e5f090de51d4526e4190980","niceDate":"2020-03-04 11:10","niceShareDate":"2020-03-04 11:10","origin":"","prefix":"","projectLink":"","publishTime":1583291437000,"selfVisible":0,"shareDate":1583291437000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android性能优化之绘制优化","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":487,"chapterName":"ViewModel","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12167,"link":"https://juejin.im/post/5e5bd0d2e51d4526e807f55f","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 08:44","origin":"","prefix":"","projectLink":"","publishTime":1583249268000,"selfVisible":0,"shareDate":1583196259000,"shareUser":"AprilEyon","superChapterId":423,"superChapterName":"Jetpack","tags":[],"title":"ViewModel 这些知识点你都知道吗？","type":0,"userId":3559,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":76,"chapterName":"项目架构","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12177,"link":"https://juejin.im/post/5e520db1e51d45270c277ca8","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 19:42","origin":"","prefix":"","projectLink":"","publishTime":1583249253000,"selfVisible":0,"shareDate":1583235761000,"shareUser":"许朋友爱玩","superChapterId":196,"superChapterName":"热门专题","tags":[],"title":"带你封装自己的MVP+Retrofit+RxJava2框架（二）","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":76,"chapterName":"项目架构","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12178,"link":"https://juejin.im/post/5e520d60f265da57127e43af","niceDate":"2020-03-03 23:27","niceShareDate":"2020-03-03 19:43","origin":"","prefix":"","projectLink":"","publishTime":1583249244000,"selfVisible":0,"shareDate":1583235821000,"shareUser":"许朋友爱玩","superChapterId":196,"superChapterName":"热门专题","tags":[],"title":"带你封装自己的MVP+Retrofit+RxJava2框架（一）","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12181,"link":"https://juejin.im/post/5e5e3cb7f265da571563183e","niceDate":"2020-03-03 23:25","niceShareDate":"2020-03-03 21:40","origin":"","prefix":"","projectLink":"","publishTime":1583249154000,"selfVisible":0,"shareDate":1583242837000,"shareUser":"darryrzhong","superChapterId":196,"superChapterName":"热门专题","tags":[],"title":"Android 内存优化篇 - 使用profile 和 MAT 工具进行内存泄漏检测","type":0,"userId":47092,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>早上看到事件分发的一个讨论：<\/p>\r\n<p><img src=\"https://wanandroid.com/blogimgs/9bfb6411-6262-4d59-884f-3e868e5493cd.jpg\" alt><\/p>\r\n<p>那么事件到底是先到DecorView还是先到Window(Activity，Dialog)的。<\/p>\r\n<ol>\r\n<li>touch相关事件在DecorView，PhoneWindow，Activity/Dialog之间传递的顺序是什么样子的？<\/li>\r\n<li>为什么要按照1这么设计？<\/li>\r\n<\/ol>","descMd":"","envelopePic":"","fresh":false,"id":12119,"link":"https://wanandroid.com/wenda/show/12119","niceDate":"2020-03-03 23:25","niceShareDate":"2020-02-27 19:07","origin":"","prefix":"","projectLink":"","publishTime":1583249132000,"selfVisible":0,"shareDate":1582801649000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 | 事件到底是先到DecorView还是先到Window的？","type":0,"userId":2,"visible":1,"zan":15},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12180,"link":"https://juejin.im/post/5e5bbcdcf265da5738464ad3","niceDate":"2020-03-03 21:38","niceShareDate":"2020-03-03 21:38","origin":"","prefix":"","projectLink":"","publishTime":1583242729000,"selfVisible":0,"shareDate":1583242729000,"shareUser":"darryrzhong","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android 组件化开源app -开眼短视频(OpenEyes)","type":0,"userId":47092,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":90,"chapterName":"数据库","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12165,"link":"https://juejin.im/post/5e5bdce6e51d452705318c59","niceDate":"2020-03-03 00:17","niceShareDate":"2020-03-03 00:16","origin":"","prefix":"","projectLink":"","publishTime":1583165838000,"selfVisible":0,"shareDate":1583165776000,"shareUser":"鸿洋","superChapterId":90,"superChapterName":"数据存储","tags":[],"title":"[译] 如何用 Room 处理一对一，一对多，多对多关系？","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>这个题目起源于一位同事问我：\u201c怎么优雅的监听双击\u201d这个行为？<\/p>\r\n<p>想到好像一直没看过GestureDetector的源码实现？<\/p>\r\n<p>那么问题就是：<\/p>\r\n<p>GestureDetector关于支持的手势是如何检测的？分析源码哈<\/p>","descMd":"","envelopePic":"","fresh":false,"id":12018,"link":"https://www.wanandroid.com/wenda/show/12018","niceDate":"2020-03-02 23:56","niceShareDate":"2020-02-20 23:50","origin":"","prefix":"","projectLink":"","publishTime":1583164598000,"selfVisible":0,"shareDate":1582213826000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 Android有个GestureDetector很好用？那么你知道它内部是如何实现的吗？","type":0,"userId":2,"visible":1,"zan":9},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":444,"chapterName":"androidx","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12164,"link":"https://juejin.im/post/5e5cd8686fb9a07cbc269d10","niceDate":"2020-03-02 23:55","niceShareDate":"2020-03-02 23:55","origin":"","prefix":"","projectLink":"","publishTime":1583164559000,"selfVisible":0,"shareDate":1583164523000,"shareUser":"鸿洋","superChapterId":55,"superChapterName":"5.+高新技术","tags":[],"title":"【背上Jetpack之Fragment】你真的会用Fragment吗？Fragment常见问题以及androidx下Fragment的使用新姿势","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":510,"chapterName":"大厂分享","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12163,"link":"https://juejin.im/post/5e5b9466518825494b3cd5aa","niceDate":"2020-03-02 23:54","niceShareDate":"2020-03-02 23:51","origin":"","prefix":"","projectLink":"","publishTime":1583164496000,"selfVisible":0,"shareDate":1583164305000,"shareUser":"鸿洋","superChapterId":510,"superChapterName":"大厂对外分享","tags":[],"title":"抖音BoostMultiDex优化实践：Android低版本上APP首次启动时间减少80%（一）","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12154,"link":"https://juejin.im/post/5e5b50eb6fb9a07cae136773","niceDate":"2020-03-02 09:32","niceShareDate":"2020-03-02 09:32","origin":"","prefix":"","projectLink":"","publishTime":1583112725000,"selfVisible":0,"shareDate":1583112725000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"【建议收藏】2020年中高级Android大厂面试秘籍，为你保驾护航金三银四，直通大厂","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12137,"link":"https://blog.csdn.net/willway_wang/article/details/104525652","niceDate":"2020-02-29 11:31","niceShareDate":"2020-02-29 11:19","origin":"","prefix":"","projectLink":"","publishTime":1582947117000,"selfVisible":0,"shareDate":1582946392000,"shareUser":"willwaywang6","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"代理模式学习笔记","type":0,"userId":833,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":375,"chapterName":"Flutter","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12132,"link":"https://juejin.im/post/5e58ceadf265da575918dd14","niceDate":"2020-02-29 00:06","niceShareDate":"2020-02-28 23:34","origin":"","prefix":"","projectLink":"","publishTime":1582905960000,"selfVisible":0,"shareDate":1582904067000,"shareUser":"鸿洋","superChapterId":375,"superChapterName":"跨平台","tags":[],"title":"Flutter 开发踩坑记录（干货总结）","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":412,"chapterName":"io","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12133,"link":"https://www.jianshu.com/p/2595445bc7e7","niceDate":"2020-02-29 00:05","niceShareDate":"2020-02-28 23:43","origin":"","prefix":"","projectLink":"","publishTime":1582905949000,"selfVisible":0,"shareDate":1582904619000,"shareUser":"鸿洋","superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"Stack Overflow上200万阅读量的一个提问：Java中怎么快速把InputStream转化为String","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12131,"link":"https://juejin.im/post/5e520c925188254903693f61","niceDate":"2020-02-28 19:35","niceShareDate":"2020-02-28 19:35","origin":"","prefix":"","projectLink":"","publishTime":1582889715000,"selfVisible":0,"shareDate":1582889715000,"shareUser":"许朋友爱玩","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"进阶之路 | 奇妙的Drawable之旅","type":0,"userId":43523,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12124,"link":"https://juejin.im/post/5e5898346fb9a07cd248d179","niceDate":"2020-02-28 13:41","niceShareDate":"2020-02-28 13:41","origin":"","prefix":"","projectLink":"","publishTime":1582868495000,"selfVisible":0,"shareDate":1582868495000,"shareUser":"DylanCai","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"优雅地管理 Loading 界面和标题栏","type":0,"userId":27680,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12122,"link":"https://juejin.im/post/5e58779f518825493f6ce7eb","niceDate":"2020-02-28 10:26","niceShareDate":"2020-02-28 10:26","origin":"","prefix":"","projectLink":"","publishTime":1582856797000,"selfVisible":0,"shareDate":1582856797000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android主流三方库源码分析（八、深入理解Dagger2源码）","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":433,"chapterName":"Window","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12118,"link":"https://juejin.im/post/5e574459518825494d4fd440","niceDate":"2020-02-27 18:59","niceShareDate":"2020-02-27 18:58","origin":"","prefix":"","projectLink":"","publishTime":1582801143000,"selfVisible":0,"shareDate":1582801116000,"shareUser":"鸿洋","superChapterId":173,"superChapterName":"framework","tags":[],"title":"进阶之路 | 奇妙的Window之旅","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12112,"link":"https://www.jianshu.com/p/df4f4467e5f1","niceDate":"2020-02-27 09:59","niceShareDate":"2020-02-27 09:59","origin":"","prefix":"","projectLink":"","publishTime":1582768766000,"selfVisible":0,"shareDate":1582768766000,"shareUser":"彭旭锐","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android | 这是一份详细的 EventBus 使用教程","type":0,"userId":30587,"visible":1,"zan":0}]
         * offset : 20
         * over : false
         * pageCount : 404
         * size : 20
         * total : 8065
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
             * author :
             * canEdit : false
             * chapterId : 502
             * chapterName : 自助
             * collect : false
             * courseId : 13
             * desc :
             * descMd :
             * envelopePic :
             * fresh : false
             * id : 12187
             * link : https://juejin.im/post/5e5f090de51d4526e4190980
             * niceDate : 2020-03-04 11:10
             * niceShareDate : 2020-03-04 11:10
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1583291437000
             * selfVisible : 0
             * shareDate : 1583291437000
             * shareUser : JsonChao
             * superChapterId : 494
             * superChapterName : 广场Tab
             * tags : []
             * title : Android性能优化之绘制优化
             * type : 0
             * userId : 611
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
            private List<?> tags;

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

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }
        }
    }
}
