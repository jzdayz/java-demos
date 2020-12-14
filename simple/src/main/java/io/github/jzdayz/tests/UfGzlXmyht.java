package io.github.jzdayz.tests;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@TableName("uf_gzl_xmyht")
public class UfGzlXmyht implements Serializable {

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

    @TableField("xmztwgbfb")
    private String xmztwgbfb;

    @TableField("htbh")
    private String htbh;

    @TableField("szdq")
    private String szdq;

    @TableField("szxs")
    private String szxs;

    @TableField("htkh")
    private String htkh;

    @TableField("khjc")
    private String khjc;

    @TableField("httjsj")
    private String httjsj;

    @TableField("xmyhtgx")
    private String xmyhtgx;

    @TableField("htzxmzb")
    private String htzxmzb;

    @TableField("htnrcbjsbl")
    private String htnrcbjsbl;

    @TableField("htje")
    private String htje;

    @TableField("htlx")
    private String htlx;

    @TableField("htqdmll")
    private String htqdmll;

    @TableField("qdyjrly")
    private String qdyjrly;

    @TableField("qdyjfyy")
    private String qdyjfyy;

    @TableField("jzdqyddhtljkpe")
    private String jzdqyddhtljkpe;

    @TableField("jzdqydhhtljdke")
    private String jzdqydhhtljdke;

    @TableField("jz19ndhtljkpe")
    private String jz19ndhtljkpe;

    @TableField("htwbzq")
    private String htwbzq;

    @TableField("htjhysrq")
    private String htjhysrq;

    @TableField("htcgygjehjy")
    private String htcgygjehjy;

    @TableField("xmlxx")
    private String xmlxx;

    @TableField("htmc")
    private String htmc;

    @TableField("htsjysrq")
    private String htsjysrq;

    @TableField("htwbjsrq")
    private String htwbjsrq;

    @TableField("htyswdgdsl")
    private String htyswdgdsl;

    @TableField("htyswdgdzt")
    private String htyswdgdzt;

    @TableField("htgdwcsj")
    private String htgdwcsj;

    @TableField("sfwxfpgssj")
    private String sfwxfpgssj;

    @TableField("sfgz")
    private String sfgz;

    @TableField("gzyf")
    private String gzyf;


}
