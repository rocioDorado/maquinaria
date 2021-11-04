const varURL = "localhost:8080"; // server_ip

async function consultarMaquinas() {
  let response;
  await $.ajax({
    url: "http://" + varURL + "/api/Machine/all",
    type: "GET",
    dataType: "json",
    success: function (data) {
      response = data;
    },
  });
  
  return response;
}

async function cargarMaquinas() {
  const data = await consultarMaquinas();
  console.log(data);
  $("#cuerpoTabla").empty();
  $.each(data, function (index, value) {
    $("#cuerpoTabla").append(
      "<tr>" +
        "<td>" +
        value.name +
        "</td>" +
        "<td>" +
        value.brand +
        "</td>" +
        "<td>" +
        value.year +
        "</td>" +
        "<td>" +
        value.description +
        "</td>" +
        "<td>" +
        value.category.name +
        "</td>" +
        "<td>" +
        '<button type="button" class="btn btn-info" onclick="editarMaquina(' +
        value.id +
        ')">Editar</button>' +
        '<button type="button" class="btn btn-danger" onclick="eliminarMaquina(' +
        value.id +
        ')">Eliminar</button>' +
        "</td>" +
        "</tr>"
    );
  });
}

function consultarCatgorias() {
  $.ajax({
    url: "http://" + varURL + "/api/Category/all",
    type: "GET",
    dataType: "json",
    success: function (data) {
      $("#category").append(
        '<option value="0">Seleccione una categoria</option>'
      );
      $.each(data, function (index, value) {
        $("#category").append(
          '<option value="' + value.id + '">' + value.name + "</option>"
        );
      });
    },
  });
}

function crearMaquina() {
  var id = $("#id").val();
  $("id").attr("disabled", "disabled");
  var brand = $("#brand").val();
  var year = $("#year").val();
  var description = $("#description").val();
  var category_id = $("#category").val();
  var name = $("#name").val();
  if (brand == "" || year == "" || description == "" || name  == "" || category_id  == "0") {
    alert('Para crear la máquina, es necesario llenar todos los campos.');
    return false;
  } else {
      const machineData = {
        brand: brand,
        year: year,
        category: { id: category_id },
        name: name,
        description: description,
      };
      console.log(JSON.stringify(machineData));
      $.ajax({
        url: "http://" + varURL + "/api/Machine/save",
        headers: {
          "Content-Type": "application/json",
        },
        type: "POST",
        dataType: "json",
        data: JSON.stringify(machineData),
        statusCode: {
          201: function () {
            cargarMaquinas();
            alert("La máquina ha sido creada.");
          },
        },
      });
    }
}
//Esta función llena los campos con los valores de la maquina seleccionada en la tabla para editar
async function editarMaquina(value) {
  console.log(value);
  const listaMaquinas = await consultarMaquinas();
  const maquina = listaMaquinas.find((element) => element.id === value);
  console.log(maquina);
  $("#id").val(maquina.id);
  $("id").attr("disabled", "disabled");
  $("#brand").val(maquina.brand);
  $("#year").val(maquina.year);
  $("#category option[value=" + maquina.category.id + "]").prop(
    "selected",
    true
  );
  $("#name").val(maquina.name);
  $("#description").val(maquina.description);
  consultarMaquinas();
}

function actualizarMaquina() {
  var id = $("#id").val();
  $("id").attr("disabled", "disabled");
  var brand = $("#brand").val();
  var year = $("#year").val();
  var name = $("#name").val();
  var category_id = $("#category").val();
  var description = $("#description").val();
  if (brand == "" || year == "" || description == "" || name  == "" || category_id  == "0") {
    alert('Para actualizar la máquina, es necesario llenar todos los campos.');
    return false;
  } else {
      var data = {
        id: id,
        brand: brand,
        year: year,
        category: { id: category_id },
        description: description,
        name: name,
      }; //Creamos un objeto con los datos a actualizar.
      $.ajax({
        url: "http://" + varURL + "/api/Machine/update",
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
            $("#brand").val("");
            $("#year").val("");
            $("#category").val("");
            $("#name").val("");
            $("#description").val("");
            cargarMaquinas();
            alert("el registro ha sido actualizado.");
          },
        },
      });
    }
}

function eliminarMaquina(idMaquina) {
  var respuesta = confirm("Esta seguro de eliminar la máquina: " + idMaquina); //Primero preguntamos si está seguro de eliminar.
  if (respuesta == true) {
    //Si está seguro, procedemos a eliminar.
    $.ajax({
      url: "http://" + varURL + "/api/Machine/" + idMaquina,
      type: "DELETE",
      contentType: "application/JSON",
      datatype: "JSON",
      success: function (respuesta) {
        $("#tabla").empty();
        cargarMaquinas();
        alert("el registro ha sido eliminado.");
      },
    });
  }
}

$(document).ready(function () {
  cargarMaquinas();
  consultarCatgorias();
});
