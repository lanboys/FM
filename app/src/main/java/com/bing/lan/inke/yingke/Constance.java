package com.bing.lan.inke.yingke;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by kay on 16/11/13.
 */
public class Constance {

    /**websocket服务器地址*/
    public static final String WSURL = "ws://f.xmg520.cn:82/WebSocket/websocketServer";

    public static final String SERVER_IP = "http://218.11.0.112/";

    public static final String INDEX_BANNER = "api/live/ticker?mtxid=FCAA14EFE150&devi=864394102521707&uid=278794547&sid=20apIKRuUve4iOLo7oSi2i0Coi2ZKfLDhW6OzONeysOV8i2cQFaIWk&conn=WIFI&imei=864394102521707&mtid=0acd9551fb5aa92442069df37393691c&aid=FCAA14EFE1500000&cv=IK3.4.20_Android&smid=DuNRd%2FUxsbeK1L9tQw0TNRvGvuiTGcC08%2FFDr9KEdjdfkXbds8BheQpVHHOYmMkTErodalrJMOW1OTVu4Hamaemw&osversion=android_19&proto=4&logid=&vv=1.0.3-201610291749.android&icc=89860081012521701423&ua=LENOVOLenovoS898t%2B&lc=3000000000034000&imsi=460072521701423&cc=GF10000&r_c=1902445306&s_sg=5dab77bfef10a1b065b6f55d25e2b6df&s_sc=100&s_st=1478679310";

    public static final String INDEX_LIVE_DATE = "api/live/gettop?lc=3000000000030074&cv=IK3.4.00_Android&cc=TG36109&ua=HTCm7spr&uid=260095067&sid=20Lei1rsdSUzDPBi1r34vRhZYR4Rf0E54Ii2HnLOtj4Nj2Y1i0ti136&devi=990004283876450&imsi=&imei=990004283876450&icc=&conn=WIFI&vv=1.0.3-201610121413.android&aid=286a0f38f1bb27b1&osversion=android_23&proto=4&smid=DuEyeb0b%2Fi60x6MRg46gR0SOfjbNSrk%2F%2FyoE6lSUx4xRN7XvMfzXo30xPPuw47w8hkpKvTVkqAVkPz2Lvf6P0sGw&mtid=4f7e5427b4778d3eeb583ae00e5368bc&mtxid=b0d59d2abf96&count=5&s_sg=4151ed284f557666cd9e401338aae750&s_sc=100&s_st=1477318354";
    public static final String INDEX_LIVEALL_DATE = "api/live/simpleall?lc=3000000000030074&cv=IK3.4.00_Android&cc=TG36109&ua=HTCm7spr&uid=260095067&sid=20Lei1rsdSUzDPBi1r34vRhZYR4Rf0E54Ii2HnLOtj4Nj2Y1i0ti136&devi=990004283876450&imsi=&imei=990004283876450&icc=&conn=WIFI&vv=1.0.3-201610121413.android&aid=286a0f38f1bb27b1&osversion=android_23&proto=4&smid=DuEyeb0b%2Fi60x6MRg46gR0SOfjbNSrk%2F%2FyoE6lSUx4xRN7XvMfzXo30xPPuw47w8hkpKvTVkqAVkPz2Lvf6P0sGw&mtid=4f7e5427b4778d3eeb583ae00e5368bc&mtxid=b0d59d2abf96&multiaddr=1&s_sg=170ae2125c405c548f16f2eba25eb2b3&s_sc=100&s_st=1479115432";
    public static final String NEAR_ALL_DATE = "api/live/near_recommend?lc=3000000000030074&cv=IK3.4.00_Android&cc=TG36109&ua=HTCm7spr&uid=260095067&sid=20Lei1rsdSUzDPBi1r34vRhZYR4Rf0E54Ii2HnLOtj4Nj2Y1i0ti136&devi=990004283876450&imsi=&imei=990004283876450&icc=&conn=WIFI&vv=1.0.3-201610121413.android&aid=286a0f38f1bb27b1&osversion=android_23&mtid=4f7e5427b4778d3eeb583ae00e5368bc&mtxid=b0d59d2abf96&proto=4&smid=DuEyeb0b%2Fi60x6MRg46gR0SOfjbNSrk%2F%2FyoE6lSUx4xRN7XvMfzXo30xPPuw47w8hkpKvTVkqAVkPz2Lvf6P0sGw&longitude=113.3752&latitude=23.132093&interest=0&s_sg=3e701fc6e3fc96e04d94c6a3109572dd&s_sc=100&s_st=1479115432";
    public static final String SEARCH_ALL = "api/recommend/aggregate?mtxid=FCAA14EFE150&devi=864394102521707&uid=278794547&sid=20apIKRuUve4iOLo7oSi2i0Coi2ZKfLDhW6OzONeysOV8i2cQFaIWk&conn=WIFI&imei=864394102521707&mtid=0acd9551fb5aa92442069df37393691c&aid=FCAA14EFE1500000&cv=IK3.4.20_Android&smid=DuNRd%2FUxsbeK1L9tQw0TNRvGvuiTGcC08%2FFDr9KEdjdfkXbds8BheQpVHHOYmMkTErodalrJMOW1OTVu4Hamaemw&osversion=android_19&proto=4&logid=&vv=1.0.3-201610291749.android&icc=89860081012521701423&ua=LENOVOLenovoS898t%2B&lc=3000000000034000&imsi=460072521701423&cc=GF10000&interest=1&longitude=116.397125&latitude=39.90678&r_c=1770525778&s_sg=b36eb8530752aa83400a8a0b77d3351a&s_sc=100&s_st=1478678609";
    public static final String SEARCH_KEYWORD = "http://218.11.0.112/api/user/search?mtxid=FCAA14EFE150&devi=864394102521707&uid=278794547&sid=20apIKRuUve4iOLo7oSi2i0Coi2ZKfLDhW6OzONeysOV8i2cQFaIWk&conn=WIFI&imei=864394102521707&mtid=0acd9551fb5aa92442069df37393691c&aid=FCAA14EFE1500000&cv=IK3.4.20_Android&smid=DuNRd%2FUxsbeK1L9tQw0TNRvGvuiTGcC08%2FFDr9KEdjdfkXbds8BheQpVHHOYmMkTErodalrJMOW1OTVu4Hamaemw&osversion=android_19&proto=4&logid=&vv=1.0.3-201610291749.android&icc=89860081012521701423&ua=LENOVOLenovoS898t%2B&lc=3000000000034000&imsi=460072521701423&cc=GF10000&count=10&start=0&keyword=_@keyword@_&r_c=1215378052&s_sg=5372685bd13f113826e5f18004f2daf5&s_sc=100&s_st=1478678705";


