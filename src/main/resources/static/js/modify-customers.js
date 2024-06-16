async function loadCustomer() {
  // Carga los datos de un usuario
  let id = window.location.search.split("id=")[1];
  if (id == null) return;
  let url = URL_SERVER + "/customer/" + id;
  let response = await fetch(url);

  let customer = await response.json();

  document.getElementById("firstName").value = customer.firstName;
  document.getElementById("lastName").value = customer.lastName;
  document.getElementById("email").value = customer.email;
  document.getElementById("address").value = customer.address;
}

function clickCreate() {
  let firstName = document.getElementById("firstName").value;
  let lastName = document.getElementById("lastName").value;
  let email = document.getElementById("email").value;
  let address = document.getElementById("address").value;
  let customer = {
    firstName: firstName,
    lastName: lastName,
    email: email,
    address: address,
  };
  console.log("customer", customer);
  create(customer);
}

async function create(customer) {
  let id = window.location.search.split("id=")[1];
  if (id != null) {
    try {
      customer.id = id;
      let url = URL_SERVER + "/customer/" + id;
      let response = await fetch(url, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json", // Especifica el tipo de contenido
        },
        body: JSON.stringify(customer),
      });
      console.log("customer", customer);
      alert("User Update");
    } catch (error) {
      console.log({ error: error });
    } finally {
      window.location.href = "customers.html";
      return;
    }
  }
  let response = "User Create";
  const url = URL_SERVER + "/customer";
  try {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json", // Especifica el tipo de contenido
      },
      body: JSON.stringify(customer),
    });
    alert(response);
    window.location.href = "customers.html";
  } catch (error) {
    console.log({ error: error });
  }
}

loadCustomer();
