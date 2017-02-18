package com.bing.lan.fm.ui.hot.bean;

/**
 * @author 蓝兵
 * @time 2017/2/17  15:14
 */
public class ListItemSpecialBean {
    // 精品听单

    /**
     * columnType : 1
     * specialId : 913
     * title : 醉爱民谣：十座城，十首歌
     * subtitle : 《歌手》火了赵雷，也红了《成都》
     * “和我在成都的街头走一走
     * 直到所有的灯都熄灭了也不停留
     * 成都 带不走的 只有你”
     * 每个人的心里都有一座带不走也留不下的城
     * 这十首民谣里唱的，有你心里的那座吗？
     * <p>
     * ——致那个带不走也留不下的城市，和你
     * footnote : 10首声音
     * coverPath : http://fdfs.xmcdn.com/group22/M03/75/88/wKgJM1idg17iev0HAASoSIVEDBg778_mobile_small.jpg
     * contentType : 2
     */

    private int columnType;
    private int specialId;
    private String title;
    private String subtitle;
    private String footnote;
    private String coverPath;
    private String contentType;


    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public int getSpecialId() {
        return specialId;
    }

    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getFootnote() {
        return footnote;
    }

    public void setFootnote(String footnote) {
        this.footnote = footnote;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "ListBeanXX{" +
                "columnType=" + columnType +
                ", specialId=" + specialId +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", footnote='" + footnote + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