    public static final String GIFT_ALL = "api/resource/gift_info?lc=3000000000034000&cv=IK3.4.20_Android&cc=GF10000&ua=LENOVOLenovoS898t%2B&uid=278794547&sid=20apIKRuUve4iOLo7oSi2i0Coi2ZKfLDhW6OzONeysOV8i2cQFaIWk&devi=864394102521707&imsi=460072521701423&imei=864394102521707&icc=89860081012521701423&conn=WIFI&vv=1.0.3-201610291749.android&aid=FCAA14EFE1500000&osversion=android_19&mtid=0acd9551fb5aa92442069df37393691c&mtxid=FCAA14EFE150&proto=4&smid=&logid=&type=2";

    public static final String ROOM_INFOR = "http://218.11.0.112/api/live/info?lc=3000000000034000&cv=IK3.4.20_Android&cc=GF10000&ua=LENOVOLenovoS898t%2B&uid=278794547&sid=20apIKRuUve4iOLo7oSi2i0Coi2ZKfLDhW6OzONeysOV8i2cQFaIWk&devi=864394102521707&imsi=460072521701423&imei=864394102521707&icc=89860081012521701423&conn=WIFI&vv=1.0.3-201610291749.android&aid=FCAA14EFE1500000&osversion=android_19&mtid=0acd9551fb5aa92442069df37393691c&mtxid=FCAA14EFE150&proto=4&smid=DuNRd%2FUxsbeK1L9tQw0TNRvGvuiTGcC08%2FFDr9KEdjdfkXbds8BheQpVHHOYmMkTErodalrJMOW1OTVu4Hamaemw&logid=30&id=$_ID_$&multiaddr=1&r_c=892174631&s_sg=a602f4128a8e034c33f1e1f7b8f73c65&s_sc=100&s_st=1482048916";

    public static final String HOT_SMALL_URL = "http://image.scale.inke.com/imageproxy2/dimgm/scaleImage?url=http%3A%2F%2F_@server@_%2F_@Src@_&w=100&h=100&s=80&c=0&o=0";
    public static final String HOT_BIG_URL = "http://image.scale.inke.com/imageproxy2/dimgm/scaleImage?url=http%3A%2F%2F_@server@_%2F_@Src@_&w=800&h=800&s=80&c=0&o=0";
    public static final String HOT_BANNER_URL = "http://image.scale.inke.com/imageproxy2/dimgm/scaleImage?url=http%3A%2F%2F_@server@_%2F_@Src@_&w=800&h=300&s=80&c=0&o=0";
    public static final String NEAR_ICON_URL = "http://image.scale.inke.com/imageproxy2/dimgm/scaleImage?url=http%3A%2F%2Fimg.meelive.cn%2F_@Src@_&w=150&h=150&s=80&c=0&o=0";
    public static final String SEARCH_ICON_URL = "http://image.scale.inke.com/imageproxy2/dimgm/scaleImage?url=http%3A%2F%2Fimg.meelive.cn%2F_@Src@_&w=100&h=100&s=80&c=0&o=0";
    public static final String SEARCH_REC_ICON_URL = "http://image.scale.inke.com/imageproxy2/dimgm/scaleImage?url=http%3A%2F%2Fimg.meelive.cn%2F_@Src@_&w=30&h=30&s=80&c=0&o=0";


