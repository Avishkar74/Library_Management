<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Authors Library</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #000; color: #fff; min-height: 100vh; padding: 30px 20px; }
        .container { max-width: 1200px; margin: 0 auto; }
        .header { background: #111; padding: 30px; border-radius: 10px; margin-bottom: 24px; border-left: 6px solid #fff; }
        .header h1 { color: #fff; font-size: 2.2em; margin-bottom: 8px; display: flex; align-items: center; gap: 12px; }
        .header p { color: #ccc; font-size: 1em; }
        .action-buttons { display: flex; gap: 10px; margin-top: 14px; flex-wrap: wrap; }
        .btn { padding: 10px 20px; border-radius: 6px; text-decoration: none; font-weight: 600; font-size: 0.95em; display: inline-flex; align-items: center; gap: 8px; transition: all 0.15s ease; cursor: pointer; border: 1px solid #444; background: #fff; color: #000; }
        .btn:hover { opacity: 0.9; transform: translateY(-1px); }
        .btn-ghost { background: transparent; color: #fff; border: 1px solid #666; }
        .btn-edit { background: #fff; color: #000; padding: 6px 12px; font-size: 0.9em; border-radius: 6px; }
        .alert { padding: 12px 16px; border-radius: 6px; margin-bottom: 18px; font-weight: 500; display: flex; align-items: center; gap: 10px; }
        .alert-success { background: #fff; color: #000; }
        .alert-danger { background: #222; color: #fff; border: 1px solid #444; }
        .table-wrapper { background: #111; border-radius: 8px; overflow: hidden; }
        table { width: 100%; border-collapse: collapse; }
        thead { background: #000; color: #fff; }
        th { padding: 14px; text-align: left; font-weight: 600; letter-spacing: 0.5px; border-bottom: 1px solid #222; }
        td { padding: 12px 14px; border-bottom: 1px solid #222; color: #ddd; }
        tbody tr { transition: background 0.15s ease; }
        tbody tr:hover { background: #0d0d0d; }
        .no-authors { text-align: center; padding: 40px; color: #aaa; font-size: 1.05em; }
        a { color: inherit; }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Authors Directory</h1>
        <p>Discover and manage talented authors</p>
        <div class="action-buttons">
            <a class="btn btn-secondary" href="/authors/add">Add New Author</a>
            <a class="btn btn-primary" href="/books">Books</a>
        </div>
    </div>

    <c:if test="${not empty success}"><div class="alert alert-success">${success}</div></c:if>
    <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

    <div class="table-wrapper">
        <c:if test="${empty authors}">
            <div class="no-authors">No authors found. <a href="/authors/add" style="color: #84fab0; text-decoration: underline;">Add one now!</a></div>
        </c:if>
        <c:if test="${not empty authors}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Nationality</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="author" items="${authors}">
                        <tr>
                            <td><strong>${author.id}</strong></td>
                            <td>${author.name}</td>
                            <td>${author.nationality}</td>
                            <td>${author.email}</td>
                            <td><a class="btn btn-edit" href="/authors/edit/${author.id}">Edit</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>