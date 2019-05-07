USE cdm;

drop table if exists order_header;
create table order_header (
    order_no int not null,
    order_type int not null,
    cust_name varchar(10) not null,
    ref_no int null,
    ref_type int null,
	entry_datetime datetime(3) null
  );
alter table order_header add primary key (order_no, order_type);

insert into order_header (order_no, order_type, cust_name, ref_no, ref_type, entry_datetime) values (11111, 1, "Woody A", -1, -1, now());
insert into order_header (order_no, order_type, cust_name, ref_no, ref_type, entry_datetime) values (11112, 1, "Woody B", -1, -1, now());
insert into order_header (order_no, order_type, cust_name, ref_no, ref_type, entry_datetime) values (11113, 1, "Woody C", -1, -1, now());
insert into order_header (order_no, order_type, cust_name, ref_no, ref_type, entry_datetime) values (11114, 1, "Woody C", -1, -1, now());
insert into order_header (order_no, order_type, cust_name, ref_no, ref_type, entry_datetime) values (11115, 1, "Woody C", -1, -1, now());
insert into order_header (order_no, order_type, cust_name, ref_no, ref_type, entry_datetime) values (11116, 1, "Woody C", -1, -1, now());
insert into order_header (order_no, order_type, cust_name, ref_no, ref_type, entry_datetime) values (11117, 4, "Woody C", -1, -1, now());
insert into order_header (order_no, order_type, cust_name, ref_no, ref_type, entry_datetime) values (11118, 4, "Woody C", -1, -1, now());

select * from order_header;

--git init
drop table if exists order_detail;
create table order_detail (
  order_no int not null,
  order_type int not null,
  order_line_no int null,
  order_qty int null,
  sku_no int null,
  delete_date datetime(3) null
);
alter table order_detail add primary key (order_no, order_type, order_line_no);

INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11111, 1, 1, 6, 123456);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11112, 1, 1, 6, 123456);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11113, 1, 1, 6, 123456);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11114, 1, 1, 6, 123456);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11115, 1, 1, 6, 123456);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11116, 1, 1, 6, 123456);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11117, 4, 1, 6, 123456);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11118, 4, 1, 6, 123456);

INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11111, 1, 2, 12, 123457);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11112, 1, 2, 12, 123457);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11113, 1, 2, 12, 123457);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11114, 1, 2, 12, 123457);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11115, 1, 2, 12, 123457);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11116, 1, 2, 12, 123457);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11117, 4, 2, 12, 123457);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11118, 4, 2, 12, 123457);

INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11111, 1, 3, 24, 123458);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11112, 1, 3, 24, 123458);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11113, 1, 3, 24, 123458);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11114, 1, 3, 24, 123458);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11115, 1, 3, 24, 123458);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11116, 1, 3, 24, 123458);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11117, 4, 3, 24, 123458);
INSERT INTO cdm.order_detail (order_no, order_type, order_line_no, order_qty, sku_no) VALUES (11118, 4, 3, 24, 123458);

select * from order_detail;

--
drop table if exists part_master;
create table part_master (
  sku_no int null,
  part_no varchar(100) null
);
alter table part_master add primary key (sku_no);
alter table part_master add unique key (part_no);

INSERT INTO cdm.part_master (sku_no, part_no) VALUES (123456, 'HYV-R-123456');
INSERT INTO cdm.part_master (sku_no, part_no) VALUES (123457, 'HYV-S-777777');
INSERT INTO cdm.part_master (sku_no, part_no) VALUES (123458, 'HYV-S-888888');

select * from part_master;


