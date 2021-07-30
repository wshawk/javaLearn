package com.hawk.example.test_mybatis_plus_dynamic_table.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hawk.example.test_mybatis_plus_dynamic_table.common.entity.TrialApplyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author hawk
 * @package com.hawk.example.test_mybatis_plus_dynamic_table.mapper
 * @desc
 * @date 2021/7/24
 */
@Mapper
public interface TrialApplyRecordMapper extends BaseMapper<TrialApplyRecord> {
    @Update({"<script>" +
            " CREATE TABLE ${tableName} (\n" +
            "  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',\n" +
            "  `user_id` bigint DEFAULT NULL COMMENT '用户编号',\n" +
            "  `trial_activity_id` bigint DEFAULT NULL COMMENT '试用活动id',\n" +
            "  `trial_commodity_id` bigint DEFAULT NULL COMMENT '试用活动商品id, 即productId',\n" +
            "  `shop_id` bigint DEFAULT NULL COMMENT '店铺id',\n" +
            "  `sku_id` bigint DEFAULT NULL COMMENT '商品sku编码',\n" +
            "  `sku_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品名称',\n" +
            "  `apply_status` int DEFAULT '10' COMMENT '申请状态 10-待抽签; 20-未中选; 30-已中选(待公布); 40-已停用(活动已停用)',\n" +
            "  `phone` varchar(16) DEFAULT NULL COMMENT '用户手机号码',\n" +
            "  `cumulative_apply_count` int DEFAULT NULL COMMENT '在该店铺累计申请次数',\n" +
            "  `cumulative_selected_count` int DEFAULT NULL COMMENT '在该店铺累计中选次数',\n" +
            "  `valid_order_count` int DEFAULT NULL COMMENT '在该店铺产生的有效订单数',\n" +
            "  `valid_order_money` decimal(16,2) DEFAULT NULL COMMENT '在该店铺产生的有效订单金额总额',\n" +
            "  `submit_time` datetime DEFAULT NULL COMMENT '提交申请时间',\n" +
            "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
            "  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',\n" +
            "  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
            "  `modify_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改人',\n" +
            "  `enable_flag` int DEFAULT NULL COMMENT '是否可用 10-可用 20-删除',\n" +
            "  `version` bigint DEFAULT NULL COMMENT '版本号--乐观锁预留字段',\n" +
            "  PRIMARY KEY (`id`) USING BTREE,\n" +
            "  KEY `index_user_id` (`user_id`),\n" +
            "  KEY `index_trial_commodity_id` (`trial_commodity_id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='试用活动申请记录' "
            +"</script>"})
    int createTable(@Param("tableName") String tableName);

    @Select("select count(1) from ${tableName}")
    int getCount(@Param("tableName") String tableName);
}
