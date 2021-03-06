package com.example.geeknews.bean;

import java.util.List;

public class SuperHot {
    private List<RecentBean> recent;

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public static class RecentBean {
        /**
         * news_id : 9710222
         * thumbnail : https://pic4.zhimg.com/v2-8566d3cca46a81ec8e7e1ef1e8ef4ea7.jpg
         * title : 瞎扯 · 如何正确地吐槽
         * url : http://news-at.zhihu.com/api/2/news/9710222
         */

        private int news_id;
        private String thumbnail;
        private String title;
        private String url;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
