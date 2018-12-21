Vue.use(VueDragDrop)

//Open the web socket connection to the server
var socketConn = new WebSocket('ws://localhost:8080/thrombose/socketHandler');


var isWhiteField = true;
const EventBus = new Vue();

document.addEventListener("DOMContentLoaded", function(event) {
    console.log("DOM fully loaded and parsed");    

  	});

//_____ _                   _                         _ 
///  __ \ |                 | |                       | |
//| /  \/ |__   ___  ___ ___| |__   ___   __ _ _ __ __| |
//| |   | '_ \ / _ \/ __/ __| '_ \ / _ \ / _` | '__/ _` |
//| \__/\ | | |  __/\__ \__ \ |_) | (_) | (_| | | | (_| |
// \____/_| |_|\___||___/___/_.__/ \___/ \__,_|_|  \__,_|
//                                                     
//                                                     





Vue.component('chessfield', {
	data: {
		
	}		
	,	
	props: ['row', 'column', 'color'],	
	template: `
		<drop v-on:drop="handleDrops" class="field unselectable" v-bind:class="color" draggable="false">
			<template v-if="getPiece() !== undefined"> 
				<drag class="piece" v-bind:class="[ getPiece().color ]" :transfer-data="getPieceDragMessage()">
					<div v-if="getPiece().isQueen" class="queen"></div>
				</drag>
			</template>
		</drop>
	`,
	methods: { 
		getPiece: function() {
			if (this.$parent.pieces[this.field] == undefined) {
				return undefined;
			} else {
				return this.$parent.pieces[this.field];
			}
		},			
		getPieceDragMessage: function() {
				return this.field;				
		},
		
		handleDrops(data, event) {
			console.log(`You dropped with data: ${data} on ${this.field}`);
			
			// send out move notification
			EventBus.$emit('pieceMove', data, this.field);
			console.log('event bus fired');
			

			
			// make the move
			// dodo: really do this here or wait for the server to confirm the
			// move?
			
			thrombose.$set(thrombose.pieces, this.field, thrombose.pieces[data]);
			thrombose.$set(thrombose.pieces, data, undefined);
			this.$forceUpdate();
		},//
		handleUpdateField(edata, efield) {
			// console.log('eventbus works!');
			// dodo: really do this here or wait for the server to confirm the
			// move?
			this.$forceUpdate();
		}
	},
	computed: {
		field: function() {
			return String(`${this.column}${this.row}`);			
		}
	},	
	created: function() {
		// listen for moves
		// dodo: really do this here or wait for the server to confirm the move?
		EventBus.$on('pieceMove', this.handleUpdateField);		
	}
})


