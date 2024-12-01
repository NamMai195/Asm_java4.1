var websocket = null;

function init() {
	// Mở kết nối đến chat server
	websocket = new WebSocket('ws://localhost:8080/websocket/text/chat');
	// Xử lý sự kiện chấp nhận kết nối
	websocket.onopen = function(resp) {
		console.log("onopen", resp);
	}
	// Xử lý sự kiện nhận tin nhắn chat
	websocket.onmessage = function(resp) {
		var message = resp.data;
		var html = document.getElementById('messages').innerHTML;
		document.getElementById('messages').innerHTML = `${html}<p>${message}</p>`;
		console.log("onmessage", resp.data);
	}
	// Xử lý sự kiện lỗi
	websocket.onerror = function(resp) {
		alert('An error occured, closing application');
		console.log("onerror", resp);
	}
	// Xử lý sự kiện đóng kết nối
	websocket.onclose = function(resp) {
		alert(resp.reason || 'Goodbye');
		console.log("onclose", resp);
	}
}
// Gửi tin nhắn chat đến server
function send() {
	var message = document.getElementById("message").value;
	websocket.send(message);
	document.getElementById("message").value = '';
}