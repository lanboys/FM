package com.bing.lan.inke.yingke.bean;



public class MMInfo {

    /**
     * slot : 3
     * city : 哈尔滨市
     * name :
     * creator : {"emotion":"单身","inke_verify":0,"verified":0,"description":"身旁的人陆续离开还有几人陪伴?\n\n找找状态等我宝贝们💪","level":63,"gender":1,"veri_info":"高富帅","profession":"网红梦😄","sex":1,"verified_reason":"","nick":"权势，","rank_veri":9,"location":"哈尔滨市","birth":"1990-09-05","hometown":"黑龙江省&哈尔滨市","id":4262984,"portrait":"http://img2.inke.cn/MTQ4MzY5MTMyMjE2MiMxNzYjanBn.jpg","gmutex":1,"third_platform":"1"}
     * online_users : 1
     * image :
     * stream_addr : http://pull99.a8.com/live/1479541998100370.flv
     * status : 0
     * version : 0
     * room_id : 770876936
     * pub_stat : 1
     * id : 1479541998100370
     * share_addr : http://mlive4.inke.cn/share/live.html?uid=278794547&liveid=1479541998100370&ctime=1479541998
     */


    /**
     * emotion : 单身
     * inke_verify : 0
     * verified : 0
     * description : 身旁的人陆续离开还有几人陪伴?

     找找状态等我宝贝们💪
     * level : 63
     * gender : 1
     * veri_info : 高富帅
     * profession : 网红梦😄
     * sex : 1
     * verified_reason :
     * nick : 权势，
     * rank_veri : 9
     * location : 哈尔滨市
     * birth : 1990-09-05
     * hometown : 黑龙江省&哈尔滨市
     * id : 4262984
     * portrait : http://img2.inke.cn/MTQ4MzY5MTMyMjE2MiMxNzYjanBn.jpg
     * gmutex : 1
     * third_platform : 1
     */

    private int slot;
    private String city;
    private String name;
    private CreatorBean creator;
    private int online_users;
    private String image;
    private String stream_addr;
    private int status;
    private int version;
    private int room_id;
    private int pub_stat;
    private String id;
    private String share_addr;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreatorBean getCreator() {
        return creator;
    }

    public void setCreator(CreatorBean creator) {
        this.creator = creator;
    }

    public int getOnline_users() {
        return online_users;
    }

    public void setOnline_users(int online_users) {
        this.online_users = online_users;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStream_addr() {
        return stream_addr;
    }

    public void setStream_addr(String stream_addr) {
        this.stream_addr = stream_addr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getPub_stat() {
        return pub_stat;
    }

    public void setPub_stat(int pub_stat) {
        this.pub_stat = pub_stat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShare_addr() {
        return share_addr;
    }

    public void setShare_addr(String share_addr) {
        this.share_addr = share_addr;
    }


    @Override
    public String toString() {
        return "MMInfo{" +
                "slot=" + slot +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", online_users=" + online_users +
                ", image='" + image + '\'' +
                ", stream_addr='" + stream_addr + '\'' +
                ", status=" + status +
                ", version=" + version +
                ", room_id=" + room_id +
                ", pub_stat=" + pub_stat +
                ", id='" + id + '\'' +
                ", share_addr='" + share_addr + '\'' +
                '}';
    }
}
