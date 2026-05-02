<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Author</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #000; color: #fff; display: flex; justify-content: center; align-items: center; min-height: 100vh; padding: 20px; }
        .form-card { background: #111; padding: 34px; border-radius: 14px; width: 100%; max-width: 540px; border-top: 4px solid #fff; }
        .form-card h2 { color: #fff; margin-bottom: 8px; font-size: 1.9em; display: flex; align-items: center; gap: 10px; }
        .form-card p { color: #bbb; margin-bottom: 22px; font-size: 0.95em; }
        .form-group { margin-bottom: 16px; }
        label { display: block; margin-bottom: 8px; color: #ddd; font-weight: 600; font-size: 0.95em; }
        input { width: 100%; padding: 12px 14px; margin-top: 5px; border: 1px solid #333; border-radius: 6px; font-size: 1em; background: #0f0f0f; color: #fff; }
        input:focus { outline: none; border-color: #777; box-shadow: none; }
        input::placeholder { color: #7a7a7a; }
        button { margin-top: 22px; width: 100%; padding: 12px; background: #fff; color: #000; border: none; border-radius: 6px; cursor: pointer; font-size: 1.02em; font-weight: 700; }
        button:hover { opacity: 0.95; }
        .back-link { display: block; margin-top: 18px; text-align: center; color: #ccc; text-decoration: none; font-weight: 600; }
    </style>
</head>
<body>
<div class="form-card">
    <h2>Add New Author</h2>
    <p>Register a new talented author</p>
    <form action="/authors/add" method="post">
        <div class="form-group">
            <label for="name">Author Name</label>
            <input type="text" id="name" name="name" placeholder="Full name" required/>
        </div>
        <div class="form-group">
            <label for="nationality">Nationality</label>
            <input type="text" id="nationality" name="nationality" placeholder="e.g., Indian, American"/>
        </div>
        <div class="form-group">
            <label for="email">Email Address</label>
            <input type="email" id="email" name="email" placeholder="author@example.com"/>
        </div>
        <button type="submit">Save Author</button>
    </form>
    <a href="/authors" class="back-link">Back to Authors</a>
</div>
</body>
</html>