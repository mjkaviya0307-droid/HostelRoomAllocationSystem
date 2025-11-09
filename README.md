# 🏫 Hostel Management System (Java Spring Boot)

A full-stack **Hostel Management System** built using **Java Spring Boot, MySQL, and Thymeleaf**, designed to simplify hostel operations for administrators and students.  
It provides secure login, automated room allocation, fee payment, and an intelligent chatbot assistant for students.


## 🚀 Features

### 👨‍💼 Admin Features
- **Admin Login & Dashboard**
- **Register and Manage Students**
- **Add and Manage Hostel Rooms**
- **Automatic Room Allocation**  
  - Allocates students to rooms based on capacity and gender.
- **Monitor Student Details and Room Status**

### 🎓 Student Features
- **Student Login & Dashboard**
- **View Allocated Room Details**
- **Pay Hostel Fees Online**
- **Receive Confirmation Emails**
- **Interactive Chatbot Assistant**  
  - Rule-based AI chatbot that helps students with hostel-related queries such as fee details, allocation info, and general support.


## 🧠 Technical Stack Used

| Layer | Technology |
|-------|-------------|
| **Frontend** | HTML5, CSS3, JavaScript, Thymeleaf Templates |
| **Backend** | Java, Spring Boot (MVC, REST) |
| **Database** | MySQL with JPA/Hibernate |
| **Tools & Frameworks** | Eclipse IDE, VS Code, Maven |
| **AI Integration** | Custom Rule-Based Chatbot |
| **Version Control** | Git & GitHub |


## ⚙️ How It Works

1. **Admins** register rooms and students via the dashboard.  
2. **Students** log in and can view their room allocation.  
3. Room allocation is **automated** and checks for capacity and gender constraints.  
4. Students can **pay fees**, and upon successful payment, **confirmation emails** are sent automatically.  
5. A **chatbot icon** is available in the student dashboard for quick query assistance.  


## 🧰 Setup Instructions

### Prerequisites
- Java 17+  
- Maven  
- MySQL Server  
- IDE (Eclipse or VS Code with Spring Boot extension)

### Steps to Run
1. Clone the repository  
   ```bash
   git clone https://github.com/<your-username>/<your-repo-name>.git

2. Open the project in Eclipse or VS Code.

3. Update application.properties with your own MySQL credentials:

   spring.datasource.url=jdbc:mysql://localhost:3306/hostel_management
   
   spring.datasource.username=root
   
   spring.datasource.password=your_password

5. Run the project using:

   mvn spring-boot:run

6. Access the application at:
   
👉 http://localhost:8080/


## 🔮 Future Enhancements

- Add online room change request system  
- Integrate a smarter NLP-based chatbot  
- Implement payment gateway (Razorpay / Stripe)  
- Add analytics dashboard for admin (room occupancy, fee stats, etc.)  
- Improve UI with responsive design and animations  
- Role-based access control for more security  


## 👩‍💻 Author

**Kavya M.J**  
🎓 Java Developer | Passionate about full-stack and AI-integrated applications  
📫 LinkedIn: www.linkedin.com/in/03kaviya01
📫 Email: mjkaviya0307@gmail.com


## 📜 License

This project is licensed under the **MIT License** – feel free to use, modify, and enhance it.









