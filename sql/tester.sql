SELECT hotel_id, room_type,count(*),normal_price AS num_of_room FROM room natural join hotel WHERE room_id not in
(SELECT room.room_id FROM room natural join room_status
where (room_status.end_date <= '2016-07-30' AND room_status.end_date > '2016-07-28') 
OR (room_status.start_date < '2016-07-30' AND room_status.start_date >= '2016-07-28')) AND hotel.city = 'Sydney' AND room.normal_price < 100
group by hotel_id,room_type;

select booking.hotel_id, booking.roomtype,sum(booking.number_of_room) FROM booking join hotel on hotel.hotel_id=booking.hotel_id AND hotel.city = 'Sydney'
group by booking.hotel_id,booking.roomtype;