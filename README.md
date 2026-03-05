# Hệ thống đặt Pizza - Pizza Ordering System

> [!NOTE]
> Đây là một dự án cá nhân tập luyện **Lập trình hướng đối tượng** (**OOP**) và các **nguyên lý SOLID** (**SOLID principles**). Hệ thống tập trung vào việc ghi log ra console để thực hành tư duy thiết kế phần mềm chuyên nghiệp.

## 1. Tổng quan - Overview

Dự án này là một bài tập thực hành xây dựng **Hệ thống đặt Pizza** (**Pizza Ordering System**). Mục tiêu không chỉ là tạo ra một chương trình hoạt động được, mà là rèn luyện quy trình tư duy của một kỹ sư phần mềm chuyên nghiệp:

**Vấn đề** (**Problem**) → **Phân tích** (**Analysis**) → **Thiết kế** (**Design**) → **Kiến trúc** (**Architecture**) → **Triển khai** (**Implementation**)

Thay vì viết mã ngay lập tức, chúng ta tập trung vào:
- Hiểu rõ vấn đề.
- Mô hình hóa **miền nghiệp vụ** (**domain**).
- Áp dụng các **nguyên lý SOLID** (**SOLID principles**).
- Thiết kế **kiến trúc có khả năng mở rộng** (**extensible architecture**).

---

## 2. Mục tiêu học tập - Learning Objectives

Dự án giúp lập trình viên thực hành:

### 2.1 Các khái niệm OOP - OOP Concepts
- **Tính đóng gói** (**Encapsulation**)
- **Tính trừu tượng** (**Abstraction**)
- **Tính kế thừa** (**Inheritance**)
- **Tính đa hình** (**Polymorphism**)

### 2.2 Các nguyên lý SOLID - SOLID Principles
- **S - Nguyên lý đơn trách nhiệm** (**Single Responsibility Principle**)
- **O - Nguyên lý Đóng/Mở** (**Open/Closed Principle**)
- **L - Nguyên lý thay thế Liskov** (**Liskov Substitution Principle**)
- **I - Nguyên lý phân tách giao diện** (**Interface Segregation Principle**)
- **D - Nguyên lý đảo ngược phụ thuộc** (**Dependency Inversion Principle**)

---

## 3. Mô tả bài toán - Problem Statement

Chúng ta xây dựng một hệ thống với các yêu cầu sau:

### 3.1 Thuộc tính của Pizza - Pizza Properties
Một chiếc Pizza bao gồm:
- **Tên** (**Name**)
- **Bột bánh** (**Dough**)
- **Sốt** (**Sauce**)
- **Phần nhân thêm** (**Toppings**): Ví dụ: **Nấm** (**Mushrooms**), **Hành tây** (**Onions**), **Thịt xông khói** (**Bacon**).

### 3.2 Các loại Pizza - Pizza Types
Hệ thống hỗ trợ các loại Pizza khác nhau:
- **Pizza phô mai** (**Cheese Pizza**)
- **Pizza Hy Lạp** (**Greek Pizza**)
- **Pizza xúc xích Ý** (**Pepperoni Pizza**)

### 3.3 Quy trình chuẩn bị - Preparation Process
Mọi chiếc Pizza đều trải qua các bước:
1. **Chọn loại Pizza** (**Select pizza type**)
2. **Chuẩn bị nguyên liệu** (**Prepare ingredients**)
3. **Nướng bánh** (**Bake pizza**)
4. **Đóng hộp** (**Box pizza**)

---

## 4. Mô hình hóa miền nghiệp vụ - Domain Modeling

### 4.1 Thực thể cốt lõi - Core Entity: `Pizza`
- **Thuộc tính** (**Attributes**): `name`, `dough`, `sauce`, `toppings`.
- **Phương thức** (**Methods**): `prepare()`, `bake()`, `box()`.

### 4.2 Các biến thể - Pizza Variants
- `Pizza` (Abstract Class)
  - `CheesePizza`
  - `GreekPizza`
  - `PepperoniPizza`

---

## 5. Phương pháp thiết kế - Design Approach

Chúng ta tuân theo quy trình kỹ thuật từng bước:

### 5.1 Bước 1: Phân tích vấn đề - Problem Analysis
Xác định:
- **Thực thể** (**Entities**): `Pizza`, `Dough`, `Sauce`, `Topping`.
- **Quy trình** (**Processes**): `prepare`, `bake`, `box`.
- **Biến thể** (**Variations**): Các loại Pizza.

### 5.2 Bước 2: Xác định các điểm thay đổi - Identify Change Points
Những gì có thể thay đổi trong tương lai?
- Các loại Pizza mới.
- Các loại phần nhân thêm khác nhau.
- Các phong cách chuẩn bị khác nhau.

> [!IMPORTANT]
> Những điểm thay đổi này sẽ định hướng cho kiến trúc của chúng ta.

### 5.3 Bước 3: Áp dụng SOLID - Apply SOLID
- **SRP**: `Pizza` quản lý hành vi bánh, `PizzaFactory` quản lý việc khởi tạo, `OrderService` quản lý luồng đặt hàng.
- **OCP**: Thêm loại Pizza mới (ví dụ: `VeggiePizza`) mà không cần sửa đổi mã nguồn hiện có.
- **DIP**: `OrderService` phụ thuộc vào trừu tượng (`PizzaFactory`), không phụ thuộc vào các lớp triển khai cụ thể.

---

## 6. Kiến trúc dự án - Project Architecture

```text
pizza-oop-solid
├── README.md
├── model
│   ├── Pizza.java
│   ├── CheesePizza.java
│   ├── GreekPizza.java
│   └── PepperoniPizza.java
├── factory
│   └── PizzaFactory.java
├── service
│   └── PizzaOrderService.java
└── Main.java
```

---

## 7. Lộ trình triển khai - Implementation Roadmap

- **Cấp độ 1: Mô hình Pizza cơ bản** (**Basic Pizza Model**): Tạo lớp `Pizza` và `CheesePizza`.
- **Cấp độ 2: Thêm các loại Pizza mới** (**Add More Pizza Types**): Thêm `GreekPizza` và `PepperoniPizza`.
- **Cấp độ 3: Giới thiệu Factory Pattern** (**Introduce Factory Pattern**): Thay thế các câu lệnh `if-else` bằng `PizzaFactory`.
- **Cấp độ 4: Cải thiện tính mở rộng** (**Improve Extensibility**): Hỗ trợ thêm nhân và phong cách chuẩn bị.
- **Cấp độ 5: Thêm kiểm thử** (**Add Tests**): Viết Unit Tests cho các loại bánh.

---

## 8. Công nghệ sử dụng - Technologies

- **Ngôn ngữ** (**Language**): Java 21+
- **Kiểm thử** (**Testing**): JUnit 5
- **Công cụ xây dựng** (**Build Tool**): Maven / Gradle

---

## 9. Tác giả - Author

Dự án này được tạo ra để phục vụ mục đích **học tập các nguyên lý thiết kế OOP và SOLID**.

_Người thực hiện: CongHungDev_
