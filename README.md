
# Spring Boot Order Management (With One-to-Many Relationship)

This is a simple Spring Boot project that demonstrates basic CRUD operations using Spring Data JPA.
It focuses on managing customer orders and removing failed orders using an in-memory copy to avoid `ConcurrentModificationException`.

---

## ⚙️ Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (or MySQL, etc.)
- Maven

---

## 🧱 Entity Relationship

### One-to-Many: Customer -> Orders

- One customer can have multiple orders.
- This relationship is implemented using `@OneToMany` annotation in the Customer entity.

```java
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> list = new ArrayList<>();
}
```

```java
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
```

---

## 📌 API Endpoints

### ➕ Place a New Order

- **POST** `/orders`

#### Sample Request Body

```json
{
  "name": "Sharad Kamble",
  "list": [
    { "productName": "Laptop", "status": "success" },
    { "productName": "Phone", "status": "failed" }
  ]
}
```

---

### ❌ Delete Failed Orders by Customer ID

- **DELETE** `/order/{cid}`

Deletes only the orders where `status = "failed"` for the specified customer.

---

## 🧪 Dummy Flow

1. Post a customer with 2-3 orders using `/orders`.
2. Call `/order/{cid}` with the customer ID.
3. All orders with `status = failed` will be removed, while successful orders will remain.

---

## ✅ Key Benefit

Using a copy of the list while iterating avoids `ConcurrentModificationException`.

```java
List<Order> copyList = new ArrayList<>(c.getList());
for (Order o : copyList) {
    if (o.getStatus().equals("failed")) {
        c.getList().remove(o);
    }
}
```

---

## 📂 Folder Structure

```
├── controller
├── service
├── repository
├── entity
│   ├── Customer.java
│   └── Order.java
└── SpringBootMainApp
```

---

## 🔗 Optional Enhancements

- Add Swagger UI for API documentation
- Connect to MySQL/PostgreSQL
- Add validation and exception handling

---

## 🧑‍💻 Author

Sharad Kamble
