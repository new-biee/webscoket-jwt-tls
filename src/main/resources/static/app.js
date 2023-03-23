let stompClient = null;

function setConnected(connected) {
  $('#connect').prop('disabled', connected);
  $('#disconnect').prop('disabled', !connected);
  if (connected) {
    $('#conversation').show();
  } else {
    $('#conversation').hide();
  }
  $('#greetings').html('');
}

function connect() {
  let accessToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLG1vbml0b3JpbmcuKixhZG1pbi4qLHZlcn' +
    'Npb24uKiIsImlkIjoiMTY3NzYzODQwNzY3MCIsImV4cCI6MTY3NzcyNDgwN30.A5vmKv7ZrEZn9Bef9R40VI9VjdQII8gro8USdXpnBuWcdyxKZIx3X' +
    'ykuuSOX3EuGTUTBNCqMWs4gX1NR1NMrHA';

  let socket = new SockJS('https://localhost:8090/stomp', null, {
    headers: { 'X-Authorization': 'Bearer ' + accessToken }
  });
  stompClient = Stomp.over(socket);
  stompClient.connect({ 'X-Authorization': 'Bearer ' + accessToken }, function(frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/destination', function(greeting) {
      showGreeting(JSON.parse(greeting.body));
    });
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log('Disconnected');
}

function sendName() {
  stompClient.send('/topic/destination', {}, JSON.stringify({ 'name': $('#name').val() }));
}

function showGreeting(message) {
  $('#greetings').append('<tr><td>' + message.message + '</td></tr>');
}

$(function() {
  $('form').on('submit', function(e) {
    e.preventDefault();
  });
  $('#connect').click(function() {
    connect();
  });
  $('#disconnect').click(function() {
    disconnect();
  });
  $('#send').click(function() {
    sendName();
  });
});