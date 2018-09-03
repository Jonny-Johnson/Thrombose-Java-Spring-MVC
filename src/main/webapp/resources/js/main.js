window
		.addEventListener(
				"DOMContentLoaded",
				function() {
					createBoard();
					beAlert();

					new Vue({
						el : '#app',
						data : {
							message : 'Hello Vue.js!'
						}
					})

					// Define a new component called button-counter
					Vue
							.component(
									'button-counter',
									{
										data : function() {
											return {
												count : 0
											}
										},
										template : '<button v-on:click="count++">You clicked me {{ count }} times.</button>'
									})

					Vue.component('chessfield', {
						data : {
							color : "white"
						},
						template : '<div class="box" :class="color"></div>'

					})

					new Vue({
						el : '#components-demo'
					})



				});

function createBoard() {
	var i;
	var j;
	var bw = [ 'white', 'black' ]
	var switchvar = 0;
	for (i = 1; i <= 8; i++) {
		for (j = 1; j <= 8; j++) {
			var identifier = i + ',' + j;
			document.getElementById("wrapper").innerHTML += '<div class="box '
					+ bw[switchvar] + ' ' + identifier + '" id="' + identifier
					+ '">' + identifier
			'</div>';
			if (j != 8) {
				switchvar = bwSwitcher(switchvar);
			}
		}
	}
}

function beAlert() {
	let boxes = document.querySelectorAll(".box");
	Array.from(boxes, function(box) {
		box.addEventListener("click", function() {
			alert(this.classList[2]);
		});
	});
}

function bwSwitcher(switching) {
	if (switching == 0) {
		return 1;
	} else {
		return 0;
	}
}