var thrombose = new Vue({
	el: '#chessboard',
	data: {
		rows: [8, 7, 6, 5, 4, 3, 2, 1],
		columns: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'],
		fields: [],
		pieces: [],
		moveID: 0,	
	},
	
	// build the chessboard including markings
	template: `
		<div>
			<h1>Thrombose!</h1>		
			<div class="board-wrapper">
	
				<template v-for="row in rows">
					<div>{{ row }}</div>
				</template>
				
				<div></div>
				
				<template v-for="column in columns">
					<div>{{ column }}</div>
				</template>	
				
				<div class="board">
					<template v-for="row in rows">		 			
						<template v-for="column in columns">						
							<chessfield v-bind:row="row" v-bind:column="column" v-bind:color="fields[column+row]">
							</chessfield> 
						</template>
					</template>
				</div>
			</div>
		</div>	
	`,
	methods: {
		toggle: function (row, column) {
			var returnColor;
			if (column != 'A') {
				isWhiteField = !isWhiteField;
			}
			if (isWhiteField) {
				returnColor = 'white-field';				
			} else {
				returnColor = 'black-field';
			}						
			this.fields[String(`${column}${row}`)] = returnColor;			
			return returnColor;
		},
		checkLegalMove: function(edata, efield) {	
			console.log('checking along');			
			// send user input to server for validation
			// undo move if necessary
			
			this.moveID++;
			//TODO: actually store the move in a move queue before sending it to the server + validating the response
			
			jsonData = JSON.stringify({'action': 'move', 'moveID': this.moveID,'move': edata + ';' + efield});
			console.log('move to send: ' + jsonData);
			socketConn.send(jsonData);
		}
	},
	created: function () {		
		console.log('start chessboard created');		
		
		// listen for moves
		EventBus.$on('pieceMove', this.checkLegalMove);
		
		// setup field colors and initial pieces
		// this happens before the components are created, no need to notify
		// them of the changed pieces
		this.pieces = [];
		var i, j;
		for (i = 0; i < this.rows.length; i++) {
			for (j = 0; j < this.columns.length; j++) {				
				var field = String(`${this.columns[j]}${this.rows[i]}`);
				
				// field colors
				this.fields[field] = this.toggle(this.rows[i], this.columns[j]);
				
				// initial pieces
				if (this.fields[field] == 'black-field' && [8, 7, 6].indexOf(this.rows[i]) >= 0) {
					this.pieces[field] = { color: 'black-piece', isQueen: false };
				}
				if (this.fields[field] == 'black-field' && [1, 2, 3].indexOf(this.rows[i]) >= 0) {
					this.pieces[field] = { color: 'white-piece', isQueen: false };
				}
			}
		}	
		console.log('end chessboard created');
	},
	mounted: function () {
		console.log('chessboard mounted');		
	}
})



//   _____ _           _   
//  /  __ \ |         | |  
//  | /  \/ |__   __ _| |_ 
//  | |   | '_ \ / _` | __|
//  | \__/\ | | | (_| | |_ 
//   \____/_| |_|\__,_|\__|
//                         
//                         




var chat = new Vue({
	el: '#chat',
	data: {
		history: '',
		txtMessage: '',
		txtName: ''
	},
	template: `
		<div>
			<h1>Chat</h1>
			<div>
				<input v-model="txtName" @keyup.enter="changeName"></input>
				<button v-on:click="changeName">Change Name</button>				
			</div>
			<textarea rows="30" cols="50" readonly="readonly">{{ history }}</textarea>
			
			<div>
				<input rows="4" cols="50" v-model="txtMessage" @keyup.enter="send"></input>				
				<button v-on:click="send">Send</button>
			</div>
			
		</div>		
	`,
	methods: {
		send: function() {
			if (this.txtMessage) {
				console.log('function send received ' + this.txtMessage);
			
				jsonData = JSON.stringify({'action': 'chatmessage', 'message': this.txtMessage});
				console.log('chat message to send: ' + jsonData);
				socketConn.send(jsonData);
				
				//socketConn.send('chatmessage:' + this.txtMessage);
				this.txtMessage = '';
			}			
		},
		changeName: function() {
			if (this.txtName) {
				console.log('function changename received ' + this.txtName);
			
				jsonData = JSON.stringify({'action': 'chatname', 'name': this.txtName});
				console.log('name change message to send: ' + jsonData);
				socketConn.send(jsonData);					
			}
		}		
	},
	created: function() {		
		//receives ALL websocket messages
		socketConn.onmessage = function(event) {
			console.log('message received: ' + event.data);
						
			jsObject = JSON.parse(event.data);
			console.log('action received: ' + jsObject.action)
			switch(jsObject.action) {
			case "chatmessage":
				chat.history += (jsObject.message + "\n");
				break;
			}			
						
		}
	}
})	
	

//
// THROMBOSE OPTIONS
//


var controls = new Vue({
	el: '#controls',
	data: {
		color: '',
		countPlayers: 0
	},
	template: `
		<div>		
			<h1>Controls</h1>
			Players: {{ countPlayers }}/2
			<div v-if="color == ''">
				Color: Not yet selected  
			</div>
			<div v-else>
				Color: You have the {{ color }} pieces
			</div>
		</div>		
	`,
	}
)








	
	
	
