const RETRY_TIMEOUT = 3000;
const ERROR_TIMEOUT = 1000;
const REFRESH_TIMEOUT = 2000;
const MAX_TIMEOUT = 10000;

const boardSelector = document.querySelector("#board-selector");
let t;

function showError(message) {
  const error = document.querySelector("#error");

  error.innerHTML = `
    <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-y relative w-1/2 mx-auto rounded" role="alert">
      <strong class="font-bold">Error!</strong>
      <span class="block sm:inline">${message}</span>
      <div class="animate-spin duration-150 transition-all">↻</div>
    </div>
  `;
}

function clearError() {
  const error = document.querySelector("#error");
  error.innerHTML = "";
}

function getData() {
  console.log("> Getting data");
  /** @type XMLHttpRequest */
  let request;
  clearTimeout(t);

  try {
    request = new XMLHttpRequest();
  } catch (error) {
    throw new Error("Could not create HTTP request object.");
  }

  request.onload = () => {
    clearError();

    /** @type {{archived: boolean, columns: {number: number, title: string}[], rows: {number: number, title: string}[], id: string, owner: {username: string, name: string, email: string}, permissions: {createdAt: string, type: string, updatedAt: string, user: {username: string, name: string, email: string}}[], title}[]} */
    const data = JSON.parse(request.responseText);

    for (const board of data)
      boardSelector.innerHTML += `<option value=${board.id} class="dark:bg-slate-600 bg-slate-200 h-12 text-md">${board.title}</option>`;
  };

  request.ontimeout = () => {
    showError("Server timeout, still trying...");
    t = setTimeout(getData, ERROR_TIMEOUT);
  };
  request.onerror = () => {
    showError("No server reply, still trying...");
    t = setTimeout(getData, RETRY_TIMEOUT);
  };

  request.open("GET", "/api/board", true);
  request.timeout = MAX_TIMEOUT;
  request.send(null);

  getAuthenticatedUser();
  getOnlineUsers();
}

function getAuthenticatedUser() {
  console.log("> Getting authenticated user");

  /** @type XMLHttpRequest */
  let authRequest;
  clearTimeout(t);

  try {
    authRequest = new XMLHttpRequest();
  } catch (error) {
    throw new Error("Could not create HTTP Request object.");
  }

  const username = document.querySelector("#user");

  authRequest.onload = () => {
    clearError();

    /** @type {{username: string, name: string, email: string, roles: string[]}} */
    const data = JSON.parse(authRequest.responseText);

    username.innerHTML = `Welcome, ${data.username}!`;
  };

  authRequest.ontimeout = () => {
    showError("Server timeout, still trying...");
    t = setTimeout(getAuthenticatedUser, ERROR_TIMEOUT);
  };
  authRequest.onerror = () => {
    showError("No server reply, still trying...");
    t = setTimeout(getAuthenticatedUser, RETRY_TIMEOUT);
  };

  authRequest.open("GET", "/api/session", true);
  authRequest.timeout = MAX_TIMEOUT;
  authRequest.send(null);
}

function getOnlineUsers() {
  console.log("> Getting online users");

  /** @type XMLHttpRequest */
  let onlineUsersRequest;
  clearTimeout(t);

  try {
    onlineUsersRequest = new XMLHttpRequest();
  } catch (error) {
    throw new Error("Could not create HTTP Request object.");
  }

  const online = document.querySelector("#online");

  onlineUsersRequest.onload = () => {
    clearError();

    /** @type {{online: number}} */
    const data = JSON.parse(onlineUsersRequest.responseText);

    online.innerHTML = `Currently active: ${data.online}`;
    setTimeout(getOnlineUsers, REFRESH_TIMEOUT);
  };

  onlineUsersRequest.ontimeout = () => {
    showError("Server timeout, still trying...");
    setTimeout(getOnlineUsers, ERROR_TIMEOUT);
  };
  onlineUsersRequest.onerror = () => {
    showError("No server reply, still trying...");
    setTimeout(getOnlineUsers, RETRY_TIMEOUT);
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
