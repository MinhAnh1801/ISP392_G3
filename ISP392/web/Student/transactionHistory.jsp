<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.List"%>
<%@page import="Model.Transactions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Transaction History</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">

        <style>
            /* Navbar styling */
            .navbar {
                background-color: #FF8C00; /* Orange color */
            }

            .navbar-brand {
                color: white;
                font-size: 24px;
                font-weight: bold;
            }

            /* Main content styling */
            .main-content {
                margin-top: 50px;
                text-align: center;
                font-family: Arial, sans-serif;
            }

            .header-title {
                font-size: 28px;
                font-weight: bold;
                color: #FF8C00; /* Main color */
                margin-bottom: 30px;
            }

            /* Table styling */
            .table-bordered th, .table-bordered td {
                border: 1px solid #FF8C00;
            }

            th {
                background-color: #FF8C00;
                color: white;
                font-size: 18px;
            }

            td {
                padding: 15px;
                font-size: 16px;
            }

            /* Footer styling */
            .footer {
                background-color: #FFA500;
                padding: 20px;
                text-align: center;
                font-size: 16px;
                color: white;
                margin-top: 40px;
                border-radius: 10px;
            }

            /* Container adjustments */
            .container {
                max-width: 800px;
                margin: 0 auto;
            }
        </style>
    </head>

    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="home">University Academic Portal</a>
            </div>
        </nav>

        <!-- Main content -->
        <div class="container main-content">
            <h1 class="header-title">Transaction History</h1>

            <!-- Filter Form -->
            <form action="transactionHistory" method="get" class="mb-4">
                <input type="hidden" name="action" value="filter" />

                <div class="row mb-3">
                    <div class="col-md-6">
                        <select name="transactionType" id="transactionType" class="form-select">
                            <option value="">All Types</option>
                            <option value="Fee" ${transactionType == 'Fee' ? 'selected' : ''}>Fee</option>
                            <option value="Other" ${transactionType == 'Other' ? 'selected' : ''}>Other</option>
                            <option value="Tuition" ${transactionType == 'Tuition' ? 'selected' : ''}>Tuition</option>
                        </select>
                    </div>
                    <div class="col-md-6 d-flex align-items-end">
                        <button type="submit" class="btn btn-primary">Filter</button>
                    </div>
                </div>
            </form>

            <!-- Transaction History Table -->
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Transaction ID</th>
                            <th>Amount (VND)</th>
                            <th>Transaction Type</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="transaction" items="${transactionList}">
                            <tr>
                                <td>${transaction.getId()}</td>
                                <td><fmt:formatNumber value="${transaction.getAmount()}"  /></td>
                                <td>${transaction.getTransaction_type()}</td>
                                <td>${transaction.getTransaction_date()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Phân trang đẹp hơn -->
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="transactionHistory?page=${currentPage - 1}&transactionType=${transactionType}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach var="i" begin="${beginPage}" end="${endPage}">
                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                            <a class="page-link" href="transactionHistory?page=${i}&transactionType=${transactionType}">${i}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${currentPage < totalPage}">
                        <li class="page-item">
                            <a class="page-link" href="transactionHistory?page=${currentPage + 1}&transactionType=${transactionType}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>

            <!-- Footer -->
            <div class="footer">
                © 2024 University Academic Portal. All rights reserved.
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
