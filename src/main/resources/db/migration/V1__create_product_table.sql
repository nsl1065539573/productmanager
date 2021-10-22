create table product
(
    ID           BIGINT(20) primary key auto_increment COMMENT 'id',
    NAME         VARCHAR(128) unique COMMENT '商品名',
    PRICE        DECIMAL(14, 4) COMMENT '商品价格',
    STATUS       VARCHAR(1) COMMENT '商品状态',
    TIME_CREATED BIGINT(20) COMMENT '创建时间',
    TIME_UPDATED BIGINT(20) COMMENT '最后一次更新时间'
) charset = 'utf8' comment '商品表';

-- 进价会变 进价放在库存表，库存记录商品当前进价的库存剩余  更新库存时，拿到最早一个库存大于0的记录来更新
create table product_inventory
(
    ID                       BIGINT(20) primary key auto_increment COMMENT 'id',
    PRODUCT_ID               BIGINT(20) COMMENT '商品id',
    PRODUCT_PURCHASING_PRICE DECIMAL(14, 4) COMMENT '进价',
    QUANTITY                 INT(10) COMMENT '库存',
    TIME_CREATED             BIGINT(20) COMMENT '进货时间',
    TIME_UPDATE              BIGINT(20) COMMENT '最后一次更新时间'
) charset = 'utf8' comment '库存表';

-- 进货记录表，记录进货的记录
create table purchase_record
(
    ID           BIGINT(20) primary key auto_increment COMMENT 'id',
    PRODUCT_ID   BIGINT(20)     NOT NULL COMMENT '商品id',
    VERSION      varchar(16)    NOT NULL COMMENT '可能每一次进货进多个货，用当天日期来记录当次进货',
    PRICE        DECIMAL(14, 4) NOT NULL COMMENT '商品进价',
    QUANTITY     BIGINT(20)     NOT NULL COMMENT '进货数量',
    TIME_CREATED BIGINT(20)     NOT NULL COMMENT '进货时间',
    TIME_UPDATED BIGINT(20)     NOT NULL COMMENT '最后一次更新时间'
) charset = 'utf8' comment '进货记录表';

create table sys_order
(
    ID           BIGINT(20) primary key auto_increment COMMENT 'id',
    AMOUNT       DECIMAL(14, 4) COMMENT '订单价格',
    TIME_CREATED BIGINT(20) COMMENT '订单创建时间',
    TIME_UPDATED BIGINT(20) COMMENT '订单最后一次更新时间'
) charset = 'utf8' comment '订单表';

create table sys_order_details
(
    ID           BIGINT(20) primary key auto_increment COMMENT 'id',
    ORDER_ID     BIGINT(20)     NOT NULL COMMENT '订单id',
    AMOUNT       DECIMAL(14, 4) NOT NULL COMMENT '商品单价',
    QUANTITY     INT(4)         NOT NULL COMMENT '这类商品卖出的数量',
    PRODUCT_ID   BIGINT(20)     NOT NULL COMMENT '商品Id',
    TIME_CREATED BIGINT(20)     NOT NULL COMMENT '创建时间',
    TIME_UPDATED BIGINT(20)     NOT NULL COMMENT '最后一次更新时间'
) charset = 'utf8' comment '订单明细表';

create table sys_user
(
    ID           BIGINT(20) primary key auto_increment COMMENT 'id',
    USERNAME     VARCHAR(24) unique COMMENT '登录用户名',
    PASSWORD     VARCHAR(64) NOT NULL COMMENT '密码',
    NAME         VARCHAR(10) NOT NULL COMMENT '昵称',
    TIME_CREATED BIGINT(20)  NOT NULL COMMENT '创建时间',
    TIME_UPDATED BIGINT(20)  NOT NULL COMMENT '最后一次更新时间'
) charset = 'utf8' comment '用户表';