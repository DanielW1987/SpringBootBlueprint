
  <script>
    var appendTableRow = function() {
      // Find a <table> element with id="employee-table":
      var table = document.getElementById("employee-table");

      // Create an empty <tr> element and add it to the last position of the table:
      var row = table.insertRow(-1);

      // Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
      var name        = row.insertCell(0);
      var email       = row.insertCell(1);
      var birthday    = row.insertCell(2);
      var teamName    = row.insertCell(3);
      var levelAndJob = row.insertCell(4);
      var status      = row.insertCell(5);
      var actions     = row.insertCell(6);

      // Add some text to the new cells:
      name.innerHTML        = 'Daniel Wagner';
      email.innerHTML       = 'danielw@lucanet.com';
      birthday.innerHTML    = '1987-07-08';
      teamName.innerHTML    = 'TeamShield';
      levelAndJob.innerHTML = 'Senior Java Developer';
      status.innerHTML      = '<span class="badge badge-success">Active</span>';
      actions.innerHTML     = '<a href="#"><i class="material-icons table-action-icon">edit</i></a>' +
                              '<a href="#"><i class="material-icons table-action-icon">delete</i></a>';
    };

    $(document).ready(function() {

      // table row click listener
      $( "tr" ).click(function() {
        let row = this;
        console.log(this.id);
      })

      // table row click listener with predefined function
      $( "tr" ).on( "click", appendTableRow );
    });

    // Using the core $.ajax() method
    $.ajax({
      // The URL for the request
      url: "/rest/v1/employees",

      // The data to send (will be converted to a query string)
      // data: {
      //     id: 123
      // },

      // Whether this is a POST or GET request
      type: "GET",

      // The type of data we expect back
      dataType : "json",
    })
      // Code to run if the request succeeds (is done);
      // The response is passed to the function
      .done(function( json ) {
         console.log( json );
         // $( "<h1>" ).text( json.title ).appendTo( "body" );
         // $( "<div class=\"content\">").html( json.html ).appendTo( "body" );
      })
      // Code to run if the request fails; the raw request and
      // status codes are passed to the function
      .fail(function( xhr, status, errorThrown ) {
        alert( "Sorry, there was a problem!" );
        console.log( "Error: " + errorThrown );
        console.log( "Status: " + status );
        console.dir( xhr );
      })
      // Code to run regardless of success or failure;
      .always(function( xhr, status ) {
        alert( "The request is complete!" );
      });
  </script>