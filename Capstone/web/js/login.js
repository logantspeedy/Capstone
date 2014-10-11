            function login(username){
                if (username === null){
                    if ($('#username').val() === ""){
                        console.log("FAILED");
                        return;
                    }
                    else{
                        username = $('#username').val();
                    }
                }
                setCookie("username", username ,20);
                $.ajax({
                  type: "POST",
                  url: "MainServlet",
                  dataType : 'json',
                  data: {command: "login", username: username},
                  success: function(data){console.log(data)},
                  error: function(jqXHR, textStatus, errorThrown){if(textStatus === "error"){console.log("Duplicate username");}}
                  }).done(function(data) {
                            console.log("In Logging Complete");
                            setCookie("username", username,20);
                            //window.location.href = 'lobby2.jsp';

                  }); 
                setTimeout(function () { window.location.href = 'lobby2.jsp';}, 500);
              }
