const RETRY_TIMEOUT = 3000;
const ERROR_TIMEOUT = 1000;
const REFRESH_TIMEOUT = 1000;
const REFRESH_ONLINE_TIMEOUT = 3000;
const MAX_TIMEOUT = 10000;

const id = window.location.search.split("=")[1];
const error = document.querySelector("#error");
const boardName = document.querySelector("#board-name");
const boardTable = document.querySelector("#board");
const userFilter = document.querySelector("#user-filter");

let authenticatedUser;
let t;
let userImages;

const N_OF_USER_IMAGES = 7;

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
  updateBoard();
  getAuthenticatedUser();
  getOnlineUsers();
}

let postIts = new Map();

function closeModal() {
  const body = document.querySelector("body");
  body.className = body.className.replace(" overflow-hidden", "");

  const modalContainer = document.querySelector("#modal-container");
  const modal = document.querySelector("#modal");

  modalContainer.remove();
  modal.remove();
}

/** @type {(postIts: { id: string, title: string, coordinates: { x: number, y: number }, state: string, boardId: string, owner: {username: string, name: string, email: string, roles: string[] }, createdAt: string, description: string | null, image: string | null }[])=>Map<string,string>} */
function assignUserImage(postIts) {
  /** @type {Map<string, string>} */
  const userImages = new Map();
  let i = 1;

  for (const postIt of postIts) {
    if (!userImages.has(postIt.owner.username))
      userImages.set(
        postIt.owner.username,
        `assets/users/user-${i++ % N_OF_USER_IMAGES}.png`
      );
  }

  return userImages;
}

function openModal(x, y) {
  /** @type {{ id: string, title: string, coordinates: { x: number, y: number }, state: string, boardId: string, owner: {username: string, name: string, email: string, roles: string[] }, createdAt: string, description: string | null, image: string | null }} */
  const postIt = postIts.get(`${x}-${y}`);
  const body = document.querySelector("body");

  body.className += " overflow-hidden";
  modalContainer = document.createElement("div");
  modalContainer.id = "modal-container";
  modalContainer.className =
    "z-30 fixed top-0 left-0 bg-gray-800 opacity-50 w-full h-full";

  const modal = document.createElement("div");
  modal.id = "modal";

  modal.className =
    "z-40 fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-white rounded-lg shadow-lg w-1/2";

  modalContainer.onclick = closeModal;

  modal.innerHTML = `
    <div class="z-50 flex flex-col gap-y-6 items-center justify-between w-full h-full rounded-lg bg-slate-200 dark:bg-slate-700 py-12 px-6">
      <h1 class="text-3xl font-bold text-center capitalize">${postIt.title}</h1>
      <div class="w-3/4 h-1 border-b-2 border-slate-300 dark:border-slate-600"></div>
      <div class="flex items-center justify-between px-8 w-full">
        <div class="flex items-center gap-x-4">
          <img src=${userImages.get(
            postIt.owner.username
          )} alt="User image" class="w-10 h-10 rounded-full">
          <span class="font-bold">
            ${postIt.owner.name}
          </span>
        </div>
        <span>
          Last update: ${postIt.createdAt}
        </span>
      </div>
      ${
        postIt.description
          ? `<p class="text-xl text-left self-start px-8">${postIt.description}</p>`
          : ""
      }

      ${
        postIt.image
          ? `
      <div class="flex flex-col items-center justify-center h-1/2">
        <img src="data:image/png;base64,${postIt.image}" alt="Post-it image" class="w-full h-full">
      </div>`
          : ""
      }
    </div>
`;

  body.appendChild(modal);
  body.appendChild(modalContainer);
}

