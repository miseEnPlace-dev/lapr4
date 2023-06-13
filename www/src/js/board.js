const RETRY_TIMEOUT = 5000;
const ERROR_TIMEOUT = 2000;
const REFRESH_TIMEOUT = 2000;
const MAX_TIMEOUT = 15000;

function getData() {
  /** @type XMLHttpRequest */
  let request;

  try {
    request = new XMLHttpRequest();
  } catch (error) {
    throw new Error("Could not create HTTP request object.");
  }

  const boardContainer = document.querySelector("#container");
  const boardName = document.querySelector("#board-name");

  request.onload = () => {
    /** @type {{archived: boolean, columns: {number: number, title: string}[], rows: {number: number, title: string}[], id: string, owner: {username: string, name: string, email: string}, permissions: {createdAt: string, type: string, updatedAt: string, user: {username: string, name: string, email: string}}[], title}} */
    const data = JSON.parse(request.responseText);
    boardName.innerHTML = data.title;
  };

  request.ontimeout = () => {
    boardContainer.innerHTML = "Server timeout, still trying...";
    boardContainer.className = "text-red-500 text-8xl";
    setTimeout(getData, ERROR_TIMEOUT);
  };
  request.onerror = () => {
    boardContainer.innerHTML = "No server reply, still trying...";
    boardContainer.className = "text-red-500 text-8xl";
    setTimeout(getData, RETRY_TIMEOUT);
  };

  const id = window.location.search.split("=")[1];
  request.open("GET", `/api/board/${id}`, true);
  request.timeout = MAX_TIMEOUT;
  request.send(null);

  /** @type XMLHttpRequest */
  let authRequest;

  try {
    authRequest = new XMLHttpRequest();
  } catch (error) {
    throw new Error("Could not create HTTP Request object.");
  }

  const username = document.querySelector("#user");

  authRequest.onload = () => {
    /** @type {{username: string, name: string, email: string, roles: string[]}} */
    const data = JSON.parse(authRequest.responseText);

    username.innerHTML = `Welcome, ${data.username}!`;
  };

  authRequest.ontimeout = () => {
    boardContainer.innerHTML = "Server timeout, still trying...";
    boardContainer.className = "text-red-500 text-8xl";
    setTimeout(getData, ERROR_TIMEOUT);
  };
  authRequest.onerror = () => {
    boardContainer.innerHTML = "No server reply, still trying...";
    boardContainer.className = "text-red-500 text-8xl";
    setTimeout(getData, RETRY_TIMEOUT);
  };

  authRequest.open("GET", "/api/session", true);
  authRequest.timeout = MAX_TIMEOUT;
  authRequest.send(null);
}
