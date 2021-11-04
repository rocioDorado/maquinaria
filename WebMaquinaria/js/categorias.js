const varURL = "localhost:8080"; // server_ip

async function consultarCategorias() {
    let response;
    await $.ajax({
      url: "http://" + varURL + "/api/Category/all",
      type: "GET",
      dataType: "json",
      success: function (data) {
        response = data;
      },
    });
    console.log(response);
    return response;
  }
  
  async function cargarCategorias() {
    const data = await consultarCategorias();
    //console.log(data);
    $("#cuerpoTabla").empty();
    $.each(data, function (index, value) {
      $("#cuerpoTabla").append(
        "<tr>" +
          "<td>" +
          value.name +
          "</td>" +
          "<td>" +
          value.description +
          "</td>" +
          "<td>" +
          '<button type="button" class="btn btn-info" onclick="editarCategoria(' +
          value.id +
          ')">Editar</button>' +
          '<button type="button" class="btn btn-danger" onclick="eliminarCategoria(' +
          value.id +
          ')">Eliminar</button>' +
          "</td>" +
          "</tr>"
      );
    });
  }
  
  function crearCategoria() {
    var id = $("#id").val();
    $("id").attr("disabled", "disabled");
    var description = $("#description").val();
    var name = $("#name").val();
    console.log(description,name);
    if (description == "" || name  == "" ) {
        alert('Para crear la Categoría, es necesario llenar todos los campos.');
        return false;
      } else {
            const CategoryData = {
            name: name,
            description: description,
            };
            console.log(JSON.stringify(CategoryData));
            $.ajax({
            url: "http://" + varURL + "/api/Category/save",
            headers: {
                "Content-Type": "application/json",
            },
            type: "POST",
            dataType: "json",
            data: JSON.stringify(CategoryData),
            statusCode: {
                201: function () {
                cargarCategorias();
                alert("La categoría ha sido creada.");
                },
            },
            });
        }
  }
  
  async function editarCategoria(value) {
    console.log(value);
    const listaCategorias = await consultarCategorias();
    const Category = listaCategorias.find((element) => element.id === value);
    console.log(Category);
    $("#id").val(Category.id);
    $("#id").attr("readonly", false);
    $("#description").val(Category.description);
    $("#name").val(Category.name);
    consultarCategorias();
  }
  
  function actualizarCategoria() {
    var id = $("#id").val();
    $("id").attr("disabled", "disabled");
    
    var description = $("#description").val();
    var name = $("#name").val();
    if (description == "" || name  == "" ) {
        alert('Para actualizar la Categoría, es necesario llenar todos los campos.');
        return false;
      } else {
            const CategoryData = {
                id: id,
                name: name,
                description: description,
            };
            console.log(JSON.stringify(CategoryData)); //Creamos un objeto con los datos a actualizar.
            $.ajax({
            url: "http://" + varURL + "/api/Category/update",
            type: "PUT",
            dataType: "json",
            data: JSON.stringify(CategoryData), //convertimos el objeto a un string para que sea compatible con el formato de la API.
            headers: {
                "Content-Type": "application/json",
            },
            statusCode: {
                201: function () {
                $("#id").val("");
                $("#id").attr("readonly", false);
                $("#description").val("");
                $("#name").val("");
                cargarCategorias();
                alert("el registro ha sido actualizado.");
                },
            },
            });
      }
  }
  
  function eliminarCategoria(id) {
    var respuesta = confirm("Esta seguro de eliminar la Categoria: " + id); //Primero preguntamos si está seguro de eliminar.
    if (respuesta == true) {
      //Si está seguro, procedemos a eliminar.
      $.ajax({
        url: "http://" + varURL + "/api/Category/" + id,
        type: "DELETE",
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
          $("#tabla").empty();
          cargarCategorias();
          alert("el registro ha sido eliminado");
        },
      });
    }
  }
  
  $(document).ready(function () {
    cargarCategorias();
  });
  