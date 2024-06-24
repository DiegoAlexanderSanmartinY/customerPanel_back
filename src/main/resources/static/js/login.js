async function login() {
  const url = URL_SERVER + "/auth/login";
  let configLogin = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email: document.getElementById("email").value,
      password: document.getElementById("password").value,
    }),
  };
  let reponse = await fetch(url, configLogin);
  let token = await reponse.text();

  sessionStorage.token = token;
  window.location.href = "/customers.html";
}

function logout() {
  sessionStorage.removeItem("token");
  window.location.href = "/login.html";
}
