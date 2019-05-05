USE cdm;

create table order_header (
    order_no int not null,
    order_type int not null,
    cust_name varchar(10) not null,
    ref_no int null,
    ref_type int null,
	entry_datetime datetime(3) null
  );

insert into order_header (order_no, order_type, cust_name, ref_no, ref_type, entry_datetime) values (111, 1, "Woody A", -1, -1, now());
insert into order_header (order_no, order_type, cust_name, ref_no, ref_type, entry_datetime) values (112, 1, "Woody B", -1, -1, now());
insert into order_header (order_no, order_type, cust_name, ref_no, ref_type, entry_datetime) values (113, 1, "Woody C", -1, -1, now());

