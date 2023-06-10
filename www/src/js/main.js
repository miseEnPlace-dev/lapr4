const RETRY_TIMEOUT = 5000;
const ERROR_TIMEOUT = 100;
const REFRESH_TIMEOUT = 2000;
const MAX_TIMEOUT = 15000;

const retry = () => {
  boardContainer.innerHTML = "Server timeout, still trying...";
  boardContainer.className = "text-red-500 text-8xl";
  setTimeout(getData, ERROR_TIMEOUT);
};

const error = () => {
  boardContainer.innerHTML = "No server reply, still trying...";
  boardContainer.className = "text-red-500 text-8xl";
  setTimeout(getData, RETRY_TIMEOUT);
};

const boardContainer = document.querySelector("#board");

function getData(boardId) {
  const request = new XMLHttpRequest();
  request.ontimeout = retry();
  request.onerror = error();
  request.timeout = MAX_TIMEOUT;

  request.onload = () => {
    const response = this.response;

    // boardContainer.innerHTML = "Success!";
    boardContainer.className = "text-black";
    setTimeout(getData, REFRESH_TIMEOUT);
  };

  request.open("GET", `/api/board/${boardId}`, true);
  request.send();
}

function selectBoard() {
  const request = new XMLHttpRequest();
  request.ontimeout = retry();
  request.onerror = error();
  request.timeout = MAX_TIMEOUT;

  request.onload = () => {
    const response = this.response;

    // boardContainer.innerHTML = "Success!";
    boardContainer.className = "text-black";
    setTimeout(getData, REFRESH_TIMEOUT);
  };

  request.open("GET", `/api/board`, true);
  request.send();
}
