const RETRY_TIMEOUT = 5000;
const ERROR_TIMEOUT = 2000;
const REFRESH_TIMEOUT = 2000;
const MAX_TIMEOUT = 15000;

const error = document.querySelector("#error");
const boardSelector = document.querySelector("#board-selector");

function getData() {
  /** @type XMLHttpRequest */
  let request;

  try {
    request = new XMLHttpRequest();
  } catch (error) {
    throw new Error("Could not create HTTP request object.");
  }

  request.onload = () => {
    /** @type {{archived: boolean, columns: {number: number, title: string}[], rows: {number: number, title: string}[], id: string, owner: {username: string, name: string, email: string}, permissions: {createdAt: string, type: string, updatedAt: string, user: {username: string, name: string, email: string}}[], title}[]} */
    const data = JSON.parse(request.responseText);

    for (const board of data)
      boardSelector.innerHTML += `<option value=${board.id} class="dark:bg-slate-600 bg-slate-200 h-12 text-md">${board.title}</option>`;
  };

  request.ontimeout = () => {
    error.innerHTML = "Server timeout, still trying...";
    error.className = "text-red-500 text-8xl";
    setTimeout(getData, ERROR_TIMEOUT);
  };
  request.onerror = () => {
    error.innerHTML = "No server reply, still trying...";
    error.className = "text-red-500 text-8xl";
    setTimeout(getData, RETRY_TIMEOUT);
  };

  request.open("GET", "/api/board", true);
  request.timeout = MAX_TIMEOUT;
  request.send(null);

  getAuthenticatedUser();
  getOnlineUsers();
}

function getAuthenticatedUser() {
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
    error.innerHTML = "Server timeout, still trying...";
    error.className = "text-red-500 text-8xl";
    setTimeout(getData, ERROR_TIMEOUT);
  };
  authRequest.onerror = () => {
    error.innerHTML = "No server reply, still trying...";
    error.className = "text-red-500 text-8xl";
    setTimeout(getData, RETRY_TIMEOUT);
  };

  authRequest.open("GET", "/api/session", true);
  authRequest.timeout = MAX_TIMEOUT;
  authRequest.send(null);
}

function getOnlineUsers() {
  /** @type XMLHttpRequest */
  let onlineUsersRequest;

  try {
    onlineUsersRequest = new XMLHttpRequest();
  } catch (error) {
    throw new Error("Could not create HTTP Request object.");
  }

  const online = document.querySelector("#online");

  onlineUsersRequest.onload = () => {
    /** @type {{online: number}} */
    const data = JSON.parse(onlineUsersRequest.responseText);

    online.innerHTML = `Currently active: ${data.online}`;
    setTimeout(getOnlineUsers, REFRESH_TIMEOUT);
  };

  onlineUsersRequest.ontimeout = () => {
    error.innerHTML = "Server timeout, still trying...";
    error.className = "text-red-500 text-8xl";
    setTimeout(getData, ERROR_TIMEOUT);
  };
  onlineUsersRequest.onerror = () => {
    error.innerHTML = "No server reply, still trying...";
    error.className = "text-red-500 text-8xl";
    setTimeout(getData, RETRY_TIMEOUT);
  };

  onlineUsersRequest.open("GET", "/api/online", true);
  onlineUsersRequest.timeout = MAX_TIMEOUT;
  onlineUsersRequest.send(null);
}

const button = document.querySelector("#confirm-button");
button.addEventListener("click", (e) => {
  e.preventDefault();
  const boardSelector = document.querySelector("#board-selector");
  const boardName = boardSelector.options[boardSelector.selectedIndex].value;
  if (boardName === "") return alert("Please select a board.");
  else window.location.href = `/board.html?board=${boardName}`;
});
