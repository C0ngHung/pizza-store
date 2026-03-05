# Phân tích yêu cầu Level 1 - Level 1 Requirement Analysis

## 1. Tổng quan dự án - Project Overview

Dự án sẽ được triển khai theo quy trình kỹ thuật chuyên nghiệp, bao gồm các giai đoạn:
- **Phân tích yêu cầu** (**Requirement Analysis**)
- **Thiết kế** (**Design**)
- **Triển khai** (**Implementation**)
- **Kiểm thử** (**Testing**)
- **Tài liệu hóa** (**Documentation**)

> [!NOTE]
> - **Ngôn ngữ**: Java.
> - **Loại ứng dụng**: Ứng dụng Console (**Console App**).

## 2. Mô tả nghiệp vụ - Business Description

Chúng ta sẽ xây dựng một **Cửa hàng Pizza** (**Pizza Store**) hỗ trợ đặt hàng các loại Pizza phổ biến:
- **Pizza phô mai** (**Cheese Pizza**)
- **Pizza Hy Lạp** (**Greek Pizza**)
- **Pizza xúc xích Ý** (**Pepperoni Pizza**)

### 2.1 Thông tin bánh Pizza - Pizza Information
Mỗi chiếc bánh Pizza khi được tạo ra (**Make a Pizza**) tối thiểu phải bao gồm:
- **Tên** (**Name**)
- **Vỏ bánh** (**Dough**)
- **Sốt** (**Sauce**)
- **Phần nhân** (**Toppings**): Bao gồm nhiều loại như **Nấm** (**Mushrooms**), **Hành tây** (**Onions**), **Thịt xông khói** (**Bacon**).

### 2.2 Quy trình đặt hàng - Ordering Process
Quy trình (**Process**) xử lý một đơn hàng Pizza:
1. **Chọn loại Pizza** (**Select Pizza Type**)
2. **Chuẩn bị bánh** (**Prepare the Pizza**)
3. **Nướng bánh** (**Bake the Pizza**)
4. **Đóng hộp** (**Box the Pizza**)

## 3. Phân tích thiết kế hệ thống - System Design Analysis

Dựa trên yêu cầu, chúng ta áp dụng 4 tính chất cốt lõi của **Lập trình hướng đối tượng** (**OOP**):

- **Kế thừa** (**Inheritance**): Thể hiện qua việc có nhiều kiểu Pizza khác nhau (`Cheese Pizza`, `Greek Pizza`, `Pepperoni Pizza`) cùng kế thừa từ một lớp nền tảng.
- **Đóng gói** (**Encapsulation**): Các thành phần của bánh (Tên, Bột, Sốt, Toppings) được quản lý và bảo vệ bên trong lớp `Pizza`.
- **Đa hình** (**Polymorphism**): Mỗi loại Pizza sẽ có cách chuẩn bị (`prepare`) các thành phần riêng biệt tùy theo công thức của nó.
- **Trừu tượng** (**Abstraction**): 

> [!IMPORTANT]
> Lớp `Pizza` sẽ là một **Lớp trừu tượng** (**Abstract class**). Chúng ta không thể tạo ra một thực thể "Pizza chung chung" mà phải thông qua các loại cụ thể như Cheese hay Greek.