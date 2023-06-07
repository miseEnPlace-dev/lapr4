const RETRY_TIMEOUT = 5000;
const ERROR_TIMEOUT = 100;
const REFRESH_TIMEOUT = 2000;
const MAX_TIMEOUT = 15000;

function getData() {
  const request = new XMLHttpRequest();
  const boardContainer = document.querySelector("#board");

  request.onload = function () {
    const response = this.response;

    //boardContainer.innerHTML = "Success!";
    boardContainer.className = "text-black";
    setTimeout(getData, REFRESH_TIMEOUT);
  };

  request.ontimeout = function () {
    boardContainer.innerHTML = "Server timeout, still trying ...";
    boardContainer.className = "text-red-500";
    setTimeout(getData, ERROR_TIMEOUT);
  };

  request.onerror = function () {
    boardContainer.innerHTML = "No server reply, still trying ...";
    boardContainer.className = "text-red-500";
    setTimeout(getData, RETRY_TIMEOUT);
  };

  request.open("GET", "/api/board", true);
  request.timeout = MAX_TIMEOUT;
  request.send();
}
