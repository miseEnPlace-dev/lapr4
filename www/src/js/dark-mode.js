const darkModeBtn = document.querySelector("#dark-mode-btn");

const theme = localStorage.getItem("@ecourse:theme") ?? "light";

darkModeBtn.innerHTML = theme === "dark" ? "☀️" : "🌙";

if (theme === "dark") document.documentElement.classList.add("dark");
else document.documentElement.classList.remove("dark");

darkModeBtn.addEventListener("click", () => {
  document.documentElement.classList.toggle("dark");

  const theme = document.documentElement.classList.contains("dark")
    ? "dark"
    : "light";
  localStorage.setItem("@ecourse:theme", theme);

  darkModeBtn.innerHTML = theme === "dark" ? "☀️" : "🌙";
});
