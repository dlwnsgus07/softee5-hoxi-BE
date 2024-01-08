-- 일반 사용자 생성
INSERT INTO customer (customer_id, name, phone_Number)
VALUES (1, '이준현', '010-9215-8223');
INSERT INTO customer (customer_id, name, phone_Number)
VALUES (2, '김지훈', '123-456-789');

-- 우리 아부지는 택시드라이버~
INSERT INTO driver (driver_id, name, phone_Number, income, car_Type, car_Number, image)
values (1, '우리 아부지는 택시드라이버', '010-3855-5779', 10000, '람보르기니', '35마 2454', null);

INSERT INTO driver (driver_id, name, phone_Number, income, car_Type, car_Number, image)
values (2, '우리 어머니는 택시드라이버', '010-8515-8223', 20000, '페라리', '60마 1212', null);

-- 예약 내용 생성
INSERT INTO reservation (name, phone_number, hotel_number)
VALUES ('이준현', '010-9215-8223', '050-1823-014');
INSERT INTO reservation (name, phone_number, hotel_number)
VALUES ('김지훈', '010-2315-8223', '013-3218-23014');
INSERT INTO reservation (name, phone_number, hotel_number)
VALUES ('뉴진스', '123-4556-745', '431-34343-224');
INSERT INTO reservation (name, phone_number, hotel_number)
VALUES ('르세라핌', '13-247-45', '431-242-13');
INSERT INTO reservation (name, phone_number, hotel_number)
VALUES ('김미로', '13-247-45', '431-242-13');
-- 콜 생성

INSERT INTO call (customer_id, source, destination, arrival_Time, requirement, carrier_num, delivery_fee, distance,
                  is_cargo, driver_id, position_x, position_y, state, reservation_id)
VALUES (1, '부산', '서울', '2014-11-23 12:34:56', '최대한 빨리 달려주세요', 1, 1000, 10, true, null, null, null, 'WAIT', 1);
INSERT INTO call (customer_id, source, destination, arrival_Time, requirement, carrier_num, delivery_fee, distance,
                  is_cargo, driver_id, state, reservation_id)
VALUES (2, '서울', '부산', '2015-05-18 09:45:00', '최대한 느리게 달려주세요', 1, 1000, 10, true, 1, 'PICKUP', 2);
INSERT INTO call (customer_id, source, destination, arrival_Time, requirement, carrier_num, delivery_fee, distance,
                  is_cargo, driver_id, position_x, position_y, state, reservation_id)
VALUES (2, '서울', '강원도', '2016-09-30 18:20:30', '최대한 느리게 달려주세요', 1, 1000, 10, true, 1, 1, 2, 'PICKUP', 3);
INSERT INTO call (customer_id, source, destination, arrival_Time, requirement, carrier_num, delivery_fee, distance,
                  is_cargo, driver_id, position_x, position_y, state, reservation_id)
VALUES (1, '부', '서', '2014-11-23 11:34:56', '오전나와라잉', 1, 1000, 10, true, null, null, null, 'WAIT', 4);
INSERT INTO call (customer_id, source, destination, arrival_Time, requirement, carrier_num, delivery_fee, distance,
                  is_cargo, driver_id, position_x, position_y, state, reservation_id, delivery_image)
VALUES (1, '부', '서', '2014-11-23 11:34:56', '오전나와라잉', 1, 1000, 10, true, null, null, null, 'COMPLETE', 5, '계란라면.jpeg');

--최근 목적지
INSERT INTO destination (destination_id, customer_id, name)
VALUES (1,1, '교대역');
INSERT INTO destination (destination_id,customer_id, name)
VALUES (2,1, '강남역');
INSERT INTO destination (destination_id,customer_id, name)
VALUES (3,1, '학동역');
INSERT INTO destination (destination_id,customer_id, name)
VALUES (4,2, '교대역');
INSERT INTO destination (destination_id,customer_id, name)
VALUES (5,2, '강남역');