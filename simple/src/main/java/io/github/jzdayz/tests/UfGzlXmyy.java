package io.github.jzdayz.tests;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author MBP
 * @since 2020-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("uf_gzl_xmyy")
public class UfGzlXmyy implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("requestId")
    private Integer requestId;

    @TableField("formmodeid")
    private Integer formmodeid;

    @TableField("modedatacreater")
    private Integer modedatacreater;

    @TableField("modedatacreatertype")
    private Integer modedatacreatertype;

    @TableField("modedatacreatedate")
    private String modedatacreatedate;

    @TableField("modedatacreatetime")
    private String modedatacreatetime;

    @TableField("modedatamodifier")
    private Integer modedatamodifier;

    @TableField("modedatamodifydatetime")
    private String modedatamodifydatetime;

    @TableField("MODEUUID")
    private String modeuuid;

    @TableField("sfsyxgzzd")
    private Integer sfsyxgzzd;

    @TableField("ghrq")
    private String ghrq;

    @TableField("qagh")
    private String qagh;

    @TableField("xmjlgh")
    private String xmjlgh;

    @TableField("zxid")
    private String zxid;

    @TableField("syqid")
    private String syqid;

    @TableField("bmid")
    private String bmid;

    @TableField("ywzx")
    private String ywzx;

    @TableField("ywq")
    private String ywq;

    @TableField("syb")
    private String syb;

    @TableField("dy")
    private String dy;

    @TableField("xmbh")
    private String xmbh;

    @TableField("xmmc")
    private String xmmc;

    @TableField("xmjl")
    private String xmjl;

    @TableField("qa")
    private String qa;

    @TableField("kh")
    private String kh;

    @TableField("xmlb")
    private String xmlb;

    @TableField("xmlxx")
    private String xmlxx;

    @TableField("xmdj")
    private String xmdj;

    @TableField("xmjd")
    private String xmjd;

    @TableField("xmzt")
    private String xmzt;

    @TableField("nczx")
    private String nczx;

    @TableField("xmqdrq")
    private String xmqdrq;

    @TableField("xmlxrq")
    private String xmlxrq;

    @TableField("xmgbrq")
    private String xmgbrq;

    @TableField("wbjdyjksrq")
    private String wbjdyjksrq;

    @TableField("wbjdyjjsrq")
    private String wbjdyjjsrq;

    @TableField("wbjdsjksrq")
    private String wbjdsjksrq;

    @TableField("yjxrq")
    private String yjxrq;

    @TableField("xmmllmb")
    private String xmmllmb;

    @TableField("xmmlljh")
    private String xmmlljh;

    @TableField("xmmllgdyc")
    private String xmmllgdyc;

    @TableField("xmmllsj")
    private String xmmllsj;

    @TableField("xmdhtjey")
    private String xmdhtjey;

    @TableField("xmdcgygjey")
    private String xmdcgygjey;

    @TableField("zrljxry")
    private String zrljxry;

    @TableField("zrljxy")
    private String zrljxy;

    @TableField("zfyjxy")
    private String zfyjxy;

    @TableField("ssjdrljxry")
    private String ssjdrljxry;

    @TableField("ssjdrljxy")
    private String ssjdrljxy;

    @TableField("ssjdfyjxy")
    private String ssjdfyjxy;

    @TableField("wbjdrljxry")
    private String wbjdrljxry;

    @TableField("wbjdrljxy")
    private String wbjdrljxy;

    @TableField("wbjdfyjxy")
    private String wbjdfyjxy;

    @TableField("xmjhbbh")
    private String xmjhbbh;

    @TableField("ssjdytrrlry")
    private String ssjdytrrlry;

    @TableField("ssjdytrrly")
    private String ssjdytrrly;

    @TableField("ssjdhxtrrlry")
    private String ssjdhxtrrlry;

    @TableField("ssjdhxtrrly")
    private String ssjdhxtrrly;

    @TableField("wbjdytrrlry")
    private String wbjdytrrlry;

    @TableField("wbjdytrrly")
    private String wbjdytrrly;

    @TableField("sjfyy")
    private String sjfyy;

    @TableField("jhwcbfb")
    private String jhwcbfb;

    @TableField("xmztwgbfb")
    private String xmztwgbfb;

    @TableField("xmsjwgbfb")
    private String xmsjwgbfb;

    @TableField("jdjxzs")
    private String jdjxzs;

    @TableField("cbjxzs")
    private String cbjxzs;

    @TableField("jz19ndcgsfje")
    private String jz19ndcgsfje;

    @TableField("rwsjwgbfb")
    private String rwsjwgbfb;

    @TableField("rwjhwgbfb")
    private String rwjhwgbfb;

    @TableField("sfgz")
    private String sfgz;

    @TableField("gzyf")
    private String gzyf;


}
