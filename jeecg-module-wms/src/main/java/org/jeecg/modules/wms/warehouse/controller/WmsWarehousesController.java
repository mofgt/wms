package org.jeecg.modules.wms.warehouse.controller;// MyBatis Plus核心组件：用于数据库查询条件封装

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.wms.warehouse.entity.WmsWarehouses;
import org.jeecg.modules.wms.warehouse.service.IWmsWarehousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

 /**
 * @Description: 仓库表
 * @Author: jeecg-boot
 * @Date:   2026-05-09
 * @Version: V1.0
 */
@Tag(name="仓库表")
@RestController
@RequestMapping("/warehouse/wmsWarehouses")//路径
@Slf4j
public class WmsWarehousesController extends JeecgController<WmsWarehouses, IWmsWarehousesService> {
	@Autowired
	private IWmsWarehousesService wmsWarehousesService;

	/**
	 * 分页列表查询仓库信息
	 *
	 * @param wmsWarehouses 仓库查询条件对象
	 * @param pageNo 页码，默认为1
	 * @param pageSize 每页记录数，默认为10
	 * @param req HTTP请求对象，用于获取查询参数
	 * @return 返回分页查询结果，包含仓库列表信息
	 */
	//@AutoLog(value = "仓库表-分页列表查询")
	@Operation(summary="仓库表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<WmsWarehouses>> queryPageList(WmsWarehouses wmsWarehouses,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<WmsWarehouses> queryWrapper = QueryGenerator.initQueryWrapper(wmsWarehouses, req.getParameterMap());
		Page<WmsWarehouses> page = new Page<WmsWarehouses>(pageNo, pageSize);
		IPage<WmsWarehouses> pageList = wmsWarehousesService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加新仓库
	 *
	 * @param wmsWarehouses 仓库实体对象，包含仓库相关信息
	 * @return 返回操作结果，成功则返回"添加成功！"提示
	 */
	@AutoLog(value = "仓库表-添加")
	@Operation(summary="仓库表-添加")
	@RequiresPermissions("warehouse:wms_warehouses:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody WmsWarehouses wmsWarehouses) {
//		wmsWarehousesService.save(wmsWarehouses);
		wmsWarehousesService.add(wmsWarehouses);

		return Result.OK("添加成功！");
	}

    //刘俊洋自己添加后端功能实验   可删除
//     这段代码定义了一个仓库添加接口：
//
//             **功能说明：**
//             - **接口路径**：POST `/add`，用于新增仓库数据
//- **权限控制**：需要 `warehouse:wms_warehouses:add` 权限才能访问
//- **日志记录**：自动记录操作日志，标注为"仓库表-刘俊洋"
//             - **业务逻辑**：调用服务层的 `add()` 方法保存仓库信息
//- **返回结果**：成功时返回"刘俊洋实验成功！"提示
//
//     这是一个标准的RESTful API接口，用于创建新仓库记录。


 	/**
	 * 添加新仓库（刘俊洋实验接口）
	 * @param wmsWarehouses 仓库实体对象，包含需要添加的仓库信息
	 * @return 返回操作结果，成功则返回"刘俊洋实验成功！"提示
	 */
//    @AutoLog(value = "仓库表-刘俊洋-回收站")//操作日志自动记录
//     @Operation(summary="仓库表-刘俊洋-回收站")// Swagger 接口文档
//     @RequiresPermissions("warehouse:wms_warehouses:restore")// 权限校验（Shiro）
//     @PostMapping(value = "/restore")
//     public Result<String> rerstore(@RequestBody WmsWarehouses wmsWarehouses) {
////		wmsWarehousesService.save(wmsWarehouses);
////         wmsWarehousesService.add(wmsWarehouses);
//        wmsWarehousesService.restore(wmsWarehouses);
//         return Result.OK("刘俊洋---回收站 恢复实验成功！");
//     }


	/**
	 * 编辑修改仓库信息
	 *
	 * @param wmsWarehouses 仓库实体对象，包含需要更新的仓库信息
	 * @return 返回操作结果，成功则返回"编辑成功!"提示
	 */
	@AutoLog(value = "仓库表-编辑")
	@Operation(summary="仓库表-编辑")
	@RequiresPermissions("warehouse:wms_warehouses:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody WmsWarehouses wmsWarehouses) {
//		wmsWarehousesService.updateById(wmsWarehouses);
		wmsWarehousesService.edit(wmsWarehouses);

		return Result.OK("编辑成功!");
	}

	/**
	 * 通过ID删除单个仓库
	 *
	 * @param id 仓库的唯一标识ID
	 * @return 返回操作结果，成功则返回"删除成功!"提示
	 */
	@AutoLog(value = "仓库表-通过id删除")
	@Operation(summary="仓库表-通过id删除")
	@RequiresPermissions("warehouse:wms_warehouses:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		wmsWarehousesService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 启用指定仓库
	 *
	 * @param id 仓库的唯一标识ID
	 * @return 返回操作结果，成功则返回"启用成功!"提示
	 */
    @AutoLog(value = "仓库表-启用仓库")//日志
    @Operation(summary="仓库表-启用仓库")//文档
//    @RequiresPermissions("warehouse:wms_warehouses:deleteBatch")//认证授权
    @RequestMapping(value = "/enable")
    public Result<String>enable(@RequestParam(name="id",required=true) String id) {
        wmsWarehousesService.enable(id);
        return Result.OK("启用成功!");
    }

	/**
	 * 禁用指定仓库
	 *
	 * @param id 仓库的唯一标识ID
	 * @return 返回操作结果，成功则返回"禁用成功!"提示
	 */
    @AutoLog(value = "仓库表-禁用")
    @Operation(summary="仓库表-禁用仓库")
    @RequestMapping(value = "/disable")
    public Result<String> disable(@RequestParam(name="id",required=true) String id) {
        wmsWarehousesService.disable( id);
        return Result.OK("禁用成功!");
    }

	/**
	 * 批量删除多个仓库
	 *
	 * @param ids 仓库ID字符串，多个ID以逗号分隔
	 * @return 返回操作结果，成功则返回"批量删除成功!"提示
	 */
	@AutoLog(value = "仓库表-批量删除")
	@Operation(summary="仓库表-批量删除")
	@RequiresPermissions("warehouse:wms_warehouses:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.wmsWarehousesService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过ID查询单个仓库详情
	 *
	 * @param id 仓库的唯一标识ID
	 * @return 返回查询结果，找到则返回仓库对象，未找到则返回错误提示
	 */
	//@AutoLog(value = "仓库表-通过id查询")
	@Operation(summary="仓库表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WmsWarehouses> queryById(@RequestParam(name="id",required=true) String id) {
		WmsWarehouses wmsWarehouses = wmsWarehousesService.getById(id);
		if(wmsWarehouses==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(wmsWarehouses);
	}

	/**
	 * 导出仓库数据到Excel文件
	 *
	 * @param request HTTP请求对象
	 * @param wmsWarehouses 仓库查询条件对象，用于筛选导出数据
	 * @return 返回ModelAndView对象，包含导出的Excel数据
	 */
    @RequiresPermissions("warehouse:wms_warehouses:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, WmsWarehouses wmsWarehouses) {
        return super.exportXls(request, wmsWarehouses, WmsWarehouses.class, "仓库表");
    }

	/**
	 * 从Excel文件导入仓库数据
	 *
	 * @param request HTTP请求对象
	 * @param response HTTP响应对象
	 * @return 返回导入结果，包含成功或失败信息
	 */
    @RequiresPermissions("warehouse:wms_warehouses:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, WmsWarehouses.class);
    }

}