    public static final String GET_ROOM_VIEWERS = "http://218.11.0.112/api/live/users?lc=3000000000034000&cv=IK3.4.20_Android&cc=GF10000&ua=LENOVOLenovoS898t%2B&uid=278794547&sid=20apIKRuUve4iOLo7oSi2i0Coi2ZKfLDhW6OzONeysOV8i2cQFaIWk&devi=864394102521707&imsi=460072521701423&imei=864394102521707&icc=89860081012521701423&conn=WIFI&vv=1.0.3-201610291749.android&aid=FCAA14EFE1500000&osversion=android_19&mtid=0acd9551fb5aa92442069df37393691c&mtxid=FCAA14EFE150&proto=4&smid=DuNRd%2FUxsbeK1L9tQw0TNRvGvuiTGcC08%2FFDr9KEdjdfkXbds8BheQpVHHOYmMkTErodalrJMOW1OTVu4Hamaemw&logid=30&count=20&id=@_ID_@&start=0&r_c=126699580&s_sg=d068727adad34e22bef4f8dda827c8dd&s_sc=100&s_st=1482048916";
    public static final String GET_GOLDS = "http://218.11.0.112/api/statistic/inout?lc=3000000000034000&cv=IK3.4.20_Android&cc=GF10000&ua=LENOVOLenovoS898t%2B&uid=278794547&sid=20apIKRuUve4iOLo7oSi2i0Coi2ZKfLDhW6OzONeysOV8i2cQFaIWk&devi=864394102521707&imsi=460072521701423&imei=864394102521707&icc=89860081012521701423&conn=WIFI&vv=1.0.3-201610291749.android&aid=FCAA14EFE1500000&osversion=android_19&mtid=0acd9551fb5aa92442069df37393691c&mtxid=FCAA14EFE150&proto=4&smid=DuNRd%2FUxsbeK1L9tQw0TNRvGvuiTGcC08%2FFDr9KEdjdfkXbds8BheQpVHHOYmMkTErodalrJMOW1OTVu4Hamaemw&logid=127%2C133%2C166%2C45&id=@_ID_@&r_c=752990390&s_sg=1dc32125d83afca78ccf5b6d8cb43039&s_sc=100&s_st=1484732271";
    public static final String GET_PICTURE="http://img2.inke.cn/";
    public static String getHotSmallUrl(String server, String icon_name) {
        String url = HOT_SMALL_URL.replaceAll("_@Src@_", icon_name);
        return url.replaceAll("_@server@_", server);
    }

    public static String getHotBigUrl(String server, String icon_name) {
        String url = HOT_BIG_URL.replaceAll("_@Src@_", icon_name);
        return url.replaceAll("_@server@_", server);

    }


    public static String getRoomInfor(String liveId){
        return ROOM_INFOR.replace("$_ID_$",liveId);
    }

    //server 图片服务期的地址
    //icon_name 图片的名称
    public static String getBannerUrl(String server, String icon_name) {
        String url = HOT_BANNER_URL.replaceAll("_@Src@_", icon_name);
        return url.replaceAll("_@server@_", server);
    }


    //获取映客图片的名称与服务器名称
    public static String[] getImageNameAndServer(String name) {
        String[] names=null;
        if(name.contains("http://")) {
            name = name.replaceAll("http://", "");
            names= name.split("/");
        }else{
            name=GET_PICTURE+name;
            name = name.replaceAll("http://", "");
            names= name.split("/");
        }
        return names;
    }


    public static String getNearIconUrl(String icon_name) {
        if (icon_name.startsWith("http")) {
            return icon_name;
        } else {
            String image_url = "";
            try {
                image_url = URLEncoder.encode(icon_name, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return NEAR_ICON_URL.replaceAll("_@Src@_", image_url);
        }
    }

    public static String getSearchUrl(String icon_name) {
        if (icon_name.startsWith("http")) {
            return icon_name;
        } else {
            String image_url = "";
            try {
                image_url = URLEncoder.encode(icon_name, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return SEARCH_ICON_URL.replaceAll("_@Src@_", image_url);
        }
    }


    public static String getSearchRecMendUrl(String icon_name) {
        if (icon_name.startsWith("http")) {
            return icon_name;
        } else {
            String image_url = "";
            try {
                image_url = URLEncoder.encode(icon_name, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return SEARCH_REC_ICON_URL.replaceAll("_@Src@_", image_url);
        }
    }


    public static String getSearchKeyword(String parms){
        return SEARCH_KEYWORD.replaceAll("_@keyword@_",parms);
    }

    public static  String getViewersUrl(String id){
        return GET_ROOM_VIEWERS.replaceAll("@_ID_@",id);
    }

    public static  String getGoldsUrl(String id){
        return GET_GOLDS.replaceAll("@_ID_@",id);
    }
}
