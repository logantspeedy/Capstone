To login
--------
http://host:port/appName/login -> Logs in via google and returns to default page

To logout
---------
http://host:port/appName/logout -> Logs out and returns to default page
http://host:port/appName/logout?url=somePage.htm -> Logs out and returns to specified url

Get user
--------
http://host:port/appName/getUser -> Returns the users email or nothing if the user is not logged in
