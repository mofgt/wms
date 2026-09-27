package org.jeecg.modules.wms.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 储区表
 * @Author: jeecg-boot
 * @Date:   2026-05-09
 * @Version: V1.0
 */
@Data
@TableName("wms_storage_zones")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="储区表")
public class WmsStorageZones implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private java.lang.String id;
	/**创建人*/
    @Schema(description = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @Schema(description = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新日期")
    private java.util.Date updateTime;
	/**库区编码*/
	@Excel(name = "库区编码", width = 15)
    @Schema(description = "库区编码")
    private java.lang.String zoneCode;
	/**库区名称*/
	@Excel(name = "库区名称", width = 15)
    @Schema(description = "库区名称")
    private java.lang.String zoneName;
	/**库区类型*/
	@Excel(name = "库区类型", width = 15)
    @Schema(description = "库区类型")
    private java.lang.String zoneType;
	/**状态: 创建,禁用, 启用*/
	@Excel(name = "状态: 创建,禁用, 启用", width = 15, dicCode = "dict_item_status")
	@Dict(dicCode = "dict_item_status")
    @Schema(description = "状态: 创建,禁用, 启用")
    private java.lang.String status;
	/**是否可售库存 0-否, 1-是*/
	@Excel(name = "是否可售库存 0-否, 1-是", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @Schema(description = "是否可售库存 0-否, 1-是")
    private java.lang.String isSellable;
	/**所属仓库*/
	@Excel(name = "所属仓库", width = 15)
    @Schema(description = "所属仓库")
    private java.lang.String warehouseId;

    //09问题注释
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;  // 加上这行
    }
    //将所属仓库成成中文   新建一个id  第二章07 ——02
    /*
    *  仓库名称
     */
//仓库对象用于显示  07 `
    @TableField(exist = false)
    @Schema(description = "仓库名称")
    @Excel(name = "仓库名称", width = 15)
    private String warehouseName;
}
