<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/main.js" />"></script>
<script src="https://unpkg.com/vue"></script>
<meta charset="utf-8">
<title>bla</title>

<script>
	function myScript() {
		new Vue({
			el : '#exe',
			data : function() {
				return {
					items : [ {
						message : 'Foo'
					}, {
						message : 'Bar'
					} ]
				}
			}
		})
	}
</script>
</head>
<body onload="myScript()">
	<ul id="exe">
		<li v-for="item in items">{{ item.message }}</li>
	</ul>

	<button onclick="exe.items.push({ message: 'Baz' })"></button>


	<!--<div id="components-demo">
		<div class="wrapper">
			<chessfield></chessfield>
			<chessfield></chessfield>
			<chessfield></chessfield>
			<chessfield></chessfield>
			<chessfield></chessfield>
			<chessfield></chessfield>
			<chessfield></chessfield>
			<chessfield></chessfield>
			<chessfield></chessfield>
			
		</div>
	</div>
-->

	<div id="app">
		<p>{{ message }}</p>
	</div>

	<div class="wrapper" id="wrapper"></div>
	<div class="box black-piece"></div>
</body>
</html>