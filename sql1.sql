create table car_users
(
    id       number(4) primary key,
    username varchar2(20),
    password varchar2(20),
    tel      varchar(11),
    role     number(1) -- 1：超级管理员 2：用户管理员 3：汽车管理员
);

create sequence seq_users;

create table car_cars
(
    id        number(4) primary key, -- 编号
    carNumber varchar2(20),          -- 车牌号
    type      varchar2(20),          -- 种类
    isNew     number(1),             -- 是否为新车
    isRepair  number(1)              -- 维修状态
);

create sequence seq_cars;

create table car_roles
(
    role    number(1) primary key, -- 角色编号
    explain varchar2(20)           -- 角色说明
);

insert into car_roles values(1,'超级管理员');
insert into car_roles values(2,'用户管理员');
insert into car_roles values(3,'汽车管理员');


create table car_menus
(
    id       number(4) primary key,
    title    varchar2(50),
    icon     varchar2(20),
    href     varchar2(255),
    spread   number(1),
    parentId number(4)
);

create sequence seq_menus;

insert into car_menus(id, title, icon, href, spread)
values (seq_menus.nextval, '用户模块', '&#xe655;', 'page/news/newsList.html', 1);
insert into car_menus(id, title, icon, href, spread)
values (seq_menus.nextval, '汽车模块', '&#xe634;', 'page/img/images.html', 0);
insert into car_menus(id, title, icon, href, spread)
values (seq_menus.nextval, '系统管理', '&#xe716;', '', 0);
insert into car_menus(id, title, icon, href, spread)
values (seq_menus.nextval, '统计分析', '&#xe630;', '', 0);
commit;
select *
from car_menus;
insert into car_menus
values (seq_menus.nextval, '用户管理', '&#xe66f;', 'page/util/content.jsp', 0, 1);
insert into car_menus
values (seq_menus.nextval, '汽车管理', '&#xe61c;', 'page/util/carContent.jsp', 0, 2);
insert into car_menus
values (seq_menus.nextval, '菜单管理', '&#xe61c;', 'page/sysManage/menuContent.jsp', 0, 3);
insert into car_menus
values (seq_menus.nextval, '角色管理', '&#xe609;', 'page/sysManage/roleContent.jsp', 0, 3);
insert into car_menus
values (seq_menus.nextval, '用户管理', '&#xe609;', 'page/404.html', 0, 3);
insert into car_menus
values (seq_menus.nextval, '日志管理', '&#xe609;', 'page/404.html', 0, 3);
insert into car_menus
values (seq_menus.nextval, '系统公告', '&#xe609;', 'page/404.html', 0, 3);
insert into car_menus
values (seq_menus.nextval, '客户地区统计', '&#xe61c;', 'page/404.html', 0, 4);
insert into car_menus
values (seq_menus.nextval, '公司年度月份销售额曲线图', '&#xe609;', 'page/404.html', 0, 4);
insert into car_menus
values (seq_menus.nextval, '业务员年度销售额柱子图', '&#xe609;', 'page/404.html', 0, 4);
