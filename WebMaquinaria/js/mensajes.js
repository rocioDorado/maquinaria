const letURL = "localhost:8080"; // server_ip

async function consultarMensajes() {
  let response;
  await $.ajax({
    url: "http://" + letURL + "/api/Message/all",
    type: "GET",
    dataType: "json",
    success: function (data) {
      response = data;
    },
  });

  return response;
}

async function cargarMensajes() {
  const data = await consultarMensajes();
  console.log(data);
  $("#cuerpoTabla").empty();
  $.each(data, function (index, value) {
    $("#cuerpoTabla").append(
      "<tr>" +
        "<td>" +
        value.client.name +
        "</td>" +
        "<td>" +
        value.machine.name +
        "</td>" +
        "<td>" +
        value.messageText +
        "</td>" +
        "<td>" +
        '<button type="button" class="btn btn-info" onclick="editarMessage(' +
        value.idMessage +
        ')">Editar</button>' +
        '<button type="button" class="btn btn-danger" onclick="eliminarMessage(' +
        value.idMessage +
        ')">Eliminar</button>' +
        "</td>" +
        "</tr>"
    );
  });
}

function consultarClientes() {
  $.ajax({
    url: "http://" + letURL + "/api/Client/all",
    type: "GET",
    dataType: "json",
    success: function (data) {
      $("#client").append('<option value="0">Seleccione un cliente</option>');
      $.each(data, function (index, value) {
        $("#client").append(
          '<option value="' + value.idClient + '">' + value.name + "</option>"
        );
      });
    },
  });
}

function consultarMaquinas() {
  $.ajax({
    url: "http://" + letURL + "/api/Machine/all",
    type: "GET",
    dataType: "json",
    success: function (data) {
      $("#machine").append('<option value="0">Seleccione una maquina</option>');
      $.each(data, function (index, value) {
        $("#machine").append(
          '<option value="' + value.id + '">' + value.name + "</option>"
        );
      });
    },
  });
}

function crearMensaje() {
  let id = $("#idMessage").val();
  $("idMessage").attr("disabled", "disabled");
  let messageText = $("#messageText").val();
  let idClient = $("#client").val();
  let machineId = $("#machine").val();
  if (messageText == "" || id == "0" || idClient == "0") {
    alert("Para crear el mensaje, es necesario llenar todos los campos.");
    return false;
  } else {
    const MessageData = {
      idMessage: id,
      messageText: messageText,
      client: { idClient: idClient },
      machine: { id: machineId },
    };
    console.log(JSON.stringify(MessageData));
    $.ajax({
      url: "http://" + letURL + "/api/Message/save",
      headers: {
        "Content-Type": "application/json",
      },
      type: "POST",
      dataType: "json",
      data: JSON.stringify(MessageData),
      statusCode: {
        201: function () {
          $("#idMessage").val("");
          $("#idMessage").attr("readonly", false);
          $("#messageText").val("");
          $("#machine").val("");
          $("#client").val("");
          cargarMensajes();
          alert("el mensaje ha sido creada.");
        },
      },
    });
  }
}
//Esta función llena los campos con los valores de la maquina seleccionada en la tabla para editar
async function editarMessage(value) {
  console.log(value);
  const listaMensajes = await consultarMensajes();
  const mensaje = listaMensajes.find((element) => element.idMessage === value);

  $("#idMessage").val(mensaje.idMessage);
  $("#idMessage").attr("disabled", "disabled");
  $("#messageText").val(mensaje.messageText);
  $("#client option[value=" + mensaje.client.idClient + "]").prop(
    "selected",
    true
  );  
  $("#machine option[value=" + mensaje.machine.id + "]").prop("selected", true);
  consultarMensajes();
}

function actualizarMensaje() {
  let idMessage = $("#idMessage").val();
  $("idMessage").attr("disabled", "disabled");

  let messageText = $("#messageText").val();
  let idClientActualizar = $("#client").val();
  let idAtualizar = $("#machine").val();
  console.log(idAtualizar);
  if (messageText == "" || idAtualizar == 0 || idClientActualizar == 0) {
    alert("Para actualizar el mensaje, es necesario llenar todos los campos.");
    return false;
  } else {
    const MessageData = {
      idMessage: idMessage,
      messageText: messageText,
      client: { idClient: idClientActualizar },
      machine: { id: idAtualizar },
    }; //Creamos un objeto con los datos a actualizar.
    console.log(JSON.stringify(MessageData));
    console.log(MessageData);
    $.ajax({
      url: "http://" + letURL + "/api/Message/update",
      type: "PUT",
      dataType: "json",
      data: JSON.stringify(MessageData), //convertimos el objeto a un string para que sea compatible con el formato de la API.
      headers: {
        "Content-Type": "application/json",
      },
      statusCode: {
        201: function () {
          $("#idMessage").val("");
          $("#idMessage").attr("readonly", false);
          $("#messageText").val("");
          $("#machine").val("");
          $("#client").val("");
          cargarMensajes();
          alert("el registro ha sido actualizado.");
        },
      },
    });
  }
}

function eliminarMessage(idMessage) {
  let respuesta = confirm("Esta seguro de eliminar el mensaje: " + idMessage); //Primero preguntamos si está seguro de eliminar.
  if (respuesta == true) {
    //Si está seguro, procedemos a eliminar.
    $.ajax({
      url: "http://" + letURL + "/api/Message/" + idMessage,
      type: "DELETE",
      contentType: "application/JSON",
      datatype: "JSON",
      success: function (respuesta) {
        $("#tabla").empty();
        cargarMensajes();
        alert("el registro ha sido eliminado.");
      },
    });
  }
}

$(document).ready(function () {
  cargarMensajes();
  consultarClientes();
  consultarMaquinas();
});
