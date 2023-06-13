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
  const boardSelector = document.querySelector("#board-selector");

  request.onload = () => {
    /** @type {{archived: boolean, columns: {number: number, title: string}[], rows: {number: number, title: string}[], id: string, owner: {username: string, name: string, email: string}, permissions: {createdAt: string, type: string, updatedAt: string, user: {username: string, name: string, email: string}}[], title}[]} */
    const data = JSON.parse(request.responseText);

    for (const board of data)
      boardSelector.innerHTML += `<option value=${board.id} class="dark:bg-slate-600 bg-slate-200 h-12 text-md">${board.title}</option>`;
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

  request.open("GET", "/api/board", true);
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

const button = document.querySelector("#confirm-button");
button.addEventListener("click", (e) => {
  e.preventDefault();
  const boardSelector = document.querySelector("#board-selector");
  const boardName = boardSelector.options[boardSelector.selectedIndex].value;
  window.location.href = `/board.html?board=${boardName}`;
});
