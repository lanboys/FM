package com.bing.lan.fm.ui.search.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/26  0:39
 */
public class SearchHotWordResult {

    //http://search.ximalaya.com/hotWord?device=iPhone&size=45&version=5.4.81

    /**
     * hotWordList : [{"searchWord":"三生三世十里桃花","display_type":1,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"蒙曼","display_type":1,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"朗读者","display_type":1,"is_through":true,"through_type":1,"tgt_id":6729285,"url":""},{"searchWord":"郭德纲相声","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"齐俊杰看财经","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"首席医官","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"岳云鹏","display_type":0,"is_through":true,"through_type":3,"tgt_id":1412883,"url":""},{"searchWord":"段子来了","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"非常溜+7","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"趣读消费心理学","display_type":0,"is_through":true,"through_type":1,"tgt_id":6817090,"url":""},{"searchWord":"十点读书","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"夏说英文晨读","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"摸金天师","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"最美国乐","display_type":0,"is_through":true,"through_type":1,"tgt_id":6496919,"url":""},{"searchWord":"波波有理","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"超品相师","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"逻辑思维","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"夜听","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"儿歌","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"神奇校车","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"跳槽","display_type":0,"is_through":true,"through_type":5,"tgt_id":0,"url":"http://www.ximalaya.com/1958673/album/6543999"},{"searchWord":"诗词大会方笑一","display_type":0,"is_through":true,"through_type":1,"tgt_id":6749828,"url":""},{"searchWord":"一个人听","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"郦波品最美情诗","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"非常不着调","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"吴晓波频道","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"大力史","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"粉红猪小妹","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"百车全说","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"冷历史","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"静说日本","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"糗事播报","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"叶文有话要说","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"吐槽大会","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"罗辑思维全集","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"365读书","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"婷婷唱古文","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"胎教故事","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"晚安妈妈睡前故事","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"岳云鹏相声精选","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"催眠音乐","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"武林外传","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"话说宋朝","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"采采","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""},{"searchWord":"汪汪队立大功","display_type":0,"is_through":false,"through_type":0,"tgt_id":0,"url":""}]
     * ret : 0
     */

    private int ret;
    private List<HotWordListBean> hotWordList;

    public static SearchHotWordResult objectFromData(String str) {

        return new Gson().fromJson(str, SearchHotWordResult.class);
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<HotWordListBean> getHotWordList() {
        return hotWordList;
    }

    public void setHotWordList(List<HotWordListBean> hotWordList) {
        this.hotWordList = hotWordList;
    }

    public static class HotWordListBean {

        /**
         * searchWord : 三生三世十里桃花
         * display_type : 1
         * is_through : false
         * through_type : 0
         * tgt_id : 0
         * url :
         */

        private String searchWord;
        private int display_type;
        private boolean is_through;
        private int through_type;
        private int tgt_id;
        private String url;

        public static HotWordListBean objectFromData(String str) {

            return new Gson().fromJson(str, HotWordListBean.class);
        }

        public String getSearchWord() {
            return searchWord;
        }

        public void setSearchWord(String searchWord) {
            this.searchWord = searchWord;
        }

        public int getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(int display_type) {
            this.display_type = display_type;
        }

        public boolean isIs_through() {
            return is_through;
        }

        public void setIs_through(boolean is_through) {
            this.is_through = is_through;
        }

        public int getThrough_type() {
            return through_type;
        }

        public void setThrough_type(int through_type) {
            this.through_type = through_type;
        }

        public int getTgt_id() {
            return tgt_id;
        }

        public void setTgt_id(int tgt_id) {
            this.tgt_id = tgt_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
