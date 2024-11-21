create DATABASE asm_java4_1
-- Tạo bảng `Users`
CREATE TABLE Users (
    user_id CHAR(20) PRIMARY KEY, -- user_id có độ dài 20 ký tự
    username NVARCHAR(50) UNIQUE NOT NULL,
    password NVARCHAR(255) NOT NULL,
    email NVARCHAR(100) UNIQUE NOT NULL,
    role BIT NOT NULL CONSTRAINT DF_Users_Role DEFAULT 0 -- BIT(1) cho role, mặc định là 0 (customer)
);

-- Tạo bảng `Videos`
CREATE TABLE Videos (
    video_id CHAR(20) PRIMARY KEY, -- video_id có độ dài 20 ký tự
    title NVARCHAR(100) NOT NULL,
    description NVARCHAR(MAX), -- Sử dụng NVARCHAR(MAX) cho mô tả dài
    href NVARCHAR(255) NOT NULL,
    views INT DEFAULT 0 NOT NULL,
    isActive bit not null DEFAULT 1
);

-- Tạo bảng `History` (thay vì `Favorites`)
CREATE TABLE History (
    history_id INT PRIMARY KEY IDENTITY, -- Sử dụng IDENTITY cho auto-increment
    user_id CHAR(20)  FOREIGN KEY (user_id) REFERENCES Users(user_id), -- user_id có độ dài 20 ký tự
    video_id CHAR(20) NOT NULL FOREIGN KEY (video_id) REFERENCES Videos(video_id), -- video_id có độ dài 20 ký tự
    viewDate datetime not null DEFAULT getdate(),
    isLike bit not null DEFAULT 0,
    likeDate DATETIME null
);

-- Tạo bảng `Shares`
CREATE TABLE Shares (
    share_id CHAR(20) PRIMARY KEY, -- share_id có độ dài 20 ký tự
    user_id CHAR(20) NOT NULL, -- user_id có độ dài 20 ký tự
    video_id CHAR(20) NOT NULL, -- video_id có độ dài 20 ký tự
    recipient_email NVARCHAR(100) NOT NULL,
    share_date DATETIME DEFAULT GETDATE(), -- SQL Server sử dụng GETDATE() cho timestamp hiện tại
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (video_id) REFERENCES Videos(video_id)
);
-- data ins
INSERT INTO Users (user_id, username, password, email, role) VALUES
('1', 'user', '1', 'john.doe@example.com', 0),
('2', 'admin', '1', 'jane.smith@example.com', 1),
('U003', 'mike_lee', 'password789', 'mike.lee@example.com', 0),
('U004', 'susan_chen', 'password321', 'susan.chen@example.com', 0),
('U005', 'lucas_tan', 'password654', 'lucas.tan@example.com', 1),
('U006', 'emma_wang', 'password987', 'emma.wang@example.com', 0),
('U007', 'lily_kim', 'password135', 'lily.kim@example.com', 0),
('U008', 'mark_liu', 'password246', 'mark.liu@example.com', 1),
('U009', 'olivia_choi', 'password369', 'olivia.choi@example.com', 0),
('U010', 'peter_zhang', 'password258', 'peter.zhang@example.com', 1);
go 
INSERT INTO Videos (video_id, title, description, href) VALUES
('V001', N'Lời Không Nói', 'Lời Không Nói | Ronboogz (Lyrics video)', 'mOxsnQNoGRI'),
('V002', N'Vu Cat Tuong', N'Vu Cat Tuong - Mơ (Dreaming) ', '2YM4j-oP_qQ'),
('V003', N'Đi Tìm Tình Yêu', N'Đi Tìm Tình Yêu-MONO', 'wO61eRtk1pw');
go
INSERT INTO History (user_id, video_id, viewDate, isLike, likeDate) VALUES
('1', 'V001', '2024-11-01', 1, '2024-11-01'),
('2', 'V002', '2024-11-02', 0, NULL),
('U003', 'V003', '2024-11-03', 1, '2024-11-03');
go
INSERT INTO Shares (share_id, user_id, video_id, recipient_email, share_date) VALUES
('S001', '1', 'V001', 'alice@example.com', '2024-11-01'),
('S002', '2', 'V002', 'bob@example.com', '2024-11-02'),
('S003', 'U003', 'V003', 'carol@example.com', '2024-11-03');
