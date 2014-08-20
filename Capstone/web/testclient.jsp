<%-- 
    Document   : testclient
    Created on : 13/08/2014, 10:38:58 PM
    Author     : Taylor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
    </head>
    <body>
        <h1>Hello World!</h1>
        Games:<br/>
        <div id="gamelist"></div>
        
        
        <script>
        function updateGameList(){
        $.ajax({
              type: "POST",
              url: "MainServlet",
              dataType : 'json',
              data: {command: "getgames"}
              }).done(function( data ) {
                    var x;
                    for(x in data){
                        console.log(data[x]);
                        $( "#gamelist" ).append( data[x] + "<br/>" );
                        //alert(x);
                    }
   
              });   
          }
          updateGameList();
        </script>
        <form action="MainServlet" method="POST">
            command: <input type="text" name="command"/><br/>
            <input type="submit" name="submit" value="Get Games"/>
        </form>

        <form action="MainServlet" method="POST">
            command: <input type="text" name="command"/><br/>
            username: <input type="text" name="username"/><br/>
            game id: <input type="text" name="gameid"/><br/>
            <input type="submit" name="submit" value="Join Game"/>
        </form>
        
        <form action="MainServlet" method="POST">
            command: <input type="text" name="command"/><br/>
            username: <input type="text" name="username"/><br/>
            game name: <input type="text" name="gamename"/><br/>
            <input type="submit" name="submit" value="Create Game"/>
        </form>        
        
        
    </body>
</html>
