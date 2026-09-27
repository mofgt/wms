package org.jeecg.modules.wms.warehouse.service;

import org.jeecg.modules.wms.warehouse.entity.WmsWarehouses;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 仓库表
 * @Author: jeecg-boot
 * @Date:   2026-05-09
 * @Version: V1.0
 */
public interface IWmsWarehousesService extends IService<WmsWarehouses> {
//刘俊洋新增娱乐   恢复
//    void resume(WmsWarehouses wmsWarehouses);
    /**
     * 新增仓库
     * @param wmsWarehouses
     */
    void add(WmsWarehouses wmsWarehouses);

    /**
     * 修改仓库
     * @param wmsWarehouses
     */
    void edit(WmsWarehouses wmsWarehouses);
    /*
     *  启用仓库
     * @pram id
     */
    void enable(String id);
    /*
    *  禁用仓库
    *   * @pram id
     */
    void disable(String id);

    //刘俊洋  娱乐  恢复
//    void restore(WmsWarehouses wmsWarehouses);
}
