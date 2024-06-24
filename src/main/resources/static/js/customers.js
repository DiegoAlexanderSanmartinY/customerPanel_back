function init() {
  renderCustomers();
}
async function getCustomers() {
  const url = URL_SERVER + "/customer";
  let configLogin = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: sessionStorage.token,
    },
  };

  let reponse = await fetch(url, configLogin);
  let data = await reponse.json();

  return data;
}

async function renderCustomers() {
  let customers = await getCustomers();

  let html = "";
  for (let customer of customers) {
    html += getHtmlCustomers(customer);
  }

  document.getElementById("tbody-customers").innerHTML = html;
}

async function onClickEliminar(id) {
  let response = confirm("Do you want remove this?");
  const url = URL_SERVER + "/customer/" + id;
  if (!response) {
    return;
  }
  await fetch(url, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
      Authorization: sessionStorage.token,
    },
  });
  renderCustomers();

  console.log(id);
}

function getHtmlCustomers(customer) {
  return `
        <tr>
            <td>${customer.id}</td>
            <td>${customer.firstName}</td>
            <td>${customer.lastName}</td>
            <td>${customer.email}</td>
            <td>
                <a href="#" onClick="onClickEliminar(${customer.id})" class="btn btn-danger btn-circle btn-sm">
                    <i class="fas fa-trash"></i>
                </a>
                <a href="update-customer.html?id=${customer.id}"  class="btn btn-primary btn-circle btn-sm">
                    <i class="fas fa-edit"></i>
                </a>
            </td>
        </tr>

  `;
}

init();
