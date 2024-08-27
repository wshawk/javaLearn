CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                        `name` varchar(100) DEFAULT NULL COMMENT '用户名称',
                        `age` int(11) DEFAULT NULL COMMENT '用户年龄',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试';
-- 插入一条数据
INSERT INTO `user` (user_name,age) VALUES ('222',1);
