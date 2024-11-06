<%@ include file="../common/top.jsp"%>
<body>
    <h2>User Logs for ${sessionScope.loginAccount.username}</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>User Name</th>
            <th>Action Type</th>
            <th>Action Details</th>
            <th>Request Time</th>
        </tr>
        <c:forEach var="log" items="${sessionScope.logList}">
            <tr>
                <td>${log.id}</td>
                <td>${log.userName}</td>
                <td>${log.actionType}</td>
                <td>${log.actionDetails}</td>
                <td><fmt:formatDate value="${log.requestTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
        </c:forEach>
    </table>

<%@ include file="../common/bottom.jsp"%>