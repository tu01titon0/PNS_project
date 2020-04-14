-- Tao database: atm
CREATE DATABASE atm;

-- Xoá bảng
DROP TABLE `atm`.`LichSuGiaoDich`;
DROP TABLE `atm`.`TaiKhoan`;


-- Tạo table TaiKhoan
-- SoTaiKhoan
-- TenTaiKhoan

CREATE TABLE `atm`.`TaiKhoan` (
	`SoTaiKhoan` varchar(15) NOT NULL,
	`TenTaiKhoan` varchar(30) NOT NULL,
	`GioiTinh` char(1) DEFAULT NULL CHECK (`GioiTinh` in (_utf8mb4'F',_utf8mb4'M')),
    `NgayMoThe` date NOT NULL,
    `HanThe` char(7) NOT NULL,
    `SoDu` double CHECK (`SoDu` >= 50000) ,
    `MatKhau` char(6),
      PRIMARY KEY (`SoTaiKhoan`)
);


CREATE TABLE `atm`.`LichSuGiaoDich` (
   `STT` int NOT NULL AUTO_INCREMENT,
   `SoTaiKhoan` varchar(15) DEFAULT NULL,
   `NgayGiaoDich` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `TenGiaoDich` varchar(20) DEFAULT NULL,
   `SoTien` double DEFAULT NULL,
   `SoDuHienTai` double DEFAULT NULL,
	PRIMARY KEY (`STT`),
	CONSTRAINT `stk_FK` FOREIGN KEY (`SoTaiKhoan`) REFERENCES `TaiKhoan` (`SoTaiKhoan`)
);

INSERT INTO `atm`.`TaiKhoan` Value 
(123, 'NGUYEN VAN', 'M', '2010-03-30', '03-2020', 50000, 'asdf'),
(456, 'TRAN THI', 'F', '2020-02-10', '02-2025', 100000, 'asdf'),
(4321, 'HOANG MAI', 'F','2020-03-20','03-2030', 45000000, 'asdf'),
(3456, 'DO TAN', 'M', '2020-04-01', '04-2030', 5000000, 'asdf');

select * from `atm`.`TaiKhoan`;

select * from atm.TaiKhoan where SoTaiKhoan="123" and MatKhau="asdf";

INSERT INTO `atm`.`LichSuGiaoDich`Value
(1,123,'2010-03-30','mở thẻ',50000,50000),
(2,456,'2020-02-10','mở thẻ',100000,100000),
(3,4321,'2020-03-20','mở thẻ',5000000,50000000),
(4,3456,'2020-03-25','rút tiền',500000,500000);


update `atm`.`TaiKhoan` set `TaiKhoan`.`MatKhau` = '1234' where `TaiKhoan`.`SoTaiKhoan` = '123' and `TaiKhoan`.`MatKhau`= 'asdf'
