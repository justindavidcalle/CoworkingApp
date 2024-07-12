-- Insert test data for users
INSERT INTO user (id, first_name, last_name, email, password, role) VALUES
(1, 'Admin', 'User', 'admin@coworkingapp.com', 'adminpass', 'ADMIN'),
(2, 'John', 'Doe', 'john.doe@example.com', 'password', 'MEMBER'),
(3, 'Jane', 'Doe', 'jane.doe@example.com', 'password', 'MEMBER');

-- Insert test data for bookings
INSERT INTO booking (id, user_id, date, half_day, status) VALUES
(1, 2, '2024-07-15', true, 'PENDING'),
(2, 2, '2024-07-16', false, 'CONFIRMED'),
(3, 3, '2024-07-17', true, 'CANCELLED'),
(4, 3, '2024-07-18', false, 'PENDING');
