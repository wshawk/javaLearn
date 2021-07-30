CREATE database if NOT EXISTS  `test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
use `test`;

CREATE TABLE `trial_activity` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                  `shop_id` bigint NOT NULL COMMENT '店铺id',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='试用活动';


CREATE TABLE `trial_activity_mapping_apply_record` (
                                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                                       `apply_table_suffix_id` bigint DEFAULT NULL,
                                                       `trial_activity_id_begin` bigint DEFAULT NULL,
                                                       `trial_activity_id_end` bigint DEFAULT NULL,
                                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `trial_apply_record_1` (
                                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='试用活动申请记录';