package org.jeecg.modules.wms.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.wms.warehouse.entity.WmsStorageZones;

import java.util.List;

/**
 * @Description: 储区表
 * @Author: jeecg-boot
 * @Date:   2026-05-09
 * @Version: V1.0
 */
public interface WmsStorageZonesMapper extends BaseMapper<WmsStorageZones> {
    /**
     * 分页查询库区信息         queryList和映射名一致
     */
    public List<WmsStorageZones> queryList(WmsStorageZones wmsStorageZones);
}
