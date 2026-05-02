<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Add Book</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #000; color: #fff; display: flex; justify-content: center; align-items: center; min-height: 100vh; padding: 20px; }
        .form-card { background: #111; padding: 34px; border-radius: 14px; width: 100%; max-width: 540px; border-top: 4px solid #fff; }
        .form-card h2 { color: #fff; margin-bottom: 8px; font-size: 1.9em; display: flex; align-items: center; gap: 10px; }
        .form-card p { color: #bbb; margin-bottom: 22px; font-size: 0.95em; }
        .form-group { margin-bottom: 16px; }
        label { display: block; margin-bottom: 8px; color: #ddd; font-weight: 600; font-size: 0.95em; }
        input, select { width: 100%; padding: 12px 14px; margin-top: 5px; border: 1px solid #333; border-radius: 6px; font-size: 1em; background: #0f0f0f; color: #fff; }
        input:focus, select:focus { outline: none; border-color: #777; }
        input::placeholder { color: #7a7a7a; }
        button { margin-top: 22px; width: 100%; padding: 12px; background: #fff; color: #000; border: none; border-radius: 6px; cursor: pointer; font-size: 1.02em; font-weight: 700; }
        button:hover { opacity: 0.95; }
        .back-link { display: block; margin-top: 18px; text-align: center; color: #ccc; text-decoration: none; font-weight: 600; }
    </style>
</head>
<body>
<div class="form-card">
    <h2>Add New Book</h2>
    <p>Create a new book entry in your library</p>
    <form action="/books/add" method="post">
        <div class="form-group">
            <label for="title">Book Title</label>
            <input type="text" id="title" name="title" placeholder="Enter book title" required/>
        </div>
        <div class="form-group">
            <label for="genre">Genre</label>
            <input type="text" id="genre" name="genre" placeholder="e.g., Fiction, Mystery, Romance"/>
        </div>
        <div class="form-group">
            <label for="year">Published Year</label>
            <input type="number" id="year" name="publishedYear" placeholder="e.g., 2024" min="1000" max="9999"/>
        </div>
        <div class="form-group">
            <label for="price">Price (₹)</label>
            <input type="number" id="price" step="0.01" name="price" placeholder="0.00" min="0"/>
        </div>
        <div class="form-group">
            <label for="author">Select Author</label>
            <select id="author" name="authorId" required>
                <option value="">-- Choose an author --</option>
                <c:forEach var="author" items="${authors}">
                    <option value="${author.id}">${author.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit">Save Book</button>
    </form>
    <a href="/books" class="back-link">Back to Books</a>
</div>
</body>
</html>