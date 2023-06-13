const RETRY_TIMEOUT = 5000;
const ERROR_TIMEOUT = 1000;
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

  /** @type Element */
  const boardContainer = document.querySelector("#board-selector");

  const retry = () => {
    boardContainer.innerHTML = "Server timeout, still trying...";
    boardContainer.className = "text-red-500 text-8xl";
    //setTimeout(getData, ERROR_TIMEOUT);
  };

  const error = () => {
    boardContainer.innerHTML = "No server reply, still trying...";
    boardContainer.className = "text-red-500 text-8xl";
    //setTimeout(getData, RETRY_TIMEOUT);
  };

  request.onload = () => {
    /** @type {{archived: boolean, columns: {number: number, title: string}[], rows: {number: number, title: string}[], id: string, owner: {username: string, name: string, email: string}, permissions: {createdAt: string, type: string, updatedAt: string, user: User}[], title}[]} */
    const data = JSON.parse(request.responseText);
    console.log(data);

    // boardContainer.innerHTML = "Success!";
    boardContainer.className = "text-black";
    //setTimeout(getData, REFRESH_TIMEOUT);
  };

  request.ontimeout = retry();
  request.onerror = error();

  request.open("GET", "/api/board", true);
  request.timeout = MAX_TIMEOUT;
  request.send(null);
}
