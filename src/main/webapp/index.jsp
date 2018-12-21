<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/main.js" />" defer></script>
<script src="<c:url value="/resources/js/vue-drag-drop.browser.js"/>"></script>
<script src="<c:url value="/resources/js/vue.js" />"></script>

<meta charset="utf-8">
<title>Thrombose</title>
</head>

<body>
	<div id="thrombose-wrapper">
		<div id="controls"></div>
		<div id="chessboard"></div>
		<div id="chat"></div>
	</div>
</body>

</html>
