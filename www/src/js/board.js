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
  const boardTable = document.querySelector("#board");

  request.onload = () => {
    /** @type {{archived: boolean, columns: {number: number, title: string}[], rows: {number: number, title: string}[], id: string, owner: {username: string, name: string, email: string}, permissions: {createdAt: string, type: string, updatedAt: string, user: {username: string, name: string, email: string}}[], title}} */
    const data = JSON.parse(request.responseText);
    boardName.innerHTML = data.title;

    const { columns, rows } = data;

    const tr = document.createElement("tr");
    const firstTh = document.createElement("th");
    firstTh.innerHTML = "";
    firstTh.className =
      "text-center py-6 text-2xl font-bold w-72 h-52 border-b-2 border-r-2";
    tr.appendChild(firstTh);

    for (let i = 0; i < columns.length; i++) {
      const col = columns[i];
      const th = document.createElement("th");
      th.innerHTML = col.title;

      th.className = "text-center py-6 text-2xl h-52 w-72 font-bold border-b-2";
      if (i !== columns.length - 1) th.className += " border-r-2";

      tr.appendChild(th);
    }

    boardTable.appendChild(tr);

    for (let i = 0; i < rows.length; i++) {
      const row = rows[i];

      const tr = document.createElement("tr");
      const th = document.createElement("th");

      th.innerHTML = row.title;
      th.className = "text-center py-6 text-2xl font-bold h-52 w-72";
      console.log({ i, th });
      if (i !== rows.length - 1) th.className += " border-b-2";

      tr.appendChild(th);

      for (let j = 0; j < columns.length; j++) {
        const td = document.createElement("td");
        td.className = "text-center py-6 text-2xl font-bold";

        if (j === columns.length - 1 && i === rows.length - 1) continue;
        else if (j === columns.length - 1) td.className += " border-b-2";
        else if (i === rows.length - 1) td.className += " border-x-2";
        else td.className += " border-b-2 border-x-2";

        td.innerHTML = "";
        tr.className = "h-52 w-72";
        tr.appendChild(td);
      }

      boardTable.appendChild(tr);
    }

    setTimeout(getData, REFRESH_TIMEOUT);
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

    console.log(data);
    online.innerHTML = `Currently active: ${data.online}`;
    setTimeout(getOnlineUsers, REFRESH_TIMEOUT);
  };

  onlineUsersRequest.ontimeout = () => {
    online.innerHTML = "Server timeout, still trying...";
    online.className = "text-red-500 text-8xl";
    setTimeout(getData, ERROR_TIMEOUT);
  };
  onlineUsersRequest.onerror = () => {
    online.innerHTML = "No server reply, still trying...";
    online.className = "text-red-500 text-8xl";
    setTimeout(getData, RETRY_TIMEOUT);
  };

  onlineUsersRequest.open("GET", "/api/online", true);
  onlineUsersRequest.timeout = MAX_TIMEOUT;
  onlineUsersRequest.send(null);
}
