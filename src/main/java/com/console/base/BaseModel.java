package com.console.base;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.console.common.constant.CommonConstant;
import com.console.common.utils.SnowFlakeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: yang
 * @Description:
 * @Date:Create In 15:19 2018/9/15
 * @Modified By:
 */
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = -1708147741074741257L;

    @Id
    @TableId
    @ApiModelProperty(value = "唯一标识")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id = SnowFlakeUtil.getFlowIdInstance().nextId();

    @ApiModelProperty(value = "创建者")
    private Long createBy;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新者")
    private Long updateBy;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除标志 默认0")
    @TableLogic
    private Integer delFlag = CommonConstant.STATUS_NORMAL;

    @ApiModelProperty(value = "生命周期 默认0")
    private Integer lifecycle = CommonConstant.STATUS_NORMAL;

}