function updateBoard() {
  console.log("> Getting board details");
  /** @type XMLHttpRequest */
  let request;
  clearTimeout(t);

  try {
    request = new XMLHttpRequest();
  } catch (e) {
    throw new Error("Could not create HTTP request object.");
  }

  request.onload = () => {
    clearError();
    /** @type {{archived: string | null, title: string, columns: {number: number, title: string}[], rows: {number: number, title: string}[], id: string, owner: {username: string, name: string, email: string}, permissions: {createdAt: string, type: string, updatedAt: string, user: {username: string, name: string, email: string}, postIts: { id: string, title: string, coordinates: { x: number, y: number }, state: string, boardId: string, owner: {username: string, name: string, email: string, roles: string[] }, createdAt: string, description: string | null, image: string | null}[] }}[]} */
    const data = JSON.parse(request.responseText);
    boardName.innerHTML = `${data.title} <span class="text-sm text-gray-500 dark:text-gray-400">by ${data.owner.name}</span>`;

    const { postIts: p, columns, rows, archived } = data;

    if (archived) {
      const archivedContainer = document.getElementById("archived")
        ? document.getElementById("archived")
        : document.createElement("div");
      archivedContainer.id = "archived";
      archivedContainer.className =
        "absolute top-16 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-white rounded-lg shadow-xl w-1/2 p-8 text-center bg-opacity-90 dark:bg-slate-700 dark:text-slate-200";
      archivedContainer.innerHTML = `<h1 class="text-3xl font-bold text-center capitalize">Archived at ${archived}</h1>`;
      document.querySelector("body").appendChild(archivedContainer);
    }

    boardTable.innerHTML = "";

    const postItsMap = new Map();
    postIts = postItsMap;
    userImages = assignUserImage(p);

    for (const postIt of p)
      postItsMap.set(
        `${postIt.coordinates.x - 1}-${postIt.coordinates.y - 1}`,
        postIt
      );

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
      if (i !== rows.length - 1) th.className += " border-b-2";

      tr.appendChild(th);

      for (let j = 0; j < columns.length; j++) {
        const td = document.createElement("td");
        td.className = "text-center py-6 text-2xl font-bold";

        if (j === columns.length - 1 && i === rows.length - 1) continue;
        else if (j === columns.length - 1) td.className += " border-b-2";
        else if (i === rows.length - 1) td.className += " border-x-2";
        else td.className += " border-b-2 border-x-2";

        if (postItsMap.has(`${j}-${i}`)) {
          /** @type {{ id: string, title: string, coordinates: { x: number, y: number }, state: string, boardId: string, owner: {username: string, name: string, email: string, roles: string[] }, createdAt: string, description: string | null, image: string | null }} */
          const postIt = postItsMap.get(`${j}-${i}`);
          if (
            (userFilter.checked &&
              postIt.owner.username === authenticatedUser.username) ||
            !userFilter.checked
          ) {
            const hasContent = postIt.description || postIt.image;

            td.innerHTML = `
            <button
              id=${postIt.id}
              ${hasContent ? `onClick="openModal(${j},${i})"` : ""}
              class="w-11/12 mx-auto flex flex-col items-center py-4 px-8 rounded-lg relative dark:bg-slate-600 bg-gray-200 ${
                hasContent
                  ? "hover:brightness-90 transition-all duration-150 cursor-pointer"
                  : "hover:brightness-100 cursor-default"
              }">
              <h3 class="text-xl font-bold text-center">${postIt.title}</h3>
              <div class="flex items-center justify-between w-full">
                ${
                  hasContent
                    ? "<div class='text-xl brightness-75 dark:brightness-100'>🗒️</div>"
                    : ""
                }
                  <div class="flex gap-x-2 items-center w-full justify-end">
                  <h3 class="text-lg font-thin">${postIt.owner.name}</h3>
                  <img src=${userImages.get(
                    postIt.owner.username
                  )} alt="User Avatar" class="h-8 w-8 bg-gray-400 rounded-full" />
                    </div>
                    </div>
                    </button>
                    `;
          } else td.innerHTML = "";
        }

        tr.className = "h-52 w-72";
        tr.appendChild(td);
      }

      boardTable.appendChild(tr);
    }

    t = setTimeout(updateBoard, REFRESH_TIMEOUT);
  };

  request.ontimeout = () => {
    showError("Server timeout, still trying...");
    t = setTimeout(updateBoard, ERROR_TIMEOUT);
  };
  request.onerror = () => {
    showError("No server reply, still trying...");
    t = setTimeout(updateBoard, RETRY_TIMEOUT);
  };

  request.open("GET", `/api/board/${id}`, true);
  request.timeout = MAX_TIMEOUT;
  request.send(null);
}

function getAuthenticatedUser() {
  console.log("> Getting authenticated user");

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

    authenticatedUser = data;
    username.innerHTML = `Welcome, ${data.username}!`;
  };

  authRequest.ontimeout = () => {
    showError("Server timeout, still trying...");
    clearTimeout(t);
    t = setTimeout(getAuthenticatedUser, ERROR_TIMEOUT);
  };

  authRequest.onerror = () => {
    showError("No server reply, still trying...");
    clearTimeout(t);
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
  let timeout;
  clearTimeout(timeout);

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

    timeout = setTimeout(getOnlineUsers, REFRESH_ONLINE_TIMEOUT);
  };

  onlineUsersRequest.ontimeout = () => {
    showError("Server timeout, still trying...");
    timeout = setTimeout(getOnlineUsers, ERROR_TIMEOUT);
  };
  onlineUsersRequest.onerror = () => {
    showError("No server reply, still trying...");
    timeout = setTimeout(getOnlineUsers, RETRY_TIMEOUT);
  };

  onlineUsersRequest.open("GET", "/api/online", true);
  onlineUsersRequest.timeout = MAX_TIMEOUT;
  onlineUsersRequest.send(null);
}
