async function consultarClientes() {
  let response;
  await $.ajax({
    url: "http://localhost:8080/api/Client/all",
    type: "GET",
    dataType: "json",
    success: function (data) {
      response = data;
    },
  });
  //console.log(response);
  return response;
}

async function cargarClientes() {
  const data = await consultarClientes();
  console.log(data);
  $("#cuerpoTabla").empty();
  $.each(data, function (index, value) {
    $("#cuerpoTabla").append(
      "<tr>" +
        "<td>" +
        value.name +
        "</td>" +
        "<td>" +
        value.email +
        "</td>" +
        "<td>" +
        value.password +
        "</td>" +
        "<td>" +
        value.age +
        "</td>" +
        "<td>" +
        '<button type="button" class="btn btn-info" onclick="editarCliente(' +
        value.idClient +
        ')">Editar</button>' +
        '<button type="button" class="btn btn-danger" onclick="eliminarCliente(' +
        value.idClient +
        ')">Eliminar</button>' +
        "</td>" +
        "</tr>"
    );
  });
}

function crearCliente() {
  var id = $("#id").val();
  var email = $("#email").val();
  var password = $("#password").val();
  var age = $("#age").val();
  var name = $("#name").val();
  const clientData = {
    name: name,
    email: email,
    password: password,
    age: age,
  };
  console.log(JSON.stringify(clientData));
  $.ajax({
    url: "http://localhost:8080/api/Client/save",
    headers: {
      "Content-Type": "application/json",
    },
    type: "POST",
    dataType: "json",
    data: JSON.stringify(clientData),
    statusCode: {
      201: function () {
        cargarClientes();
      },
    },
  });
}

async function editarCliente(value) {
  console.log(value);
  const listaClientes = await consultarClientes();
  const client = listaClientes.find((element) => element.idClient === value);
  console.log(client);
  $("#id").val(client.idClient);
  $("#id").attr("readonly", false);
  $("#email").val(client.email);
  $("#password").val(client.password);
  $("#age").val(client.age);
  $("#name").val(client.name);
  consultarClientes();
}

function actualizarCliente() {
  var id = $("#id").val();
  var email = $("#email").val();
  var password = $("#password").val();
  var age = $("#age").val();
  var name = $("#name").val();
  var data = {
    idClient: id,
    email: email,
    password: password,
    age: age,
    name: name,
  }; //Creamos un objeto con los datos a actualizar.
  $.ajax({
    url: "http://localhost:8080/api/Client/update",
    type: "PUT",
    dataType: "json",
    data: JSON.stringify(data), //convertimos el objeto a un string para que sea compatible con el formato de la API.
    headers: {
      "Content-Type": "application/json",
    },
    statusCode: {
      201: function () {
        $("#id").val("");
        $("#id").attr("readonly", false);
        $("#email").val("");
        $("#password").val("");
        $("#age").val("");
        $("#name").val("");
        cargarClientes();
      },
    },
  });
}

function eliminarCliente(idClient) {
  var respuesta = confirm("Esta seguro de eliminar el cliente: " + idClient); //Primero preguntamos si está seguro de eliminar.
  if (respuesta == true) {
    //Si está seguro, procedemos a eliminar.
    $.ajax({
      url: "http://localhost:8080/api/Client/" + idClient,
      type: "DELETE",
      contentType: "application/JSON",
      datatype: "JSON",
      success: function (respuesta) {
        $("#tabla").empty();
        cargarClientes();
        alert("el registro ha sido eliminado");
      },
    });
  }
}

$(document).ready(function () {
  cargarClientes();
});
