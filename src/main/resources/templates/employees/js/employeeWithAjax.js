const requestEmployees = function () {
  $.ajax({
      url: "/rest/v1/employees",
      type: "GET",
      dataType: "json"
  })
  .done(function (json) {
      clearHtmlTable();
      jQuery.each(json, function (i, employee) {
          appendToHtmlTable(employee);
      });
  })
  .fail(function (xhr, status, errorThrown) {
      alert( "Sorry, there was a problem!" );
      console.log( "Error: " + errorThrown );
      console.log( "Status: " + status );
      console.dir( xhr );
  })
  .always(function (xhr, status) {
      // alert("The request is complete!" + xhr + " " + status)
  })
};

const createEmployee = function () {
    const employee = {
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        email: $("#email").val(),
        teamName: $("#teamName").val(),
        active: true,
        job: $("#job").val(),
        careerLevel: $("#careerLevel").val()
    };

    $.ajax({
        url: '/rest/v1/employees',
        data: JSON.stringify(employee),
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json'
    })
        .done(function (json) {
            requestEmployees();
        })
        .fail(function (xhr, status, errorThrown) {
            alert( "Sorry, there was a problem!" );
            console.log( "Error: " + errorThrown );
            console.log( "Status: " + status );
            console.dir( xhr );
        })
        .always(function (xhr, status) {
            // alert("The request is complete!" + xhr + " " + status)
        })
};
