package org.jeecg.modules.wms.warehouse.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.wms.config.WarehouseDictEnum;
import org.jeecg.modules.wms.warehouse.entity.WmsWarehouses;
import org.jeecg.modules.wms.warehouse.mapper.WmsWarehousesMapper;
import org.jeecg.modules.wms.warehouse.service.IWmsWarehousesService;
import org.springframework.stereotype.Service;

/**
 * @Description: 仓库表
 * @Author: jeecg-boot
 * @Date:   2026-05-09
 * @Version: V1.0
 */
@Service
public class WmsWarehousesServiceImpl extends ServiceImpl<WmsWarehousesMapper, WmsWarehouses> implements IWmsWarehousesService {

    @Override
    public void add(WmsWarehouses wmsWarehouses) {
/// 校验仓库的代码唯一性
        String warehouseCode = wmsWarehouses.getWarehouseCode();
//查询数据库  如果存在则说明代码已存在 select *from wms_warehouses  where warehouse_code=? and！=?; 排除自己
        LambdaQueryWrapper<WmsWarehouses> eq = new LambdaQueryWrapper<WmsWarehouses>()
                .eq(WmsWarehouses::getWarehouseCode,warehouseCode);
        Long count = this.count(eq);
        if(count>0){
            //抛出异常
            throw new JeecgBootException("仓库代码已存在");
        }
        //仓库的状态默认为 创建
        wmsWarehouses.setStatus(WarehouseDictEnum.STATUS_CREATED.getCode());
        this.save(wmsWarehouses);
    }

    @Override
    public void edit(WmsWarehouses wmsWarehouses) {
        String warehouseCode = wmsWarehouses.getWarehouseCode();
//查询数据库  如果存在则说明代码已存在 select *from wms_warehouses  where warehouse_code=? and！=?; 排除自己
        LambdaQueryWrapper<WmsWarehouses> eq = new LambdaQueryWrapper<WmsWarehouses>()
                .eq(WmsWarehouses::getWarehouseCode,warehouseCode)
                .ne(WmsWarehouses::getId,wmsWarehouses.getId());
        Long count = this.count(eq);
        if(count>0){
            //抛出异常
            throw new RuntimeException("仓库代码已存在");
        }
        //仓库的状态默认为 创建
        this.updateById(wmsWarehouses);
    }

    /**
     * 启用仓库
     * @param id
     */
    @Override
    public void enable(String id) {
        //校验参数是否为空
        if (StringUtils.isEmpty( id)) {
            throw new JeecgBootException("参数为空");
        }
        //校验仓库是否存在
        WmsWarehouses wmsWarehouses = getById(id);
        if (wmsWarehouses == null) {
            throw new JeecgBootException("仓库不存在");
        }
        //仓库状态为“创建”或禁用时方可启用
        if (!WarehouseDictEnum.STATUS_CREATED.getCode().equals(wmsWarehouses.getStatus()) && !WarehouseDictEnum.STATUS_INACTIVE.getCode().equals(wmsWarehouses.getStatus())) {
            throw new JeecgBootException("仓库状态为“创建”或禁用时方可启用");
        }
        wmsWarehouses.setStatus(WarehouseDictEnum.STATUS_ACTIVE.getCode());
        updateById(wmsWarehouses);
    }

    @Override
    public void disable(String id) {
        //校验参数是否为空
        if (StringUtils.isEmpty( id)) {
            throw new JeecgBootException("参数为空");
        }
        //校验仓库是否存在
        WmsWarehouses wmsWarehouses = getById(id);
        if (wmsWarehouses == null) {
            throw new JeecgBootException("仓库不存在");
        }
        //仓库状态为“启用”时方可禁用
        if (!WarehouseDictEnum.STATUS_ACTIVE.getCode().equals(wmsWarehouses.getStatus())) {
            throw new JeecgBootException("仓库状态为“启用”时方可禁用");
        }
        wmsWarehouses.setStatus(WarehouseDictEnum.STATUS_INACTIVE.getCode());
        updateById(wmsWarehouses);
    }
//
//    // 刘俊洋实验娱乐  恢复   实现类
//    @Override
//    public void restore(WmsWarehouses wmsWarehouses) {
//        // 1. 根据传入的仓库对象获取ID
//        Long id = wmsWarehouses.getId();
//
//        // 2. 查询数据库中的这条数据
//        WmsWarehouses warehouse = this.getById(id);
//
//        // 3. 判断是否存在
//        if (warehouse == null) {
//            throw new RuntimeException("仓库不存在");
//        }
//
//        // 4. 将 isRecycle 改为 0（0=正常，1=回收站）
//        warehouse.setIsRecycle(0);
//
//        // 5. 更新到数据库
//        this.updateById(warehouse);
//    }


}
